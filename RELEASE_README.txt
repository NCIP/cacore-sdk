The SDK now supports both code generation and automated deployment.

To prepare the SDK and Workbench release distribution package (ZIP) files, take the following steps.  
Note:  This release process assumes a QA tested and approved production tag has already been created in SVN !!!

*  Create a new Java project by checking out from SVN the production tag approved by QA.
*  Execute the 'package' target of the /build.xml Ant script file located in the trunk of the production tag project.
*  Open the /dist folder located in the trunk of the production tag project.  This folder should contain four files:  
   SDK-{ver}-JavaDocs.zip, SDK-{ver}-src.zip, workbench_install_{ver}.zip, and workbench_upgrade_{ver}.zip 
*  Email the SDK-{ver}-JavaDocs.zip file for posting to the ZOPE site to Horacio Chamorro, and cc: Clint Malone.
*  Create a new SDK release on the "Files" tab of the SDK GForge project:  https://gforge.nci.nih.gov/frs/?group_id=148.
*  Upload the SDK-{ver}-src.zip file to the new release
*  Create a new Workbench release on the "Files" tab of the Workbench GForge project:  https://gforge.nci.nih.gov/frs/?group_id=624.
*  Upload the workbench_install_{ver}.zip, and workbench_upgrade_{ver}.zip file to the new release
*  The remote webstart version of the Workbench also needs to be deployed to the dev, qa, stage and production tiers using anthillpro3. 

For a complete checklist of the SDK and Workbench release procedures, please refer to the "SDK Release Checklist.doc" file.