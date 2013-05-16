Welcome to the caCORE SDK Project!
=====================================

The caCORE Software Development Kit (SDK) generates a system built on the principles of Model Driven Architecture (MDA), n-tier architecture and a common API for data access. The use of MDA and n-tier architecture allows for easy access to data, particularly by other applications.

When the caCORE SDK-generated system is combined with controlled vocabularies and registered metadata, the resulting software system, called "caCORE-like" system, is "semantically integrated." All exposed API elements have runtime accessible metadata that defines the meaning of the elements using controlled terminology.

The caCORE SDK has two modules:
* Code Generation, which accepts a UML model as input and produces java classes and other artifacts such as a standard XML Schema (xsd) that can be used by standard client APIs
* Middleware, that provides the actual infrastructure including the server and standard client APIs to provide access to the underlying data system through n-tier architecture

The caCORE SDK is distributed under the BSD 3-Clause License.
Please see the NOTICE and LICENSE files for details.

You will find more details about the caCORE SDK in the following links:
 * [Community Wiki] (https://wiki.nci.nih.gov/x/XIAI)
 * [Issue Tracker] (https://tracker.nci.nih.gov/browse/SDK)
 * [Code Repository] (https://github.com/NCIP/cacore-sdk)
 * [Documentation] (https://wiki.nci.nih.gov/x/zZCPAQ)
 * [Release Notes] (https://wiki.nci.nih.gov/x/DQrx)
 * [Installation Packages] (https://wiki.nci.nih.gov/x/CQFwBg) 
 * [Forum] (https://cabig-kc.nci.nih.gov/CaGrid/forums/viewforum.php?f=31&sid=317abcd4b7ae1f07e547e12f9c2b9059)

Please join us in further developing and improving caCORE SDK.

# Prerequisites
 * [Minimum software requirements] (https://wiki.nci.nih.gov/display/caCORE/caCORE+SDK+Minimum+System+Requirements) 
 
# How to use SDK
 * [SDK Components] (https://wiki.nci.nih.gov/display/caCORE/Components+of+caCORE+SDK)
 * [How to use SDK] (https://wiki.nci.nih.gov/display/caCORE/How+to+Use+the+caCORE+SDK+-+Guide+for+Users)
 
# Sub Projects
   * caCORE Workbench
   
     The caCORE Workbench is a tool with a graphical user interface (GUI) to facilitate the creation of a caBIG silver or gold compliant system. The caCORE Workbench acts as a process guide and an integrated platform, enabling the user to more readily create a Data or Analytical service on the Grid. The following process workflows are supported:
     1.  Creation of a UML Model (ArgoUML, Enterprise Architect)
     2.  Semantic integration (SIW, CDE Browser, UML Model Browser, Curation Tool)
     3.  Model mapping (caAdapter)
     4.  Application creation and deployment (SDK)
     5.  Creation of a grid service (Introduce)
   
      * [Community Wiki] (https://wiki.nci.nih.gov/display/caCORE/caCORE+Workbench)
      * [Binaries packages] (https://wiki.nci.nih.gov/display/caCORE/caCORE+Workbench+Downloads)
      * [User Guide] (https://wiki.nci.nih.gov/display/caCORE/caCORE+Workbench+Version+0.2+Guide)
      * [Release Notes] (https://gforge.nci.nih.gov/docman/index.php?group_id=624&selected_doc_group_id=5781&language_id=1)
   
   * caCORE RESTful Wrapper
   
   The caCORE RESTful Wrapper is aimed at lowering the barrier to create RESTful services based on existing data sources. The wrapper provides interface in RESTful manner while it internally maps with existing data source to query data. The RESTful Wrapper will provide User Interface where developers can create RESTful resources wrapping existing data services.
      * [Community Wiki] (https://wiki.nci.nih.gov/display/caCORE/caCORE+RESTful+Wrapper)
      * [User Guide] (https://wiki.nci.nih.gov/display/caCORE/caCORE+RESTful+Wrapper+v1.0+User+Guide)
      * [Release Notes] (https://wiki.nci.nih.gov/display/caCORE/caCORE+RESTful+Wrapper+v1.0+Release+Notes)

   * caCORE Hydra
   
   The caCORE Hydra is a pilot project developed as Eclipse IDE plugin (referred to from here on as the plugin) to provide a development environment that fosters the generation of application artifacts from an Ecore model.  The plugin helps developers convert application models created in a variety of meta-model representations into an Ecore model.  From this ecore model, pluggable artifact generators can produce artifacts.  
 
 
# Related Projects
  * [Common Security Module] (https://github.com/NCIP/common-security-module)
  * [Common Logging Module] (https://github.com/NCIP/common-logging-module)
  * [NCI CBIIT ISO21090 Common Library Module]  (https://github.com/NCIP/iso21090)
  * [XMI Handler] (https://github.com/NCIP/xmihandler)
   
