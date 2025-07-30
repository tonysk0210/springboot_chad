package com.example.demo.rest;

import com.example.demo.entity.Student;
import com.example.demo.entity.StudentErrorResponse;
import com.example.demo.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init() {
        students.add(new Student("Poornima", "Petal", "p@gmail.com"));
        students.add(new Student("Mario", "Rossi", "m@gmail.com"));
        students.add(new Student("Mary", "Smith", "mary@gmail.com"));
    }

    @GetMapping("/students")
    public List<Student> students() {
        return students;
    }

    //define endpoint /students/{studentId} retrieve student at index of the json array
    @GetMapping("/students/{studentId}")
    public Student student(@PathVariable int studentId) {

        //check studentId against list size, and throws customized exception
        if ((studentId >= students.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id " + studentId + " not found");
        }
        return students.get(studentId);
    }

    //add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception) {

        //create a StudentErrorResponse
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setMessage(exception.getMessage());
        studentErrorResponse.setTimestamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
    }
}
