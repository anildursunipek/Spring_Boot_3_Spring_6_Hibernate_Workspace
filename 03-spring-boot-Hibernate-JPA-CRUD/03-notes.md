## Hibernate
* A framework for persisting / saving Java objects in a database
* Hibernate handles all of the low level SQL
* Minimize the amount of JDBC code you have to develop
* Hibernate provides the Object-to-Relational Mapping (ORM)
* The developer defines mapping between java class and database table
## JPA (Java/Jakarta Persistence API)
* Standart API for ORM
* Defines a set of interface
* Requires an implementation to be usable
* By having a standart API, you are not locked to vendor's implementation
* Maintain portable, flexible code by coding to JPA spec (interfaces)
* Can theoretically switch vendor implementations
* CRUD
    * Create objects
    * Read objects
    * Update objects
    * Delete objects
* Hibernate / JPA uses JDBC for all database communications
* Hibernate / JPA is just another layer of abstraction on top of JDBC

## Automatic Data Source Configuration
* In Spring Boot, Hibernate is the default implementation of JPA
* **EntityManager** is main component for creating queries. It is from JPA.
* Based on configs, Spring Boot will automatically create the beans:
    * DataSource, EntityManager, ...
* You can then inject these into your app. for example your DAO
* Spring Boot will automatically configure your data source for you
* Based on entries from Maven pom file
* DB connection info from application.properties
* No need to give JDBC driver class name Spring Boot wil automatically detect it based on URL

## Creating Command Line Application
* Executed after the Spring Beans have been loaded
```Java
	@Bean
	public CommandLineRunner commandLineRunner(String[] args){
		return runner -> {
			System.out.println("Hello world!");
		};
```

```properties
# Turn off the Spring Boot banner
spring.main.banner-mode=off

# Reduce logging level. Set loggin level to warn
logging.level.root=warn
```

## JPA Annotations
* **Entity Class**
    * @Entity
    * Java class that is mapped to a database table
    * Must have a public or protected no-argument constructor
    * The class can have other constructor
    * If you don't declare any constructors, java will provide no-argument constructor for free
    * If you declare constructors with arguments then you don't get a no-argument constructor for free
    * In this case, you have to explicitly declare a no-argumnet constructor
    * @Column and @Table annotations are optinal but it is recommended
* **Primary Key**
    * Uniquely identifies each row in a table
    * Must be a unique value
    * Cannot contain NULL values
    * ID generation strategies
    
Name | Description |
--- | --- |
GenerationType.***AUTO*** | Pick an appropriate strategy for the particular database |
GenerationType.***IDENTITY*** | Assign primary keys using database identity column |
GenerationType.***SEQUENCE*** | Assign primary keys using a database sequence  |
GenerationType.***TABLE*** | Assign primary keys using an underlying database table to ensure uniqueness |

```Java
@Entity
@Table(name="Student")
public class Student {
    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    // define constructors
    public Student(){

    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // define getters/setters

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // define toString() method

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

```