package com.moodle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.moodle.entities.Course;
import com.moodle.repositories.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	public Course getCourseById(Integer id) {
		return courseRepository.findByIdAndDeletedAtNull(id).orElse(null);
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAllByDeletedAtNull();
	}

	

}
