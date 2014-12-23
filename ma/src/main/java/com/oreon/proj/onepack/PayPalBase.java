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
public class PayPalBase extends com.oreon.proj.onepack.PaymentMethod {

	@Column(name = "paypalAccountNumber", unique = false)
	private String paypalAccountNumber

	;

	public void setPaypalAccountNumber(String paypalAccountNumber) {
		this.paypalAccountNumber = paypalAccountNumber;
	}

	public String getPaypalAccountNumber() {
		return paypalAccountNumber;
	}

	@Transient
	public String getDisplayName() {
		try {
			return paypalAccountNumber;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
