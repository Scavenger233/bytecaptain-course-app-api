package com.bytecaptain.controller;

import java.net.URI;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bytecaptain.model.Course;
import com.bytecaptain.service.CourseService;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@GetMapping("/instructors/{username}/courses")
	public List<Course> getAllCourses(
	        @PathVariable String username, 
	        @RequestParam(required = false) String searchQuery) {
	    
	      return courseService.getAllCourses(username, searchQuery);
	}

	@GetMapping("/instructors/{username}/courses/{id}")
	public Course getCourse(@PathVariable String username, @PathVariable long id) {
		
		Course course = courseService.getCourse(username, id);
		return course;
	}
	
	@DeleteMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable String username, @PathVariable long id) {

		//TODO not found
		courseService.deleteCourse(username, id);

		ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();
		return responseEntity;
	}

	@PutMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable String username, @PathVariable long id,
			@RequestBody Course course) {
		course.setId(id); //make sure course id is syncing
		course.setUsername(username);
		Course courseUpdated = courseService.updateCourse(username, id, course);
		
		ResponseEntity<Course> responseEntity = new ResponseEntity<Course>(courseUpdated, HttpStatus.OK);

		return responseEntity;
	}

	@PostMapping("/instructors/{username}/courses")
	public ResponseEntity<Void> createCourse(@PathVariable String username, @RequestBody Course course) {
		
		course.setUsername(username);

		//TODO add already exists
		//TODO maybe add UI how to handle exceptions
		Course createdCourse = courseService.createCourse(username, course);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
				.toUri(); 

		return ResponseEntity.created(uri).build();
	}

}