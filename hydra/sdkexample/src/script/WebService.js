importPackage(Packages.gov.nih.nci.sdk.example.generator);
importPackage(Packages.gov.nih.nci.sdk.core.generator);

function generate(context)
{
	generateJAXBPojo(context);
	generatePojo(context);
	generateWebserviceInterface(context);
	generateSpringConf(context);
}

function generateJAXBPojo(context)
{
	var pojoGenerator = new JAXBPojoGenerator(context);
	pojoGenerator.generate();
}

function generatePojo(context)
{
	var pojoGenerator = new PojoGenerator(context);
	pojoGenerator.generate();
}

function generateWebserviceInterface(context)
{
	var interfaceGenerator = new WebServiceGenerator(context);
	interfaceGenerator.generate();
}

function generateSpringConf(context)
{
	var interfaceGenerator = new CXFSpringConfGenerator(context);
	interfaceGenerator.generate();
}