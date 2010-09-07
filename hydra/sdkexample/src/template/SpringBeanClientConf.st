<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:jaxws="http://cxf.apache.org/jaxws"
	xsi:schemaLocation="
http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
http://cxf.apache.org/jaxws http://cxf.apache.org/schemas/jaxws.xsd">

   $beanId, serviceClass, serviceAddress: { b, sc, sa | <jaxws:client id="$b$" serviceClass="$sc$" address="$sa$" ></jaxws:client>
}$

</beans>
