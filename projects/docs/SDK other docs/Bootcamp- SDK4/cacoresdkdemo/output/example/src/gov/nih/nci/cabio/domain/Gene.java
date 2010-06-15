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
   * Gene objects are the effective portal to most of the genomic information provided by the caBIO data 
   * services; organs, diseases, chromosomes, pathways, sequence data, and expression experiments 
   * are among the many objects accessible via a gene. 
   * 
   */

public  class Gene 	implements java.io.Serializable 
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;
	
	
		
	/**
   * internal id
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
@param id internal id
   */
	
	public void setId(java.lang.Long id)
	{
		this.id = id;
	}
	
		
	/**
   * formal name
   */

	private java.lang.String title;
	
	/**
Retreives the value of title attribue
   * @return title

   */

	public java.lang.String getTitle()
	{
		return title;
	}
	
	/**
Sets the value of title attribue
@param title formal name
   */
	
	public void setTitle(java.lang.String title)
	{
		this.title = title;
	}
	
		
	/**
   * gene abbreviation
   */

	private java.lang.String symbol;
	
	/**
Retreives the value of symbol attribue
   * @return symbol

   */

	public java.lang.String getSymbol()
	{
		return symbol;
	}
	
	/**
Sets the value of symbol attribue
@param symbol gene abbreviation
   */
	
	public void setSymbol(java.lang.String symbol)
	{
		this.symbol = symbol;
	}
	
		
	/**
   * summary from Locus Link
   */

	private java.lang.String locusLinkSummary;
	
	/**
Retreives the value of locusLinkSummary attribue
   * @return locusLinkSummary

   */

	public java.lang.String getLocusLinkSummary()
	{
		return locusLinkSummary;
	}
	
	/**
Sets the value of locusLinkSummary attribue
@param locusLinkSummary summary from Locus Link
   */
	
	public void setLocusLinkSummary(java.lang.String locusLinkSummary)
	{
		this.locusLinkSummary = locusLinkSummary;
	}
	
		
	/**
   * Online medelian inheritance in man id
   */

	private java.lang.String OMIMID;
	
	/**
Retreives the value of OMIMID attribue
   * @return OMIMID

   */

	public java.lang.String getOMIMID()
	{
		return OMIMID;
	}
	
	/**
Sets the value of OMIMID attribue
@param OMIMID Online medelian inheritance in man id
   */
	
	public void setOMIMID(java.lang.String OMIMID)
	{
		this.OMIMID = OMIMID;
	}
	
		
	/**
   * interal id link to locus link
   */

	private java.lang.String locusLinkId;
	
	/**
Retreives the value of locusLinkId attribue
   * @return locusLinkId

   */

	public java.lang.String getLocusLinkId()
	{
		return locusLinkId;
	}
	
	/**
Sets the value of locusLinkId attribue
@param locusLinkId interal id link to locus link
   */
	
	public void setLocusLinkId(java.lang.String locusLinkId)
	{
		this.locusLinkId = locusLinkId;
	}
	
		
	/**
   * The cluster identification for the gene.
   */

	private java.lang.Long clusterId;
	
	/**
Retreives the value of clusterId attribue
   * @return clusterId

   */

	public java.lang.Long getClusterId()
	{
		return clusterId;
	}
	
	/**
Sets the value of clusterId attribue
@param clusterId The cluster identification for the gene.
   */
	
	public void setClusterId(java.lang.Long clusterId)
	{
		this.clusterId = clusterId;
	}
	
	
		
		
	/**
An associated  collection of Library object
   */
		
	private java.util.Collection libraryCollection = new java.util.HashSet();
	
	/**
Retreives the value of libraryCollection attribue
   * @return libraryCollection
   */
		
	public java.util.Collection getLibraryCollection()
	{
		if (libraryCollection==null || libraryCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.libraryCollection from gov.nih.nci.cabio.domain.Gene as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Library");				 
				libraryCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getLibraryCollection throws exception ... ...",ex);
			}
		}	
		return libraryCollection;
	}
	
	/**
Sets the value of libraryCollection attribue
@param libraryCollection
   */
		
	public void setLibraryCollection(java.util.Collection libraryCollection)
	{
		this.libraryCollection = libraryCollection;
	}	
		
	
	
	
		
		
	/**
An associated Chromosome object
   */
		
	private gov.nih.nci.cabio.domain.Chromosome chromosome;

	/**
Retreives the value of chromosome attribue
   * @return chromosome
   */
		
	public gov.nih.nci.cabio.domain.Chromosome getChromosome()
	{
			
		if(chromosome==null ||  chromosome.getClass().getName().indexOf('$')>0)
		{			
			try
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.chromosome from gov.nih.nci.cabio.domain.Gene as parent where parent.id="+idString;
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Chromosome");				 
				if (resultList!=null && resultList.size()>0) 
					chromosome = (gov.nih.nci.cabio.domain.Chromosome)resultList.get(0);
				else
					chromosome = null;
			}
			catch(Exception ex) 
			{ 
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getChromosome throws exception ... ...",ex);
			}
		}
		return chromosome;	
					
	}

	/**
Sets the value of chromosome attribue
@param chromosome
   */
		
	public void setChromosome(gov.nih.nci.cabio.domain.Chromosome chromosome)
	{
		this.chromosome = chromosome;
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
				String hql = "select parent.taxon from gov.nih.nci.cabio.domain.Gene as parent where parent.id="+idString;
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
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getTaxon throws exception ... ...",ex);
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
		
	
	
	
		
		
	/**
An associated  collection of Sequence object
   */
		
	private java.util.Collection sequenceCollection = new java.util.HashSet();
	
	/**
Retreives the value of sequenceCollection attribue
   * @return sequenceCollection
   */
		
	public java.util.Collection getSequenceCollection()
	{
		if (sequenceCollection==null || sequenceCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.sequenceCollection from gov.nih.nci.cabio.domain.Gene as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Sequence");				 
				sequenceCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getSequenceCollection throws exception ... ...",ex);
			}
		}	
		return sequenceCollection;
	}
	
	/**
Sets the value of sequenceCollection attribue
@param sequenceCollection
   */
		
	public void setSequenceCollection(java.util.Collection sequenceCollection)
	{
		this.sequenceCollection = sequenceCollection;
	}	
		
	
	
	
		
		
	/**
An associated  collection of Target object
   */
		
	private java.util.Collection targetCollection = new java.util.HashSet();
	
	/**
Retreives the value of targetCollection attribue
   * @return targetCollection
   */
		
	public java.util.Collection getTargetCollection()
	{
		if (targetCollection==null || targetCollection.getClass().getName().indexOf("PersistentSet")>0)		
		{
	      try 
			{
				String idString = (Class.forName("java.lang.String").isInstance(getId()))? "'"+ getId() + "'" : ""+getId(); 
				String hql = "select parent.targetCollection from gov.nih.nci.cabio.domain.Gene as parent where parent.id="+idString;
				
				HQLCriteria hqlCriteria = new HQLCriteria(hql);
				ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
				java.util.List resultList = applicationService.query(hqlCriteria,"gov.nih.nci.cabio.domain.Target");				 
				targetCollection = resultList;	 
			}
			catch(Exception ex) 
			{
				Logger log = Logger.getLogger(Gene.class.getName());
				log.error("Gene:getTargetCollection throws exception ... ...",ex);
			}
		}	
		return targetCollection;
	}
	
	/**
Sets the value of targetCollection attribue
@param targetCollection
   */
		
	public void setTargetCollection(java.util.Collection targetCollection)
	{
		this.targetCollection = targetCollection;
	}	
		
	

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof Gene) 
		{
			Gene c =(Gene)obj; 			 
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