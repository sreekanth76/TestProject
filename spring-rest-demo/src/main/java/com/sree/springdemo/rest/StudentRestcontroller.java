package com.sree.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sree.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestcontroller {
	
	List<Student> theStudent;
	
	@PostConstruct
	public void loadData() {
          theStudent = new ArrayList<>();
		
		theStudent.add(new Student("Sreekanth","Singam"));
		theStudent.add(new Student("Sukku","Singam"));
		theStudent.add(new Student("Machi","Kanala"));
		theStudent.add(new Student("Chaithu","kanala"));
		
		
	}
	
	@GetMapping("/student{studentId}")
	
	public Student getStudents(@PathVariable int studentId)
	{
		
		if ((studentId>=theStudent.size() || studentId<0))
		throw new StudentNotFoundException("Student Id not Found" +theStudent);
		
		return theStudent.get(studentId);
	}

	 @ExceptionHandler
	 
	  public ResponseEntity<StudentErrorResponse> handler(StudentNotFoundException exe){
		 
		 StudentErrorResponse obj = new StudentErrorResponse();
		 
		 obj.setStatus(HttpStatus.NOT_FOUND.value());
		 obj.setMessage(exe.getMessage());
		 obj.setTimeStamp(System.currentTimeMillis());
		 
		 return new ResponseEntity<StudentErrorResponse>(obj, HttpStatus.NOT_FOUND);
	 }
	
	
}
