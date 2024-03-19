# Section 1

## Spring Boot Overwiev
* Provides a large number of helper classes and annotations.
* Make it easier to get started with Spring development.
* Minimize tha amount of manual configuration.
* Perform auto-configuration.
* Helps to resolve dependency conflicts.
* Provided embeded HTTP server. (Tomcat, Jetty, ...)
    * No need to install a server separately.
    * JAR file includes your app code and server.
* Spring Boot uses Spring behind.
* Simply makes it easier to use Spring.
* [Spring Initializr [https://start.spring.io/]](https://start.spring.io/)
    * Quickly create a starter Spring Boot project.
    * Select dependencies.
    * Create a Maven/Gradle project.
* Spring Boot apps can be run standalone.
* Run the Spring Boot app from the IDE or CLI.
    * Example: java -jar myapp.jar
* Spring Boot apps can aslo be deployed in the traditional way.
    * Deploy WAR(Web Application Archive) to an external server. No need to HTTP server. It inclued only code!!!
* Spring Boot uses same code of Spring Framework.

## Spring Initializr
* One approach is to download the JAR files from each project web site, Manually add the JAR files to your build path.
* Seceond approach is when building your java project, Maven will go out and download the Jar files for your project. (Spring, Hibernate, etc.)

## REST Controller
* It is a very simple REST controller
```java
@RestController /* Set up the rest controller*/
public class DemoRestController{
    @GetMapping('/') /* Handle the get request */
    public String sayHello(){
        return "Hello World!";
    }
}
```
* Goals of Spring
    1. Lightweigh development with Java POJOs (Plain-Old-Java_Objects)
    1. Dependency injection to promote loose coupling
    1. Minimize boilerplate Java Code

* Spring projects: Additional Spring modules built-on top of the core Spring Framework.

* What is Maven?
    * It is a friendly helper!
    * Project management tool
    * Most popular use is for build management and dependenceis.
    * When you generate projects using Spring Initializr, It can generate Maven project for you.
    * Maven makes Jar files available during compile / run.
    * If you don't use Maven, You have to download JAR files manually. It takes a lot of time.
    * How it works
        1. Read config file
        1. Check local repo
        1. If it is not in local repo, get from remote repo
        1. Save in local repo
        1. Build and Run
    * When Maven retrieves a project dependency it will also downlaod supporting dependencies!!!
    * When you build and run your app Maven will handle class and build path for you.
    * Maven projects are portable. Developers can easily share projects between IDEs.
* Standart Directory Structure
    * **src/main/java/**: Your Java source code
    * **src/main/resources**: Properties / config files used by your app
    * **pom.xml**: Maven configuration file (Project Object Model) like shopping list :)
    * **src/main/webapp**: JSP files and web config files other web assets (image, css, js, etc.)
    * **src/test**: Unit testing code and properties
    * **target**: Destination directory for compiled code. Automatically created by Maven.

* POM.xml file structure
    * Project meta data
    * Dependencies
    * Plug ins: Additional custom tasks to run.
    * Project Coordinates
    ```xml
    <groupId>com.anilipek.springboot.demo</groupId> <!--Company / Domain name-->
    <artifactId>springbootdemo</artifactId> <!--Project Name-->
    <version>0.0.1-SNAPSHOT</version> <!--Version-->
    ```
    * SNAPSHOT means that this work still under active development.
    * To add given dependency project, we need Group ID, Artifact ID, version (optional). Best practice is to include the version.
    ```xml
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

    ```
    * **GAV**: **G**roup ID, **A**rtifact ID and **V**ersion

* Maven Standart Directory Structure (Spring Boot)
    * **mvnw** allows you to run a Maven project
        * No need to have Maven installed or present on your path
        * If correct version of Maven is Not found on your computer Automatically downloads correct version and runs Maven.
    * **pom.xml**
    * **application.properties** created by Spring Initializr
        * Spring Boot load properties from "application.properties"
        * Ex: server.port=8555
        * Also you can add your custom properties
        * team.name = Crew
            ```java
            /* Inject the team name*/   
            @Value("${team.name}")
            private string teamName;
            ```
## Template Engines
* FreeMarker
* Thymeleaf (Popular)
* Mustache

## Spring Boot Starter
* A collection of dependencies grouped together
* Tested and verified by the Spring Dev. Team
* Ex: spring-boot-starter-web
    * spring-web
    * spring-webmvc
    * hibernate-validator
    * json
    * tomcat

## Spring Boot Starter Parent
* Spring Boot provides a "Starter Parent"
* It provides dependency management
* You dont have to specify every starter  version. Just specify starter parent version and the other starters will be compatible. Starter dependencies inherit version from parent.
```xml
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.2.3</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
```
* This is a special starter that provides Maven default
* Default compiler level,  UTF-8 source encoding, ...
* Specify  your  Java version.
    ```xml
        <properties>
            <java.version>17</java.version>
        </properties>
    ```

## Spring Boot Dev Tools
* spring-boot-devtools automatically restart your application when code is updated.
```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
	</dependency>
```

# Spring Boot Actuator
* Exposes endpoints to monitor and manage your application.
* You easily get DevOps functionality out-of-the-box.
* Simply add the dependency to your POM file.
* REST endpoint are automatically to added your application.
```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-actuator</artifactId>
	</dependency>
```
* By default only /health endpoint is exposed!
* Endpoints are prefixed with: /actuator
    * /healt: Health information about your application.
    * /info: Provide information about your application.
    * /beans
    * /mappings
    * /threaddupm
    * There are many actuator actuator endpoints.
    * http://localhost:8080/actuator/info
```xml
    <!--ENABLE /info endpoint-->
    management.endpoints.web.exposure.include=health,info 
    management.info.env.enabled=true
    <!--Define new information for your app-->
    info.app.name = Actuator test
    info.app.name.description = Test application running!
    info.app.version = 1.0.0
    <!--It will expose all of the Spring Boot Actuator endpoints over the web-->
    management.endpoints.web.exposure.include=*
```










