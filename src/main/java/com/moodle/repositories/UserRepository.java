package com.moodle.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.moodle.entities.User;


public interface UserRepository extends JpaRepository <User, Integer> {

User findByEmail(String email);

User findByContactPrimary(String contactPrimary);

Integer countByUserType(String userType);
}
