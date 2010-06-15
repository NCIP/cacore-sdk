

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Gene 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   private java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   private java.lang.String title;
	   public  java.lang.String getTitle(){
	      return title;
	   }
	   
	   public void setTitle( java.lang.String title){
	      this.title = title;
	   }
	
	   
	   private java.lang.String symbol;
	   public  java.lang.String getSymbol(){
	      return symbol;
	   }
	   
	   public void setSymbol( java.lang.String symbol){
	      this.symbol = symbol;
	   }
	
	   
	   private java.lang.String locusLinkSummary;
	   public  java.lang.String getLocusLinkSummary(){
	      return locusLinkSummary;
	   }
	   
	   public void setLocusLinkSummary( java.lang.String locusLinkSummary){
	      this.locusLinkSummary = locusLinkSummary;
	   }
	
	   
	   private java.lang.String OMIMID;
	   public  java.lang.String getOMIMID(){
	      return OMIMID;
	   }
	   
	   public void setOMIMID( java.lang.String OMIMID){
	      this.OMIMID = OMIMID;
	   }
	
	   
	   private java.lang.String locusLinkId;
	   public  java.lang.String getLocusLinkId(){
	      return locusLinkId;
	   }
	   
	   public void setLocusLinkId( java.lang.String locusLinkId){
	      this.locusLinkId = locusLinkId;
	   }
	
	   
	   private java.lang.Long clusterId;
	   public  java.lang.Long getClusterId(){
	      return clusterId;
	   }
	   
	   public void setClusterId( java.lang.Long clusterId){
	      this.clusterId = clusterId;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection libraryCollection = new java.util.HashSet();
			public java.util.Collection getLibraryCollection(){
	              return libraryCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setLibraryCollection(java.util.Collection libraryCollection){
	   		this.libraryCollection = libraryCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Chromosome chromosome;
			public gov.nih.nci.cabio.domain.ws.Chromosome getChromosome(){
			  return chromosome;
                        }
		   
	      
	               
	   
	   
	   
	   public void setChromosome(gov.nih.nci.cabio.domain.ws.Chromosome chromosome){
		this.chromosome = chromosome;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Taxon taxon;
			public gov.nih.nci.cabio.domain.ws.Taxon getTaxon(){
			  return taxon;
                        }
		   
	      
	               
	   
	   
	   
	   public void setTaxon(gov.nih.nci.cabio.domain.ws.Taxon taxon){
		this.taxon = taxon;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection sequenceCollection = new java.util.HashSet();
			public java.util.Collection getSequenceCollection(){
	              return sequenceCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setSequenceCollection(java.util.Collection sequenceCollection){
	   		this.sequenceCollection = sequenceCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection targetCollection = new java.util.HashSet();
			public java.util.Collection getTargetCollection(){
	              return targetCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setTargetCollection(java.util.Collection targetCollection){
	   		this.targetCollection = targetCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Gene) {
				Gene c =(Gene)obj; 			 
				Long thisId = getId();		
				
					if(thisId != null && thisId.equals(c.getId())) {
					   eq = true;
				    }		
				
			}
			return eq;
		}
		
		public int hashCode(){
			int h = 0;
			
			if(getId() != null) {
				h += getId().hashCode();
			}
			
			return h;
	}
	
	
}
