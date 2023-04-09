package com.Poc.SpringPoc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Poc.SpringPoc.Entity.studentEntity;
import com.Poc.SpringPoc.repository.jparepo;

@RestController
@RequestMapping("/api")
public class studentController {
	@Autowired
	jparepo repo;

//	url: http://localhost:1111/api/student
//	inputs: {
//	    "studentName":"sai",
//	    "studentEmail":"srinivas@gmail.com",
//	    "studentAddress":"rjy"
//	}
	@PostMapping("/student")
	public ResponseEntity<studentEntity> saveStudent(@RequestBody studentEntity student) {
		System.err.println(student);
		return new ResponseEntity<>(repo.save(student), HttpStatus.CREATED);
	}

	// url: http://localhost:1111/api/allstudents
	@GetMapping("/allstudents")
	public ResponseEntity<List<studentEntity>> getallstudents() {
		System.err.println(repo.findAll());
		return new ResponseEntity<>(repo.findAll(), HttpStatus.CREATED);
	}

	// url: http://localhost:1111/api/allstudents/3
	@GetMapping("/allstudents/{id}")
	public ResponseEntity<studentEntity> getstudents(@PathVariable long id) {
		Optional<studentEntity> student = repo.findById(id);
		if (student.isPresent()) {
			return new ResponseEntity<>(student.get(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

//url: http://localhost:1111/api/allstudents/4
//inputs:{
//    "studentName":"sai",
//    "studentEmail":"srinivas@gmail.com",
//    "studentAddress":"rjy"
//}
	@PutMapping("/allstudents/{id}")
	public ResponseEntity<studentEntity> updateStudents(@PathVariable long id, @RequestBody studentEntity stud) {
		Optional<studentEntity> student = repo.findById(id);
		if (student.isPresent()) {
			student.get().setStudentName(stud.getStudentName());
			student.get().setStudentEmail(stud.getStudentEmail());
			student.get().setStudentAddress(stud.getStudentAddress());
			return new ResponseEntity<>(repo.save(student.get()), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

//	url :http://localhost:1111/api/delstudents/4

	@DeleteMapping("/delstudents/{id}")
	public ResponseEntity<studentEntity> deleteStudents(@PathVariable long id) {
		Optional<studentEntity> student = repo.findById(id);
		if (student.isPresent()) {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
