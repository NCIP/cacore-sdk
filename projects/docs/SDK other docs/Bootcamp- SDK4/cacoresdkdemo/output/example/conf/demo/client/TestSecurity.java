import gov.nih.nci.system.applicationservice.*;
import java.util.*;

import gov.nih.nci.cabio.domain.*;
import gov.nih.nci.cabio.domain.impl.*;
import gov.nih.nci.common.util.*;
import gov.nih.nci.system.comm.client.*;
import org.hibernate.criterion.*;




/**
	* TestClient.java demonstrates various ways to execute searches with and without
    * using Application Service Layer (convenience layer that abstracts building criteria
    * Uncomment different scenarios below to demonstrate the various types of searches
*/

public class TestSecurity {

    public static void main(String[] args) {

		System.out.println("*** Test Secured Client...");

		try{

			ClientSession session = ClientSession.getInstance();
			session.startSession("userId", "password");
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
