package com.anil.cruddemo;

import com.anil.cruddemo.dao.StudentDAO;
import com.anil.cruddemo.dao.StudentDAOImlp;
import com.anil.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.Jsr310Converters;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			// createStudent(studentDAO);

			// readStudent(studentDAO);

			// queryForStudents(studentDAO);

			queryForStudentsByLastName(studentDAO);
		};
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> students = studentDAO.findByLastName("Cengiz");

		// display list of students
		for(Student student: students){
			System.out.println(student);
		}
	}


	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();

		for(Student student: students){
			System.out.println(student);
		}
	}

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
