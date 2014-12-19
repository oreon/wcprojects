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

	@Column(name = "gender", unique = false)
	private Gender gender;

	@Column(name = "dob", unique = false)
	private Date dob;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false, updatable = true, insertable = true)
	private Address address;

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
