# ssh-dome
spring hibernate struts2 doem

### SSH 环境搭建过程
>* 了解 maven 环境搭建 web project 工程
>* 了解 eclipse 集成开发环境
>* 了解 git 版本控制
>* 会使用 spring4 框架
>* 会使用 struts2 框架
>* 会使用 hibernate4 框架

>### 创建项目
项目采用maven方式创建，目录结构因为符合maven标准方式
>#### 目录结构
![tool-manager](https://raw.githubusercontent.com/kuailelp/ssh-dome/master/image/file.png) <br/>

>* 目录说明
```
src/main/java         源码存放地址
src/main/resources    资源和配置文件存放地址
src/test/java         测试源码
main/webapp/WEB-INF   web配置文件地址
target                测试编译文件二进制码
```

**注意：** 用 maven 创建的 web 应用默认的是 Dynamic Web Module 2.3 只能用 jdk1.5 tomcat6 所以需要修改配置文件，使其可以应用到 tomcat6+ 的服务上

****
##搭建过程##
 >1. 搭建spring 环境 <br />
    >a. 引入 jar 包 <br/>
    **定义 spring 版本** 


 ```xml
    <properties>
             <spring.version>3.2.3.RELEASE</spring.version>
    </properties>

```


**导入 jar 包 **


```xml
        <dependency>
			<groupId>org.springframework</groupId>
			<artifactId>org.springframework.core</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>org.springframework.beans</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>org.springframework.context</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>org.springframework.aop</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>org.springframework.jdbc</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>org.springframework.test</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>org.springframework.orm</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>org.springframework.oxm</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>org.springframework.expression</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-support</artifactId>
			<version>${spring.version}</version>
		</dependency>


```

>>b. web.xml 配置文件


```xml

<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
	http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="WebApp_ID" version="3.0">
	<display-name>Archetype Created Web Application</display-name>
	<!-- Spring配置 -->
	<!-- ====================================== -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	<!-- 指定Spring Bean的配置文件所在目录。默认配置在WEB-INF目录下 -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:spring-config.xml</param-value>
	</context-param>
	<!--log4j配置文件加载 -->
	<context-param>
		<param-name>log4jConfigLocation</param-name>
		<param-value>/WEB-INF/classes/log4j.properties</param-value>
	</context-param>
	<!--启动一个watchdog线程每1800秒扫描一下log4j配置文件的变化 -->
	<context-param>
		<param-name>log4jRefreshInterval</param-name>
		<param-value>1800000</param-value>
	</context-param>
	<!--spring log4j监听器 -->
	<listener>
		<listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
	</listener>
</web-app>
```

>>c. spring 配置文件


```xml

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx" xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="
	http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
	http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.1.xsd
	http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.1.xsd
	http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.1.xsd
	">
	<!-- 扫描注解Bean -->
	<context:component-scan base-package="com.cwca.*">
		<!-- <context:exclude-filter type="annotation" -->
		<!-- expression="org.springframework.stereotype.Controller" /> -->
	</context:component-scan>
	<!-- 引入jdbc配置文件 -->
	<context:property-placeholder location="classpath*:jdbc.properties" />
	<context:annotation-config />
	<!-- druid 数据管理池配置 -->
	<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
		init-method="init" destroy-method="close">
		<!-- 基本属性 url、user、password -->
		<property name="driverClassName" value="${driver}" />
		<property name="url" value="${jdbc_url}" />
		<property name="username" value="${jdbc_user}" />
		<property name="password" value="${jdbc_password}" />
		<!-- 配置初始化大小、最小、最大 -->
		<property name="initialSize" value="1" />
		<property name="minIdle" value="1" />
		<property name="maxActive" value="20" />
		<!-- 配置获取连接等待超时的时间 -->
		<property name="maxWait" value="60000" />
		<!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
		<property name="timeBetweenEvictionRunsMillis" value="60000" />
		<!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
		<property name="minEvictableIdleTimeMillis" value="300000" />
		<property name="validationQuery" value="SELECT 'x'" />
		<property name="testWhileIdle" value="true" />
		<property name="testOnBorrow" value="false" />
		<property name="testOnReturn" value="false" />
		<!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
		<property name="poolPreparedStatements" value="true" />
		<property name="maxPoolPreparedStatementPerConnectionSize"
			value="20" />
	</bean>
	<context:annotation-config />
	<context:component-scan base-package="com.cwca" />
	<!-- json -->
	<bean id="jsonHttpMessageConverter"
		class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter" />
	<bean id="stringConverter"
		class="org.springframework.http.converter.StringHttpMessageConverter">
		<property name="supportedMediaTypes">
			<list>
				<value>text/plain;charset=UTF-8</value>
			</list>
		</property>
	</bean>
	<!-- 启动Spring MVC的注解功能，完成请求和注解POJO的映射 -->
	<bean
		class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
		<property name="messageConverters">
			<list>
				<ref bean="stringConverter" />
				<ref bean="jsonHttpMessageConverter" />
			</list>
		</property>
	</bean>
	<!-- 前台模版映射 JSP -->
	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="viewNames" value="*.jsp" />
		<!-- 默认转向地址主目录 -->
		<property name="prefix" value="/" />
		<!-- 默认追加后缀 -->
		<property name="suffix" value="" />
		<!-- 默认编码格式 -->
		<property name="contentType" value="text/html; charset=utf-8" />
		<property name="order" value="0"></property>
	</bean>
	<!-- 前台模版映射 freeMarker -->
	<!-- 设置freeMarker的配置文件路径 -->
	<!-- <bean id="freemarkerConfiguration" -->
	<!-- class="org.springframework.beans.factory.config.PropertiesFactoryBean"> -->
	<!-- <property name="location" value="classpath:freemarker.properties" /> -->
	<!-- </bean> -->
	<!-- 配置freeMarker的模板路径 -->
	<!-- <bean id="freemarkerConfig" -->
	<!-- class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer"> -->
	<!--property name="freemarkerSettings" ref="freemarkerConfiguration"/ -->

	<!-- <property name="templateLoaderPath"> -->
	<!-- <value>/</value> -->
	<!-- </property> -->
	<!-- <property name="freemarkerVariables"> -->
	<!-- <map> -->
	<!-- <entry key="xml_escape" value-ref="fmXmlEscape" /> -->
	<!-- </map> -->
	<!-- </property> -->
	<!-- </bean> -->
	<!-- <bean id="fmXmlEscape" class="freemarker.template.utility.XmlEscape" 
		/> -->
	<!-- 配置freeMarker视图解析器 -->
	<!-- <bean id="freemarkerviewResolver" -->
	<!-- class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver"> -->
	<!-- <property name="viewClass" -->
	<!-- value="org.springframework.web.servlet.view.freemarker.FreeMarkerView" 
		/> -->
	<!-- <property name="viewNames" value="*.html" /> -->
	<!-- <property name="contentType" value="text/html; charset=utf-8" /> -->
	<!-- <property name="cache" value="false" /> -->
	<!-- <property name="prefix" value="" /> -->
	<!-- <property name="suffix" value="" /> -->
	<!-- <property name="order" value="1" /> -->
	<!-- <property name="requestContextAttribute" value="request" /> -->
	<!-- </bean> -->
</beans>


```

**注意：** 这个时候要运行一下项目，看是否可以运行，来检查依赖关系和配置问题。如果一次性都配置完成后再运行，很多问题都是不能确定在那个环节上出现问题，对我我们来说加大了调试的难度和浪费了时间


>2.集成 struts2 
 ****
>* 视图的集成相对于 持久层框架 `hibernate` 简单一点，并且方便测试
>* 现阶段很多项目将 struts2 替换为 spring mvc 这样可以减少开发人员的时间和精力，因为 spring 和 spring mvc 为一母同胞，所以在兼容问题不会有什么问题，并且配置很简单，在这个环境中已经集成了 spring mvc

>> 引入jar包

```xml
        <dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-core</artifactId>
			<version>2.3.16</version>
		</dependency>
		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-spring-plugin</artifactId>
			<version>2.3.16</version>
		</dependency>
		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-convention-plugin</artifactId>
			<version>2.3.16</version>
		</dependency>
		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-json-plugin</artifactId>
			<version>2.3.16</version>
		</dependency>
		<dependency>
			<groupId>org.apache.struts.xwork</groupId>
			<artifactId>xwork-core</artifactId>
			<version>2.3.16</version>
		</dependency>

```

**配置文件**

```xml

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE struts PUBLIC
        "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
        "http://struts.apache.org/dtds/struts-2.0.dtd">
<struts>
	<!-- 指定Web应用的默认编码集,相当于调用HttpServletRequest的setCharacterEncoding方法 -->
	<constant name="struts.i18n.encoding" value="UTF-8" />
	<!-- spring 托管 指定Struts的工厂类 -->
	<constant name="struts.objectFactory" value="spring" />
	<!-- 开发模式下使用,这样可以打印出更详细的错误信息 -->
	<constant name="struts.devMode" value="true" />
	<constant name="struts.configuration.xml.reload" value="true" />
	<package name="default" extends="struts-default" namespace="/">
		<!-- 声明拦截器 -->
		<interceptors>
			<!-- 权限拦截器 -->
			<!-- <interceptor name="authority" -->
			<!-- class="us.xuhang.project.interceptor.AuthorityInterceptor" /> -->
			<!-- 声明拦截器栈！解决struts安全漏洞，拦截所有的带有#号的url -->
			<interceptor-stack name="MyStack">
				<interceptor-ref name="params">
					<param name="excludeParams">.*\\u0023.*</param>
				</interceptor-ref>
				<!-- 使用自定义拦截器后就不会再使用默认拦截器栈，这里需要把默认拦截器栈加进来。 -->
				<interceptor-ref name="defaultStack" />
			</interceptor-stack>
		</interceptors>
		<default-interceptor-ref name="MyStack" />
		<!-- 定义全局的result -->
		<global-results>
			<result name="login">/index.jsp</result>
			<result name="error">/error.jsp</result>
		</global-results>
	</package>
	<!-- <include file="struts-action.xml"></include> -->
</struts>


```

>3. Hibernate 4 集成

>>a. 创建 hibernate 配置文件

```properties
hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
hibernate.hbm2ddl.auto=none
hibernate.show_sql=true
hibernate.format_sql=true
hibernate.query.substitutions=true 1, false 0
hibernate.default_batch_fetch_size=16
hibernate.max_fetch_depth=2
hibernate.bytecode.use_reflection_optimizer=true
hibernate.cache.use_second_level_cache=true
hibernate.cache.use_query_cache=true
hibernate.cache.region.factory_class=org.hibernate.cache.EhCacheRegionFactory
net.sf.ehcache.configurationResourceName=ehcache.xml
hibernate.cache.use_structured_entries=true
hibernate.generate_statistics=true
hibernate.current_session_context_class=org.springframework.orm.hibernate3.SpringSessionContext

``` 

>>b.spring 集成配置

```xml

<!-- 引入jdbc配置文件 -->
	<bean id="propertyConfigurer"
		class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="locations">
			<list>
				<value>classpath*:jdbc.properties</value>
				<value>classpath*:hibernate.properties
				</value>
			</list>
		</property>
	</bean>
	<!-- druid 数据管理池配置 -->
	<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
		init-method="init" destroy-method="close">
		<!-- 基本属性 url、user、password -->
		<property name="driverClassName" value="${driver}" />
		<property name="url" value="${jdbc_url}" />
		<property name="username" value="${jdbc_user}" />
		<property name="password" value="${jdbc_password}" />
		<!-- 配置初始化大小、最小、最大 -->
		<property name="initialSize" value="1" />
		<property name="minIdle" value="1" />
		<property name="maxActive" value="20" />
		<!-- 配置获取连接等待超时的时间 -->
		<property name="maxWait" value="60000" />
		<!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
		<property name="timeBetweenEvictionRunsMillis" value="60000" />
		<!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
		<property name="minEvictableIdleTimeMillis" value="300000" />
		<property name="validationQuery" value="SELECT 'x'" />
		<property name="testWhileIdle" value="true" />
		<property name="testOnBorrow" value="false" />
		<property name="testOnReturn" value="false" />
		<!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
		<property name="poolPreparedStatements" value="true" />
		<property name="maxPoolPreparedStatementPerConnectionSize"
			value="20" />
	</bean>
	<bean id="sessionFactory"
		class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="packagesToScan">
			<list>
				<value>com.cwca.ssh</value>
			</list>
		</property>
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect">${hibernate.dialect}</prop>
				<prop key="hibernate.show_sql">${hibernate.show_sql}</prop>
				<prop key="hibernate.format_sql">true</prop>
				<prop key="hibernate.query.substitutions">${hibernate.query.substitutions}</prop>
				<prop key="hibernate.default_batch_fetch_size">${hibernate.default_batch_fetch_size}</prop>
				<prop key="hibernate.max_fetch_depth">${hibernate.max_fetch_depth}</prop>
				<prop key="hibernate.generate_statistics">${hibernate.generate_statistics}</prop>
				<prop key="hibernate.bytecode.use_reflection_optimizer">${hibernate.bytecode.use_reflection_optimizer}</prop>
				<prop key="hibernate.cache.use_second_level_cache">${hibernate.cache.use_second_level_cache}</prop>
				<prop key="hibernate.cache.use_query_cache">${hibernate.cache.use_query_cache}</prop>
				<prop key="hibernate.cache.region.factory_class">${hibernate.cache.region.factory_class}</prop>
				<prop key="net.sf.ehcache.configurationResourceName">${net.sf.ehcache.configurationResourceName}</prop>
				<prop key="hibernate.cache.use_structured_entries">${hibernate.cache.use_structured_entries}</prop>
				<!-- <prop key="hibernate.current_session_context_class">thread</prop> -->
				<!-- <prop key="hibernate.current_session_context_class">${hibernate.current_session_context_class}</prop> -->
			</props>
		</property>
	</bean>
	<bean id="txManager"
		class="org.springframework.orm.hibernate4.HibernateTransactionManager">
		<property name="sessionFactory" ref="sessionFactory" />
	</bean>
	<!-- 开启注解事务 只对当前配置文件有效 -->
	<tx:annotation-driven transaction-manager="txManager"
		proxy-target-class="true" />
	<!-- 开启AOP监听 只对当前配置文件有效 -->
	<aop:aspectj-autoproxy expose-proxy="true" />
	<!-- hibernate 事务配置 -->
	<tx:advice id="txAdvice" transaction-manager="txManager">
		<tx:attributes>
			<tx:method name="save*" propagation="REQUIRED" />
			<tx:method name="add*" propagation="REQUIRED" />
			<tx:method name="create*" propagation="REQUIRED" />
			<tx:method name="insert*" propagation="REQUIRED" />
			<tx:method name="update*" propagation="REQUIRED" />
			<tx:method name="merge*" propagation="REQUIRED" />
			<tx:method name="del*" propagation="REQUIRED" />
			<tx:method name="remove*" propagation="REQUIRED" />
			<tx:method name="put*" propagation="REQUIRED" />
			<tx:method name="use*" propagation="REQUIRED" />
			<tx:method name="get*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="count*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="find*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="list*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="*" propagation="SUPPORTS" read-only="true" />
		</tx:attributes>
	</tx:advice>
	<aop:config expose-proxy="true">
		<!-- 只对业务逻辑层实施事务 -->
		<aop:pointcut id="txPointcut"
			expression="execution(* com.cwca.ssh..service..*.*(..))" />
		<aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut" />
	</aop:config>


```
 
##相关链接

>1.[Struts2 配置项](http://my.oschina.net/5365437/blog/145589) <br/>
>2.[Hibernate 相关问题](http://blog.csdn.net/haifengyouxi/article/details/17374627)<br/>
>3.[Eclipse Git 相关问题](http://blog.csdn.net/pandakong/article/details/7234974)<br/>
>4.[maven 相关问题](http://www.yiibai.com/maven/maven_eclispe_ide.html)