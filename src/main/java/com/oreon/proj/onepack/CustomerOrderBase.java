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

/** 
 * Entity containing orders that a customer places. 
 **/

@MappedSuperclass
public class CustomerOrderBase extends BaseEntity {

	/** 
	 * order related notes like if the order is urgent and any other comments that customer might have put in.  
	 **/

	@NotNull
	@Lob
	@Column(name = "notes", unique = false)
	private String notes;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false, updatable = true, insertable = true)
	private Customer customer;

	@OneToMany(mappedBy = "customerOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id DESC")
	private List<OrderItem> orderItems;

	@Column(name = "ship_Date", unique = false)
	private Date shipDate;

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void addOrderItem(OrderItem orderItem) {

		orderItem.setCustomerOrder((CustomerOrder) this);

		if (orderItems == null) {
			orderItems = new ArrayList<com.oreon.proj.onepack.OrderItem>();
		}

		this.orderItems.add(orderItem);
	}

	@Transient
	public String createListOrderItemsAsString() {
		return listAsString(orderItems);
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	@Transient
	public String getDisplayName() {
		try {
			return notes;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
