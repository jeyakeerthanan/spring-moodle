package com.moodle.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Integer id;

	@Column(nullable = false, length = 100)
	@NotEmpty(message = "First name cannot be empty")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String firstName;

	@Column(nullable = true)
	private String lastName;

	@Column(nullable = true)
	private String otherName;

	@Column(nullable = true)
	private String district;

	@Column(nullable = true)
	private String code;

	@Column(nullable = true)
	private String address;

	@Column(nullable = true)
	private String username;
	
	@Column(nullable = true)
	private String type;

	@Column(unique = true, nullable = false, length = 255)
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Invalid email")
	private String email;

	@Column(nullable = false, columnDefinition = "LONGTEXT")
	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 8, message = "Password should have atleast 8 characters")
	private String password;

	@Column(columnDefinition = "LONGTEXT")
	private String token;

	@Column(name = "is_active", nullable = false)
	private boolean isActive;


	@Column(unique = true, nullable = false, length = 100)
	@NotEmpty(message = "Contact primary cannot be empty")
	private String contactPrimary;

	@Column(nullable = true)
	private String contactSecondary;

	@Column(nullable = true)
	private String avatar;
	
	@Column(name = "deleted_at")
	private Calendar deletedAt;

	@Column(nullable = false, length = 100)
	@NotEmpty(message = "User type cannot be empty")
	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Calendar getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Calendar deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getContactPrimary() {
		return contactPrimary;
	}

	public void setContactPrimary(String contactPrimary) {
		this.contactPrimary = contactPrimary;
	}

	public String getContactSecondary() {
		return contactSecondary;
	}

	public void setContactSecondary(String contactSecondary) {
		this.contactSecondary = contactSecondary;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
}
