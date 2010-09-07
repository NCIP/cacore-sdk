var print = function(_string)
{
	Packages.java.lang.System.out.println(_string);
}
var assert = function(_condition, _message)
{
	var message = (!_message) ? "Assertion failed" : _message;
	if (_condition !== true) { 	print(message); }
}

var createGeneratorContext = function(_generatorDirectory, _targetDirectory, _properties, _ePackage, _domainSet)
{
	var generatorDirectory = (!_generatorDirectory) ? "workspace/sdkexample" : _generatorDirectory;
	var file = new Packages.java.io.File(generatorDirectory);
	var generatorBase = file.toURI();

	targetDirectory = (!_targetDirectory) ? "workspace/src" : _targetDirectory;
	file = new Packages.java.io.File(targetDirectory);
	var targetBase = file.toURI();

	var domainSet = (!_domainSet) ? new Packages.java.util.HashSet() : _domainSet;
	var properties = (!_properties) ? null : _properties;
	var ePackage = (!_ePackage) ? null : _ePackage;

	var logger = Packages.java.util.logging.Logger.getLogger(_generatorDirectory);

	return new Packages.gov.nih.nci.sdk.core.GeneratorContext(generatorBase, targetBase, properties, ePackage, domainSet, logger);
}

var addAnnotation = function(_modelElement, _name, _value)
{
	var ecoreFactory = Packages.org.eclipse.emf.ecore.EcoreFactory.eINSTANCE;
	var annotation = ecoreFactory.createEAnnotation();

	annotation.setSource(_name);
	annotation.setEModelElement(_modelElement);
	annotation.getDetails().put(_name, _value);

	return annotation;
}

var createEPackage = function()
{
	importClass(Packages.org.eclipse.emf.ecore.ETypedElement);

	var ecoreFactory = Packages.org.eclipse.emf.ecore.EcoreFactory.eINSTANCE;
	var ecorePackage = Packages.org.eclipse.emf.ecore.EcorePackage.eINSTANCE;

	// create an Company class
	var companyClass = ecoreFactory.createEClass();
	companyClass.setName("company.Company");

	// create company name
	var companyName = ecoreFactory.createEAttribute();
	companyName.setName("name");
	companyName.setEType(ecorePackage.getEString());
	companyClass.getEStructuralFeatures().add(companyName);

	//create an Employee class
	var employeeClass = ecoreFactory.createEClass();
	employeeClass.setName("company.Employee");

	//add a name attribute to an Employee class
	var employeeName = ecoreFactory.createEAttribute();
	employeeName.setName("name");
	employeeName.setEType(ecorePackage.getEString());
	employeeClass.getEStructuralFeatures().add(employeeName);

	//create a Department class
	var departmentClass = ecoreFactory.createEClass();
	departmentClass.setName("company.Department");

	//add department identification number
	var departmentNumber = ecoreFactory.createEAttribute();
	departmentNumber.setName("number");
	departmentNumber.setEType(ecorePackage.getEInt());
	departmentClass.getEStructuralFeatures().add(departmentNumber);

	//add department identification number
	var fireDepartment = ecoreFactory.createEOperation();
	fireDepartment.setName("fireDepartment");
	fireDepartment.setEType(ecorePackage.getEInt()); //number of people fired in department
	addAnnotation(fireDepartment, "oper.ser.service", "true");
	//fireDepartment.getEParameters().add(departmentNumber);
	departmentClass.getEOperations().add(fireDepartment);
	
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

	return companyPackage;
}

var createProperties = function()
{
	var properties = new Packages.java.util.Properties();

	properties.setProperty("PROJECT_ROOT", "./workspace");
	properties.setProperty("PROJECT_SRC", "src");
	properties.setProperty("JAVA_HOME", "c:\\Program Files\\Java\\jdk1.6.0_18");
	properties.setProperty("CXF_HOME", "C:\\apps\\cxf\\apache-cxf-2.2.10");
	
	//TODO set these properties when you get a chance ...
	properties.setProperty("CATALINA_ADDRESS", "localhost");
	properties.setProperty("CATALINA_PORT", "8080");
	properties.setProperty("APPLICATION_NAME", "SDK Example");
	
	return properties;
}

var testCompile = function()
{
	var ePackage = createEPackage();
	var properties = createProperties();

	var generator = new Packages.gov.nih.nci.sdk.core.Generator();

	var domainSet = new Packages.java.util.HashSet();
	domainSet.add("company.Company");
	domainSet.add("company.Department");

	var generatorContext = createGeneratorContext("workspace/sdkexample", "workspace/src", properties, ePackage, domainSet);

	generator.compile(generatorContext);

	print("Errors?: " + generatorContext.hasErrors());
	print("Errors: " + generatorContext.getErrorManager().getMessageList());

	print("testCompile test completed");	
}

testCompile();