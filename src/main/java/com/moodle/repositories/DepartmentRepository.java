package com.moodle.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moodle.entities.Department;

public interface DepartmentRepository extends JpaRepository <Department, Integer> {
	Optional<Department> findByIdAndDeletedAtNull(Integer id);

	List<Department> findAllByDeletedAtNull();

}
