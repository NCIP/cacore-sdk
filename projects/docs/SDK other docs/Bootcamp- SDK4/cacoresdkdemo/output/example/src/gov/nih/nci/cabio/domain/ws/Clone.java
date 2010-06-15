

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Clone 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   protected java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public java.lang.Boolean verified;
	   public  java.lang.Boolean getVerified(){
	      return verified;
	   }
	   
	   public void setVerified( java.lang.Boolean verified){
	      this.verified = verified;
	   }
	
	   
	   private java.lang.Long insertSize;
	   public  java.lang.Long getInsertSize(){
	      return insertSize;
	   }
	   
	   public void setInsertSize( java.lang.Long insertSize){
	      this.insertSize = insertSize;
	   }
	
	   
	   private java.lang.String accessionNumber;
	   public  java.lang.String getAccessionNumber(){
	      return accessionNumber;
	   }
	   
	   public void setAccessionNumber( java.lang.String accessionNumber){
	      this.accessionNumber = accessionNumber;
	   }
	
	   
	   private java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	
	   
	   private java.lang.String version;
	   public  java.lang.String getVersion(){
	      return version;
	   }
	   
	   public void setVersion( java.lang.String version){
	      this.version = version;
	   }
	
	   
	   private java.lang.String strain;
	   public  java.lang.String getStrain(){
	      return strain;
	   }
	   
	   public void setStrain( java.lang.String strain){
	      this.strain = strain;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Library library;
			public gov.nih.nci.cabio.domain.ws.Library getLibrary(){
			  return library;
                        }
		   
	      
	               
	   
	   
	   
	   public void setLibrary(gov.nih.nci.cabio.domain.ws.Library library){
		this.library = library;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection sequenceCollection = new java.util.HashSet();
			public java.util.Collection getSequenceCollection(){
	              return sequenceCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setSequenceCollection(java.util.Collection sequenceCollection){
	   		this.sequenceCollection = sequenceCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Clone) {
				Clone c =(Clone)obj; 			 
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
