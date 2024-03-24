package com.anil.cruddemo;

import com.anil.cruddemo.dao.StudentDAO;
import com.anil.cruddemo.dao.StudentDAOImlp;
import com.anil.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	
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
}
