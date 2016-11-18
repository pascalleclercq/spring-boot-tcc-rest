# spring-boot-tcc-rest
Sample demo for Atomikos TCC Rest

To start the coordinator, create a maven project and add this dependency:
```xml
<dependency>
  <groupId>com.atomikos</groupId>
  <artifactId>transactions-tcc-rest</artifactId>
  <version>LATEST</version>
</dependency>
<!-- This dependency is needed if you're using the Jetty container -->
<dependency>
  <groupId>org.apache.cxf</groupId>
  <artifactId>cxf-rt-transports-http-jetty</artifactId>
  <version>3.1.6</version>
  <optional>true</optional>
</dependency>
<dependency>
<groupId>org.apache.cxf</groupId>
   <artifactId>cxf-rt-rs-client</artifactId>
   <version>3.1.6</version>
   <optional>true</optional>
 </dependency>
 <dependency>
   <groupId>org.apache.cxf</groupId>
   <artifactId>cxf-rt-frontend-jaxrs</artifactId>
   <version>3.1.6</version>
   <optional>true</optional>
 </dependency>
 <dependency>
   <groupId>org.apache.cxf</groupId>
   <artifactId>cxf-rt-rs-extension-providers</artifactId>
   <version>3.1.6</version>
   <optional>true</optional>
 </dependency>
 <dependency>
   <groupId>com.fasterxml.jackson.jaxrs</groupId>
   <artifactId>jackson-jaxrs-json-provider</artifactId>
   <version>2.7.4</version>
   <optional>true</optional>
 </dependency>
 <dependency>
   <groupId>org.codehaus.jettison</groupId>
   <artifactId>jettison</artifactId>
   <version>1.3.7</version>
   <optional>true</optional>
 </dependency>
```
where LATEST corresponds to the latest possible version found.

maven will grab automatically all required dependencies transitively.

Configure the maven-exec-plugin :

```xml
<build>
 <plugins>
    ...
	<plugin>
   	<groupId>org.codehaus.mojo</groupId>
   	<artifactId>exec-maven-plugin</artifactId>
   	<version>1.2.1</version>
   	<executions>
    <execution>
      <goals>
      	<goal>exec</goal>
      </goals>
    </execution>
   	</executions>
   	<configuration>
    <executable>java</executable>
    <arguments>
      <argument>-classpath</argument>
      <!-- automatically creates the classpath using all project dependencies, 
      	also adding the project build directory -->
      <classpath />
      <argument>com.atomikos.icatch.tcc.rest.Server</argument>
      <argument>8080</argument>
    </arguments>
   	</configuration>
   </plugin>
```
where 8080 represents the port of the http server.

then simply perform at the root of the project : 
```
mvn exec:exec
```
you should be albe to see : INFOS: Setting the server's publish address to be http://localhost:8080


# Power feature : 
Commercial version of Atomikos provides a simpler way to start the coordinator using one simple command.
