

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Target 
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
	
	   
	   private java.lang.String type;
	   public  java.lang.String getType(){
	      return type;
	   }
	   
	   public void setType( java.lang.String type){
	      this.type = type;
	   }
	
	   
	   private java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   
	   public void setName( java.lang.String name){
	      this.name = name;
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
			if(obj instanceof Target) {
				Target c =(Target)obj; 			 
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
