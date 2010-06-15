import gov.nih.nci.cabio.domain.Chromosome;
import gov.nih.nci.cabio.domain.Taxon;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;

import java.util.Iterator;
import java.util.List;

public class TestWritableAPIClient {

    public static void main(String[] args) {

		System.out.println("*** Test Writable API Client...");

		try{

			//ApplicationService appService = ApplicationServiceProvider.getRemoteInstance("http://localhost:8080/example/server/HTTPServer");
			ApplicationService appService = ApplicationServiceProvider.getApplicationService();
	        
			try {

				try {
					List resultList = null;
					
					System.out.println("Creating Chromosome with Id=103\n");
			        Chromosome chromosome = new Chromosome() ;
			        chromosome.setId(new java.lang.Long("103"));
			        chromosome.setName("Test Chromosome");
			        chromosome.setGeneCollection(new java.util.HashSet());
			        
			        System.out.println("Associating Chromosome with Taxon Id=5\n");
			        Taxon taxon = new Taxon();
			        taxon.setId(new java.lang.Long("5"));
		
			        chromosome.setTaxon(taxon);
			        
			        System.out.println("Persisting Chromosome with Taxon Id=5 to the database\n");
			        appService.createObject(chromosome);
			        
			        System.out.println("Reading Chromosome with Taxon Id=5 from the database\n");
					resultList = appService.search(Chromosome.class, chromosome);
					
					Chromosome returnedChromosome = null;
					for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
						returnedChromosome = (Chromosome) resultsIterator.next();
						System.out.println(
							"Chromosome Id: " + returnedChromosome.getId() + "\n" +
							"\tTaxon Id: " + returnedChromosome.getTaxon().getId() + "\n" +
							"\tName: " + returnedChromosome.getName() + "\n" +
							"");
					}			
					
					System.out.println("Updating Chromosome with Id=103\n");
					returnedChromosome.setName("New name");
					System.out.println("Testing getGeneCollection() and getTaxon() invocations for Chromosome with Id=103\n");
					returnedChromosome.getGeneCollection();
					returnedChromosome.getTaxon();
					
					System.out.println("Persisting updated Chromosome with Taxon Id=5 to the database\n");
					appService.updateObject(returnedChromosome);
					
					System.out.println("Reading updated Chromosome with Taxon Id=5 from the database\n");
					Chromosome updatedChromosome = new Chromosome() ;
			        updatedChromosome.setId(new java.lang.Long("103"));
			        
					resultList = appService.search(Chromosome.class, updatedChromosome);					
					
					for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
						returnedChromosome = (Chromosome) resultsIterator.next();
						System.out.println(
							"Updated Chromo Id: " + returnedChromosome.getId() + "\n" +
							"\tUpdated Taxon Id: " + returnedChromosome.getTaxon().getId() + "\n" +
							"\tUpdated Name: " + returnedChromosome.getName() + "\n" +
							"");
					}	
					
					System.out.println("Deleting Chromosome with Taxon Id=5 from the database\n");
					appService.removeObject(returnedChromosome);
					
					System.out.println("Writable API tests successfully completed\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (RuntimeException e2) {
				e2.printStackTrace();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Test Writable API client throws Exception = "+ ex);
		}

	}
}
