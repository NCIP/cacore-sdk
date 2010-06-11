The SDK now supports both code generation and automated deployment.

To build and deploy the SDK 'example' project:

*  Open /example-project/build directory;
*  Modify the code generation (codegen.properties) and deployment (install.properties) property files as desired for your environment
*  Open the build.xml Ant script file
*  Execute the 'dist' target of the build.xml Ant script file to generate the application using the properties within codegen.properties file
*  Execute the 'deploy:local:install:no-build' target to deploy the application using the properties within the install.properties file

To build and deploy the SDK 'iso-example' project:

*  Open /iso-example-project/build directory;
*  Modify the code generation (codegen.properties) and deployment (install.properties) property files as required for your environment
*  Open the build.xml Ant script file
*  Execute the 'dist' target of the build.xml Ant script file to generate the application using the properties within codegen.properties file
*  Execute the 'deploy:local:install:no-build' target to deploy the application using the properties within the install.properties file