package com.moodle.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.moodle.entities.Batch;
import com.moodle.entities.Course;
import com.moodle.entities.Department;
import com.moodle.entities.Faculty;
import com.moodle.entities.Student;
import com.moodle.entities.User;
import com.moodle.services.BatchService;
import com.moodle.services.CourseService;
import com.moodle.services.DepartmentService;
import com.moodle.services.FacultyService;
import com.moodle.services.StudentService;
import com.moodle.services.UserService;
import com.moodle.settings.Constants;
import com.moodle.settings.GenericResponse;
import com.moodle.settings.Pagination;
import com.moodle.settings.Validator;


@RestController
@RequestMapping(Constants.BASE_URI)
public class StudentController {
	

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private BatchService batchService;
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/students")
	public ResponseEntity<?> addJobSeeker( @RequestBody Student student)  {
		try {
		if (student.getUser() == null ) {
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg("User data Not Found").statusCode(Constants.HTTP_BAD_REQUEST_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error("Invalid users").entity();
		
	}
		
		
		if (student.getUser() != null && student.getUser().getEmail() == null) {
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg("Email Cannot be  Empty").statusCode(Constants.HTTP_BAD_REQUEST_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error("Email cannot be empty.").entity();
		
	}
		
		 if (student.getUser() != null && student.getUser().getFirstName() == null) {
				return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
						.msg("First Name cannot be empty ").statusCode(Constants.HTTP_BAD_REQUEST_CODE)
						.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error("First Name cannot be empty.").entity();
			
		}
		if (student.getUser() != null && student.getUser().getPassword() == null) {
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg("Password Cannot be  Empty").statusCode(Constants.HTTP_BAD_REQUEST_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error("password cannot be empty.").entity();
		
	}
		if (student.getUser() != null && student.getUser().getContactPrimary() == null) {
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg("Contact Cannot be  Empty").statusCode(Constants.HTTP_BAD_REQUEST_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error("Contact cannot be empty.").entity();
		
	}

		
		if (student.getDob()==null) {
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg("Date of Birth Cannot be  Empty").statusCode(Constants.HTTP_BAD_REQUEST_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error("DOB cannot be empty.").entity();
		
	}
		
		
		if (student.getUser() != null && student.getUser().getEmail() != null) {
			if (!Validator.isValid(student.getUser().getEmail())) {
				return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED).msg("Invalid email")
						.statusCode(Constants.HTTP_BAD_REQUEST_CODE).isSuccess(Constants.HTTP_RESULT_FAILED_BOOL)
						.error("Invalid email.").entity();
			}
			User getAlreadyExistUserEmail = userService.getOneByEmail(student.getUser().getEmail());
			if (getAlreadyExistUserEmail != null) {
				return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
						.msg(Validator.uniqueValidationMessage("Email")).statusCode(Constants.HTTP_BAD_REQUEST_CODE)
						.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error("This email has been already taken.")
						.entity();
			}
			
		}
		
		
		

		String primary_number=student.getUser().getContactPrimary();
		if (student.getUser().getContactSecondary()!=null) {
			String secondary_number=student.getUser().getContactSecondary();

			if (!secondary_number.matches("\\d{10}")) {
			        return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED).msg("Invalid Secondary Phone number")
								.statusCode(Constants.HTTP_BAD_REQUEST_CODE).isSuccess(Constants.HTTP_RESULT_FAILED_BOOL)
									.error("Invalid phone.").entity();	
							}
				
}
		
			if (!primary_number.matches("\\d{10}")) {
					return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED).msg("Invalid Primary Phone number")
					.statusCode(Constants.HTTP_BAD_REQUEST_CODE).isSuccess(Constants.HTTP_RESULT_FAILED_BOOL)
					.error("Invalid phone.").entity();	
			}
			
			
		if (student.getUser() != null && student.getUser().getContactPrimary() != null) {
			User getAlreadyExistUserContactPrimary = userService
					.getOneByContactPrimary(student.getUser().getContactPrimary());
			if (getAlreadyExistUserContactPrimary != null) {
				return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
						.msg(Validator.uniqueValidationMessage("Contact Primary"))
						.statusCode(Constants.HTTP_BAD_REQUEST_CODE).isSuccess(Constants.HTTP_RESULT_FAILED_BOOL)
						.error("This contact primary has been already taken.").entity();
			}
		} 
		
		
			
			
			if (student.getFaculty()==null || student.getFaculty().getId()==null  ){
				 return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED).msg("Please add your Faculty")
							.statusCode(Constants.HTTP_BAD_REQUEST_CODE).isSuccess(Constants.HTTP_RESULT_FAILED_BOOL)
								.error("Invalid Faculty Reference").entity();	
			}
			
			if ( student.getDepartment()==null || student.getDepartment().getId()==null){
				 return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED).msg("Please add your Department")
							.statusCode(Constants.HTTP_BAD_REQUEST_CODE).isSuccess(Constants.HTTP_RESULT_FAILED_BOOL)
								.error("Invalid Department Reference").entity();	
			}
			
			
			
			if (student.getBatch()==null || student.getBatch().getId()==null){
				 return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED).msg("Please add your Batch")
							.statusCode(Constants.HTTP_BAD_REQUEST_CODE).isSuccess(Constants.HTTP_RESULT_FAILED_BOOL)
								.error("Invalid Batch Reference").entity();	
			}
			
			if (student.getCourse()==null || student.getCourse().getId()==null){
				 return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED).msg("Please add your faculty")
							.statusCode(Constants.HTTP_BAD_REQUEST_CODE).isSuccess(Constants.HTTP_RESULT_FAILED_BOOL)
								.error("Invalid faculty Reference").entity();	
			}
			
			Faculty existingObjectFac = null;
			Department existingObjectDep = null;
			Batch existingObjectBat = null;
			Course existingObjectCou = null;
			
			if (student.getCourse() != null && student.getCourse().getId() != null) {
				existingObjectCou = courseService.getCourseById(student.getCourse().getId());
				student.setCourse(existingObjectCou);
			}
			if (student.getFaculty() != null && student.getFaculty().getId() != null) {
				existingObjectFac = facultyService.getFacultyById(student.getFaculty().getId());
				student.setFaculty(existingObjectFac);
			}
			if (student.getDepartment() != null && student.getDepartment().getId() != null) {
				existingObjectDep = departmentService.getDepartmentById(student.getDepartment().getId());
				student.setDepartment(existingObjectDep);
			}
			if (student.getBatch() != null && student.getBatch().getId() != null) {
				existingObjectBat = batchService.getBatchById(student.getBatch().getId());
				student.setBatch(existingObjectBat);
			}
			
	        	
					
		
		
		System.out.println("shsjs"+student.getDepartment().getId());
		User userData = student.getUser();
		Integer maxId =userService.getMaxId("UOK");
	
		userData.setActive(false);
		userData.setCode("UOK" + (100000 + maxId));
		//save user
		User saveUser = userService.saveUser(userData);
		
		//User existingObject = userService.getOneById(jobSeeker.getUser().getId());
		saveUser.setType("student");
		student.setUser(saveUser);
		
        
		student.setActive(false);
		Student saveStudent = studentService.saveStudent(student);
	
       
		return GenericResponse.builder().status(Constants.HTTP_RESULT_SUCCESS)
				.msg("Student added successfully!!!").statusCode(Constants.HTTP_SUCCESS_CODE)
				.isSuccess(Constants.HTTP_RESULT_SUCCESS_BOOL).data(saveStudent).entity();}
		
		catch (Exception e) {
			e.printStackTrace();
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg(Constants.HTTP_EXPECTATION_FAILED_MESSAGE).statusCode(Constants.HTTP_EXPECTATION_FAILED_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error(e.toString()).entity();

		}
	}

	
	@GetMapping("/students")
	public ResponseEntity<?> getAllJobSeekers(@RequestParam(defaultValue = "0", required = false) Integer page, String search) {
		try {
			Page<?> getJobSeekers = studentService.getStudents(Pagination.paginationRequest(page),search);
			return GenericResponse.builder().status(Constants.HTTP_RESULT_SUCCESS)
					.msg("students get successfully!").statusCode(Constants.HTTP_SUCCESS_CODE)
					.isSuccess(Constants.HTTP_RESULT_SUCCESS_BOOL).data(Pagination.paginatedData(getJobSeekers))
					.entity();

		} catch (Exception e) {
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg(Constants.HTTP_EXPECTATION_FAILED_MESSAGE).statusCode(Constants.HTTP_EXPECTATION_FAILED_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error(e.toString()).entity();

		}
	}
	
	
	@GetMapping("/students/{id}")
	public ResponseEntity<?> getJobSeekerById(@PathVariable Integer id) {
		try {
			Student getStudent = studentService.getStudentByUserId(id);

			if (getStudent == null) {
				return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
						.msg("Student get by Id " + id + " not found").statusCode(Constants.HTTP_NOTFOUND_CODE)
						.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error("Not found").entity();

			}
			return GenericResponse.builder().status(Constants.HTTP_RESULT_SUCCESS)
					.msg("Student get successfully!").statusCode(Constants.HTTP_SUCCESS_CODE)
					.isSuccess(Constants.HTTP_RESULT_SUCCESS_BOOL).data(getStudent).entity();

		} catch (Exception e) {
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg(Constants.HTTP_EXPECTATION_FAILED_MESSAGE).statusCode(Constants.HTTP_EXPECTATION_FAILED_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error(e.toString()).entity();

		}

	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<?> deleteJobSeekerId(@PathVariable Integer id) {
		try {
			String getJobSeeker = studentService.deleteStudent(id);

			if (getJobSeeker == null) {
				return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
						.msg("Job seeker get by Id " + id + " not found").statusCode(Constants.HTTP_NOTFOUND_CODE)
						.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error("Not found").entity();
			}

			return GenericResponse.builder().status(Constants.HTTP_RESULT_SUCCESS).msg(getJobSeeker)
					.statusCode(Constants.HTTP_SUCCESS_CODE).isSuccess(Constants.HTTP_RESULT_SUCCESS_BOOL)
					.data(getJobSeeker).entity();

		} catch (Exception e) {
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg(Constants.HTTP_EXPECTATION_FAILED_MESSAGE).statusCode(Constants.HTTP_EXPECTATION_FAILED_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error(e.toString()).entity();

		}
	}
	
	
	@GetMapping("/test")
	public ResponseEntity<?> testServer() {
		try {
			
			return GenericResponse.builder().status(Constants.HTTP_RESULT_SUCCESS)
					.msg("students get successfully!").statusCode(Constants.HTTP_SUCCESS_CODE)
					.isSuccess(Constants.HTTP_RESULT_SUCCESS_BOOL).data("hello JK")
					.entity();

		} catch (Exception e) {
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg(Constants.HTTP_EXPECTATION_FAILED_MESSAGE).statusCode(Constants.HTTP_EXPECTATION_FAILED_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error(e.toString()).entity();

		}
	}
	
	
	@GetMapping("/students/page-data")
	public ResponseEntity<?> getPageData() {
		try {
			Map<String, List<?>> result = new HashMap<>();

			List<Course> courseList = courseService.getAllCourses();
			List<Faculty> facultyList = facultyService.getAllFaculty();
			List<Department> departmentList = departmentService.getAllDepartment();
			List<Batch> batchList = batchService.getAllBatch();

			result.put("courses", courseList);
			result.put("faculties", facultyList);
			result.put("departments", departmentList);
			result.put("batches", batchList);
			return GenericResponse.builder().status(Constants.HTTP_RESULT_SUCCESS)
					.msg("Page data get successfully!").statusCode(Constants.HTTP_SUCCESS_CODE)
					.isSuccess(Constants.HTTP_RESULT_SUCCESS_BOOL).data(result).entity();

		} catch (Exception e) {
			e.printStackTrace();
			return GenericResponse.builder().status(Constants.HTTP_RESULT_FAILED)
					.msg(Constants.HTTP_EXPECTATION_FAILED_MESSAGE).statusCode(Constants.HTTP_EXPECTATION_FAILED_CODE)
					.isSuccess(Constants.HTTP_RESULT_FAILED_BOOL).error(e.toString()).entity();

		}

	}
	
	@GetMapping("/test2")
	public String testServer2() {
		try {
			
			return "working";

		} catch (Exception e) {
			return e.toString();

		}
	}
	
	
}
