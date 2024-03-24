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

## Data Access Object (DAO)
* Responsible for interfacing with the database
* This is a common design pattern
* Helper class for communicating with database
* DAO Methods:
    * save(..)
    * findById(..)
    * findAll(..)
    * findByLastName(..)
    * update(..)
    * delete(..)
    * deleteAll()
* DAO needs a JPA Entity Manager
* JPA Entity Manager is the main component for saving/retrieving entities

## JPA Entity Manager
* JPA Entity Manager needs a Data Source
* The Data Source defines database connection info
* These are automatically created by Spring Boot.
    * based on the application.properties
* We can autowire/inject the JPA Entity Manager into our DAO
* If you need low-level control and flexibility, use **EntityManager**
* If you want high-level of abstraction, use **JpaRepostiry**

## Example Code

### Create Object
---

```Java
public interface StudentDAO {
    void save(Student theStudent);
}
```
```Java
// Specialized annotation for repositories
// Support component scanning
// Translate JDBC exceptions
@Repository
public class StudentDAOImlp implements StudentDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImlp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
}
```
```Java
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			createStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Created new student object");
		Student student = new Student("Anil", "Ipek", "anildursunipek@gmail.com");

		// save the student object
		System.out.println("Saved to database");
		studentDAO.save(student);

		// display id of the saved student
		System.out.println("Saved student id: " + student.getId());
	}
```

### Read Object
* No need to add @Transactional since we are doing a query
---
```Java
public interface StudentDAO {
    Student find(Integer id);
}
```
```Java
@Override
public Student find(Integer id) {
    // Student.class -> Entity class
    // id -> primary key
    return entityManager.find(Student.class, id);
}
```
```Java
private void readStudent(StudentDAO studentDAO) {
    // Create a student object
    Student student = new Student("Sabah", "Ipek", "sabanipek@gmail.com");

    // Save the student
    studentDAO.save(student);

    // Display the id of the saved student
    int theId = student.getId();
    System.out.println("Saved student id: " + theId);

    // Retrive student based on the id: primary key
    Student tempStudent = studentDAO.find(theId);

    // Display student
    System.out.println(tempStudent);
}
```

## Querying Objects with JPA
* JPA has the JPA query language or JPQL
* Query language for retrieving objects
* Similar in concept to SQL
    * where, like, order by, join, etc...
* JPQL is based on entity name and entity fields

```Java
public interface StudentDAO {
    List<Student> findByLastName(String theLastName);
}
```

```Java
@Override
public List<Student> findByLastName(String theLastName) {
    // Create query
    TypedQuery<Student> theQuery = entityManager.createQuery("From Student WHERE lastName=:theData", Student.class);
    // JPQL Named Parameters are prefixed with a colon :

    // Set query parameters
    theQuery.setParameter("theData", theLastName);

    // Return query results
    return theQuery.getResultList();
}
```
```Java
@Bean
public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
    return runner -> {
        queryForStudentsByLastName(studentDAO);
    };
}
```

```Java
private void queryForStudentsByLastName(StudentDAO studentDAO) {
    // get a list of students
    List<Student> students = studentDAO.findByLastName("Cengiz");

    // display list of students
    for(Student student: students){
        System.out.println(student);
    }
}
```

## Update Object
```Java
public interface StudentDAO {
    void update(Student theStudent);
}
```
```Java
@Override
@Transactional
public void update(Student theStudent) {
    entityManager.merge(theStudent);
}
```
```Java
private void updateStudent(StudentDAO studentDAO) {
    // retrieve student based on the id: primary key
    int studentId = 5;
    System.out.println("Gettin student with id: " + studentId);
    Student student = studentDAO.find(studentId);

    // change first name
    System.out.println("Updating student...");
    student.setFirstName("Ahmet");

    // update the student
    studentDAO.update(student);

    // display the updated student
    System.out.println("Updated student: " + student);
}
```

## Delete Object/Objects
```Java
public interface StudentDAO {
    void deelete(Integer id);
    int deleteAll();
}
```
```Java
@Override
@Transactional
public int deleteAll() {
    int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
    return numRowsDeleted;
}

@Override
@Transactional
public void deelete(Integer id) {
    // retrieve the student
    Student theStudent = entityManager.find(Student.class, id);

    // delete the student
    entityManager.remove(theStudent);
}
```
```Java
private void deleteAllStudents(StudentDAO studentDAO) {
    System.out.println("All students deleting...");
    int numRowsDeleted = studentDAO.deleteAll();
    System.out.println("Deleted row count: " + numRowsDeleted);
}

private void deleteStudent(StudentDAO studentDAO) {
    int studentId = 5;
    System.out.println("Deleting student with id: " + studentId);
    studentDAO.deelete(studentId);
}
```

## Create Database Table from Java Code
* JPA/Hibernate provides an option to automagically create database tables
* Creates tables based on Java code with JPA/Hibernate annotations
* Useful for development and testing
```properties
spring.jpa.hibernate.dll-auto=create
# Database tables are dropped first and then created from scratch
# Modes:
    * none
    * create-only
    * drop
    * create
    * create-drop
    * validate
    * update
```
* For basic projects, can use auto configuration
* Automatic table generation is useful for:
    * Database integration testing with in-memory databases
    * Basic, small hobby projects
* In general, Auto generation is not recommended for enterprise, real-time projects
```

```