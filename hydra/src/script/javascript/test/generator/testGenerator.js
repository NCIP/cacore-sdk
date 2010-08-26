var print = function(_string)
{
	Packages.java.lang.System.out.println(_string);
}
var assert = function(_condition, _message)
{
	var message = (!_message) ? "Assertion failed" : _message;
	if (_condition !== true) { 	print(message); }
}

var createGeneratorContext = function(_directory)
{
	var directory = (!_directory) ? "workspace" : _directory;
	var file = new Packages.java.io.File(directory);
	var uri = file.toURI();
	return new Packages.gov.nih.nci.sdk.core.GeneratorContext(uri, null, new Packages.java.util.HashSet());
}

var createEPackage = function()
{
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

	return companyPackage;
}

var determineFileExtensionTest = function()
{
	var generator = new Packages.gov.nih.nci.sdk.core.Generator();
	var extension = generator.determineFileExtension("C:\work\hydra\.script.js");
	assert(("js".equalsIgnoreCase(extension) === true), "File extension test failed");
	Packages.java.lang.System.out.println("determineFileExtensionTest test completed");
}

var determineGeneratorScriptsTest = function()
{
	var script = "./workspace/testScript.js";
	var scriptFile = new Packages.java.io.File(script);
	var fileWriter = new Packages.java.io.FileWriter(scriptFile);
	fileWriter.write("Packages.java.lang.System.out.println(\"Found this script: \"  + SCRIPT_CONTEXT.getScript());\n");
	fileWriter.write("SCRIPT_CONTEXT.getMemory().put(\"author\", \"Bediako\");");
	fileWriter.flush();
	fileWriter.close();

	var generator = new Packages.gov.nih.nci.sdk.core.Generator();
	var generatorContext = createGeneratorContext();
	
	var fileList = generator.determineGeneratorScripts(generatorContext);

	for (var fileName in Iterator(fileList))
	{
		if (fileName.endsWith("testScript.js") === true)
		{
			var foundFile = true;
		}
	}
	
	assert((foundFile === true), "determineGeneratorScriptsTest failed to find this file");
	Packages.java.lang.System.out.println("determineGeneratorScriptsTest test completed");
}

var determineScriptContextTest = function()
{
	var generator = new Packages.gov.nih.nci.sdk.core.Generator();
	var generatorContext = createGeneratorContext();

	var scriptContext1 = generator.determineScriptContext("myscript.js", "", generatorContext);
	var scriptContext2 = generator.determineScriptContext("myscript.js", "", generatorContext);

	assert((scriptContext1 === scriptContext2), "Generator is not managing script contexts as expected");
	Packages.java.lang.System.out.println("determineScriptContextTest test completed");
}

var executeScriptTest = function()
{
	var script = "./workspace/testScript.js";
	var scriptFile = new Packages.java.io.File(script);
	var fileWriter = new Packages.java.io.FileWriter(scriptFile);
	fileWriter.write("SCRIPT_CONTEXT.getMemory().put(\"author\", \"Bediako\");");
	fileWriter.flush();
	fileWriter.close();

	var generator = new Packages.gov.nih.nci.sdk.core.Generator();
	var generatorContext = createGeneratorContext();
	var scriptContext = new Packages.gov.nih.nci.sdk.core.ScriptContext(script, generatorContext.getEPackage(), generatorContext.getMemory());

	generator.executeScript(script, scriptContext);

	assert((scriptContext.getMemory().containsKey("author") === true), "Script execution apparently failed");
	scriptFile["delete"]();
	Packages.java.lang.System.out.println("executeScriptTest test completed");
}

var conductTest = function()
{
	var ePackage = createEPackage();

	var script = "workspace/testScript.js";
	var scriptFile = new Packages.java.io.File(script);
	var fileWriter = new Packages.java.io.FileWriter(scriptFile);
	fileWriter.write("var ePackage = SCRIPT_CONTEXT.getEPackage();\n");
	fileWriter.write("SCRIPT_CONTEXT.getMemory().put(SCRIPT_CONTEXT.getFocusDomain(), \"found this one\");");
	fileWriter.flush();
	fileWriter.close();
	
	var generator = new Packages.gov.nih.nci.sdk.core.Generator();
	var generatorContext = createGeneratorContext();

	var domainSet = new Packages.java.util.HashSet();
	domainSet.add("Department");
	
	generatorContext.setEPackage(ePackage);
	generatorContext.setDomainSet(domainSet);

	generator.conduct(generatorContext);
	var scriptContext = generator.determineScriptContext(scriptFile.getAbsolutePath(), "", generatorContext)
	
	assert((scriptContext.getMemory().keySet().containsAll(domainSet) === true), "Method conductTest not completed successfully");
	
	scriptFile["delete"]();
	Packages.java.lang.System.out.println("conductTest test completed");	
}

//Execute Tests

determineFileExtensionTest();
determineGeneratorScriptsTest();
determineScriptContextTest();
executeScriptTest();
conductTest();