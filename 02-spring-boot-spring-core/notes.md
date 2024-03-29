## Inversion of Control
* Textboox definition: The approach of outsourcing the construction and management of objects.
* App should be configurable.
* Easily change the object for another object.
* Object factory gives a object.
* Primary functions:
    * Create and manage objects (IoC)
    * Inject object dependencies (DI)
* Configuring Spring Container
    * Xml file (legacy)
    * Java Annotations (modern)
    * Java Source Code (moders)
* IoC container

## Dependency Injection
* The dependency inversion principle
* The client delegates to another object the responsibility of providing its dependencies.
* Inject the given dependencies or helper components for a given object.
* Injection Types:
    1. Constructor Injection
        * Use this when you have required dependencies
    1. Setter Injection
        * Use this when you have optional dependencies

## AutoWiring
* For dependency injection, Spring can use autowiring.
* Spring will look for a class that matches
    * matches by type: class or interface
* Spring will inject it automatically .. hence it is autowired
* Spring will scan for component

## Constructor Injection (CI)
* The Spring Framework will perform operations behind ther scenes for you.
1. Define the dependency interface and class
1. Create Demo REST Controller
1. Create a constructor in your class for injections
1. Add @GetMapping 
```java
public interface Coach{
    String getDailyWorkout();
}

@Component
public class CricketCoach implements Coach{
    @Override
    public String getDailyWorkout(){
        return "Some String Here..."
    }
}
```
* **@Component** annotation marks the class as a Spring Bean
* A Spring Bean is just a regular Java class that is managed by Spring
* **@Component** also makes the bean available for Dependency Injection
* **@Autowired** tells spring to inject the dependency
* If you have one constructor @Autowired annotation is optional!!!

```Java
@RestController
public class DemoController{
    
    private Coach myCoach;
    
    @Autowired
    public DemoController(Coach theCoach){
        myCoach = theCoach;
    }

    @GetMapping("dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
```

## Component Scanning
* Spring will scan your Java classes for special annotations such as @Component.
* Automatically register the beans in the Spring container 
* **SpringBootAplication** enables auto configuration, component scanning and additional configuration
    1. @EnableAutoConfiguration 
    1. @ComponentScan
    1. @Configuration

* **SpringBootAplication**: Behin the scenes creates application context and registers all beans. Starts the embedded server Tomcat etc.
* Starts components scanning from same package as your main Spriing Boot application and also scans sub-packages recursively

## Setter Injection (CI)
* Inject dependencies by calling your setter method(s) on your calass
* The Spring Framework will perform operations behind the scenes for you
* Inject dependencies by calling ANY method on your class
1. Create setter method(s) in your class for injection.
1. Configure the dependency injection with @Autowired annotation
```Java
@RestController
public class DemoController{
    
    private Coach myCoach;

    @Autowired
    public void setCoach(Coach theCoach){
        this.myCoach = theCoach;
    }

    @GetMapping("dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
```

## Field Injection (deprecated)
* In the early days, field injection was popular on Spring projects
* In recent years, it has fallen out of favor
* In general, it makes the code harder to unit test
* A a result, the spring.io team does not recommend field injection
* You will see it being used on legacy projects
```Java
@Autowired
private Coach myCoach;
// no need for constructors or setters
```

## Qualifiers
* If there is more than one bean, we may receive an error. To solve this problem, **Qualifier** is used.
```Java
@Autowired
public DemoController(@Qualifier("tenisCoach") Coach theCoach){
    this.myCoach = theCoach;
}
```
* The qualifier name is the same as the class name. Just it starts with lower-case.

## Primary
* When using **@Primary**, can have only one for multiple implementation. If you mark multiple classes with @Primary it is a problem.
```Java
@Component
@Primary
public class TennisCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 30 minutes";
    }
}
```
* If you mix @Primary and @Qualifier
    * @Qualifier has higher priority
* @Primary could have   the issue of multiple @Primary classes leading to an error
* @Qualifier allows to you be very specific on which bean you want
* In general using @Qualifier is recommended
    * More specific
    * Higher priority

## Initialization
* By default, when your application starts, all beans are intialized
    * @Component, etc.
* Spring will create an instance of each and make them available

## Lazy Initialization
* Instead of creating all beans up front, we can specify lazy initialization
* All beans will only be initialized in the following cases:
    * It is needed for DI
    * It is explicitly requested
* Add the @Lazy annotation to a given class
* It is global property (default=false). No beans are created until needed!
```
    spring.main.lazy-initialization = true
```
* Advantages:
    * Only create objects as needed
    * May help with faster startup time if you have large number of components
* Disadvantages:
    * If you have web related components like @RestController, not created until requested
    * May not discover configuration issues until too late
    * Need to make sure you have enough memory for all beans once created

## Bean Scopes
* Scopre refers to the lifecycle of a bean
    * How long does the bean live?
    * How many instances are created?
    * How is the bean shared?
* Default scope is **Singleton**
    * Spring Container creates only one instance of the bean, by default
    * It is cached in memory
    * All dependency injections for the bean will reference the **SAME** bean
* **Prototype** Scope
    * New object instance for each injection
    * In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean: the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance.

    * Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.

    * Prototype Beans and Lazy Initialization
    Prototype beans are lazy by default. There is no need to use the @Lazy annotation for prototype scopes beans.
```Java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TennisCoach implements Coach{
    //...
}
```

## Bean Lifecycle Methods - Annotations
* Bean Lifecycle:
    1. Container Started
    1. Bean Instantiated
    1. Dependencies Injected
    1. Internal Spring Processing
    1. Your Custom Inıt Method
    1. Bean is Reay for Use
* Container Shutdown
    1. Your custom destroy method
    1. Stop!
* You can add custom code during bean initialization
    * Calling custom business logic methods
    * Setting up handles to resources(db, sockets, file etc.)
* You can add custom code during bean destruction
    * Calling custom business logic method
    * Clean up handles to resources (db, sockets, file etc.)
```Java
    @PostConstruct
    public void doMyStartupStaff(){
        System.out.println("In doMyStartupStaff(): "+ getClass().getSimpleName());
    }

    @PreDestroy
    public void doMyCleanupStaff(){
        System.out.println("In doMyCleanupStaff(): "+ getClass().getSimpleName());
    }
```

## Configuring Beans with Java Code
* **Make an existing third-party class available to Spring Framework**
* You moy not have access to the source code of third-party class
* However, you would like to use the third-party class as a Spring Bean
* We can modify third-party class (like Amazon S3 Client. It is part of AWS SDK)
* Development Process:
    1. Create @Configuration class
    1. Define @Bean method to configure the bean
    1. Inject the bean into our controller
```Java
@Configuration
public class SportConfig {

    @Bean("aquatic") // Custom bean id
    public Coach swimCoach(){
        return new SwimCoach();
    }
}

//...

@Autowired
public DemoController(@Qualifier("aquatic") Coach theCoach)
{
    System.out.println("In Constructor: " + getClass().getSimpleName());
    this.myCoach = theCoach;
}
```
