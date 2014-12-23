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
public class CreditCardBase extends com.oreon.proj.onepack.PaymentMethod {

	@Column(name = "ccNumber", unique = false)
	private String ccNumber

	;

	@Column(name = "expiry", unique = false)
	private Date expiry

	;

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public Date getExpiry() {
		return expiry;
	}

	@Transient
	public String getDisplayName() {
		try {
			return ccNumber;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
