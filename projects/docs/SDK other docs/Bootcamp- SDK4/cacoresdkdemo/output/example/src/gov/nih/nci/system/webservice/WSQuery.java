package gov.nih.nci.system.webservice;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;

import java.util.*;
import java.lang.reflect.*;

import org.apache.log4j.*;


public class WSQuery {
    private static Logger log = Logger.getLogger(WSQuery.class);
    private boolean wsPackage = true;
    private String fileName = "CORESystem.properties";
    private int maximumRecordsPerQuery = 1000;
    private int recordsPerQuery = 1000;
    private String version = "3.2";
    private boolean processOntology = true;
    private String beanFileName = "cacoreBeans.properties";
    private WSTransformer transformer;

    public WSQuery() throws Exception{
        loadProperties();
        loadWSTransformer();
    }

    //======================================================
    public String getVersion(){
        return version;
    }
    public int getRecordsPerQuery(){
        return recordsPerQuery;
    }
    public int getMaximumRecordsPerQuery(){
        return maximumRecordsPerQuery;
    }
    public boolean getProcessOntology(){
        return processOntology;
    }
    public int getTotalNumberOfRecords(String targetClassName, Object criteria) throws Exception{
        return getResultSet(targetClassName,criteria).size();
    }

    //  ======================================================
     public List queryObject(String targetClassName, Object criteria) throws Exception
     {
         return query(targetClassName,criteria,0,0);
     }
    //==========================================================
      public List query(String targetClassName, Object criteria, int startIndex, int recordCounter) throws Exception
      {
          List alteredResults = new ArrayList();
          List results = new ArrayList();

              results = getResultSet(targetClassName, criteria);
              List resultList = new ArrayList();

              if(results.size()>= startIndex){
                  if(recordCounter <=0 || recordCounter > (startIndex + recordsPerQuery) ){
                      recordCounter = startIndex + recordsPerQuery;
                  }
                for(int i= startIndex;( i<=(recordCounter + startIndex) && i<results.size()); i++){
                    resultList.add(results.get(i));
                  }
              }
              if(resultList.size()>0){
                  alteredResults = transformer.generateWSResults(resultList);
                  }



        return alteredResults;
      }

      //===========================================================================
      private List getResultSet(String targetClassName, Object criteria) throws Exception{

          List results = new ArrayList();

          String searchClassName = transformer.getSearchClassName(targetClassName);
          Object searchCriteria =  transformer.getSearchCriteria(criteria);

          try
          {
              if(searchClassName != null && searchCriteria != null){
                  ApplicationService app = ApplicationServiceProvider.getLocalInstance();
                  results = app.search(searchClassName, searchCriteria);
              }
              else{
                  throw new Exception("Invalid arguments passed over to the server");
              }

          }
          catch(Exception e)
          {
              log.error("WSQuery caught an exception: ", e);
              throw e;
          }
          return results;
      }

      private  void loadProperties() throws Exception{
          try{
              java.util.Properties properties = new java.util.Properties();
              properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
              for(Iterator i= properties.keySet().iterator(); i.hasNext();){
                  String key = (String)i.next();
                  String value = (String)properties.get(key);
                  if(key.equalsIgnoreCase("RECORDSPERQUERY")){
                      try{
                          if(value.length()>0 && value != null){
                              recordsPerQuery = Integer.parseInt(value);
                          }
                      }catch(Exception ex){
                          throw new Exception(ex.getMessage());
                      }
                  }
                  else if(key.equalsIgnoreCase("MAXRECORDSPERQUERY")){
                      try{
                          if(value.length()>0 && value != null){
                              maximumRecordsPerQuery = Integer.parseInt(value);
                          }
                      }catch(Exception ex){
                          throw new Exception(ex.getMessage());
                      }
                  }
                  else if(key.equalsIgnoreCase("VERSION")){
                      version = value;
                  }
                  else if(key.equalsIgnoreCase("CACORE_ONTOLOGY")){
                      if(value.equalsIgnoreCase("TRUE")|| value.equalsIgnoreCase("false")){
                          processOntology = Boolean.valueOf(value).booleanValue();
                      }

                  }
                  else if(key.equalsIgnoreCase("PROPERTY_FILE")){
                      beanFileName = value;
                  }

              }
          }catch(Exception ex){
              log.error("Error: Unable to read file: "+ fileName);
              throw new Exception("Error: Unable to read file: "+ fileName);
          }
      }
      private void loadWSTransformer() throws Exception{
          try{
              transformer = new WSTransformer(beanFileName);
              transformer.setProcessOntology(processOntology);
          }catch(Exception ex){
              throw new Exception(ex.getMessage());
          }

      }

}
