package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.Customer;

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

import com.oreon.proj.onepack.Customer;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CustomerListQueryBase extends BaseQuery<Customer, Long> {

	private static final String EJBQL = "select customer from Customer customer";

	protected Customer customer = new Customer();

	public CustomerListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Customer getInstance() {
		return getCustomer();
	}

	@Override
	//@Restrict("#{s:hasPermission('customer', 'view')}")
	public List<Customer> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Customer> getEntityClass() {
		return Customer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dobRange = new Range<Date>();

	public Range<Date> getDobRange() {
		return dobRange;
	}
	public void setDob(Range<Date> dobRange) {
		this.dobRange = dobRange;
	}

	private static final String[] RESTRICTIONS = {
			"customer.id = #{customerList.customer.id}",

			"customer.archived = #{customerList.customer.archived}",

			"lower(customer.firstName) like concat(lower(#{customerList.customer.firstName}),'%')",

			"lower(customer.lastName) like concat(lower(#{customerList.customer.lastName}),'%')",

			"lower(customer.city) like concat(lower(#{customerList.customer.city}),'%')",

			"customer.dob >= #{customerList.dobRange.begin}",
			"customer.dob <= #{customerList.dobRange.end}",

			"customer.dateCreated <= #{customerList.dateCreatedRange.end}",
			"customer.dateCreated >= #{customerList.dateCreatedRange.begin}",};

	@Observer("archivedCustomer")
	public void onArchive() {
		refresh();
	}

}
