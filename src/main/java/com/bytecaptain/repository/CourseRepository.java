package com.bytecaptain.repository;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytecaptain.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	List<Course> findByUsername(String username);
	
	// Seacrh from username and description
	List<Course> findByUsernameAndDescriptionContaining(String username, String description);
}