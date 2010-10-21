 The following targets can be executed by ant.  To get an up to date list of targets execute "ant -p" at the command line.


 buildJar                      This target creates a jar file from the build artifacts and the compiled classes
 clean                         This target will remove all artifacts created by this build.
 clean-classes                 This target removes the compiled classes and the distribution jars created by this build
 clean-plugin                  This target will remove build generator plugin files and artifacts from the -D directory installDir
 clean-target                  This target removes artifacts from the archive, deploy, and stage directories
 compile                       This target will initialize the build, tokenize all relevant source files, then compile the Java source code
 createPackageStructure        This target will create the Java package directories under the java source directory
 createRootPackageDirProperty  This target will create the root package dir from the package property defined in the build property file
 deploy                        This target will copy all relevant artifacts like jar files, build scripts, executables, etc. to the relevant deployment directory
 deployPlugin                  This target removes the target code generator plugin from the build and recreates the plugin
 init                          This target initializes the build directory in preparation for a new build run.
 installPlugin                 This target will install a plugin created by makePlugin into the -D property installDir
 javadoc                       This plugin will create JavaDoc documentation for Java files defined in this project
 makePlugin                    This target will produce a generator plugin from file setups in the src/script directory
 prepareSource                 This target will replace tokens in src files in the stage directory with values defined as properties in this build script.
 recompile                     This target cleans all build artifacts, then compiles all Java source
 redeploy                      This target initializes the build, then executes a deploy
 runScript                     This plugin will execute a JavaScript any named script from the script/javascript directory.  Please provide the name of the script (without the extension) as the script property using Ant's -D option
 unit-test                     this target will execute the builds JUnit tests.  Results are written to the result directory
