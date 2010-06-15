package gov.nih.nci.cabio.domain;

import gov.nih.nci.cabio.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * An object representing a specific chromosome for a specific taxon; provides accessxx to all known 
   * genes contained in the chromosome and to the taxon. 
   * 
   */

public  class Chromosome 	implements java.io.Serializable 
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;
	
	
		
	/**
   * internal Id
   */

	private java.lang.Long id;
	
	/**
Retreives the value of id attribue
   * @return id

   */

	public java.lang.Long getId()
	{
		return id;
	}
	
	/**
Sets the value of id attribue
@param id internal Id
   */
	
	public void setId(java.lang.Long id)
	{
		this.id = id;
	}
	
		
	/**
   * commonly used chromosome number
   */

	private java.lang.String name;
	
	/**
Retreives the value of name attribue
   * @return name

   */

	public java.lang.String getName()
	{
		return name;
	}
	
	/**
Sets the value of name attribue
@param name commonly used chromosome number
   */
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
	
	
		
		
	/**
An associated  collection of Gene object
   */
		
	private java.util.Collection geneCollection = new java.util.HashSet();
	
	/**
Retreives the value of geneCollection attribue
   * @return geneCollection
   */
		
	public java.util.Collection getGeneCollection()
	{
		if (geneCollection==null || geneCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.geneCollection from gov.nih.nci.cabio.domain.Chromosome as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Gene");				 
				geneCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Chromosome.class.getName());
				log.error("Chromosome:getGeneCollection throws exception ... ...",ex);
			}
		}	
		return geneCollection;
	}
	
	/**
Sets the value of geneCollection attribue
@param geneCollection
   */
		
	public void setGeneCollection(java.util.Collection geneCollection)
	{
		this.geneCollection = geneCollection;
	}	
		
	
	
	
		
		
	/**
An associated Taxon object
   */
		
	private gov.nih.nci.cabio.domain.Taxon taxon;

	/**
Retreives the value of taxon attribue
   * @return taxon
   */
		
	public gov.nih.nci.cabio.domain.Taxon getTaxon()
	{
			
		if(taxon==null ||  taxon.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.taxon from gov.nih.nci.cabio.domain.Chromosome as parent where parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Taxon");				 
				if (resultList!=null && resultList.size()>0) 
					taxon = (gov.nih.nci.cabio.domain.Taxon)resultList.get(0);
				else
					taxon = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Chromosome.class.getName());
				log.error("Chromosome:getTaxon throws exception ... ...",ex);
			}
		}
		return taxon;	
					
	}

	/**
Sets the value of taxon attribue
@param taxon
   */
		
	public void setTaxon(gov.nih.nci.cabio.domain.Taxon taxon)
	{
		this.taxon = taxon;
	}
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Chromosome) 
		{
			Chromosome c =(Chromosome)obj; 			 
			Long thisId = getId();		
			
			if(thisId != null && thisId.equals(c.getId()))
				eq = true;
			
			}
			return eq;
		}
		
	public int hashCode()
	{
		int h = 0;
		
		if(getId() != null)
			h += getId().hashCode();
		
		return h;
	}
}