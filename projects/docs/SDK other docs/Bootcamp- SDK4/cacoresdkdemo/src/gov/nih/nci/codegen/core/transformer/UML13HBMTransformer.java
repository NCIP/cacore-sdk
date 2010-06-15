/*
 * Created on Dec 6, 2004
 */
package gov.nih.nci.codegen.core.transformer;

import gov.nih.nci.codegen.core.BaseArtifact;
import gov.nih.nci.codegen.core.ConfigurationException;
import gov.nih.nci.codegen.core.XMLConfigurable;
import gov.nih.nci.codegen.core.util.UML13Utils;
import gov.nih.nci.codegen.core.util.XMLUtils;
import gov.nih.nci.codegen.framework.TransformationException;
import gov.nih.nci.codegen.framework.Transformer;
import gov.nih.nci.common.util.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.jmi.reflect.RefObject;

import org.apache.log4j.Logger;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.omg.uml.foundation.core.AssociationEnd;
import org.omg.uml.foundation.core.Attribute;
import org.omg.uml.foundation.core.Classifier;
import org.omg.uml.foundation.core.Dependency;
import org.omg.uml.foundation.core.GeneralizableElement;
import org.omg.uml.foundation.core.Generalization;
import org.omg.uml.foundation.core.ModelElement;
import org.omg.uml.foundation.core.Operation;
import org.omg.uml.foundation.core.Parameter;
import org.omg.uml.foundation.core.UmlAssociation;
import org.omg.uml.foundation.core.UmlClass;
import org.omg.uml.foundation.extensionmechanisms.Stereotype;
import org.omg.uml.foundation.extensionmechanisms.TaggedValue;
import org.omg.uml.modelmanagement.UmlPackage;

/**
 * Transforms the UML model to an hbm mapping file.
 * 
 * @author caBIO Team
 * @version $Id: UML13HBMTransformer.java,v 1.36 2006/03/03 14:50:04 connellm
 *          Exp $
 * 
 */
public class UML13HBMTransformer implements Transformer, XMLConfigurable {

	private static Logger log = Logger.getLogger(UML13HBMTransformer.class);

	private String _omPkg, _dmPkg;

	private Element _idGenEl;

	private Vector eagerFetchPackages;

	/**
	 * 
	 */
	public UML13HBMTransformer() {
		super();
		// Read the deploy.properties to get the setting for many-to-one
		// fetching
		eagerFetchPackages = getProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.codegen.framework.Transformer#execute(javax.jmi.reflect.RefObject,
	 *      java.util.Collection)
	 */
	public Collection execute(RefObject modelElement, Collection artifacts)
			throws TransformationException {
		if (modelElement == null) {
			log.error("model element is null");
			throw new TransformationException("model element is null");
		}
		if (!(modelElement instanceof UmlClass)) {
			log.error("model element not instance of UmlClass: "
					+ modelElement.getClass().getName());
			throw new TransformationException(
					"model element not instance of UmlClass: "
							+ modelElement.getClass().getName());
		}
		Collection newArtifacts = new ArrayList();
		UmlClass klass = (UmlClass) modelElement;
		UmlClass superClass = UML13Utils.getSuperClass(klass);
		if (superClass != null) {
			; // HAS A SUPERCLASS DO NOTHING
		} else {
			// NO SUPERCLASS - GO AND PROCESS
			Element mappingEl = new Element("hibernate-mapping");
			// mappingEl.setAttribute("package", getPackage(klass)+".impl");
			mappingEl.setAttribute("package", getPackage(klass));
			doMapping(klass, mappingEl);
			// doImplMapping(klass, mappingEl);
			DocType docType = new DocType("hibernate-mapping",
					"-//Hibernate/Hibernate Mapping DTD 3.0//EN",
					"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd");
			Document doc = new Document();
			doc.setDocType(docType);
			doc.setRootElement(mappingEl);
			XMLOutputter p = new XMLOutputter();
			p.setFormat(Format.getPrettyFormat());
			newArtifacts.add(new BaseArtifact(klass.getName(), modelElement, p
					.outputString(doc)));
		}
		return newArtifacts;
	}

	/**
	 * @param klass
	 * @param mappingEl
	 */
	private void doMapping(UmlClass klass, Element mappingEl) {
		// Get table
		UmlClass table = getTable(klass);
		if (table == null) {
			log.error("No table found for "
					+ UML13Utils.getQualifiedName(klass));
			throw new RuntimeException("No table found for "
					+ UML13Utils.getQualifiedName(klass));
		}
		boolean hasSubClasses = hasSubClasses(klass);

		Element classEl = new Element("class");
		mappingEl.addContent(classEl);
		// classEl.setAttribute("name", klass.getName()+"Impl");
		classEl.setAttribute("name", klass.getName());
		classEl.setAttribute("table", table.getName());
		if (eagerFetchPackages.contains(getPackage(klass))) {
			classEl.setAttribute("lazy", "false");
		} else {
			classEl.setAttribute("lazy", "true");
		}

		classEl.setAttribute("polymorphism", "explicit");

		Element cache = new Element("cache");
		cache.setAttribute("usage", "read-write");
		classEl.addContent(0, cache);

		// Do properties
		for (Iterator i = UML13Utils.getAttributes(klass).iterator(); i
				.hasNext();) {
			Attribute att = (Attribute) i.next();
			Attribute col = getColumn(table, att);
			if (col == null) {
				log.warn("No column found for "
						+ UML13Utils.getQualifiedName(klass) + Constant.DOT
						+ att.getName() + ", continuing.");
				continue;
			}
			if (isPK(col)) {
				Element idEl = new Element("id");
				classEl.addContent(1, idEl);
				idEl.setAttribute("name", att.getName());
				if (getQualifiedName(att.getType()).startsWith(".")) {
					idEl.setAttribute("type", getQualifiedName(att.getType())
							.substring(1));
				} else {
					idEl.setAttribute("type", getQualifiedName(att.getType()));
				}
				idEl.setAttribute("column", col.getName());

				idEl.addContent(getIDGeneratorEl());
			} else if (isCollection(col)) {
				String name = null;
				String table1 = null;
				String keyColumn = null;
				String column1 = null;
				String type = null;
				try {
					StringTokenizer st = new StringTokenizer(
							getCollectionMapping(col), "::");
					st.nextToken();
					name = st.nextToken();
					table1 = st.nextToken();
					keyColumn = st.nextToken();
					column1 = st.nextToken();
					type = st.nextToken();
				} catch (Exception ex) {
					log.error("Couldn't process collection mapping");
				}

				Element setEl = new Element("set");
				classEl.addContent(setEl);
				setEl.setAttribute("name", name);
				setEl.setAttribute("table", table1);
				setEl.setAttribute("lazy", "false");
				Element cacheEl = new Element("cache");
				setEl.addContent(cacheEl);
				cacheEl.setAttribute("usage", "read-write");
				Element setKey = new Element("key");
				setEl.addContent(setKey);
				setKey.setAttribute("column", keyColumn);

				Element setElement = new Element("element");
				setEl.addContent(setElement);
				setElement.setAttribute("column", column1);
				setElement.setAttribute("type", type);
			} else {
				Element propEl = new Element("property");
				classEl.addContent(propEl);
				propEl.setAttribute("name", att.getName());
				if (getQualifiedName(att.getType()).startsWith(".")
						&& !isCLOB(col)) {
					propEl.setAttribute("type", getQualifiedName(att.getType())
							.substring(1));
				} else if (!getQualifiedName(att.getType()).startsWith(".")
						&& !isCLOB(col)) {
					propEl
							.setAttribute("type", getQualifiedName(att
									.getType()));
				} else {
					propEl.setAttribute("type",
							"gov.nih.nci.common.util.StringClobType");
				}
				propEl.setAttribute("column", col.getName());
			}
		}// End iterating through properties

		// Do associations
		for (Iterator i = UML13Utils.getAssociationEnds(klass).iterator(); i
				.hasNext();) {
			AssociationEnd thisEnd = (AssociationEnd) i.next();
			AssociationEnd otherEnd = UML13Utils
					.getOtherAssociationEnd(thisEnd);
			if (UML13Utils.isOne2One(thisEnd, otherEnd)) {
				doOne2One(classEl, klass, table, thisEnd, otherEnd);
			} else if (UML13Utils.isMany2One(thisEnd, otherEnd)) {
				doMany2One(classEl, klass, table, thisEnd, otherEnd);
			} else if (UML13Utils.isOne2Many(thisEnd, otherEnd)) {
				doOne2Many(classEl, klass, table, thisEnd, otherEnd);
			} else if (UML13Utils.isMany2Many(thisEnd, otherEnd)) {
				doMany2Many(classEl, klass, table, thisEnd, otherEnd);
			}
		}

		if (hasSubClasses) {
			doSubMapping(klass, classEl);
		}
	}

	private String getCollectionMapping(Attribute att) {
		String collectionMapping;
		TaggedValue mappedCollection = UML13Utils.getTaggedValue(att,
				"mapped-attributes");
		collectionMapping = mappedCollection.getValue();
		return collectionMapping;
	}

	private boolean isCollection(Attribute col) {
		boolean isCollection = false;
		TaggedValue mappedCollection = UML13Utils.getTaggedValue(col,
				"mapped-attributes");
		if (mappedCollection.getValue().endsWith("##")) {
			isCollection = true;
		}
		return isCollection;
	}

	private void doSubMapping(UmlClass klass, Element mappingE1) {

		UmlClass subClass;
		for (Iterator i = klass.getSpecialization().iterator(); i.hasNext();) {
			Generalization subClass1 = (Generalization) i.next();
			GeneralizableElement myClassifier = subClass1.getChild();
			UmlClass table1 = getTable((UmlClass) myClassifier);
			subClass = (UmlClass) myClassifier;
			Element join = new Element("joined-subclass");
			mappingE1.addContent(join);
			// String temp1 = getPackage(subClass) + ".impl." +
			// subClass.getName()+"Impl";
			String temp1 = getPackage(subClass) + Constant.DOT + subClass.getName();
			join.setAttribute("name", temp1);
			join.setAttribute("table", table1.getName());
			if (eagerFetchPackages.contains(getPackage(klass))) {
				join.setAttribute("lazy", "false");
			} else {
				join.setAttribute("lazy", "true");
			}
			Element key = new Element("key");
			key.setAttribute("column", getPrimaryKey((UmlClass) myClassifier));
			join.addContent(key);
			for (Iterator j = UML13Utils.getAttributes(subClass).iterator(); j
					.hasNext();) {
				Attribute att = (Attribute) j.next();
				Attribute col;
				col = getColumn(table1, att);
				if (col == null) {
					log.warn("No column found for "
							+ UML13Utils.getQualifiedName(subClass) + Constant.DOT
							+ att.getName() + ", continuing.");
					continue;
				}
				if (isPK(col)) {
					; // Do nothing;
				} else {
					Element propEl = new Element("property");
					join.addContent(propEl);
					propEl.setAttribute("name", att.getName());
					if (getQualifiedName(att.getType()).startsWith(".")
							&& !isCLOB(col)) {
						propEl.setAttribute("type", getQualifiedName(
								att.getType()).substring(1));
					} else if (!getQualifiedName(att.getType()).startsWith(".")
							&& !isCLOB(col)) {
						propEl.setAttribute("type", getQualifiedName(att
								.getType()));
					} else {
						propEl.setAttribute("type",
								"gov.nih.nci.common.util.StringClobType");
					}
					propEl.setAttribute("column", col.getName());
				}
			}// End iterating through properties

			// Do associations
			for (Iterator k = UML13Utils.getAssociationEnds(subClass)
					.iterator(); k.hasNext();) {
				AssociationEnd thisEnd = (AssociationEnd) k.next();
				AssociationEnd otherEnd = UML13Utils
						.getOtherAssociationEnd(thisEnd);

				if (UML13Utils.isOne2One(thisEnd, otherEnd)) {
					doOne2One(join, subClass, table1, thisEnd, otherEnd);
				} else if (UML13Utils.isMany2One(thisEnd, otherEnd)) {
					doMany2One(join, subClass, table1, thisEnd, otherEnd);
				} else if (UML13Utils.isOne2Many(thisEnd, otherEnd)) {
					doOne2Many(join, subClass, table1, thisEnd, otherEnd);
				} else if (UML13Utils.isMany2Many(thisEnd, otherEnd)) {
					doMany2Many(join, subClass, table1, thisEnd, otherEnd);
				}
			}
			if (hasSubClasses(subClass)) {
				doSubMapping(subClass, join);
			}
		}
	}

	private void doMany2Many(Element classEl, UmlClass klass, UmlClass table,
			AssociationEnd thisEnd, AssociationEnd otherEnd) {
		if (otherEnd.isNavigable() && thisEnd.isNavigable()) {
			UmlClass corrTable = getCorrelationTable(otherEnd.getAssociation());
			if (corrTable == null) {
				log.error("Couldn't find correlation table for "
						+ classEl.getName() + "->" + otherEnd.getName());
				throw new RuntimeException(
						"Couldn't find correlation table for "
								+ classEl.getName() + "->" + otherEnd.getName());
			}
			Element setEl = new Element("set");
			classEl.addContent(setEl);
			setEl.setAttribute("name", otherEnd.getName());
			setEl.setAttribute("table", corrTable.getName());
			setEl.setAttribute("lazy", "true");
			Element cacheEl = new Element("cache");
			setEl.addContent(cacheEl);
			cacheEl.setAttribute("usage", "read-write");

			// Get the FK colum
			String thisMatch = getQualifiedName(otherEnd.getType()) + Constant.DOT
					+ thisEnd.getName();
			Attribute keyCol = getKeyColumn(corrTable, thisMatch);
			if (keyCol == null) {
				log.error("Couldn't find key column for " + thisMatch);
				throw new RuntimeException("Couldn't find key column for "
						+ thisMatch);
			}
			Element keyEl = new Element("key");
			setEl.addContent(keyEl);
			keyEl.setAttribute("column", keyCol.getName());
			Element m2nEl = new Element("many-to-many");
			setEl.addContent(m2nEl);
			// String temp1 = getPackage((UmlClass)otherEnd.getType()) +
			// ".impl." + otherEnd.getType().getName()+"Impl";
			String temp1 = getPackage((UmlClass) otherEnd.getType()) + Constant.DOT
					+ otherEnd.getType().getName();
			m2nEl.setAttribute("class", temp1);

			// Get other FK
			String otherMatch = getQualifiedName(klass) + Constant.DOT
					+ otherEnd.getName();
			Attribute otherKeyCol = getKeyColumn(corrTable, otherMatch);
			if (otherKeyCol == null) {
				log.error("Couldn't find other key column for " + otherMatch);
				throw new RuntimeException(
						"Couldn't find other key column for " + otherMatch);
			}
			m2nEl.setAttribute("column", otherKeyCol.getName());
			TaggedValue inverseOf = UML13Utils.getTaggedValue(otherKeyCol,
					"inverse-of");
			if (inverseOf != null && inverseOf.getValue() != null
					&& inverseOf.getValue().indexOf(thisMatch) != -1) {
				setEl.setAttribute("inverse", "true");
			}
		} else if (otherEnd.isNavigable() && !thisEnd.isNavigable()) {
			UmlClass corrTable = getCorrelationTable(otherEnd.getAssociation());
			if (corrTable == null) {
				log.error("Couldn't find correlation table for "
						+ classEl.getName() + "->" + otherEnd.getName());
				throw new RuntimeException(
						"Couldn't find correlation table for "
								+ classEl.getName() + "->" + otherEnd.getName());
			}
			Element setEl = new Element("set");
			classEl.addContent(setEl);
			setEl.setAttribute("name", otherEnd.getName());
			setEl.setAttribute("table", corrTable.getName());
			setEl.setAttribute("lazy", "true");
			Element cacheEl = new Element("cache");
			setEl.addContent(cacheEl);
			cacheEl.setAttribute("usage", "read-write");
			// Get the FK colum
			String thisMatch = getQualifiedName(otherEnd.getType()) + Constant.DOT
					+ thisEnd.getName();
			Attribute keyCol = getKeyColumn(corrTable, thisMatch);
			if (keyCol == null) {
				log.error("Couldn't find key column for " + thisMatch);
				throw new RuntimeException("Couldn't find key column for "
						+ thisMatch);
			}
			Element keyEl = new Element("key");
			setEl.addContent(keyEl);
			keyEl.setAttribute("column", keyCol.getName());
			Element m2nEl = new Element("many-to-many");
			setEl.addContent(m2nEl);
			// String temp1 = getPackage((UmlClass)otherEnd.getType()) +
			// ".impl." + otherEnd.getType().getName()+"Impl";
			String temp1 = getPackage((UmlClass) otherEnd.getType()) + Constant.DOT
					+ otherEnd.getType().getName();
			m2nEl.setAttribute("class", temp1);

			// Get other FK
			String otherMatch = getQualifiedName(klass) + Constant.DOT
					+ otherEnd.getName();
			Attribute otherKeyCol = getKeyColumn(corrTable, otherMatch);
			if (otherKeyCol == null) {
				log.error("Couldn't find other key column for " + otherMatch);
				throw new RuntimeException(
						"Couldn't find other key column for " + otherMatch);
			}
			m2nEl.setAttribute("column", otherKeyCol.getName());

			TaggedValue inverseOf = UML13Utils.getTaggedValue(otherKeyCol,
					"inverse-of");
			if (inverseOf != null && inverseOf.getValue() != null
					&& inverseOf.getValue().indexOf(thisMatch) != -1) {
				setEl.setAttribute("inverse", "true");
			}
		}
	}

	private Attribute getKeyColumn(UmlClass table, String match) {
		Attribute keyCol = null;
		search: for (Iterator i = UML13Utils.getAttributes(table).iterator(); i
				.hasNext();) {
			Attribute aCol = (Attribute) i.next();
			TaggedValue tv = UML13Utils.getTaggedValue(aCol,
					"implements-association");
			if (tv != null && tv.getValue() != null) {

				StringTokenizer st = new StringTokenizer(tv.getValue(), ";");
				while (st.hasMoreTokens()) {
					String t = st.nextToken();

					if (t.equalsIgnoreCase(match)) {
						keyCol = aCol;
						break search;
					}
				}
			}
		}
		return keyCol;
	}

	/**
	 * @param association
	 * @return
	 */
	private UmlClass getCorrelationTable(UmlAssociation association) {
		UmlClass corrTable = null;
		TaggedValue tv = UML13Utils.getTaggedValue(association,
				"correlation-table");
		if (tv != null) {
			String tableName = null;
			if (_dmPkg == null) {
				tableName = tv.getValue();
			} else {
				tableName = _dmPkg + Constant.DOT + tv.getValue();
			}
			corrTable = UML13Utils.getClass(UML13Utils.getModel(association),
					tableName);
		}
		return corrTable;
	}

	private void doOne2Many(Element classEl, UmlClass klass, UmlClass table,
			AssociationEnd thisEnd, AssociationEnd otherEnd) {
		if ((otherEnd.isNavigable()) && (thisEnd.isNavigable())) {
			UmlClass otherTable = getTable((UmlClass) otherEnd.getType());
			if (otherTable == null) {
				log.error("Couldn't find table for "
						+ otherEnd.getType().getName());
				throw new RuntimeException("Couldn't find table for "
						+ otherEnd.getType().getName());
			}
			Element setEl = new Element("set");
			classEl.addContent(setEl);
			setEl.setAttribute("name", otherEnd.getName());
			setEl.setAttribute("lazy", "true");
			if (thisEnd.isNavigable()) {
				setEl.setAttribute("inverse", "true");
			}
			String match = getQualifiedName(otherEnd.getType()) + Constant.DOT
					+ thisEnd.getName();
			Attribute keyCol = getKeyColumn(otherTable, match);
			if (keyCol == null) {
				log.error("A Couldn't find key column for " + match);
				throw new RuntimeException("A Couldn't find key column for "
						+ match);
			}
			Element cacheEl = new Element("cache");
			setEl.addContent(cacheEl);
			cacheEl.setAttribute("usage", "read-write");
			Element keyColEl = new Element("key");
			setEl.addContent(keyColEl);
			keyColEl.setAttribute("column", keyCol.getName());
			Element one2NEl = new Element("one-to-many");
			setEl.addContent(one2NEl);
			// String temp1 = getPackage((UmlClass)otherEnd.getType()) +
			// ".impl." + otherEnd.getType().getName()+"Impl";
			String temp1 = getPackage((UmlClass) otherEnd.getType()) + Constant.DOT
					+ otherEnd.getType().getName();
			one2NEl.setAttribute("class", temp1);
		} else if ((otherEnd.isNavigable()) && (!thisEnd.isNavigable())) {
			UmlClass otherTable = getTable((UmlClass) otherEnd.getType());
			if (otherTable == null) {
				log.error("Couldn't find table for "
						+ otherEnd.getType().getName());
				throw new RuntimeException("Couldn't find table for "
						+ otherEnd.getType().getName());
			}
			Element setEl = new Element("set");
			classEl.addContent(setEl);
			setEl.setAttribute("name", otherEnd.getName());
			setEl.setAttribute("lazy", "true");
			if (thisEnd.isNavigable()) {
				setEl.setAttribute("inverse", "true");
			}
			Element cacheEl = new Element("cache");
			setEl.addContent(cacheEl);
			cacheEl.setAttribute("usage", "read-write");
			String match = getQualifiedName(otherEnd.getType()) + Constant.DOT
					+ thisEnd.getName();
			Attribute keyCol = getKeyColumn(otherTable, match);
			if (keyCol == null) {
				log.error(" B Couldn't find key column for " + match);
				throw new RuntimeException(" B Couldn't find key column for "
						+ match);
			}
			Element keyColEl = new Element("key");
			setEl.addContent(keyColEl);
			keyColEl.setAttribute("column", keyCol.getName());
			Element one2NEl = new Element("one-to-many");
			setEl.addContent(one2NEl);
			// String temp1 = getPackage((UmlClass)otherEnd.getType()) +
			// ".impl." + otherEnd.getType().getName()+"Impl";
			String temp1 = getPackage((UmlClass) otherEnd.getType()) + Constant.DOT
					+ otherEnd.getType().getName();
			one2NEl.setAttribute("class", temp1);
		}
	}

	private void doMany2One(Element classEl, UmlClass klass, UmlClass table,
			AssociationEnd thisEnd, AssociationEnd otherEnd) {
		if (otherEnd.isNavigable()) {
			Element m2OneEl = new Element("many-to-one");
			classEl.addContent(m2OneEl);
			m2OneEl.setAttribute("name", otherEnd.getName());
			// String temp1 = getPackage((UmlClass)otherEnd.getType()) +
			// ".impl." + otherEnd.getType().getName()+"Impl";
			String temp1 = getPackage((UmlClass) otherEnd.getType()) + Constant.DOT
					+ otherEnd.getType().getName();
			m2OneEl.setAttribute("class", temp1);
			String match = getQualifiedName(klass) + Constant.DOT + otherEnd.getName();
			Attribute col = getKeyColumn(table, match);
			if (col == null) {
				log.error("Couldn't find key columns for " + match);
				throw new RuntimeException("Couldn't find key columns for "
						+ match);
			}
			m2OneEl.setAttribute("column", col.getName());
			if (eagerFetchPackages.contains(getPackage(klass))) {
				m2OneEl.setAttribute("lazy", "false");
				m2OneEl.setAttribute("fetch", "join");
			} else {
				m2OneEl.setAttribute("lazy", "proxy");
			}
		}
	}

	private void doOne2One(Element classEl, UmlClass klass, UmlClass table,
			AssociationEnd thisEnd, AssociationEnd otherEnd) {
		if ((otherEnd.isNavigable()) && (thisEnd.isNavigable())) {

			String match = getQualifiedName(klass) + Constant.DOT + otherEnd.getName();
			Attribute keyCol = getKeyColumn(table, match);
			if (keyCol == null) {
				// then this is the un-constrained side
				Element one2oneEl = new Element("one-to-one");
				classEl.addContent(one2oneEl);
				one2oneEl.setAttribute("name", otherEnd.getName());
				String temp1 = getPackage((UmlClass) otherEnd.getType()) + Constant.DOT
						+ otherEnd.getType().getName();
				one2oneEl.setAttribute("class", temp1);
				String temp2 = thisEnd.getName();
				one2oneEl.setAttribute("property-ref", temp2);

			} else {
				// then this is the constrained side
				Element m2oneEl = new Element("many-to-one");
				classEl.addContent(m2oneEl);
				m2oneEl.setAttribute("name", otherEnd.getName());
				String temp1 = getPackage((UmlClass) otherEnd.getType()) + Constant.DOT
						+ otherEnd.getType().getName();
				m2oneEl.setAttribute("class", temp1);
				m2oneEl.setAttribute("column", keyCol.getName());
				m2oneEl.setAttribute("unique", "true");
				if (eagerFetchPackages.contains(getPackage(klass))) {
					m2oneEl.setAttribute("lazy", "false");
					m2oneEl.setAttribute("fetch", "join");
				} else {
					m2oneEl.setAttribute("lazy", "proxy");
				}
			}
		} else if ((otherEnd.isNavigable()) && (!thisEnd.isNavigable())) {
			// The following code handles the situation where there is a
			// unidirectional one-to-one mapping
			// between two objects. The problem we have here is that in a
			// unidirectional on-to-one the
			// foreign key that links two tables can exist on either table. In
			// order to handle that we
			// have to determine where the foreign key column is and then build
			// the appropriate
			// hbm entry to handle it.


			UmlClass thisEndClass = (UmlClass) thisEnd.getType();
			UmlClass thisOtherEndClass = (UmlClass) otherEnd.getType();

			String match = getQualifiedName(klass) + Constant.DOT + otherEnd.getName();
			
			UmlClass table1 = getTable(thisEndClass);
			UmlClass table2 = getTable(thisOtherEndClass);

			Attribute keyCol1 = getKeyColumn(table1, match);
			Attribute keyCol2 = getKeyColumn(table2, match);

			if (keyCol1 != null) {
				Element many2oneEl = new Element("many-to-one");
				classEl.addContent(many2oneEl);
				many2oneEl.setAttribute("name", otherEnd.getName());
				String temp1 = getPackage((UmlClass) otherEnd.getType()) + Constant.DOT
						+ otherEnd.getType().getName();
				many2oneEl.setAttribute("class", temp1);
				many2oneEl.setAttribute("column", keyCol1.getName());
				many2oneEl.setAttribute("lazy", "false");
				many2oneEl.setAttribute("fetch", "join");

			} else if (keyCol2 != null) {

				Element many2oneEl = new Element("many-to-one");
				classEl.addContent(many2oneEl);
				many2oneEl.setAttribute("name", otherEnd.getName());
				String temp1 = getPackage((UmlClass) otherEnd.getType()) + Constant.DOT
						+ otherEnd.getType().getName();
				many2oneEl.setAttribute("class", temp1);
				many2oneEl.setAttribute("column", keyCol2.getName());
				many2oneEl.setAttribute("lazy", "false");
				many2oneEl.setAttribute("fetch", "join");
			}
			else
			{
				throw new RuntimeException("Couldn't find key columns for "
						+ match);
			}
		}
	}

	private String getPrimaryKey(UmlClass klass) {
		String primaryKey = new String();
		UmlClass table = getTable(klass);
		UmlClass superClass = UML13Utils.getSuperClass(klass);
		for (Iterator i = UML13Utils.getAttributes(klass, true).iterator(); i
				.hasNext();) {
			Attribute att = (Attribute) i.next();
			Attribute col;
			if (superClass == null) {
				col = getColumn(table, att);
			} else {
				col = getColumn(table, att, getQualifiedName(klass));
			}
			if (col == null) {
				log.warn("No column found for "
						+ UML13Utils.getQualifiedName(klass) + Constant.DOT
						+ att.getName() + ", continuing.");
				continue;
			}
			if (isPK(col)) {
				primaryKey = col.getName();

			} else {
				log.debug("No primary key found for " + UML13Utils.getQualifiedName(klass) + "\n");
			}
		}// End iterating through properties
		return primaryKey;
	}

	private boolean isPK(Attribute col) {
		log.debug("isPK#####################");
		boolean isPK = false;
		Stereotype colSter = UML13Utils.getStereotype(col);
		if (colSter != null && "PK".equals(colSter.getName())) {
			isPK = true;
		}
		if (colSter == null) {
			TaggedValue tvSter = UML13Utils.getTaggedValue(col, "stereotype");
			if ("PK".equals(tvSter.getValue())) {
				isPK = true;
			}
		}
		if (!isPK) {
			if ("id".equalsIgnoreCase(col.getName())) {
				isPK = true;
			}
		}
		if (!isPK) {
			log.debug("Checking operations");
			Classifier klass = col.getOwner();
			Collection ops = UML13Utils.getOperations(klass);
			log.debug("got " + ops.size() + " operations");
			search: for (Iterator i = ops.iterator(); i.hasNext();) {
				Operation op = (Operation) i.next();
				log.debug("op: " + op.getName());
				Stereotype ster = UML13Utils.getStereotype(op);
				if (ster != null) {
					log.debug("ster: " + ster.getName());
					if ("PK".equals(ster.getName())) {
						for (Iterator j = UML13Utils.getParameters(op)
								.iterator(); j.hasNext();) {
							Parameter param = (Parameter) j.next();
							log.debug("comparing: " + param.getName() + " to "
									+ col.getName());
							if (col.getName().equals(param.getName())) {
								isPK = true;
								break search;
							}
						}
					}
				}
			}
		}
		return isPK;
	}

	/**
	 * @return
	 */
	private Element getIDGeneratorEl() {
		return (Element) _idGenEl.clone();
	}

	private String getQualifiedName(ModelElement me) {
		String qName = null;
		UmlPackage pkg = null;
		if (_omPkg != null) {
			pkg = UML13Utils.getPackage(UML13Utils.getModel(me), _omPkg);
		} else {
			pkg = UML13Utils.getModel(me);
		}
		qName = UML13Utils.getNamespaceName(pkg, me) + Constant.DOT + me.getName();
		return qName;
	}

	/**
	 * @param klass
	 * @return
	 */
	private String getPackage(UmlClass klass) {
		UmlPackage pkg = null;
		if (_omPkg != null) {
			pkg = UML13Utils.getPackage(UML13Utils.getModel(klass), _omPkg);
		} else {
			pkg = UML13Utils.getModel(klass);
		}
		return UML13Utils.getNamespaceName(pkg, klass);

	}

	private boolean hasSubClasses(UmlClass klass) {
		return klass.getSpecialization().size() > 0;
	}

	private boolean isCLOB(Attribute col) {
		boolean isCLOB = false;
		TaggedValue tv = UML13Utils.getTaggedValue(col, "type");
		if (tv != null && tv.getValue() != null) {
			if (tv.getValue().equals("CLOB")) {
				isCLOB = true;
			}
		}

		return isCLOB;
	}

	private Attribute getColumn(UmlClass table, Attribute att) {
		String match = getPackage((UmlClass) att.getOwner()) + Constant.DOT
				+ att.getOwner().getName() + Constant.DOT + att.getName();
		log.debug("Looking for " + match + " on table " + table.getName());
		Attribute theCol = null;
		search: for (Iterator i = UML13Utils.getAttributes(table).iterator(); i
				.hasNext();) {
			Attribute col = (Attribute) i.next();
			TaggedValue tv = UML13Utils
					.getTaggedValue(col, "mapped-attributes");
			if (tv != null && tv.getValue() != null) {
				if (tv != null && tv.getValue() != null) {
					StringTokenizer st = new StringTokenizer(tv.getValue(), ";");
					while (st.hasMoreTokens()) {
						String t = st.nextToken();
						if (t.equalsIgnoreCase(match)) {
							theCol = col;
							break search;
						}
					}
				}
			}
		}
		return theCol;
	}

	private Attribute getColumn(UmlClass table, Attribute att,
			String correctPackageName) {

		String match = correctPackageName + Constant.DOT + att.getName();
		log.debug("Looking for " + match + " on table " + table.getName());
		Attribute theCol = null;
		search: for (Iterator i = UML13Utils.getAttributes(table).iterator(); i
				.hasNext();) {
			Attribute col = (Attribute) i.next();
			TaggedValue tv = UML13Utils
					.getTaggedValue(col, "mapped-attributes");
			if (tv != null && tv.getValue() != null) {
				if (tv != null && tv.getValue() != null) {
					StringTokenizer st = new StringTokenizer(tv.getValue(), ";");
					while (st.hasMoreTokens()) {
						String t = st.nextToken();
						if (t.equalsIgnoreCase(match)) {
							theCol = col;
							break search;
						}
					}
				}
			}
		}
		return theCol;
	}

	private UmlClass getTable(UmlClass klass) {
		UmlClass table = null;
		Collection clients = new ArrayList();
		for (Iterator j = klass.getSupplierDependency().iterator(); j.hasNext();) {
			Dependency d = (Dependency) j.next();
			Stereotype s = UML13Utils.getStereotype(d);
			if (s != null && "DataSource".equals(s.getName())) {
				clients.addAll(d.getClient());
			}
		}
		if (clients.size() != 1) {
			log.error(clients.size() + " data sources found for "
					+ klass.getName());
		} else {
			table = (UmlClass) clients.iterator().next();
		}
		return table;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.codegen.core.XMLConfigurable#configure(org.w3c.dom.Element)
	 */
	public void configure(org.w3c.dom.Element config)
			throws ConfigurationException {

		_omPkg = getParameter(config, "omPackage");
		log.info("omPackage: " + _omPkg);
		_dmPkg = getParameter(config, "dmPackage");
		log.info("dmPackage: " + _dmPkg);

		org.w3c.dom.Element idGenEl = XMLUtils.getChild(config, "generator");
		if (idGenEl != null) {
			_idGenEl = (new DOMBuilder()).build(idGenEl);
		} else {
			_idGenEl = new Element("generator");
			_idGenEl.setAttribute("class", "hilo");
			Element tableParamEl = new Element("param");
			tableParamEl.setAttribute("table", "hilo");
			_idGenEl.addContent(tableParamEl);
			Element columnParamEl = new Element("param");
			columnParamEl.setAttribute("column", "next_value");
			_idGenEl.addContent(columnParamEl);
			Element max_loParamEl = new Element("param");
			max_loParamEl.setAttribute("max_lo", "100");
			_idGenEl.addContent(max_loParamEl);
		}
	}

	private String getParameter(org.w3c.dom.Element el, String paramName) {
		String param = null;

		List params = XMLUtils.getChildren(el, "param");
		for (Iterator i = params.iterator(); i.hasNext();) {
			org.w3c.dom.Element paramEl = (org.w3c.dom.Element) i.next();
			if (paramName.equals(paramEl.getAttribute("name"))) {
				param = paramEl.getAttribute("value");
				break;
			}
		}

		return param;
	}

	private Vector getProperties() {
		Vector vec = new Vector();
		try {
			Properties _properties = new Properties();

			_properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("CORESystem.properties"));

			String eagerPackages = _properties
					.getProperty("eager_fetch_many2one_packages");
			StringTokenizer tokens = new StringTokenizer(eagerPackages, ",");
			while (tokens.hasMoreTokens()) {
				String temp = tokens.nextToken();
				vec.add(temp.trim());
			}
		} catch (IOException e) {
			log.error("IOException: " + e.getMessage());
			// System.out.println("getProperties.IOException occured:
			// "+e.getMessage());
		} catch (Exception ex) {
			log.error("Exception: " + ex.getMessage());
			// System.out.println("getProperties Exception - "+
			// ex.getMessage());
		}

		return vec;
	}
}
