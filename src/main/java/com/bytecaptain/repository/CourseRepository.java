package com.bytecaptain.repository;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bytecaptain.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	List<Course> findByUsername(String username);
	
	@Query("SELECT c FROM Course c WHERE c.username LIKE %:searchQuery% OR c.description LIKE %:searchQuery%")
    List<Course> findByUsernameOrDescriptionContaining(@Param("searchQuery") String searchQuery);
}