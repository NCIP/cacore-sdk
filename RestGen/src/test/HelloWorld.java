package test;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.company.auth.bean.Employee;
import com.company.auth.service.AuthService;

public class HelloWorldClient {

    private HelloWorldClient() {
    } 

    public static void main(String args[]) throws Exception {

    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

    	factory.getInInterceptors().add(new LoggingInInterceptor());
    	factory.getOutInterceptors().add(new LoggingOutInterceptor());
    	factory.setServiceClass(AuthService.class);
    	factory.setAddress("http://localhost:7001/authManager/services/cxfAuth");
    	AuthService client = (AuthService) factory.create();

    	Employee employee = client.getEmployee("0223938");
    	//System.out.println("Server said: " + employee.getLastName() + ", " + employee.getFirstName());
    	System.exit(0);

    }

}