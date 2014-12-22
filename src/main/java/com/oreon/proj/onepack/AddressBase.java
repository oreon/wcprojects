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

	@NotNull
	@Column(name = "street", unique = false)
	private String street;

	@NotNull
	@Column(name = "city", unique = false)
	private String city;

	@NotNull
	@Column(name = "province", unique = false)
	private String province;

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return province;
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
