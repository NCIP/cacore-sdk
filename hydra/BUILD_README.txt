 The following list the available build targets for build.xml in this directory.  To generate an up to date version of this output execute "ant -p" on the command line.

 buildJar                      This target creates a jar file from the build artifacts and the compiled classes
 clean                         This target will remove all artifacts created by this build.
 clean-classes                 This target removes the compiled classes and the distribution jars created by this build
 clean-target                  This target removes artifacts from the archive, deploy, and stage directories
 compile                       This target will initialize the build, tokenize all relevant source files, then compile the Java source code
 createPackageStructure        This target will create the Java package directories under the java source directory
 createRootPackageDirProperty  This target will create the root package dir from the package property defined in the build property file
 deploy                        This target will copy all relevant artifacts like jar files, build scripts, executables, etc. to the relevant deployment directory
 init                          This target initializes the build directory in preparation for a new build run.
 javadoc                       This plugin will create JavaDoc documentation for Java files defined in this project
 prepareSource                 This target will replace tokens in src files in the stage directory with values defined as properties in this build script.
 recompile                     This target cleans all build artifacts, then compiles all Java source
 redeploy                      This target initializes the build, then executes a deploy
 runScript                     This plugin will execute a JavaScript any named script from the script/javascript directory.  Please provide the name of the script (without the extension) as the script property using Ant's -D option
 unit-test                     this target will execute the builds JUnit tests.  Results are written to the result directory
