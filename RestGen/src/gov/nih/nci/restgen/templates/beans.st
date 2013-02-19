<?xml version="1.0" encoding="UTF-8"?> 

<beans xmlns="http://www.springframework.org/schema/beans" 
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
  xmlns:jaxrs="http://cxf.apache.org/jaxrs" 
xmlns:jaxws="http://cxf.apache.org/jaxws" 
xmlns:util="http://www.springframework.org/schema/util" 
  xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://cxf.apache.org/jaxws
http://cxf.apache.org/schemas/jaxws.xsd
http://cxf.apache.org/jaxrs
http://cxf.apache.org/schemas/jaxrs.xsd
http://www.springframework.org/schema/util
http://www.springframework.org/schema/util/spring-util-2.0.xsd"> 

  <bean id="jaxbXmlProvider" class="org.apache.cxf.jaxrs.provider.JAXBElementProvider">
        <property name="jaxbElementClassNames" ref="elements"/>
  </bean>
  $ProviderBean$
 
  <util:list id="elements">
   		$ResourcePojo; separator="\n"$
  </util:list>


  <jaxrs:server id="$ResourceName$Service" address="/">
    <jaxrs:serviceBeans>
      $ResourceRefBean; separator="\n"$
    </jaxrs:serviceBeans>
    <jaxrs:extensionMappings>
      <entry key="xml" value="application/xml" />
    </jaxrs:extensionMappings>
    <jaxrs:providers>
        <ref bean="jaxbXmlProvider"/>
        $ProviderRef$
    </jaxrs:providers>    
 </jaxrs:server>

   $ResourceRefBeanDef; separator="\n"$
</beans>