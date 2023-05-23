package com.marketing.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

///mapped with dB
@Entity
///when the entity class name and DB table name is different then use this annotation
@Table(name = "leads")
public class Lead {
//we want id as primary key
	@Id
//want id to be auto generated so
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
//here is writing name in camelcasing  but in DB it is snake casing so
	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;
	@Column(name = "email", nullable = false, length = 128, unique = true)
	private String email;
	@Column(name = "mobile", nullable = false, unique = true)
	private String mobile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
