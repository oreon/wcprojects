package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.CustomerOrder;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.proj.onepack.CustomerOrder;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CustomerOrderListQueryBase
		extends
			BaseQuery<CustomerOrder, Long> {

	private static final String EJBQL = "select customerOrder from CustomerOrder customerOrder";

	protected CustomerOrder customerOrder = new CustomerOrder();

	public CustomerOrderListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public CustomerOrder getInstance() {
		return getCustomerOrder();
	}

	@Override
	//@Restrict("#{s:hasPermission('customerOrder', 'view')}")
	public List<CustomerOrder> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<CustomerOrder> getEntityClass() {
		return CustomerOrder.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> shipDateRange = new Range<Date>();

	public Range<Date> getShipDateRange() {
		return shipDateRange;
	}
	public void setShipDate(Range<Date> shipDateRange) {
		this.shipDateRange = shipDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"customerOrder.id = #{customerOrderList.customerOrder.id}",

			"customerOrder.archived = #{customerOrderList.customerOrder.archived}",

			"lower(customerOrder.notes) like concat(lower(#{customerOrderList.customerOrder.notes}),'%')",

			"customerOrder.customer.id = #{customerOrderList.customerOrder.customer.id}",

			"customerOrder.shipDate >= #{customerOrderList.shipDateRange.begin}",
			"customerOrder.shipDate <= #{customerOrderList.shipDateRange.end}",

			"customerOrder.dateCreated <= #{customerOrderList.dateCreatedRange.end}",
			"customerOrder.dateCreated >= #{customerOrderList.dateCreatedRange.begin}",};

	@Observer("archivedCustomerOrder")
	public void onArchive() {
		refresh();
	}

	public void setCustomerId(Long id) {
		if (customerOrder.getCustomer() == null) {
			customerOrder.setCustomer(new com.oreon.proj.onepack.Customer());
		}
		customerOrder.getCustomer().setId(id);
	}

	public Long getCustomerId() {
		return customerOrder.getCustomer() == null ? null : customerOrder
				.getCustomer().getId();
	}

}
