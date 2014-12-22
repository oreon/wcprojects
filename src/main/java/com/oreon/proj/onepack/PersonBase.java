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
public class PersonBase extends BaseEntity {

	@NotNull
	@Column(name = "gender", unique = false)
	private Gender gender;

	@NotNull
	@Column(name = "dob", unique = false)
	private Date dob;

	@Embedded
	private Address address = new Address();

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {
		return gender;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDob() {
		return dob;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		if(address == null)
			address = new Address();
		return address;
	}

	@Transient
	public String getDisplayName() {
		try {
			return gender + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
