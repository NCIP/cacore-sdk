importClass(Packages.java.util.HashMap);
importClass(Packages.com.google.gson.TypeInfoMap);

var map = new HashMap();

importClass(Packages.org.eclipse.emf.ecore.ETypedElement);

var ecoreFactory = Packages.org.eclipse.emf.ecore.EcoreFactory.eINSTANCE;
var ecorePackage = Packages.org.eclipse.emf.ecore.EcorePackage.eINSTANCE;

// create an Company class
var companyClass = ecoreFactory.createEClass();
companyClass.setName("Company");

// create company name
var companyName = ecoreFactory.createEAttribute();
companyName.setName("name");
companyName.setEType(ecorePackage.getEString());
companyClass.getEStructuralFeatures().add(companyName);

//create an Employee class
var employeeClass = ecoreFactory.createEClass();
employeeClass.setName("Employee");

//add a name attribute to an Employee class
var employeeName = ecoreFactory.createEAttribute();
employeeName.setName("name");
employeeName.setEType(ecorePackage.getEString());
employeeClass.getEStructuralFeatures().add(employeeName);

//create a Department class
var departmentClass = ecoreFactory.createEClass();
departmentClass.setName("Department");

//add department identification number
var departmentNumber = ecoreFactory.createEAttribute();
departmentNumber.setName("number");
departmentNumber.setEType(ecorePackage.getEInt());
departmentClass.getEStructuralFeatures().add(departmentNumber);

//department class can contain reference to one or many employees
var departmentEmployees = ecoreFactory.createEReference();
departmentEmployees.setName("employees");
departmentEmployees.setEType(employeeClass);

// specify that it could be one or more employees
departmentEmployees.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
departmentEmployees.setContainment(true);
departmentClass.getEStructuralFeatures().add(departmentEmployees);

// company can contain reference to one or more departments
var companyDepartments = ecoreFactory.createEReference();
companyDepartments.setName("department");
companyDepartments.setEType(departmentClass);
companyDepartments.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
companyDepartments.setContainment(true);
companyClass.getEStructuralFeatures().add(companyDepartments);

//create a package that represents company
var companyPackage = ecoreFactory.createEPackage();
companyPackage.setName("company");
companyPackage.setNsPrefix("company");
companyPackage.setNsURI("http:///com.example.company.ecore");
companyPackage.getEClassifiers().add(employeeClass);
companyPackage.getEClassifiers().add(departmentClass);
companyPackage.getEClassifiers().add(companyClass);

// create resource set and resource 
var resourceSet = new Packages.org.eclipse.emf.ecore.resource.impl.ResourceSetImpl();
var xmiResourceFactory = new Packages.org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl();

// Register XML resource factory
resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", xmiResourceFactory);

var resource = resourceSet.createResource(Packages.org.eclipse.emf.common.util.URI.createFileURI("/home/bediako/work/lucidlabs/company.xmi"));
resource.getContents().add(companyClass);
resource.getContents().add(departmentClass);
resource.getContents().add(employeeClass);
resource.save(null);