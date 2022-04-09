package com.moodle.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.moodle.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

		Optional<Course> findByIdAndDeletedAtNull(Integer id);

		List<Course> findAllByDeletedAtNull();

}
