package com.bytecaptain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bytecaptain.exception.CourseNotFoundException;
import com.bytecaptain.model.Course;
import com.bytecaptain.repository.CourseRepository;
import com.bytecaptain.service.impl.CourseServiceImpl;

@ExtendWith(SpringExtension.class)
public class CourseServiceMockTest {
	
	@Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService = new CourseServiceImpl();

    @Test
    public void getAllCourses() {
    	List<Course> Courses = Arrays.asList(
                new Course(10001, "bytecaptain", "Learn Java"),
                new Course(10002, "bytecaptain", "Learn Spring Boot"));
    	
    	when(courseRepository.findAll()).thenReturn(Courses);
		assertEquals(Courses, courseService.getAllCourses("in28minutes", "test"));
    }
    
    @Test
    public void getCourse() {
    	Course Course = new Course(10001, "bytecaptain", "Learn Java");
    	
    	when(courseRepository.findById(Long.valueOf(10001))).thenReturn(Optional.of(Course));
		assertEquals(Course, courseService.getCourse("bytecaptain", Long.valueOf(10001)));
    }
    
    @Test
    public void getCourseNotFound() {
    	
    	CourseNotFoundException exception = assertThrows(
    			CourseNotFoundException.class,
    	           () -> courseService.getCourse("bytecaptain", Long.valueOf(10001)),
    	           "Course id not found : 10001"
    	    );

    	    assertEquals("Course id not found : 10001", exception.getMessage());
    }
    
    @Test
    public void deleteCourse() {
    	
    	Course Course = new Course(10001, "bytecaptain", "Learn Java");
    	
    	when(courseRepository.findById(Long.valueOf(10001))).thenReturn(Optional.of(Course));
    	courseService.deleteCourse("bytecaptain", Long.valueOf(10001));

		verify(courseRepository, times(1)).deleteById(Long.valueOf(10001));
    }
    
    @Test
    public void updateCourse() {
    	Course Course = new Course(10001, "in28minutes", "Learn Java");
    	
    	when(courseRepository.save(Course)).thenReturn(Course);
		assertEquals(Course, courseService.updateCourse("bytecaptain", Long.valueOf(10001), Course));
    }
    
    @Test
    public void createCourse() {
    	Course Course = new Course(10001, "bytecaptain", "Learn Java");
    	
    	when(courseRepository.save(Course)).thenReturn(Course);
		assertEquals(Course, courseService.createCourse("bytecaptain", Course));

    }

}
