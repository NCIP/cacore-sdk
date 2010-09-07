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
    }
}
