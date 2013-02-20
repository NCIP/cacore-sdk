package test.gov.nih.nci.restgen.client;

import gov.nih.nci.ejb.Book;
import gov.nih.nci.ejb.Catalog;
import gov.nih.nci.ejb.CatalogHome;

import java.util.*;
import javax.naming.InitialContext;
import javax.naming.Context;

public class BookEJBClient {
  public static void main(String z[]) {

    try {
      int choice = 1;
      Properties p = new Properties();
      p.put(Context.INITIAL_CONTEXT_FACTORY,
          "org.jnp.interfaces.NamingContextFactory");
      p.put(Context.URL_PKG_PREFIXES,
          "org.jboss.naming.jnp.interfaces");
      p.put(Context.PROVIDER_URL, "localhost:22099");
      InitialContext ctx = new InitialContext(p);

      
      Object ref = ctx.lookup("ejb/CatalogRemoteHome");
      CatalogHome ejbHome = null;
      if (java.rmi.Remote.class.isAssignableFrom(gov.nih.nci.ejb.CatalogHome.class))
      {
    	  ejbHome = (gov.nih.nci.ejb.CatalogHome)javax.rmi.PortableRemoteObject.narrow(ref,
                                       gov.nih.nci.ejb.CatalogHome.class);
      }
      Catalog ejbRemote = ejbHome.create();  
	  ejbRemote.getBooks();
      
      CatalogHome home = (CatalogHome) ctx.lookup("CatalogRemoteHome");

      Catalog bean = home.create();
      Scanner sc = new Scanner(System.in);
      while (choice != 2) {
        String bname;
        String bid;
        System.out.println("Enter choice 1. Add 2. Over");
        choice = sc.nextInt();
        if (choice == 1) {
          System.out.println("Enter book name");
          bname = sc.next();
          System.out.println("Enter book id");
          bid = sc.next();
          
          Book book = new Book();
          book.setId(bid);
          book.setTitle(bname);
          bean.addBook(book);
        } else if (choice == 2) {
          break;
        }
      }

      List v = bean.getBooks();

      System.out.println("Size of vector \t" + v.size());
      for (int i = 0; i < v.size(); ++i) {
        System.out.println((Book) v.get(i));
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}