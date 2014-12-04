package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.OrderItem;

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

import com.oreon.proj.onepack.OrderItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class OrderItemListQueryBase extends BaseQuery<OrderItem, Long> {

	private static final String EJBQL = "select orderItem from OrderItem orderItem";

	protected OrderItem orderItem = new OrderItem();

	public OrderItemListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public OrderItem getInstance() {
		return getOrderItem();
	}

	@Override
	//@Restrict("#{s:hasPermission('orderItem', 'view')}")
	public List<OrderItem> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<OrderItem> getEntityClass() {
		return OrderItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> qtyRange = new Range<Integer>();

	public Range<Integer> getQtyRange() {
		return qtyRange;
	}
	public void setQty(Range<Integer> qtyRange) {
		this.qtyRange = qtyRange;
	}

	private static final String[] RESTRICTIONS = {
			"orderItem.id = #{orderItemList.orderItem.id}",

			"orderItem.archived = #{orderItemList.orderItem.archived}",

			"orderItem.qty >= #{orderItemList.qtyRange.begin}",
			"orderItem.qty <= #{orderItemList.qtyRange.end}",

			"orderItem.product.id = #{orderItemList.orderItem.product.id}",

			"orderItem.customerOrder.id = #{orderItemList.orderItem.customerOrder.id}",

			"orderItem.dateCreated <= #{orderItemList.dateCreatedRange.end}",
			"orderItem.dateCreated >= #{orderItemList.dateCreatedRange.begin}",};

	/** 
	 * List of all OrderItems for the given CustomerOrder
	 * @param patient
	 * @return 
	 */
	public List<OrderItem> getAllOrderItemsByCustomerOrder(
			final com.oreon.proj.onepack.CustomerOrder customerOrder) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		orderItem.setCustomerOrder(customerOrder);
		return getResultListTable();
	}

	public LazyDataModel<OrderItem> getOrderItemsByCustomerOrder(
			final com.oreon.proj.onepack.CustomerOrder customerOrder) {

		EntityLazyDataModel<OrderItem, Long> orderItemLazyDataModel = new EntityLazyDataModel<OrderItem, Long>(
				this) {

			@Override
			public List<OrderItem> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				orderItem.setCustomerOrder(customerOrder);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return orderItemLazyDataModel;

	}

	@Observer("archivedOrderItem")
	public void onArchive() {
		refresh();
	}

	public void setProductId(Long id) {
		if (orderItem.getProduct() == null) {
			orderItem.setProduct(new com.oreon.proj.onepack.Product());
		}
		orderItem.getProduct().setId(id);
	}

	public Long getProductId() {
		return orderItem.getProduct() == null ? null : orderItem.getProduct()
				.getId();
	}

	public void setCustomerOrderId(Long id) {
		if (orderItem.getCustomerOrder() == null) {
			orderItem
					.setCustomerOrder(new com.oreon.proj.onepack.CustomerOrder());
		}
		orderItem.getCustomerOrder().setId(id);
	}

	public Long getCustomerOrderId() {
		return orderItem.getCustomerOrder() == null ? null : orderItem
				.getCustomerOrder().getId();
	}

}
