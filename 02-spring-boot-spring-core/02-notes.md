# Inversion of Control
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

# Dependency Injection
* The dependency inversion principle
* The client delegates to another object the responsibility of providing its dependencies.
* Inject the given dependencies or helper components for a given object.
* Injection Types:
    1. Constructor Injection
        * Use this when you have required dependencies
    1. Setter Injection
        * Use this when you have optional dependencies

# AutoWiring
* For dependency injection, Spring can use autowiring.
* Spring will look for a class that matches
    * matches by type: class or interface
* Spring will inject it automatically .. hence it is autowired
* Spring will scan for component

# Constructor Injection (CI)
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
