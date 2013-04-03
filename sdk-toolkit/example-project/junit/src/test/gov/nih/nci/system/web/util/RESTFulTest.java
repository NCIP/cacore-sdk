package test.gov.nih.nci.system.web.util;

import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.web.SDKQuery;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class RESTFulTest {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		
		String cql = "<ns1:CQLQuery xmlns:ns1=\"http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery\">" +
		"<ns1:Target name=\"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher\">" +
		"</ns1:Target>" +
		"</ns1:CQLQuery>";
		
		WebResource service = client.resource(getBaseURI());
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("cql", cql);
		
		PrivateTeacher searchObject = new PrivateTeacher();
		java.util.List list = new java.util.ArrayList(1);
		list.add(searchObject);
		SDKQuery query = new SDKQuery("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher", list);
		
		HQLCriteria hqlCriteria = new HQLCriteria(
		"from gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");

		service.path("RESTful").queryParam("cql", cql).type(MediaType.APPLICATION_XML).post(query);

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:29080/example/cxf").build();
	}

}