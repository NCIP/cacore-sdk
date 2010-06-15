

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Sequence 
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
	
	   
	   private java.lang.Long length;
	   public  java.lang.Long getLength(){
	      return length;
	   }
	   
	   public void setLength( java.lang.Long length){
	      this.length = length;
	   }
	
	   
	   private java.lang.Boolean isReferenceSequence;
	   public  java.lang.Boolean getIsReferenceSequence(){
	      return isReferenceSequence;
	   }
	   
	   public void setIsReferenceSequence( java.lang.Boolean isReferenceSequence){
	      this.isReferenceSequence = isReferenceSequence;
	   }
	
	   
	   private java.lang.String description;
	   public  java.lang.String getDescription(){
	      return description;
	   }
	   
	   public void setDescription( java.lang.String description){
	      this.description = description;
	   }
	
	   
	   private java.lang.String asciiString;
	   public  java.lang.String getAsciiString(){
	      return asciiString;
	   }
	   
	   public void setAsciiString( java.lang.String asciiString){
	      this.asciiString = asciiString;
	   }
	
	   
	   private java.lang.String accessionNumber;
	   public  java.lang.String getAccessionNumber(){
	      return accessionNumber;
	   }
	   
	   public void setAccessionNumber( java.lang.String accessionNumber){
	      this.accessionNumber = accessionNumber;
	   }
	
	   
	   private java.lang.String accessionNumberVersion;
	   public  java.lang.String getAccessionNumberVersion(){
	      return accessionNumberVersion;
	   }
	   
	   public void setAccessionNumberVersion( java.lang.String accessionNumberVersion){
	      this.accessionNumberVersion = accessionNumberVersion;
	   }
	
	   
	   private java.lang.Long type;
	   public  java.lang.Long getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.Long type){
	      this.type = type;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.cabio.domain.ws.Clone clone;
			public gov.nih.nci.cabio.domain.ws.Clone getClone(){
			  return clone;
                        }
		   
	      
	               
	   
	   
	   
	   public void setClone(gov.nih.nci.cabio.domain.ws.Clone clone){
		this.clone = clone;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneCollection = new java.util.HashSet();
			public java.util.Collection getGeneCollection(){
	              return geneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setGeneCollection(java.util.Collection geneCollection){
	   		this.geneCollection = geneCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Sequence) {
				Sequence c =(Sequence)obj; 			 
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
