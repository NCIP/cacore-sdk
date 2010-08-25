package $packageName$;

$importSt;separator=";\n"$

import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class $clientName$ {

    public $clientName$() {
    }

    public static void main(String args[]) throws Exception {
         ClassPathXmlApplicationContext context 
            = new ClassPathXmlApplicationContext(new String[] {"$packagePath$client-beans.xml"});

        $clientName$ client = ($clientName$) context.getBean("$clientName$");
/*		Person person = new Person();
		person.setName("Test User");
		person.setaddress1("2115 E.Jefferson St");
		person.setaddress2("Rockville, MD 20850");

        client.addPerson(person);
        String message = (orderID == null) ? "Order not approved" : "Order approved; order ID is " + orderID;
		System.out.println(message);
  */          
    }
}
