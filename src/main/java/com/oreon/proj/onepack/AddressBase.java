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
public class AddressBase {

	@Column(name = "street", unique = false)
	private String street;

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	@Transient
	public String getDisplayName() {
		try {
			return street;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
