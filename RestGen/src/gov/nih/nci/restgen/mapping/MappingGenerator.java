package gov.nih.nci.restgen.mapping;

import gov.nih.nci.restgen.codegen.GeneratorException;
import gov.nih.nci.restgen.mapping.model.Mapping;

import java.io.StringReader;

public class MappingGenerator {
	public static Mapping fromXML(String mappingXML) throws GeneratorException
	{
		JAXBUnmarshaller unmarshaller = new JAXBUnmarshaller(true,
				"gov.nih.nci.restgen.mapping.model");
		StringReader reader = new StringReader(mappingXML);
		try {
			return (Mapping) unmarshaller.fromXML(reader);
		} catch (XMLUtilityException e) {
			e.printStackTrace();
			throw new GeneratorException("Failed to read mapping file: ", e);
		}
	}
	
	public static String toXML(Mapping mapping) throws GeneratorException
	{
		JAXBMarshaller marshaller = new JAXBMarshaller(true,
				"gov.nih.nci.restgen.mapping.model");
		try {
			return marshaller.toXML(mapping);
		} catch (XMLUtilityException e) {
			e.printStackTrace();
			throw new GeneratorException("Failed to read mapping file: ", e);
		}
	}
}
