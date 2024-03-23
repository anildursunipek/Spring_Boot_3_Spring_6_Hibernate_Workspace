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