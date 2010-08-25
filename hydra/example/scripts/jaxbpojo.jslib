importPackage(Packages.com.nih.nci.sdk.example.generator);
importPackage(Packages.com.nih.nci.sdk.core.generator);

function generateJaxbPojo(context)
{
	var pojoGenerator = new JAXBPojoGenerator(context);
	pojoGenerator.generate();
}