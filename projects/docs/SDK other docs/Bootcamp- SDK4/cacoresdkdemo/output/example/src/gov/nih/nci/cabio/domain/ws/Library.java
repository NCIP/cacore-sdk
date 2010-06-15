

package gov.nih.nci.cabio.domain.ws;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Library 
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
	
	   
	   private java.lang.String keyword;
	   public  java.lang.String getKeyword(){
	      return keyword;
	   }
	   
	   public void setKeyword( java.lang.String keyword){
	      this.keyword = keyword;
	   }
	
	   
	   private java.lang.String description;
	   public  java.lang.String getDescription(){
	      return description;
	   }
	   
	   public void setDescription( java.lang.String description){
	      this.description = description;
	   }
	
	   
	   private java.lang.String RSite1;
	   public  java.lang.String getRSite1(){
	      return RSite1;
	   }
	   
	   public void setRSite1( java.lang.String RSite1){
	      this.RSite1 = RSite1;
	   }
	
	   
	   private java.lang.String RSite2;
	   public  java.lang.String getRSite2(){
	      return RSite2;
	   }
	   
	   public void setRSite2( java.lang.String RSite2){
	      this.RSite2 = RSite2;
	   }
	
	   
	   private java.lang.Long unigeneId;
	   public  java.lang.Long getUnigeneId(){
	      return unigeneId;
	   }
	   
	   public void setUnigeneId( java.lang.Long unigeneId){
	      this.unigeneId = unigeneId;
	   }
	
	   
	   private java.util.Date creationDate;
	   public  java.util.Date getCreationDate(){
	      return creationDate;
	   }
	   
	   public void setCreationDate( java.util.Date creationDate){
	      this.creationDate = creationDate;
	   }
	
	   
	   private java.lang.String labHost;
	   public  java.lang.String getLabHost(){
	      return labHost;
	   }
	   
	   public void setLabHost( java.lang.String labHost){
	      this.labHost = labHost;
	   }
	
	   
	   private java.lang.Long clonesToDate;
	   public  java.lang.Long getClonesToDate(){
	      return clonesToDate;
	   }
	   
	   public void setClonesToDate( java.lang.Long clonesToDate){
	      this.clonesToDate = clonesToDate;
	   }
	
	   
	   private java.lang.Long sequencesToDate;
	   public  java.lang.Long getSequencesToDate(){
	      return sequencesToDate;
	   }
	   
	   public void setSequencesToDate( java.lang.Long sequencesToDate){
	      this.sequencesToDate = sequencesToDate;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection cloneCollection = new java.util.HashSet();
			public java.util.Collection getCloneCollection(){
	              return cloneCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setCloneCollection(java.util.Collection cloneCollection){
	   		this.cloneCollection = cloneCollection;
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
			if(obj instanceof Library) {
				Library c =(Library)obj; 			 
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
