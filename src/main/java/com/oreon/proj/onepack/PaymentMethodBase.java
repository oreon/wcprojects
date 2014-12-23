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
public class PaymentMethodBase extends BaseEntity {

	@Column(name = "accountAddress", unique = false)
	private String accountAddress

	;

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public String getAccountAddress() {
		return accountAddress;
	}

	@Transient
	public String getDisplayName() {
		try {
			return accountAddress;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
