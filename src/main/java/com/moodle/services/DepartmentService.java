package com.moodle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.moodle.entities.Department;
import com.moodle.repositories.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	public Department getDepartmentById(Integer id) {
		return departmentRepository.findByIdAndDeletedAtNull(id).orElse(null);
	}

	public List<Department> getAllDepartment() {
		return departmentRepository.findAllByDeletedAtNull();
	}
}
