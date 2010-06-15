import gov.nih.nci.cabio.domain.Gene;
import gov.nih.nci.cabio.domain.impl.GeneImpl;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;

import java.util.Iterator;
import java.util.List;


public class TestClient {

    public static void main(String[] args) {

		System.out.println("*** TestClient...");

		try{

			ApplicationService appService = ApplicationServiceProvider.getApplicationService();

			try {

				System.out.println("Retrieving all genes based on symbol=IL*");
				Gene gene = new GeneImpl();
				gene.setSymbol("IL*");

				try {
					List resultList = appService.search(Gene.class, gene);
					
					for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
						Gene returnedGene = (Gene) resultsIterator.next();
						System.out.println(
							"Symbol: " + returnedGene.getSymbol() + "\n" +
							"\tTaxon:" + returnedGene.getTaxon().getScientificName() + "\n" +
							"\tName " + returnedGene.getTitle() + "\n" +
							"");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (RuntimeException e2) {
				e2.printStackTrace();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Test client throws Exception = "+ ex);
		}

	}
}
