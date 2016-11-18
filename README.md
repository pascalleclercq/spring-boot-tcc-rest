# spring-boot-tcc-rest
Sample demo for Atomikos TCC Rest

To start the coordinator, create a maven project and add this dependency:
```xml
<dependency>
  <groupId>com.atomikos</groupId>
  <artifactId>transactions-tcc-rest</artifactId>
  <version>LATEST</version>
</dependency>
```
where LATEST correspond to the latest possible version found.

maven will grab automatically all required dependencies transitively.

Then start the Coordinator HTTP server like this :
java com.atomikos.icatch.tcc.rest.Server 8080

where 8080 reoresents the port of the http server.
