var print = function(_string)
{
	Packages.java.lang.System.out.println(_string);
}
var assert = function(_condition, _message)
{
	var message = (!_message) ? "Assertion failed" : _message;
	if (_condition !== true) { 	print(message); }
}

var testConvertFirstCharToUpperCase = function()
{
	var string = "person";
	var upperCaseString = Packages.gov.nih.nci.sdk.example.generator.util.GeneratorUtil.convertFirstCharToUpperCase(string);

	assert(("Person".equals(upperCaseString) === true), "convertFirstCharToUpperCase function failed");
	print("testConvertFirstCharToUpperCase completed");
}

var testConvertFirstCharToLowerCase = function()
{
	var string = "Person";
	var lowerCaseString = Packages.gov.nih.nci.sdk.example.generator.util.GeneratorUtil.convertFirstCharToLowerCase(string);

	assert(("person".equals(lowerCaseString) === true), "convertFirstCharToLowerCase function failed");
	print("testConvertFirstCharToLowerCase completed");
}

var testGetServiceClientPackageName = function()
{
	
	var focusDomain = "gov.nih.nci.cadsr.DataElement";
	var targetServiceClientPackage = "gov.nih.nci.cadsr." +
									 Packages.gov.nih.nci.sdk.example.generator.Generator.SERVICE_PACKAGE_NAME + "." +
									 Packages.gov.nih.nci.sdk.example.generator.Generator.SERVICE_CLIENT_PACKAGE_NAME;

	var serviceClientPackageName = Packages.gov.nih.nci.sdk.example.generator.util.GeneratorUtil.getServiceClientPackageName(focusDomain);
	assert((targetServiceClientPackage.equals(serviceClientPackageName) === true), "getServiceClientPackageName function failed");
	print("testGetServiceClientPackageName completed");
}

testConvertFirstCharToUpperCase();
testConvertFirstCharToLowerCase();
testGetServiceClientPackageName();