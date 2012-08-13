        
package test.gov.nih.nci.cacoresdk.domain.operations;

import gov.nih.nci.cacoresdk.domain.operations.AbstractDomainObject;

import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileWriter;
import java.io.File;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.FileEntity;

import org.apache.cxf.common.util.Base64Utility;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKRESTfulTestBase;
import gov.nih.nci.system.applicationservice.ApplicationException;

public class AbstractDomainObjectResourceTest extends SDKRESTfulTestBase
{
	public static String getTestCaseName()
	{
		return "AbstractDomainObject  RESTful Resource Test Case";
	}

public void testNothing()
{
	assertTrue(Boolean.TRUE);
}
}