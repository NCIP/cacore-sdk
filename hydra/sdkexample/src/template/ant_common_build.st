<!--
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements. See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership. The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License. You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied. See the License for the
  specific language governing permissions and limitations
  under the License.
-->
<project name="cxf  build file" default="build">


    <property environment="env"/>
	<property file="project.build" />
    <property name="build.dir" location ="\${basedir}"/>
    <property name="build.classes.dir" location ="\${build.dir}/classes"/>
    <property name="build.src.dir" location ="\${build.dir}/src"/>
    <property name="build.var.dir" location ="\${build.dir}/var"/>
    <property name="codegen.timestamp.file" value="\${build.src.dir}/.CODEGEN_DONE"/>
    <property name="war.dir" location="\${build.dir}/deploy"/>

    <property name="host" value="$hostName$"/>
    <property name="port" value="$hostPort$"/>
    <property name="base.url" value="http://\${host}:\${port}"/>

    <available property="wsdl.dir" value="\${basedir}/conf"
        file="\${basedir}/conf"/>
    <property name="wsdl.dir" location="."/>

    <!-- find the tools jar -->
    <available property="tools.jar" value="\${java.home.lib}/tools.jar"
       file="\${java.home.lib}/lib/tools.jar"/>
    <path id="jdk.tools.classpath">
        <pathelement location="\${tools.jar}"/>
    </path>

    <condition property="is.java.version.15">
        <or>
          <equals arg1="\${ant.java.version}" arg2="1.5"/>
          <equals arg1="\${ant.java.version}" arg2="1.6"/>
        </or>
    </condition>

    <fail message="Apache CXF requires Java version 1.5 or higher. You are currently using Java version \${ant.java.version}."
        unless="is.java.version.15"/>



    <!-- Determine cxf.home, either from the environment variable CXF_HOME
       - or using ../..
       -->
    <condition property="cxf.home" value="\${cxf.home}">
        <isset property="cxf.home"/>
    </condition>
    <condition property="cxf.home" value="../..">
        <available file="../../etc" type="dir" property=""/>
    </condition>
    <condition property="cxf.home" value="../../..">
        <available file="../../../etc" type="dir" property=""/>
    </condition>
    <property name="cxf.home" location="../.."/>
    <property name="cxf.etc.dir" location="\${cxf.home}/etc"/>

    <path id="cxf.classpath">
        <pathelement location="\${basedir}"/>
        <pathelement location="\${build.classes.dir}"/>
        <pathelement path="\${srcbuild.classpath}"/>
        <pathelement location="\${cxf.home}/lib/cxf-manifest.jar"/>
    </path>

    <path id="generator.classpath">
	<fileset dir="$generatorLibDir$">
		<include name="*.jar" />
	</fileset>
    </path>

    <target name="compile">
	    <mkdir dir="\${basedir}/lib"/>
		<mkdir dir="\${basedir}/workspace/lib"/>
        <mkdir dir="\${build.classes.dir}"/>
        <mkdir dir="\${build.src.dir}"/>
		<mkdir dir="\${build.src.dir}/cfg"/>

        <javac destdir="\${build.classes.dir}" debug="true">
            <src path="\${build.src.dir}"/>
            <src path="\${basedir}/src"/>
            <classpath>
                <path refid="cxf.classpath"/>
                <path refid="generator.classpath"/>
            </classpath>
        </javac>

        <copy todir="\${build.classes.dir}">
            <fileset dir="\${basedir}/src" includes="**/*.xml, **/*.txt" />
            <fileset dir="\${build.src.dir}" includes="**/*.xml, **/*.txt" />
        </copy>
    </target>

    <target name="checkstyle">
        <checkstyle config="\${checkstyle.config.file}">
            <fileset dir="\${basedir}/src" includes="**/*.java"/>
            <classpath>
                <path refid="cxf.classpath"/>
                <pathelement path="\${thirdparty.classpath}"/>
            </classpath>
        </checkstyle>
    </target>

    <target name="clean">
        <delete dir="\${build.classes.dir}"/>
        <delete dir="\${build.src.dir}"/>
        <delete file="\${codegen.timestamp.file}"/>
        <delete file="demo.log"/>
        <delete dir="\${build.var.dir}"/>
        <delete dir="\${activemq.home}/var"/>
        <delete dir="\${build.dir}"/>
    </target>


    <target name="build"  depends="compile" description="build demo client and server"/>

    <macrodef name="cxfrun">
        <attribute name="logging-properties-file" default="\${cxf.etc.dir}/logging.properties"/>
        <attribute name="classname"/>
        <attribute name="param1" default=""/>
        <attribute name="param2" default=""/>
        <attribute name="param3" default=""/>
        <attribute name="param4" default=""/>
        <attribute name="param5" default=""/>
        <attribute name="jvmarg1" default="-D' '"/>
        <attribute name="jvmarg2" default="-D' '"/>
        <attribute name="jvmarg3" default="-D' '"/>
        <attribute name="jvmarg4" default="-D' '"/>
        <attribute name="jvmarg5" default="-D' '"/>
        <sequential>
            <java classname="@{classname}" fork="yes">
                <classpath>
                    <path refid="cxf.classpath"/>
                    <pathelement path="\${thirdparty.classpath}"/>
                    <path refid="jdk.tools.classpath"/>
                </classpath>
                <arg value="@{param1}"/>
                <arg value="@{param2}"/>
                <arg value="@{param3}"/>
                <arg value="@{param4}"/>
                <arg value="@{param5}"/>
                <jvmarg value="-Dcxf.home=\${cxf.home}"/>
                <jvmarg value="@{jvmarg1}"/>
                <jvmarg value="@{jvmarg2}"/>
                <jvmarg value="@{jvmarg3}"/>
                <jvmarg value="@{jvmarg4}"/>
                <jvmarg value="@{jvmarg5}"/>
                <!--assertions>
                    <enable package="org.apache.cxf"/>
                </assertions-->
                <sysproperty key="java.util.logging.config.file" value="@{logging-properties-file}"/>
            </java>
        </sequential>
    </macrodef>

    <macrodef name="wsdl2java">
        <attribute name="srcdestdir" default="\${build.src.dir}"/>
        <attribute name="destdir" default="\${build.classes.dir}"/>
        <attribute name="file"/>
        <attribute name="servicename.arg" default=""/>
        <attribute name="bindingfile" default=""/>
        <attribute name="dir" default="\${wsdl.dir}"/>
        <attribute name="package" default="NOT_SPECIFIED"/>
        <sequential>
            <mkdir dir="@{destdir}"/>
            <mkdir dir="@{srcdestdir}"/>
            <condition property="package.arg.@{file}" value="-p @{package}">
                <not>
                    <equals arg1="@{package}" arg2="NOT_SPECIFIED"/>
                </not>
            </condition>
            <property name="package.arg.@{file}" value=""/>
            <condition property="binding.arg" value='-b "@{bindingfile}"'>
                <not>
                    <equals arg1="@{bindingfile}" arg2=""/>
                </not>
            </condition>
            <property name="binding.arg" value=""/>
            <java failonerror="true" classname="org.apache.cxf.tools.wsdlto.WSDLToJava" fork="yes">
                <classpath>
                    <path refid="cxf.classpath" />
                </classpath>
                <sysproperty key="java.util.logging.config.file" value="\${cxf.etc.dir}/logging.properties"/>
                <sysproperty key="exitOnFinish" value="true"/>
                <arg line="@{servicename.arg}"/>
                <arg line="\${package.arg.@{file}}"/>
                <arg line="\${binding.arg}"/>
                <arg value="-verbose"/>
                <arg value="-d"/>
                <arg value="@{srcdestdir}"/>
                <arg value="@{dir}/@{file}"/>
            </java>
        </sequential>
    </macrodef>
		
		<property name="war-lib" value="\${basedir}/libs" />
		<property name="config.dir" value="\${basedir}/config" />
    <macrodef name="cxfwar">
        <attribute name="filename"/>
        <attribute name="wsdl" default=""/>
        <attribute name="dir" default="\${war.dir}"/>
        <attribute name="classesdir" default="\${build.classes.dir}"/>
        <attribute name="webxml" default="\${cxf.home}/etc/web.xml" />
        <sequential>
            <mkdir dir="@{dir}"/>
            <mkdir dir="\${war-lib}"/>
            <mkdir dir="\${config.dir}"/>
            <antcall target="copy-war-libs"/>
            
            <available property="has.config" file="\${basedir}/webapp/WEB-INF" type="dir" />
            <antcall target="copy-config-files" />
            
            <delete file="@{dir}/@{filename}" />
            <war destfile="@{dir}/@{filename}" webxml="@{webxml}">
                <classes dir="@{classesdir}"/>
                <webinf dir="\${wsdl.dir}">
                    <include name="cxf-servlet.xml"/>
                </webinf>
                <webinf dir="\${wsdl.dir}/..">
                    <include name="wsdl/@{wsdl}"/>
                </webinf>
                <webinf dir="\${config.dir}">
                		<include name="*.*" />
                </webinf> 
                <lib dir="\${war-lib}">
    							<include name="*.jar"/>
  							</lib>
            </war>
            
            <delete dir="\${war-lib}" />
            <delete dir="\${config.dir}" />
        </sequential>
    </macrodef>
    
    <target name="copy-war-libs" unless="without.libs">
    	<copy todir="\${war-lib}">
    		<fileset dir="\${cxf.home}/lib">
    		   <exclude name="servlet-api-*.jar" />
                   <exclude name="geronimo-servlet_*.jar" />
                   <exclude name="jetty-*.jar"/>
                   <exclude name="WHICH_JARS" />
    		</fileset>
    	</copy>	
    </target>
    
    <target name="copy-config-files" if="has.config">
    		<copy todir="\${config.dir}" >
    			<fileset dir="\${basedir}/webapp/WEB-INF">
    				<include name="*.xml" />
    				<exclude name="web.xml" />
    			</fileset>
    		</copy>
    </target>

    <target name="deploy" description="deploy the application into the container">
        <antcall target="war"/>
        <antcall target="deploy-tomcat"/>
    </target>
    <target name="undeploy" depends="undeploy-tomcat" description="undeploy the application from the container"/>

    <target name="deploy-tomcat">
        <antcall target="validate-tomcat"/>
        <copy file="\${war.dir}/\${cxf.war.file.name}.war"
              todir="\${catalina.home}/webapps"/>
    </target>

    <target name="undeploy-tomcat">
        <antcall target="validate-tomcat"/>
        <delete file="\${catalina.home}/webapps/\${cxf.war.file.name}.war"/>
        <delete dir="\${catalina.home}/webapps/\${cxf.war.file.name}"/>
    </target>

    <target name="validate-tomcat">
        <fail unless="catalina.home" message="You should set the CATALINA_HOME, if you want to deploy into tomcat"/>
    </target>

	<condition property="tomcat-startup-cmd" value="startup.bat" else="startup.sh">
		<and><os family="windows"/></and>
	</condition>

	<condition property="tomcat-shutdown-cmd" value="shutdown.bat" else="shutdown.sh">
		<and><os family="windows"/></and>
	</condition>

	<target name="start-tomcat">

	   <chmod dir="\${catalina.home}/bin" includes="**/*.sh, **/*.bat"  perm="ugo+rx"/>
	   <exec dir="\${catalina.home}/bin" executable="sh">
	      <arg line="\${tomcat-startup-cmd}" />
	   </exec>
	</target>

	<target name="stop-tomcat">

	   <chmod dir="\${catalina.home}/bin" includes="**/*.sh, **/*.bat"  perm="ugo+rx"/>
	   <exec dir="\${catalina.home}/bin" executable="sh">
	      <arg line="\${tomcat-shutdown-cmd}" />
	   </exec>
	</target>

</project>
