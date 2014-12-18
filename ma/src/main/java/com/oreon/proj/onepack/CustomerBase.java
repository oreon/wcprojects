package com.oreon.proj.onepack;

import javax.persistence.*;
import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import javax.validation.constraints.*;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@MappedSuperclass
public class CustomerBase extends BaseEntity {

	@Column(name = "firstName", unique = false)
	private String firstName;

	@Column(name = "lastName", unique = false)
	private String lastName;

	@Column(name = "city", unique = false)
	private String city;

	@Column(name = "dob", unique = false)
	private Date dob;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDob() {
		return dob;
	}

	@Transient
	public String getDisplayName() {
		try {
			return firstName;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
