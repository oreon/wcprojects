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
public class EmployeeBase extends com.oreon.proj.onepack.Person {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false, updatable = true, insertable = true)
	private Department department;

	@Column(name = "firstName", unique = false)
	private String firstName;

	@Column(name = "lastName", unique = false)
	private String lastName;

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {
		return department;
	}

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

	@Transient
	public String getDisplayName() {
		try {
			return firstName;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
