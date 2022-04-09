package com.moodle.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moodle.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {
	
	@Query(value = "SELECT * FROM students s " + "left join users  u on s.user_id = u.id "
			+ " left join courses c on s.course_id=c.id"
			+ " left join departments d on s.department_id=d.id"
			+ " left join faculties f on s.faculty_id=f.id"
			+ " left join batches b on s.batch_id=b.id"
			+ " where s.deleted_at is null   and (:search is null or(s.type LIKE %:search% or u.address LIKE %:search% or u.district LIKE %:search% or u.email LIKE %:search% or u.first_name LIKE %:search% or u.last_name LIKE %:search% or u.other_name LIKE %:search% or u.username"
			+ " LIKE %:search% or u.code LIKE %:search% OR u.contact_primary LIKE %:search% OR s.gender LIKE %:search% OR c.name LIKE %:search% "
			+ " OR f.name LIKE %:search% OR d.name LIKE %:search%) )", nativeQuery = true)
	Page<Student> getAllStudents(Pageable paginationRequest, String search);

	Student findByUserIdAndDeletedAtNull(Integer id);
	
	
	
	

}
