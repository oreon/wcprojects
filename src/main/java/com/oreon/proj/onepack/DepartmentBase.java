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
public class DepartmentBase extends BaseEntity {

	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("id DESC")
	private List<Employee> employees;

	//@Unique(entityName = "com.oreon.proj.onepack.Department", fieldName = "name")

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "name", unique = true)
	private String name;

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addEmployee(Employee employee) {

		employee.setDepartment((Department) this);

		if (employees == null) {
			employees = new ArrayList<com.oreon.proj.onepack.Employee>();
		}

		this.employees.add(employee);
	}

	@Transient
	public String createListEmployeesAsString() {
		return listAsString(employees);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
