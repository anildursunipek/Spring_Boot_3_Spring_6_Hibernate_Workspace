package com.anil.cruddemo.dao;

import com.anil.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Student find(Integer id) {
        // Student.class -> Entity class
        // id -> primary key
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student order by firstName asc", Student.class);
        // Default asc, We can change with desc

        // return query results
        return theQuery.getResultList();
    }

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
}
