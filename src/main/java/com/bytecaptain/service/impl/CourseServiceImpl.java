package com.bytecaptain.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytecaptain.exception.CourseNotFoundException;
import com.bytecaptain.model.Course;
import com.bytecaptain.repository.CourseRepository;
import com.bytecaptain.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
    @Autowired 
    private CourseRepository courseRepository;

   	@Override
	public List<Course> getAllCourses(String username) {
   		//TODO maybe next for rest api development
   		logger.trace("Entered getAllCourses method");
   		
   		List<Course> courses = courseRepository.findAll();

		return courses;
	}

	@Override
	public Course getCourse(String username, long id) {
		return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
	}

	@Override
	public void deleteCourse(String username, long id) {
		courseRepository.deleteById(id);
	}

	@Override
	public Course updateCourse(String username, long id, Course course) {
		Course courseUpdated = courseRepository.save(course);
		return courseUpdated;
	}

	@Override
	public Course createCourse(String username, Course course) {
		Course createdCourse = courseRepository.save(course);
		return createdCourse;
		
	}
    
}
