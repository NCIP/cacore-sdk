

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Taxon 
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
	
	   
	   private java.lang.String scientificName;
	   public  java.lang.String getScientificName(){
	      return scientificName;
	   }
	   
	   public void setScientificName( java.lang.String scientificName){
	      this.scientificName = scientificName;
	   }
	
	   
	   private java.lang.String ethnicityOrStrain;
	   public  java.lang.String getEthnicityOrStrain(){
	      return ethnicityOrStrain;
	   }
	   
	   public void setEthnicityOrStrain( java.lang.String ethnicityOrStrain){
	      this.ethnicityOrStrain = ethnicityOrStrain;
	   }
	
	   
	   private java.lang.String abbreviation;
	   public  java.lang.String getAbbreviation(){
	      return abbreviation;
	   }
	   
	   public void setAbbreviation( java.lang.String abbreviation){
	      this.abbreviation = abbreviation;
	   }
	
	   
	   private java.lang.String commonName;
	   public  java.lang.String getCommonName(){
	      return commonName;
	   }
	   
	   public void setCommonName( java.lang.String commonName){
	      this.commonName = commonName;
	   }
	
	   
	   private java.lang.Boolean isPreferred;
	   public  java.lang.Boolean getIsPreferred(){
	      return isPreferred;
	   }
	   
	   public void setIsPreferred( java.lang.Boolean isPreferred){
	      this.isPreferred = isPreferred;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection geneCollection = new java.util.HashSet();
			public java.util.Collection getGeneCollection(){
	              return geneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneCollection(java.util.Collection geneCollection){
	   		this.geneCollection = geneCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection chromosomeCollection = new java.util.HashSet();
			public java.util.Collection getChromosomeCollection(){
	              return chromosomeCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setChromosomeCollection(java.util.Collection chromosomeCollection){
	   		this.chromosomeCollection = chromosomeCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Taxon) {
				Taxon c =(Taxon)obj; 			 
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
