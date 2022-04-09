package com.moodle.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.moodle.entities.Student;
import com.moodle.entities.User;
import com.moodle.repositories.StudentRepository;
import com.moodle.repositories.UserRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	

	@Autowired
	private UserRepository userRepository;
	
	public Student saveStudent(Student student) {
		return studentRepository.save(student);	}

	public Page<Student> getStudents(Pageable paginationRequest, String search) {
		// TODO Auto-generated method stub
		return studentRepository.getAllStudents(paginationRequest,search);
	}

	public Student getStudentByUserId(Integer id) {
		return studentRepository.findByUserIdAndDeletedAtNull(id);
	}

	public String deleteStudent(Integer id) {
		Student existingStudent = studentRepository.findByUserIdAndDeletedAtNull(id);
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingStudent == null) {
			return null;
		}
		if (existingUser == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		existingStudent.setDeletedAt(cal);
		studentRepository.save(existingStudent);

		existingUser.setDeletedAt(cal);
		existingUser.setActive(false);
		userRepository.save(existingUser);
		return "Student deleted successfully!!!";
	}
	
	

}
