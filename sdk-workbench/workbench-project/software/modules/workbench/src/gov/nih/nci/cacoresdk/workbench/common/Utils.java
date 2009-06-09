package gov.nih.nci.cacoresdk.workbench.common;

import java.io.InputStream;
import org.apache.axis.utils.XMLUtils;
import org.globus.wsrf.encoding.ObjectDeserializer;

public class Utils {

	public static Object deserializeDocument(String fileName, Class objectType)
	throws Exception {
		InputStream inputStream = null;

		inputStream = Utils.class.getResourceAsStream(fileName);
		org.w3c.dom.Document doc = XMLUtils.newDocument(inputStream);
		Object obj = ObjectDeserializer.toObject(doc.getDocumentElement(),
				objectType);
		inputStream.close();
		return obj;
	}

}
