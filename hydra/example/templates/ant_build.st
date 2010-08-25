<?xml version="1.0" encoding="UTF-8"?>
<project name="$applicationName$" default="build" basedir=".">
	<import file="common_build.xml"/>        
    <target name="client" description="run client" depends="build">
        <property name="param" value=""/>
        <cxfrun classname="$serviceClient$" />
    </target> 
    <property name="cxf.war.file.name" value="$archiveName$"/>
      <target name="war" depends="build">
	  <cxfwar filename="\${cxf.war.file.name}.war" webxml="webapp/WEB-INF/web.xml" />
    </target>
</project>
