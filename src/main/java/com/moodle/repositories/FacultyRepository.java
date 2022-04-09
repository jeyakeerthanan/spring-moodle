package com.moodle.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moodle.entities.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {

	Optional<Faculty> findByIdAndDeletedAtNull(Integer id);

	List<Faculty> findAllByDeletedAtNull();
}
