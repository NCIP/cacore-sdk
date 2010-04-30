package gov.nih.nci.codegen.util;

import gov.nih.nci.codegen.GenerationException;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAttribute;
import gov.nih.nci.ncicb.xmiinout.domain.UMLClass;
import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.ncicb.xmiinout.domain.UMLPackage;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;
import gov.nih.nci.ncicb.xmiinout.util.ModelUtil;
import gov.nih.nci.iso21090.hibernate.node.ComplexNode;
import gov.nih.nci.iso21090.hibernate.node.Node;
import gov.nih.nci.iso21090.hibernate.node.RootNode;
import gov.nih.nci.iso21090.hibernate.node.SimpleNode;
import gov.nih.nci.iso21090.hibernate.node.ConstantNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsoDatatypeTransformationHelper
{
	
	private UMLModel model;
	private TransformerUtils utils;
	
	public UMLModel getModel()
	{
		return model;
	}

	public void setModel(UMLModel model)
	{
		this.model = model;
	}

	public TransformerUtils getUtils()
	{
		return utils;
	}

	public void setUtils(TransformerUtils utils)
	{
		this.utils = utils;
	}


	/**
	 * Determines if the node requires Join element in the Hibernate layer
	 * 
	 * @param rootNode
	 * @return
	 */
	public boolean requiresJoin(RootNode rootNode)
	{
		if(rootNode.getTargetTableName() != null && !"".equals(rootNode.getTargetTableName()))
		{
			if((rootNode.getTargetTablePrimaryKey() == null || "".equals(rootNode.getTargetTablePrimaryKey())) && !rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))
			{
					return true;
			}
			else if(rootNode.getJoinTableName() != null && !"".equals(rootNode.getJoinTableName()) && !rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))
			{
					return true;
			}
		}
		return false;
	}

	/**
	 * Determines if the node requires separate entity class mapping in the Hibernate layer
	 * 
	 * @param rootNode
	 * @return
	 */
	public boolean requiresSeperateClassMapping(RootNode rootNode)
	{
		if(rootNode.getTargetTableName()!= null && !"".equals(rootNode.getTargetTableName()) && rootNode.getTargetTablePrimaryKey() != null && !"".equals(rootNode.getTargetTablePrimaryKey()))
		{
				return true;
		}		
		
		return false;

	}
	
	/**
	 * Higher level class which constructs the Graph by reading the mapped-attributes tag values. 
	 * Subsequently it looks into the UML Model for the ISO type structure and sets the datatype for all the attributes 
	 * 
	 * @param klass
	 * @param attr
	 * @param table
	 * @throws GenerationException
	 */
	public RootNode getDatatypeNode(UMLClass klass, UMLAttribute attr, UMLClass table) throws GenerationException
	{
		RootNode rootNode = createDatatypeNode(klass, attr, table);
		rootNode.setIsoClassName(utils.ISO_ROOT_PACKAGE_NAME+"."+attr.getDatatype().getName());
		rootNode.setParentClassName(utils.getFQCN(klass));
		traverseNodeAndAttachDataType(rootNode, klass, attr);
		getGlobalConstantNode(rootNode);
		traverseNodeAndAttachDataType(rootNode, klass, attr);
		attachDataModelInformation(rootNode,klass, attr, table);
		//printNode(rootNode, "");
		return rootNode;
	}


	
	/**
	 * Converts the rootNode into corresponding Hibernate entity class mapping
	 * 
	 * @param rootNode
	 * @param prefix
	 * @return
	 * @throws GenerationException
	 */
	public StringBuffer convertToHibernateClass(RootNode rootNode, String prefix) throws GenerationException
	{
		StringBuffer buffer = new StringBuffer();
		
		ComplexNode processingNode = rootNode;
		if(rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))
		{
			for(Node innerNode: rootNode.getInnerNodes())
			{
				if(innerNode instanceof ComplexNode)
				{
					processingNode = (ComplexNode)innerNode;
					break;
				}
			}
		}
		
		String entityName = converteIsoPropertyToEntityName(rootNode.getParentClassName(),rootNode.getName());
		String className = converteIsoClassNameToJavaClassName(processingNode.getIsoClassName());
		
		buffer.append(prefix+"<class name=\""+className+"\" table=\""+rootNode.getTargetTableName()+"\" entity-name=\""+entityName+"\">");
		buffer.append(prefix+"\t<id column=\""+rootNode.getTargetTablePrimaryKey()+"\" type=\"int\"/>");
		
		for(Node innerNode: processingNode.getInnerNodes())
		{
			if(innerNode instanceof SimpleNode)
			{
				convertSimpleNodeToHibernateProperty((SimpleNode)innerNode, buffer, prefix+"\t");
			}
		}
		for(Node innerNode: processingNode.getInnerNodes())
		{
			if(innerNode instanceof ComplexNode)
			{
				convertComplexNodeToHibernateComponent((ComplexNode)innerNode,buffer,prefix+"\t");
			}
		}
		buffer.append(prefix+"</class>");
		return buffer;
	}
	
	
	/**
	 * Converts the rootNode into corresponding Hibernate component mapping
	 * 
	 * @param rootNode
	 * @param prefix
	 * @return
	 * @throws GenerationException
	 */
	public StringBuffer convertToHibernateComponent(RootNode rootNode, String prefix) throws GenerationException
	{
		StringBuffer buffer = new StringBuffer();
		
		String mappingStyle;
		if(rootNode.getTargetTableName() == null || "".equals(rootNode.getTargetTableName()))
		{
			//Same table
			mappingStyle = "component";
			convertComplexNodeToHibernateComponent(rootNode,buffer,prefix);
		}
		else
		{
			if(rootNode.getTargetTablePrimaryKey() == null || "".equals(rootNode.getTargetTablePrimaryKey()))
			{
				if(!rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))
				{
					//Other table with no primary key 
					mappingStyle = "joined component";
					convertComplexNodeToHibernateJoinedComponent(rootNode,buffer,prefix, rootNode.getTargetTableName(), rootNode.getTargetTableForeignKey());
				}
				else
				{
					//Other table collection with no primary key
					mappingStyle = "component with set of composite-elements";
					convertComplexNodeToHibernateComponentWithSet(rootNode,buffer,prefix,rootNode.getTargetTableName(), rootNode.getTargetTableForeignKey(),null,0);
				}

			}
			else
			{
				if(rootNode.getJoinTableName() == null || "".equals(rootNode.getJoinTableName()))
				{
					if(!rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))
					{
						//Other table with primary key
						mappingStyle = "many-to-one";
						convertComplexNodeToHibernateManyToOne(rootNode,buffer,prefix,rootNode.getSelfTableForeignKey());
					}
					else
					{
						//Other table collection with primary key
						mappingStyle = "component with set of one-to-many";
						convertComplexNodeToHibernateComponentWithSet(rootNode,buffer,prefix,rootNode.getTargetTableName(), rootNode.getTargetTableForeignKey(),null,1);
					}
				}
				else
				{
					if(!rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))
					{
						//Other table with primary key and join table
						mappingStyle = "joined many-to-one";
						convertComplexNodeToHibernateJoinedManyToOne(rootNode,buffer,prefix,rootNode.getJoinTableName(), rootNode.getJoinTableForeignKey(), rootNode.getJoinTableInverseKey());
					}
					else
					{
						//Other table collection with primary key and join table						
						mappingStyle = "component with set of many-to-many";
						convertComplexNodeToHibernateComponentWithSet(rootNode,buffer,prefix,rootNode.getJoinTableName(), rootNode.getJoinTableInverseKey(),rootNode.getJoinTableForeignKey(),2);						
					}
				}
			}
		}
	
		return buffer;
	}

	private void convertComplexNodeToHibernateJoinedComponent(ComplexNode node, StringBuffer buffer, String prefix, String tableName, String keyColumnName) throws GenerationException
	{
		buffer.append(prefix+"<join table=\""+tableName+"\" optional=\"true\">");
		buffer.append(prefix+"\t<key column=\""+keyColumnName+"\"/>");
		convertComplexNodeToHibernateComponent(node, buffer, prefix+"\t", "component", true);
		buffer.append(prefix+"</join>");		
	}
	
	private void convertComplexNodeToHibernateJoinedManyToOne(RootNode rootNode, StringBuffer buffer, String prefix, String joinTableName, String keyColumnName, String inverseColumnName)
	{
		buffer.append(prefix+"<join table=\""+joinTableName+"\" optional=\"true\">");
		buffer.append(prefix+"\t<key column=\""+inverseColumnName+"\"/>");
		convertComplexNodeToHibernateManyToOne(rootNode, buffer, prefix+"\t",keyColumnName );
		buffer.append(prefix+"</join>");		
	}
	
	
	private void convertComplexNodeToHibernateManyToOne(RootNode rootNode, StringBuffer buffer, String prefix, String keyColumnName)
	{
		String entityName = converteIsoPropertyToEntityName(rootNode.getParentClassName(),rootNode.getName());
		buffer.append(prefix+"<many-to-one name=\""+rootNode.getName()+"\" entity-name=\""+entityName+"\" column=\""+keyColumnName+"\" lazy=\"false\" cascade=\"all\"/>");
		
	}
	
	private void convertComplexNodeToHibernateOneToMany(RootNode rootNode, StringBuffer buffer, String prefix)
	{
		String entityName = converteIsoPropertyToEntityName(rootNode.getParentClassName(),rootNode.getName());
		buffer.append(prefix+"<one-to-many entity-name=\""+entityName+"\"/>");
	}

	private void convertComplexNodeToHibernateManyToMany(RootNode rootNode, StringBuffer buffer, String prefix, String keyColumnName)
	{
		String entityName = converteIsoPropertyToEntityName(rootNode.getParentClassName(),rootNode.getName());
		buffer.append(prefix+"<many-to-many entity-name=\""+entityName+"\" column=\""+keyColumnName+"\" lazy=\"false\"/>");
	}
	
	private void convertComplexNodeToHibernateComponentWithSet(RootNode rootNode, StringBuffer buffer, String prefix, String tableName, String inverseColumnName, String keyColumnName, int collectionNodeProcessingInstruction) throws GenerationException
	{
		String componentClassName = converteIsoClassNameToJavaClassName(rootNode.getIsoClassName());
		buffer.append(prefix+"<component name=\""+rootNode.getName()+"\" class=\""+componentClassName+"\">");
		//buffer.append(prefix+"\t<tuplizer class=\"gov.nih.nci.iso21090.hibernate.tuple.ConstantAndNullFlavorTuplizer\"/>");
		
		for(Node innerNode: rootNode.getInnerNodes())
		{
			if(innerNode instanceof SimpleNode)
			{
				convertSimpleNodeToHibernateProperty((SimpleNode)innerNode, buffer, prefix+"\t");
			}
		}
		for(Node innerNode: rootNode.getInnerNodes())
		{
			if(innerNode instanceof ComplexNode)
			{
				ComplexNode collectionNode = (ComplexNode) innerNode;
				String tableNameString = "table=\""+tableName+"\"";
				buffer.append(prefix+"\t<set name=\""+collectionNode.getName()+"\" lazy=\"false\" "+tableNameString+" cascade=\"all\">");
				buffer.append(prefix+"\t\t<key column=\""+inverseColumnName+"\" not-null=\"false\"/>");
				switch(collectionNodeProcessingInstruction)
				{
					case 1:
						convertComplexNodeToHibernateOneToMany(rootNode, buffer, prefix+"\t\t");
						break;
					case 2:
						convertComplexNodeToHibernateManyToMany(rootNode, buffer, prefix+"\t\t", keyColumnName);
						break;
					default:
						convertComplexNodeToHibernateComponent(collectionNode, buffer,prefix+"\t\t", "composite-element",false);
				}
					
				buffer.append(prefix+"\t</set>");
				break; //Can process only one collection element
			}
		}
		buffer.append(prefix+"</component>");
	}




	private void convertComplexNodeToHibernateComponent(ComplexNode node, StringBuffer buffer, String prefix) throws GenerationException
	{
		convertComplexNodeToHibernateComponent(node, buffer, prefix, "component", true);
	}
	
	private void convertComplexNodeToHibernateComponent(ComplexNode node, StringBuffer buffer, String prefix, String elementType, Boolean useElementName) throws GenerationException
	{
		String componentClassName = converteIsoClassNameToJavaClassName(node.getIsoClassName());

		if(node.getName().indexOf('[')>0)
		{
			String name = node.getName();
			name = name.replace('[','_');
			name = name.substring(0, name.length()-1);
			String componentName = useElementName?" name=\""+name+"\"":"";
			String propertyAccessor = "gov.nih.nci.iso21090.hibernate.property.CollectionPropertyAccessor";
			buffer.append(prefix+"<"+elementType+componentName+" class=\""+componentClassName+"\" access=\""+propertyAccessor+"\">");
		}
		else
		{
			String componentName = useElementName?" name=\""+node.getName()+"\"":"";
			buffer.append(prefix+"<"+elementType+componentName+" class=\""+componentClassName+"\">");
			if(((utils.ISO_ROOT_PACKAGE_NAME+".AD").equals(node.getIsoClassName())) || (node.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".EN") && !node.getIsoClassName().equals(utils.ISO_ROOT_PACKAGE_NAME+".ENXP")))
				buffer.append(prefix+"\t<tuplizer class=\"gov.nih.nci.iso21090.hibernate.tuple.PartCollectionTuplizer\"/>");
				
			//buffer.append(prefix+"\t<tuplizer class=\"gov.nih.nci.iso21090.hibernate.tuple.ConstantAndNullFlavorTuplizer\"/>");
		}
		
		
		for(Node innerNode: node.getInnerNodes())
		{
			if(innerNode instanceof SimpleNode)
			{
				convertSimpleNodeToHibernateProperty((SimpleNode)innerNode, buffer, prefix+"\t");
			}
		}
		for(Node innerNode: node.getInnerNodes())
		{
			if(innerNode instanceof ComplexNode)
			{
				String convertedElementType = "composite-element".equals(elementType)?"nested-composite-element":elementType;
				convertComplexNodeToHibernateComponent((ComplexNode)innerNode, buffer, prefix+"\t", convertedElementType, true);
			}
		}
		buffer.append(prefix+"</"+elementType+">");
	}
	
	private void convertSimpleNodeToHibernateProperty(SimpleNode node, StringBuffer buffer, String prefix) throws GenerationException
	{
		String type = node.getIsoClassName();
			
		if(isEnum(type))
		{
			buffer.append(prefix+"<property name=\""+node.getName()+"\" column=\""+node.getColumnName()+"\">");
			buffer.append(prefix+"\t<type name=\"gov.nih.nci.iso21090.hibernate.usertype.EnumUserType\">");
			buffer.append(prefix+"\t\t<param name=\"enumClassName\">"+type+"</param>");
			buffer.append(prefix+"\t</type>");
			buffer.append(prefix+"</property>");
		}
		else
		{
			String convertedType = converteIsoAttributeTypeToHibernateType(type);
			buffer.append(prefix+"<property name=\""+node.getName()+"\" column=\""+node.getColumnName()+"\" type=\""+convertedType+"\"/>");
		}
	}

	public boolean isEnum(String type) throws GenerationException
	{
		if(!type.startsWith(utils.ISO_ROOT_PACKAGE_NAME+".")) return false;

		UMLClass klass = findISOClass(type);
		
		if (klass == null) return false;
		if("enumeration".equals(klass.getStereotype()))
			return true;
		
		return false;
	}

	private String converteIsoAttributeTypeToHibernateType(String isoType) throws GenerationException
	{
		if(isoType.startsWith(utils.ISO_ROOT_PACKAGE_NAME+"."))
			isoType = isoType.substring((utils.ISO_ROOT_PACKAGE_NAME+".").length());
		
		String propertyType = null;
		if("Uri".equals(isoType))
			propertyType = "gov.nih.nci.iso21090.hibernate.usertype.UriUserType";
		if("Uid".equals(isoType))
			propertyType = "string";
		if("Code".equals(isoType))
			propertyType = "string";
		if("String".equals(isoType))
			propertyType = "string";
		if("Binary".equals(isoType))
			propertyType = "org.springframework.orm.hibernate3.support.BlobByteArrayType";
		if("Integer".equals(isoType)) 
			propertyType = "integer";
		if("Real".equals(isoType))
			propertyType = "big_decimal";
		if("Boolean".equals(isoType))
			propertyType = "boolean";
		if("date".equals(isoType))
			propertyType = "java.util.Date";
		if("ENXP_qualifier".equals(isoType))
			propertyType = "string";
		
		if(propertyType == null)
			throw new GenerationException("Can not determine Hibernate Type for corresponding ISO Datatype:"+isoType);

		return propertyType;
	}

	public String converteIsoClassNameToJavaClassName(String isoClassName)
	{
		String isoName = isoClassName.substring((utils.ISO_ROOT_PACKAGE_NAME+".").length());
		if(isoName.indexOf('<')>0)
			isoName = isoName.substring(0,isoName.indexOf('<'));
		return utils.ISO_ROOT_PACKAGE_NAME+"."+utils.isoDatatypeCompleteMap.get(isoName);
	}

	private String converteIsoPropertyToEntityName(String className, String attributeName)
	{
		return "_xxEntityxx_"+className.replace('.', '_')+"_"+attributeName;
	}
	
	/**
	 * Determines the table that the datatype is suppose to be persisted. 
	 * Determines the foreign key column, inverse column, primary key column, join table
	 *  
	 * @param rootNode
	 * @throws GenerationException 
	 */
	private void attachDataModelInformation(RootNode rootNode, UMLClass klass, UMLAttribute attr, UMLClass table) throws GenerationException
	{
		String selfTableName = null;
		String selfTableForeignKey = null;
		
		String targetTableName = null;
		String targetTablePrimaryKey = null;
		String targetTableForeignKey = null;
		
		String joinTableName = null;
		String joinTableForeignKey = null;
		String joinTableInverseKey = null;
		
		selfTableName = table.getName();
		targetTableName = utils.getTagValue(attr,utils.TV_MAPPED_COLLECTION_TABLE);
		if(targetTableName != null && !"".equals(targetTableName))
		{
			UMLClass assocTable = ModelUtil.findClass(model,utils.getBasePkgDataModel()+"."+targetTableName);
			if(assocTable == null) throw new GenerationException("No associated table found named : \""+targetTableName+"\"");
	
			String fqcn = utils.getFQCN(klass);
			String attrFQCN = fqcn + "." + attr.getName();
	
			joinTableName = utils.getTagValue(attr,utils.TV_CORRELATION_TABLE);
			if(joinTableName != null && !"".equals(joinTableName))
			{
				UMLClass joinTable = ModelUtil.findClass(model,utils.getBasePkgDataModel()+"."+joinTableName);
				if(joinTable == null) throw new GenerationException("No associated table found named : \""+targetTableName+"\"");
				
				joinTableForeignKey = utils.getColumnName(joinTable, utils.TV_ASSOC_COLUMN, attrFQCN, false, 1, 1);
				joinTableInverseKey = utils.getColumnName(joinTable, utils.TV_INVERSE_ASSOC_COLUMN, attrFQCN, false, 1, 1);
				targetTablePrimaryKey = utils.getColumnName(assocTable, utils.TV_MAPPED_ATTR_COLUMN, attrFQCN + ".id", false, 1, 1);
			}
			else
			{

				targetTablePrimaryKey = utils.getColumnName(assocTable, utils.TV_MAPPED_ATTR_COLUMN, attrFQCN + ".id", false, 0, 1);
				
				if(rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))
				{
					targetTableForeignKey = utils.getColumnName(assocTable, utils.TV_ASSOC_COLUMN, attrFQCN, false, 1, 1);
				}
				else
				{
					if(targetTablePrimaryKey!=null && !"".equals(targetTablePrimaryKey))
					{
						selfTableForeignKey = utils.getColumnName(table, utils.TV_ASSOC_COLUMN, attrFQCN, false, 1, 1);	
					}
					else
					{
						targetTableForeignKey = utils.getColumnName(assocTable, utils.TV_ASSOC_COLUMN, attrFQCN, false, 1, 1);
					}
				}
			}
		}

		rootNode.setSelfTableName(selfTableName);
		rootNode.setSelfTableForeignKey(selfTableForeignKey);
		
		rootNode.setTargetTableName(targetTableName);
		rootNode.setTargetTablePrimaryKey(targetTablePrimaryKey);
		rootNode.setTargetTableForeignKey(targetTableForeignKey);
		
		rootNode.setJoinTableName(joinTableName);
		rootNode.setJoinTableForeignKey(joinTableForeignKey);
		rootNode.setJoinTableInverseKey(joinTableInverseKey);
		
	}

	public ComplexNode getGlobalConstantNode(ComplexNode rootNode) throws GenerationException
	{
		String isoClassName = rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET")?utils.ISO_ROOT_PACKAGE_NAME+".DSET":rootNode.getIsoClassName();
		UMLClass datatypeClass = findISOClass(isoClassName);

		UMLClass currentKlass = datatypeClass;
		while(currentKlass != null)
		{
			for(UMLAttribute attribute:currentKlass.getAttributes())
			{
				String tagValuePrefix = utils.TV_MAPPED_ATTR_CONSTANT+":"+utils.getFQCN(currentKlass)+".";
				for(UMLTaggedValue tv: attribute.getTaggedValues())
				{
					if (tv.getName().startsWith(tagValuePrefix))
					{
						String tvName = tv.getName();
						parseAndAddNode(rootNode,tvName.substring(tagValuePrefix.length())
								,tv.getValue(),false,true);
					}
				}
				//Locate the child node and if it exist then proceed recursively
				for(Node node: rootNode.getInnerNodes())
				{
					if(node instanceof ComplexNode &&
							((node.getName().equals(attribute.getName())) ||
									((utils.ISO_ROOT_PACKAGE_NAME+".AD").equals(isoClassName) && node.getName().startsWith("part")) ||
									((isoClassName.startsWith(utils.ISO_ROOT_PACKAGE_NAME+".EN")) && node.getName().startsWith("part"))))
					{
						getGlobalConstantNode((ComplexNode)node);
					}
				}
			}
			currentKlass = utils.getSuperClass(currentKlass);
		}
		
		return null;
	}
	
	/**
	 * Main method to create the data structure for the graph by reading the mapped-attributes tag value 
	 *  
	 * @param klass
	 * @param attr
	 * @param table
	 * @return
	 * @throws GenerationException
	 */
	private RootNode createDatatypeNode(UMLClass klass, UMLAttribute attr, UMLClass table) throws GenerationException
	{
		String prefix = utils.getFQCN(klass) + "." + attr.getName() + ".";

		String assocTableName = utils.getTagValue(attr,utils.TV_MAPPED_COLLECTION_TABLE);
		UMLClass datatypeTable = assocTableName == null? table : utils.findCollectionTable(attr, model);
		
		RootNode rootNode = new RootNode(attr.getName());
		for(UMLAttribute column: datatypeTable.getAttributes())
		{
			for(UMLTaggedValue tv: column.getTaggedValues())
			{
				if (utils.TV_MAPPED_ATTR_COLUMN.equals(tv.getName()))
				{
					String tvValue = tv.getValue();
					String[] tvValues = tvValue.split(",");
					for(String val:tvValues)
					{
						if(val.startsWith(prefix) && !val.equals(prefix+"id"))
						{
							parseAndAddNode(rootNode,val.substring(prefix.length()),column.getName(),true,false);
						}
					}
				}
			}
		}
		
		UMLClass currentKlass = klass;
		Set<String> processedSuffix = new HashSet<String>(); 
		while(currentKlass!=null)
		{
			String currentPrefix = utils.getFQCN(currentKlass) + "." + attr.getName() + ".";
			String tagValuePrefix = utils.TV_MAPPED_ATTR_CONSTANT+":"+currentPrefix;
				
			for(UMLTaggedValue tv: attr.getTaggedValues())
			{
				if (tv.getName().startsWith(tagValuePrefix))
				{
					String tvName = tv.getName();
					String suffix = tvName.substring(tagValuePrefix.length());
					if(!processedSuffix.contains(suffix))
					{
						processedSuffix.add(suffix);
						parseAndAddNode(rootNode,suffix,tv.getValue(),false,false);
					}
				}
			}
			currentKlass = utils.getSuperClass(currentKlass);
		}
		return rootNode;
	}
	
	/**
	 * Method responsible for parsing the tag value and converting in the graph node
	 * 
	 * @param rootNode
	 * @param value
	 * @param nodeValue
	 * @throws GenerationException
	 */
	private void parseAndAddNode(ComplexNode rootNode, String value, String nodeValue, boolean isSimpleNode, boolean addGlobalConstantFlag) throws GenerationException
	{
		String[] nodePath = value.split("\\.");
	
		ComplexNode currentNode = rootNode;
		for(int i=0;i<nodePath.length -1; i++)
		{
			boolean createNewNode = true;
			for(Node node: currentNode.getInnerNodes())
			{
				if(node.getName().equals(nodePath[i]))
				{
					if(node instanceof SimpleNode || node instanceof ConstantNode)
						throw new GenerationException("Can not add "+rootNode.getName()+"."+value+". It is not defined as complex.");
					currentNode = (ComplexNode)node;
					createNewNode = false;
				}
			}
			if(createNewNode)
			{
				if(addGlobalConstantFlag)
					return;
				
				ComplexNode newNode = new ComplexNode(nodePath[i]);
				currentNode.addInnerNode(newNode);
				currentNode = newNode;
			}
		}

		boolean createNewNode = true;
		for(Node node: currentNode.getInnerNodes())
		{
			if(node.getName().equals(nodePath[nodePath.length-1]))
			{
				if(addGlobalConstantFlag)
				{
					createNewNode = false;
				}
				else
				{
					throw new GenerationException("Can not map "+rootNode.getName()+"."+value+" twice. It is already defined");
				}
			}
		}
		if(createNewNode)
		{
			if(isSimpleNode){
				SimpleNode newNode = new SimpleNode(nodePath[nodePath.length-1]);
				newNode.setColumnName(nodeValue);
				currentNode.addInnerNode(newNode);
			} else {
				ConstantNode newNode = new ConstantNode(nodePath[nodePath.length - 1]);
				newNode.setConstantValue(nodeValue);
				currentNode.addInnerNode(newNode);
			}
		}
	}

	
	/**
	 * Traverses the graph and attaches the property type to the graph nodes
	 * 
	 * @param parentNode
	 * @throws GenerationException
	 */
	private void traverseNodeAndAttachDataType(Node parentNode, UMLClass klass, UMLAttribute attribute) throws GenerationException
	{
		if(parentNode instanceof ComplexNode)
		{
			ComplexNode currentNode = (ComplexNode)parentNode;

			for(Node node: currentNode.getInnerNodes())
			{
				if(node.getIsoClassName() == null)
				{
					String datatype = findIsoDatatypeInNode(currentNode.getIsoClassName(), node.getName(), klass, attribute);
					node.setIsoClassName(datatype);
				}
				if(node instanceof ComplexNode)
					traverseNodeAndAttachDataType(node, klass, attribute);
				else if(!isEnum(node.getIsoClassName()) && !isValidIsoLeafLevelProperty(node.getIsoClassName()))
					throw new GenerationException("Can not map the ISO datatype as a Simple or Constant type :"+parentNode.getName()+"."+node.getName());
			}
		}
	}

	@SuppressWarnings("serial")
	private boolean isValidIsoLeafLevelProperty(String isoType)
	{
		if(isoType.startsWith(utils.ISO_ROOT_PACKAGE_NAME+".")) 
			isoType = isoType.substring((utils.ISO_ROOT_PACKAGE_NAME+".").length());
		
		Set<String> validTypes = new HashSet<String>(Arrays.asList(new String[] {"Uri","Uid","Code","String","Binary","Integer", "Real", "Boolean", "date", "ENXP_qualifier"}));
		
		return validTypes.contains(isoType);
	}

	/**
	 * Looks up the type of the attributeName in the isoClassName. Certain specific rules on the DSET, EN, and AD are followed
	 * 
	 * @param isoClassName
	 * @param attributeName
	 * @param attribute 
	 * @param klass 
	 * @return
	 * @throws GenerationException
	 */
	private String findIsoDatatypeInNode(String isoClassName, String attributeName, UMLClass klass, UMLAttribute attribute) throws GenerationException
	{
		if(!isoClassName.startsWith(utils.ISO_ROOT_PACKAGE_NAME+"."))
			throw new GenerationException("Can not process Non-ISO datatype "+isoClassName+". Discovered in "+utils.getFQCN(klass)+"."+attribute.getName());
		
		String returnVal = null;

		if(isoClassName.startsWith(utils.ISO_ROOT_PACKAGE_NAME+".EN") && !isoClassName.equals(utils.ISO_ROOT_PACKAGE_NAME+".ENXP") && !"nullFlavor".equals(attributeName))
		{
			returnVal = utils.ISO_ROOT_PACKAGE_NAME+".ENXP";
		}
		else if(isoClassName.equals(utils.ISO_ROOT_PACKAGE_NAME+".ENXP") && "qualifier".equals(attributeName))
		{
			returnVal = utils.ISO_ROOT_PACKAGE_NAME+".ENXP_qualifier";
		}
		else if(isoClassName.equals(utils.ISO_ROOT_PACKAGE_NAME+".AD") && !"nullFlavor".equals(attributeName))
		{
			
			String key = utils.TV_MAPPED_COLLECTION_ELEMENT_TYPE+":"+attributeName;
			String collectionElementTypeValue = utils.getTagValue(klass, attribute, key, null , false, 0, 1);
			if(collectionElementTypeValue == null)
				throw new GenerationException("Can not find type of AD collection item for "+attributeName +"in "+utils.getFQCN(klass)+"."+attribute.getName());
			
			UMLClass isoKlass = findISOClass(utils.ISO_ROOT_PACKAGE_NAME+"."+collectionElementTypeValue);
			if(isoKlass == null)
				throw new GenerationException("Can not find class "+collectionElementTypeValue);
			
			//TODO
			returnVal = utils.ISO_ROOT_PACKAGE_NAME+"."+collectionElementTypeValue;
		}
		else if(isoClassName.equals(utils.ISO_ROOT_PACKAGE_NAME+".TS") && "value".equals(attributeName))
		{
			returnVal = "date";
		}
		else
		{
			
			
			//For DSet elements
			if(isoClassName.indexOf('<')>0 && isoClassName.startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET")&& "item".equals(attributeName))
			{
				String typeClassName = isoClassName.substring(isoClassName.indexOf('<')+1,isoClassName.indexOf('>'));
				returnVal = utils.ISO_ROOT_PACKAGE_NAME+"."+typeClassName;
			}
			else
			{
				String tempIsoClassName = isoClassName; 
				if (isoClassName.indexOf('<')>0 && isoClassName.startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))
					tempIsoClassName = utils.ISO_ROOT_PACKAGE_NAME+".DSET";
				UMLClass isoKlass = findISOClass(tempIsoClassName);
				UMLClass currentKlass = isoKlass;
				UMLAttribute attr = null;
				while(currentKlass != null)
				{
					attr = currentKlass.getAttribute(attributeName);
					if(attr!=null)
						break;
					currentKlass = utils.getSuperClass(currentKlass);
				}
		
				if(attr == null)
					throw new GenerationException("Can not find attribute "+attributeName+ " in "+ isoClassName);
					
				UMLClass attrKlass = findISOClass(utils.ISO_ROOT_PACKAGE_NAME+"."+attr.getDatatype().getName());
				
				if(attrKlass != null)
				{
					returnVal = utils.ISO_ROOT_PACKAGE_NAME+"."+attr.getDatatype().getName();
				}
				else
				{
					returnVal = attr.getDatatype().getName();	
				}
			}
		}
		
		return returnVal;
	}

	
	/**
	 * Finds the ISO datatype class in the UML Model
	 * 
	 * @param isoType
	 * @return
	 * @throws GenerationException
	 */
	private UMLClass findISOClass(String isoType) throws GenerationException
	{
		UMLPackage pkg = ModelUtil.findPackage(model, utils.getBasePkgLogicalModel()+"."+utils.ISO_ROOT_PACKAGE_NAME);
		UMLClass klass = pkg.getClass(isoType.substring((utils.ISO_ROOT_PACKAGE_NAME+".").length()));
		return klass;
	}
	
	
	
	/**
	 * Prints the Node on the console
	 * 
	 * @param node
	 * @param prefix
	 */
	private void printNode(Node node, String prefix)
	{
		Node currentNode = node;
		if (currentNode != null)
		{
			System.out.println(prefix+node.getName()+":"+node.getIsoClassName());
			if(node instanceof RootNode)
			{
				RootNode rootNode = (RootNode)node;
				System.out.println(prefix+"{");
				System.out.println(prefix+"\tSelf Table:"+rootNode.getSelfTableName());
				System.out.println(prefix+"\tSelf Table FK:"+rootNode.getSelfTableForeignKey());
				if(((RootNode)node).getTargetTableName()!=null)
				{
					System.out.println(prefix+"\tTarget Table:"+rootNode.getTargetTableName());
					System.out.println(prefix+"\tTarget Table PK:"+rootNode.getTargetTablePrimaryKey());
					System.out.println(prefix+"\tTarget Table FK:"+rootNode.getTargetTableForeignKey());
				}
				if(((RootNode)node).getJoinTableName()!=null)
				{
					System.out.println(prefix+"\tJoin Table Name:"+rootNode.getJoinTableName());
					System.out.println(prefix+"\tJoin Table FK:"+rootNode.getJoinTableForeignKey());
					System.out.println(prefix+"\tJoin Table IK:"+rootNode.getJoinTableInverseKey());
				}

				String mappingStyle;
				if(rootNode.getTargetTableName() == null || "".equals(rootNode.getTargetTableName()))
				{
					mappingStyle = "component";
				}
				else
				{
					if(rootNode.getTargetTablePrimaryKey() == null || "".equals(rootNode.getTargetTablePrimaryKey()))
					{
						if(!rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))
							mappingStyle = "joined component";
						else
							mappingStyle = "component with set of composite-elements";

					}
					else
					{
						if(rootNode.getJoinTableName() == null || "".equals(rootNode.getJoinTableName()))
						{
							if(!rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))
								mappingStyle = "one-to-one";
							else
								mappingStyle = "component with set of one-to-many";
						}
						else
						{
							if(!rootNode.getIsoClassName().startsWith(utils.ISO_ROOT_PACKAGE_NAME+".DSET"))							
								mappingStyle = "joined many-to-one";
							else
								mappingStyle = "component with set of many-to-many";
						}
					}
				}
				System.out.println(prefix+"\tMapping Style:"+mappingStyle);
				System.out.println(prefix+"}");
			}
			if(node instanceof ComplexNode)
			{
				System.out.println(prefix+"[");
				for(Node innerNode: ((ComplexNode)currentNode).getInnerNodes())
					printNode(innerNode, prefix+"\t");
				System.out.println(prefix+"]");
			}
		}
	}
	
	
	/**
	 * Temporary method for testing purpose
	 * 
	 * @throws GenerationException
	 */
	private void executeTest() throws GenerationException
	{
		String fileName =  "CodegenConfig.xml";
		ObjectFactory.initialize(fileName);
		utils = (TransformerUtils)ObjectFactory.getObject("TransformerUtils");
		model = (UMLModel)ObjectFactory.getObject("UMLModel");

		UMLPackage pkg = ModelUtil.findPackage(model, utils.getBasePkgLogicalModel()+".gov.nih.nci.cacoresdk.iso21090.datatype");
		for(UMLClass klass:pkg.getClasses())
		{
			//UMLClass klass = ModelUtil.findClass(model, utils.getBasePkgLogicalModel()+".gov.nih.nci.cacoresdk.iso21090.datatype.EnDataType");
			try
			{
				UMLClass table = utils.getTable(klass);
			
				List<UMLAttribute> attributes = sortAttributesByJoin(klass, table);
				
				for(UMLAttribute attr: attributes)
				{
					RootNode rootNode = getDatatypeNode(klass, attr, table);
					printNode(rootNode, "");
					StringBuffer buffer = convertToHibernateComponent(rootNode,"\n");
					System.out.print(buffer);
				}
				for(UMLAttribute attr: attributes)
				{
					RootNode rootNode = getDatatypeNode(klass, attr, table);
					if(requiresSeperateClassMapping(rootNode))
					{
						StringBuffer buffer = convertToHibernateClass(rootNode,"\n");
						System.out.print(buffer);
					}
				}
			} 
			catch (Exception e)
			{
				System.out.println("Error causing class:"+utils.getFQCN(klass));
				e.printStackTrace();
			}
		}
	}

	/**
	 * Temporary method used for testing purpose only
	 * @param klass
	 * @param table
	 * @return
	 * @throws GenerationException
	 */
	private List<UMLAttribute> sortAttributesByJoin(UMLClass klass, UMLClass table) throws GenerationException
	{
		List<UMLAttribute> noJoinCollection = new ArrayList<UMLAttribute>();
		List<UMLAttribute> joinCollection = new ArrayList<UMLAttribute>();

		for(UMLAttribute attr: klass.getAttributes())
		{
			if(!attr.getName().equals("id"))
			{
				RootNode rootNode = getDatatypeNode(klass, attr, table);
				if(requiresJoin(rootNode))
					joinCollection.add(attr);
				else
					noJoinCollection.add(attr);
					
			}
		}
		
		noJoinCollection.addAll(joinCollection);
		return noJoinCollection;
	}
	
	public static void main(String args[]) throws GenerationException
	{
		IsoDatatypeTransformationHelper t = new IsoDatatypeTransformationHelper();
		t.executeTest();
	}
	
}