package com.example.demo;

import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            //createStudent(studentDAO);
            //createMultipleStudent(studentDAO);
            //readStudent(studentDAO);
            //readAllStudents(studentDAO);
            //readByLastName(studentDAO);
            //updateStudent(studentDAO);
            //deleteStudent(studentDAO);
            //deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all students...");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("numRowsDeleted: " + numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        System.out.println("Deleting student object...");
        studentDAO.delete(1);
    }

    private void updateStudent(StudentDAO studentDAO) {
        //retrieve student based on id
        System.out.println("Getting student object...");
        Student student = studentDAO.findById(1);

        //change last name to Test
        student.setLastName("ZZZ");

        //update the student
        studentDAO.update(student);

        //display updated student
        System.out.println(student);
    }

    private void readByLastName(StudentDAO studentDAO) {
        System.out.println("Retrieving student object by last name...");
        List<Student> students = studentDAO.findByLastName("Doe");
        for (var student : students) {
            System.out.println(student);
        }
    }

    private void readAllStudents(StudentDAO studentDAO) {
        System.out.println("Reading all students...");
        List<Student> students = studentDAO.findAll();

        for (var student : students) {
            System.out.println(student);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        System.out.println("Retrieving student object...");
        Student student = studentDAO.findById(1); //Student(id=1, firstName=Paul, lastName=Doe, email=Paul@luv2code.com)
        System.out.println(student);
    }

    private void createMultipleStudent(StudentDAO studentDAO) {
        //create multiple students
        System.out.println("Creating 3 student objects...");
        var tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        var tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
        var tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

        //save all the students
        System.out.println("Saving a student...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);

    }

    private void createStudent(StudentDAO studentDAO) {
        //create student object
        System.out.println("Creating new student object...");
        var tempStudent = new Student("Paul", "Doe", "Paul@luv2code.com");

        //save student object
        System.out.println("Saving a student...");
        studentDAO.save(tempStudent);

        //display id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }


}
