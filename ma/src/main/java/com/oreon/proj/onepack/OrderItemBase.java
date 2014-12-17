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
public class OrderItemBase extends BaseEntity {

	@Column(name = "qty", unique = false)
	private Integer qty;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, updatable = true, insertable = true)
	private Product product;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "customerOrder_id", nullable = false, updatable = true, insertable = true)
	private CustomerOrder customerOrder;

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getQty() {
		return qty;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	@Transient
	public String getDisplayName() {
		try {
			return qty + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
