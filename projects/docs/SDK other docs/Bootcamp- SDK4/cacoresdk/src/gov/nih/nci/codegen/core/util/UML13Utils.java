package gov.nih.nci.codegen.core.util;

import gov.nih.nci.common.util.Constant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.omg.uml.foundation.core.Abstraction;
import org.omg.uml.foundation.core.AssociationEnd;
import org.omg.uml.foundation.core.Attribute;
import org.omg.uml.foundation.core.Classifier;
import org.omg.uml.foundation.core.Dependency;
import org.omg.uml.foundation.core.Generalization;
import org.omg.uml.foundation.core.Interface;
import org.omg.uml.foundation.core.ModelElement;
import org.omg.uml.foundation.core.Namespace;
import org.omg.uml.foundation.core.Operation;
import org.omg.uml.foundation.core.Parameter;
import org.omg.uml.foundation.core.UmlAssociation;
import org.omg.uml.foundation.core.UmlClass;
import org.omg.uml.foundation.datatypes.MultiplicityRange;
import org.omg.uml.foundation.datatypes.ParameterDirectionKindEnum;
import org.omg.uml.foundation.extensionmechanisms.Stereotype;
import org.omg.uml.foundation.extensionmechanisms.TaggedValue;
import org.omg.uml.modelmanagement.Model;
import org.omg.uml.modelmanagement.UmlPackage;

/**
 * <!-- LICENSE_TEXT_START -->
 * Copyright 2001-2004 SAIC. Copyright 2001-2003 SAIC. This software was developed in conjunction with the National Cancer Institute,
 * and so to the extent government employees are co-authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the disclaimer of Article 3, below. Redistributions
 * in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution.
 * 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
 * "This product includes software developed by the SAIC and the National Cancer Institute."
 * If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself,
 * wherever such third-party acknowledgments normally appear.
 * 3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or promote products derived from this software.
 * 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize
 * the recipient to use any trademarks owned by either NCI or SAIC-Frederick.
 * 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE,
 * SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * @author caBIO Team
 * @version 1.0
 */
public class UML13Utils {

	private static Logger _logger = Logger.getLogger(UML13Utils.class);

	public UML13Utils() {
		super();
	}

	public static Collection getInterfaces(UmlClass klass) {
		return getInterfaces(klass, false);
	}

	public static Collection getInterfaces(UmlClass klass, boolean deep) {
		List interfaces = new ArrayList();
		UmlClass superClass = klass;

		uml.UmlPackage umlExtent = (uml.UmlPackage) klass.refOutermostPackage();
		do {
			for (Iterator i = umlExtent.getFoundation().getCore()
					.getAbstraction().refAllOfClass().iterator(); i.hasNext();) {
				Abstraction a = (Abstraction) i.next();
				Collection c = a.getClient();
				if (c.size() != 1) {
					/*
					 * throw new RuntimeException("Abstraction has " + c.size() +
					 * "client(s)");
					 */
					// System.out.println("Abstraction has " + c.size()+
					// "client(s)");
					_logger.warn("Abstraction has " + c.size() + "client(s)");
				}
				Classifier cls = (Classifier) c.iterator().next();
				if (getQualifiedName(cls).equals(getQualifiedName(superClass))) {
					c = a.getSupplier();
					if (c.size() != 1) {
						/*
						 * throw new RuntimeException("Abstraction has " +
						 * c.size() + "supplier(s)");
						 */
						// System.out.println("Abstraction has " + c.size() +
						// "supplier(s)");
						_logger.warn("Abstraction has " + c.size()
								+ "supplier(s)");

					}
					Classifier inter = (Classifier) c.iterator().next();
					if (!(inter instanceof Interface)) {
						/*
						 * throw new RuntimeException(inter.getName() + " is not
						 * an interface");
						 */
						// System.out.println(inter.getName()
						// + " is not an interface");
						_logger
								.warn(inter.getName()
										+ " is not an	  interface");

					}
					interfaces.add(inter);
				}
			}
			superClass = getSuperClass(superClass);
		} while (deep && superClass != null);

		return interfaces;
	}

	public static UmlClass getSuperClass(UmlClass klass) {
		UmlClass superClass = null;
		List superClasses = getSuperClasses(klass);
		if (superClasses.size() > 0) {
			superClass = (UmlClass) superClasses.iterator().next();
		}
		return superClass;
	}

	public static List getSuperClasses(UmlClass klass) {
		List superClasses = new ArrayList();
		for (Iterator i = klass.getGeneralization().iterator(); i.hasNext();) {
			superClasses.add(((Generalization) i.next()).getParent());
		}
		return superClasses;
	}

	public static String getNamespaceName(ModelElement me) {
		return getNamespaceName(getModel(me), me);
	}

	public static String getTestNamespaceName(ModelElement me) {
		return getTestNamespaceName(getModel(me), me);
	}

	public static String getQualifiedName(ModelElement me, String basePkg) {

		String qName = null;
		UmlPackage pkg = null;
		if (basePkg != null) {
			pkg = UML13Utils.getPackage(UML13Utils.getModel(me), basePkg);
		} else {
			pkg = UML13Utils.getModel(me);
		}
		qName = UML13Utils.getNamespaceName(pkg, me) + Constant.DOT + me.getName();
		return qName;
	}

	public static Model getModel(uml.UmlPackage umlExtent, String modelName) {
		Model model = null;
		for (Iterator i = umlExtent.getModelManagement().getModel()
				.refAllOfClass().iterator(); i.hasNext();) {
			Model m = (Model) i.next();
			if (modelName.equals(m.getName())) {
				model = m;
				break;
			}
		}
		return model;
	}

	public static Model getModel(ModelElement me) {
		if (me == null) {
			return null;
		}
		uml.UmlPackage umlExtent = (uml.UmlPackage) me.refOutermostPackage();
		return (Model) umlExtent.getModelManagement().getModel()
				.refAllOfClass().iterator().next();
	}

	public static String getNamespaceName(UmlPackage container, ModelElement me) {
		StringBuffer sb = new StringBuffer();
		ModelElement pkg = (ModelElement) me.getNamespace();
		while (!(pkg instanceof Model) && pkg != container && pkg != null) {
			if (sb.length() > 0) {
				sb.insert(0, Constant.DOT);
			}
			sb.insert(0, pkg.getName());
			pkg = (ModelElement) pkg.getNamespace();
		}
		return sb.toString();
	}

	public static String getTestNamespaceName(UmlPackage container,
			ModelElement me) {
		StringBuffer sb = new StringBuffer();
		ModelElement pkg = (ModelElement) me.getNamespace();
		while (!(pkg instanceof Model) && pkg != container && pkg != null) {
			if (sb.length() > 0) {
				sb.insert(0, Constant.DOT);
			}
			sb.insert(0, pkg.getName());
			pkg = (ModelElement) pkg.getNamespace();
		}
		int length = sb.length();
		int endPoint = sb.lastIndexOf(".");
		String prefix = sb.substring(0, endPoint);
		String suffix = sb.substring(endPoint, length);
		StringBuffer testPackage = new StringBuffer();
		testPackage.append(prefix);
		testPackage.append(".test");
		testPackage.append(suffix);
		return testPackage.toString();
	}

	public static String getQualifiedName(ModelElement me) {
		return getQualifiedName((UmlPackage) getModel(me), me);
	}

	public static String getTestQualifiedName(ModelElement me) {
		return getTestQualifiedName((UmlPackage) getModel(me), me);
	}

	public static String getQualifiedName(UmlPackage container, ModelElement me) {
		return getNamespaceName(container, me) + Constant.DOT + me.getName();
	}

	public static String getTestQualifiedName(UmlPackage container,
			ModelElement me) {
		return getTestNamespaceName(container, me) + Constant.DOT + me.getName();
	}

	public static Collection getAssociationEnds(Classifier klass) {
		return getAssociationEnds(klass, false);
	}

	public static Collection getAssociationEnds(Classifier klass,
			boolean includeInherited) {
		_logger.debug("class = " + klass.getName() + ", includeInherited = "
				+ includeInherited);
		Map assocEndsMap = new HashMap();
		Classifier superClass = klass;
		while (superClass != null) {
			Collection assocs = getAssociations(superClass);
			for (Iterator i = assocs.iterator(); i.hasNext();) {
				UmlAssociation assoc = (UmlAssociation) i.next();
				for (Iterator j = assoc.getConnection().iterator(); j.hasNext();) {
					AssociationEnd ae = (AssociationEnd) j.next();
					AssociationEnd otherEnd = getOtherAssociationEnd(ae);
					String id = otherEnd.getName() + Constant.LEFT_BRACKET
							+ getQualifiedName(otherEnd.getType()) + Constant.RIGHT_BRACKET;
					if (ae.getType() == superClass
							&& assocEndsMap.get(id) == null) {
						_logger.debug("adding assoc: " + id);
						assocEndsMap.put(id, ae);
					}
				}
			}
			if (includeInherited) {

				Collection gens = superClass.getGeneralization();
				if (gens.size() > 0) {
					superClass = (Classifier) ((Generalization) gens.iterator()
							.next()).getParent();
				} else {
					superClass = null;
				}

			} else {
				superClass = null;
			}
		}
		/*
		 * if (includeInherited) { Map assocEndsMap = new HashMap(); Classifier
		 * superClass = klass; do { Collection assocs = getAssociations(klass);
		 * for (Iterator i = assocs.iterator(); i.hasNext();) { UmlAssociation
		 * assoc = (UmlAssociation) i.next(); for (Iterator j =
		 * assoc.getConnection().iterator(); j .hasNext();) { AssociationEnd ae =
		 * (AssociationEnd) j.next(); AssociationEnd otherEnd =
		 * getOtherAssociationEnd(ae); String id = otherEnd.getName() + "[" +
		 * getQualifiedName(otherEnd.getType()) + "]"; if (ae.getType() ==
		 * superClass && assocEndsMap.get(id) == null) { _logger.debug("adding
		 * assoc: " + id); assocEndsMap.put(id, ae); } } } superClass =
		 * getSuperClass((UmlClass) superClass); } while (superClass != null);
		 * assocEnds = assocEndsMap.values(); } else { Collection assocs =
		 * getAssociations(klass); for (Iterator i = assocs.iterator();
		 * i.hasNext();) { UmlAssociation assoc = (UmlAssociation) i.next(); for
		 * (Iterator j = assoc.getConnection().iterator(); j.hasNext();) {
		 * AssociationEnd ae = (AssociationEnd) j.next(); if (ae.getType() ==
		 * klass) { assocEnds.add(ae); } } } }
		 */
		return assocEndsMap.values();
	}

	public static Collection getAssociations(Classifier klass) {
		uml.UmlPackage umlExtent = (uml.UmlPackage) klass.refOutermostPackage();
		Collection assocs = new ArrayList();
		for (Iterator i = umlExtent.getFoundation().getCore()
				.getUmlAssociation().refAllOfClass().iterator(); i.hasNext();) {
			UmlAssociation assoc = (UmlAssociation) i.next();
			for (Iterator j = assoc.getConnection().iterator(); j.hasNext();) {
				AssociationEnd ae = (AssociationEnd) j.next();
				if (ae.getType() == klass) {
					assocs.add(assoc);
					break;
				}
			}
		}
		return assocs;
	}

	public static Collection getAssociations(UmlClass classA, UmlClass classB) {
		Collection associations = new ArrayList();
		for (Iterator i = getAssociationEnds(classA).iterator(); i.hasNext();) {
			AssociationEnd endA = (AssociationEnd) i.next();
			AssociationEnd endB = getOtherAssociationEnd(endA);
			if (endB.getType() == classB) {
				associations.add(endB.getAssociation());
			}
		}
		return associations;
	}

	protected static Collection getAssociations(UmlPackage pkg, Classifier klass) {

		Collection assocs = new ArrayList();
		for (Iterator i = pkg.getOwnedElement().iterator(); i.hasNext();) {
			Object o = i.next();
			if (o instanceof UmlAssociation) {
				UmlAssociation assoc = (UmlAssociation) o;
				for (Iterator j = assoc.getConnection().iterator(); j.hasNext();) {
					UmlClass c = (UmlClass) ((AssociationEnd) j.next())
							.getType();
					if (getQualifiedName(klass).equals(getQualifiedName(c))) {

						assocs.add(assoc);
						break;
					}
				}
			} else if (o instanceof UmlPackage) {
				assocs.addAll(getAssociations((UmlPackage) o, klass));
			}
		}
		return assocs;
	}

	public static Attribute getAttribute(UmlClass klass, String attName) {
		Attribute att = null;
		for (Iterator i = klass.getFeature().iterator(); i.hasNext();) {
			Object o = i.next();
			if (o instanceof Attribute) {
				Attribute anAtt = (Attribute) o;

				if (anAtt.getName().equals(attName)) {
					att = anAtt;
					break;
				}
			}
		}
		return att;
	}

	public static Collection getAttributes(UmlClass klass) {
		return getAttributes(klass, false);
	}

	public static Collection getAttributes(UmlClass klass,
			boolean includeInherited) {

		Collection atts = new ArrayList();

		if (includeInherited) {

			Map attsMap = new HashMap();
			UmlClass superClass = klass;
			do {
				for (Iterator i = superClass.getFeature().iterator(); i
						.hasNext();) {
					Object o = i.next();
					if (o instanceof Attribute) {
						Attribute att = (Attribute) o;
						if (attsMap.get(att.getName()) == null) {
							attsMap.put(att.getName(), att);
						}
					}
				}
				superClass = getSuperClass(superClass);
			} while (superClass != null);

			atts = attsMap.values();
		} else {
			for (Iterator i = klass.getFeature().iterator(); i.hasNext();) {
				Object o = i.next();
				if (o instanceof Attribute) {
					atts.add(o);
				}
			}
		}
		return atts;
	}

	public static UmlClass getClass(UmlPackage pkg, String className) {
		UmlClass klass = null;
		int idx = className.indexOf(Constant.DOT);
		if (idx == -1) {

			for (Iterator i = pkg.getOwnedElement().iterator(); i.hasNext();) {
				Object o = i.next();
				if (o instanceof UmlClass) {
					UmlClass c = (UmlClass) o;
					if (c.getName().equals(className)) {
						klass = c;
						break;
					}
				}
			}
		} else {

			String subPkgName = className.substring(0, idx);
			UmlPackage subPkg = getPackage(pkg, subPkgName);
			if (subPkg != null) {
				String newClassName = className.substring(idx + 1);
				klass = getClass(subPkg, newClassName);
			}
		}
		return klass;
	}

	/*
	 * public Collection getExceptions(Operation op) { Collection exceptions =
	 * new ArrayList(); AContextRaisedSignal q = _umlExtent
	 * .getBehavioralElements() .getCommonBehavior() .getAContextRaisedSignal();
	 * for (Iterator i = q.getRaisedSignal(op).iterator(); i.hasNext();) {
	 * Object o = i.next(); //_logger.debug("exception class: " +
	 * o.getClass().getName()); if (o instanceof UmlException) { UmlException e =
	 * (UmlException) o; //_logger.debug(" namespace: " +
	 * e.getNamespace().getName()); exceptions.add(e.getNamespace()); } } return
	 * exceptions; }
	 */

	public static uml.UmlPackage getUmlExtent(ModelElement me) {
		return (uml.UmlPackage) me.refOutermostPackage();
	}

	public static Collection getExceptions(Operation op) {
		Collection exceptions = new ArrayList();
		Collection throwsSters = new ArrayList();
		for (Iterator i = getUmlExtent(op).getFoundation()
				.getExtensionMechanisms().getStereotype().refAllOfClass()
				.iterator(); i.hasNext();) {
			Stereotype s = (Stereotype) i.next();
			if ("throws".equalsIgnoreCase(s.getName())) {
				throwsSters.add(s);
			}
		}
		for (Iterator i = throwsSters.iterator(); i.hasNext();) {
			Stereotype s = (Stereotype) i.next();
			for (Iterator j = s.getExtendedElement().iterator(); j.hasNext();) {
				Dependency d = (Dependency) j.next();
				for (Iterator k = d.getClient().iterator(); k.hasNext();) {
					if (k.next() == op) {
						exceptions.add(d.getSupplier().iterator().next());
						break;
					}
				}
			}
		}
		return exceptions;
	}

	public static Collection getOperations(Classifier klass) {
		Collection operations = new ArrayList();
		for (Iterator i = klass.getFeature().iterator(); i.hasNext();) {
			Object o = i.next();
			if (o instanceof Operation) {
				operations.add(o);
			}
		}
		return operations;
	}

	public static AssociationEnd getOtherAssociationEnd(AssociationEnd assocEnd) {
		AssociationEnd otherAssocEnd = null;
		for (Iterator i = assocEnd.getAssociation().getConnection().iterator(); i
				.hasNext();) {
			AssociationEnd ae = (AssociationEnd) i.next();
			if (ae != assocEnd) {
				otherAssocEnd = ae;
				break;
			}
		}
		return otherAssocEnd;
	}

	public static AssociationEnd getOtherAssociationEnd(Classifier klass,
			String name) {
		AssociationEnd assocEnd = null;
		for (Iterator i = getAssociationEnds(klass).iterator(); i.hasNext();) {
			AssociationEnd ae = getOtherAssociationEnd((AssociationEnd) i
					.next());
			if (name.equals(ae.getName())) {
				assocEnd = ae;
				break;
			}
		}
		return assocEnd;
	}

	public static ModelElement getModelElement(Namespace ns, Class klass,
			String name) {

		ModelElement me = null;

		for (Iterator i = ns.getOwnedElement().iterator(); i.hasNext();) {
			Object o = i.next();
			if (o instanceof ModelElement
					&& klass.isAssignableFrom(o.getClass())) {
				ModelElement aMe = (ModelElement) o;
				if (aMe.getName().equals(name)) {
					me = aMe;
				}
			}
		}
		return me;
	}

	public static UmlPackage getPackage(UmlPackage parentPkg, String pkgName) {
		UmlPackage pkg = null;
		int idx = pkgName.indexOf(Constant.DOT);
		if (idx == -1) {

			pkg = (UmlPackage) getModelElement(parentPkg, UmlPackage.class,
					pkgName);
		} else {
			String subPkgName = pkgName.substring(idx + 1);

			for (Iterator i = parentPkg.getOwnedElement().iterator(); i
					.hasNext();) {
				Object o = i.next();
				if (o instanceof UmlPackage) {
					UmlPackage subPkg = (UmlPackage) o;
					pkg = getPackage(subPkg, subPkgName);
					if (pkg != null) {
						break;
					}
				}
			}
		}
		return pkg;
	}

	public static List getParameters(Operation operation) {
		List params = new ArrayList();
		for (Iterator iter = operation.getParameter().iterator(); iter
				.hasNext();) {
			Parameter p = (Parameter) iter.next();
			if (!ParameterDirectionKindEnum.PDK_RETURN.equals(p.getKind())) {
				params.add(p);
			}
		}
		return params;
	}

	public static Stereotype getStereotype(ModelElement me) {
		Stereotype ster = null;
		uml.UmlPackage umlExtent = (uml.UmlPackage) me.refOutermostPackage();
		Collection sters = umlExtent.getFoundation().getExtensionMechanisms()
				.getStereotype().refAllOfClass();
		search: for (Iterator i = sters.iterator(); i.hasNext();) {
			Stereotype s = (Stereotype) i.next();
			for (Iterator j = s.getExtendedElement().iterator(); j.hasNext();) {
				if (j.next() == me) {
					ster = s;
					break search;
				}
			}
		}
		return ster;
	}

	public static TaggedValue getTaggedValue(ModelElement me, String tagTypeName) {
		if (me == null) {
			/*
			 * throw new RuntimeException("model element is null");
			 */
			// System.out.println("model element is null");
			_logger.warn("model element is null");
		}
		if (tagTypeName == null) {
			/*
			 * throw new RuntimeException("tagType is null");
			 */
			_logger.warn("tagType is null");

		}
		TaggedValue taggedValue = null;
		uml.UmlPackage umlExtent = (uml.UmlPackage) me.refOutermostPackage();
		for (Iterator i = umlExtent.getFoundation().getExtensionMechanisms()
				.getAModelElementTaggedValue().getTaggedValue(me).iterator(); i
				.hasNext();) {
			TaggedValue tv = (TaggedValue) i.next();
			if (tagTypeName.equalsIgnoreCase(tv.getTag())) {
				taggedValue = tv;
				break;
			}
		}

		return taggedValue;
	}

	/**
	 * @param thisEnd
	 * @param otherEnd
	 * @return
	 */
	public static boolean isOne2One(AssociationEnd thisEnd,
			AssociationEnd otherEnd) {

		MultiplicityRange thisRange = (MultiplicityRange) thisEnd
				.getMultiplicity().getRange().iterator().next();
		// System.out.println("isOne2One ThisRange upper: " +
		// thisRange.getUpper());
		// System.out.println("isOne2One ThisRange lower: " +
		// thisRange.getLower());

		MultiplicityRange otherRange = (MultiplicityRange) otherEnd
				.getMultiplicity().getRange().iterator().next();
		// System.out.println("isOne2One OtherRange upper: " +
		// otherRange.getUpper());
		// System.out.println("isOne2One OtherRange lower: " +
		// otherRange.getLower());

		return (thisRange.getUpper() == 1 && otherRange.getUpper() == 1);
	}

	/**
	 * @param thisEnd
	 * @param otherEnd
	 * @return
	 */
	public static boolean isMany2One(AssociationEnd thisEnd,
			AssociationEnd otherEnd) {

		MultiplicityRange thisRange = (MultiplicityRange) thisEnd
				.getMultiplicity().getRange().iterator().next();
		// System.out.println("isMany2One ThisRange upper: " +
		// thisRange.getUpper());
		// System.out.println("isMany2One ThisRange lower: " +
		// thisRange.getLower());
		MultiplicityRange otherRange = (MultiplicityRange) otherEnd
				.getMultiplicity().getRange().iterator().next();
		// System.out.println("isMany2One OtherRange upper: " +
		// otherRange.getUpper());
		// System.out.println("isMany2One OtherRange lower: " +
		// otherRange.getLower());
		return ((thisRange.getUpper() == -1 || thisRange.getUpper() > 1) && otherRange
				.getUpper() == 1);
		// return (5 > 1 && 1 == 1);
	}

	/**
	 * @param thisEnd
	 * @param otherEnd
	 * @return
	 */
	public static boolean isOne2Many(AssociationEnd thisEnd,
			AssociationEnd otherEnd) {

		MultiplicityRange thisRange = (MultiplicityRange) thisEnd
				.getMultiplicity().getRange().iterator().next();
		// System.out.println("isOne2Many ThisRange upper: " +
		// thisRange.getUpper());
		// System.out.println("isOne2Many ThisRange lower: " +
		// thisRange.getLower());
		MultiplicityRange otherRange = (MultiplicityRange) otherEnd
				.getMultiplicity().getRange().iterator().next();
		// System.out.println("isOne2Many OtherRange upper: " +
		// otherRange.getUpper());
		// System.out.println("isOne2Many OtherRange lower: " +
		// otherRange.getLower());
		return ((thisRange.getUpper() == 1) && (otherRange.getUpper() == -1 || otherRange
				.getUpper() > 1));

	}

	/**
	 * @param thisEnd
	 * @param otherEnd
	 * @return
	 */
	public static boolean isMany2Many(AssociationEnd thisEnd,
			AssociationEnd otherEnd) {

		MultiplicityRange thisRange = (MultiplicityRange) thisEnd
				.getMultiplicity().getRange().iterator().next();
		// System.out.println("isMany2Many ThisRange upper: " +
		// thisRange.getUpper());
		// System.out.println("isMany2Many ThisRange lower: " +
		// thisRange.getLower());
		MultiplicityRange otherRange = (MultiplicityRange) otherEnd
				.getMultiplicity().getRange().iterator().next();
		// System.out.println("isMany2Many OtherRange upper: " +
		// otherRange.getUpper());
		// System.out.println("isMany2Many OtherRange lower: " +
		// otherRange.getLower());

		return ((thisRange.getUpper() == -1 || thisRange.getUpper() > 1) && (otherRange
				.getUpper() == -1 || otherRange.getUpper() > 1));
	}

	public static String getLowerBound(AssociationEnd thisEnd,
			AssociationEnd otherEnd) {

		MultiplicityRange otherRange = (MultiplicityRange) otherEnd
				.getMultiplicity().getRange().iterator().next();
		Integer x = new Integer(otherRange.getLower());
		return x.toString();
	}

	public static String getUpperBound(AssociationEnd thisEnd,
			AssociationEnd otherEnd) {

		MultiplicityRange otherRange = (MultiplicityRange) otherEnd
				.getMultiplicity().getRange().iterator().next();
		int multiplicity = otherRange.getUpper();
		String finalMultiplicity = new String();
		if (multiplicity == -1) {
			finalMultiplicity = "unbounded";
		} else {
			Integer x = new Integer(multiplicity);
			finalMultiplicity = x.toString();
		}
		return finalMultiplicity;
	}

	/**
	 * @param klass
	 * @param attName
	 * @param includeInherited
	 * @return
	 */
	public static boolean hasAttribute(Classifier klass, String attName,
			boolean includeInherited) {
		boolean hasAtt = false;
		for (Iterator i = getAttributes((UmlClass) klass, includeInherited)
				.iterator(); i.hasNext();) {
			Attribute att = (Attribute) i.next();
			if (att.getName().equals(attName)) {
				hasAtt = true;
				break;
			}
		}
		return false;
	}

	/**
	 * @param op
	 * @return
	 */
	public static Classifier getReturnType(Operation op) {
		Classifier retType = null;
		for (Iterator iter = op.getParameter().iterator(); iter.hasNext();) {
			Parameter para = (Parameter) iter.next();
			if (ParameterDirectionKindEnum.PDK_RETURN.equals(para.getKind())) {
				if (para.getType() != null) {
					retType = para.getType();
				}
				break;
			}
		}
		return retType;
	}
	
	public static boolean isStatic(ModelElement me){
		
		TaggedValue tValue = UML13Utils.getTaggedValue(me, "static");
		
		if (tValue == null) {
			return false;
		}
		
		return ("1".equalsIgnoreCase(tValue.getValue()));
		
	}
}
