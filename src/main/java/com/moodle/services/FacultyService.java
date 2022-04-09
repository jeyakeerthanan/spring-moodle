package com.moodle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.moodle.entities.Faculty;
import com.moodle.repositories.FacultyRepository;

@Service
public class FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;
	
	
	public Faculty getFacultyById(Integer id) {
		return facultyRepository.findByIdAndDeletedAtNull(id).orElse(null);
	}


	public List<Faculty> getAllFaculty() {
		return facultyRepository.findAllByDeletedAtNull();
	}

}
