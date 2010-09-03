importPackage(Packages.gov.nih.nci.sdk.example.generator);
importPackage(Packages.gov.nih.nci.sdk.core.generator);

function generate(_scriptContext)
{
	generateJAXBPojo(_scriptContext);
	generatePojo(_scriptContext);
	generateWebserviceInterface(_scriptContext);
	//generateSpringConf(_scriptContext);
}

function generateJAXBPojo(_scriptContext)
{
	var pojoGenerator = new JAXBPojoGenerator(_scriptContext);
	pojoGenerator.generate();
}

function generatePojo(_scriptContext)
{
	var pojoGenerator = new PojoGenerator(_scriptContext);
	pojoGenerator.generate();
}

function generateWebserviceInterface(_scriptContext)
{
	var interfaceGenerator = new WebServiceGenerator(_scriptContext);
	interfaceGenerator.generate();
}

function generateSpringConf(_scriptContext)
{
	var interfaceGenerator = new CXFSpringConfGenerator(_scriptContext);
	interfaceGenerator.generate();
}

generate(SCRIPT_CONTEXT);