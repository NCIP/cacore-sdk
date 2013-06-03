/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.security.authentication.cagrid;


import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.syncgts.bean.SyncDescription;
import org.apache.axis.utils.XMLUtils;

import java.io.InputStream;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.apache.log4j.Logger;

public class SyncGTS
{
	protected static Logger log = Logger.getLogger(SyncGTS.class.getName());

	/*public SyncGTS(File file, Boolean reSync) throws Exception
	{

		try
		{
			SyncDescription description = (SyncDescription) Utils.deserializeDocument(file.getAbsolutePath(),SyncDescription.class);
			gov.nih.nci.cagrid.syncgts.core.SyncGTS instance = gov.nih.nci.cagrid.syncgts.core.SyncGTS.getInstance();
			if(reSync)
				instance.syncAndResyncInBackground(description, false);
			else
				instance.syncOnce(description);
		}
		catch (Exception e)
		{
			log.error(e);
			throw e;
		}
	}*/


	public SyncGTS(String file, Boolean reSync) throws Exception
	{

		try
		{
			System.out.println("File is: "+file);
			//ClassPathResource resource = new ClassPathResource(file);
			SyncDescription description = (SyncDescription) deserializeDocument(file,SyncDescription.class);
			gov.nih.nci.cagrid.syncgts.core.SyncGTS instance = gov.nih.nci.cagrid.syncgts.core.SyncGTS.getInstance();
			if(reSync)
				instance.syncAndResyncInBackground(description, false);
			else
				instance.syncOnce(description);
		}
		catch (Exception e)
		{
			log.error(e);
			throw e;
		}
	}

    private <T> T deserializeDocument(String fileName, Class<T> objectType) throws Exception {
        InputStream inputStream = FileLoader.getInstance().getFileAsStream(fileName);
        org.w3c.dom.Document doc = XMLUtils.newDocument(inputStream);
        Object obj = ObjectDeserializer.toObject(doc.getDocumentElement(), objectType);
        inputStream.close();
        return objectType.cast(obj);
    }

}