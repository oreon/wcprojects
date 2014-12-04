package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.CustomerOrder;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.proj.onepack.OrderItem;

//
public abstract class CustomerOrderActionBase extends BaseAction<CustomerOrder>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long customerOrderId;

	public void setCustomerOrderId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setCustomerOrderIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getCustomerOrderId() {
		return (Long) getId();
	}

	public CustomerOrder getCustomerOrder() {
		return getEntity();
	}

	@Override
	@Restrict("#{s:hasPermission('customerOrder', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('customerOrder', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected CustomerOrder createInstance() {
		CustomerOrder instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		if (isNew()) {
			instance = getInstance();

			if (instance.getOrderItems().isEmpty()) {
				for (int i = 0; i < 1; i++) {
					com.oreon.proj.onepack.OrderItem item = new com.oreon.proj.onepack.OrderItem();
					item.setCustomerOrder(getInstance());
					getListOrderItems().add(item);
				}
			}

		}
	}

	public void wire() {
		/*
		if (isNew()){
			getInstance();
				 
					com.oreon.proj.onepack.Customer customer = customerAction.getInstance();
					if (customer != null  ) {
						 getInstance().setCustomer(customer);
					}
				 
			
		}
		 */
	}

	public CustomerOrder getDefinedInstance() {
		return (CustomerOrder) (isIdDefined() ? getInstance() : null);
	}

	public void setCustomerOrder(CustomerOrder t) {
		setEntity(t);
	}

	@Override
	public Class<CustomerOrder> getEntityClass() {
		return CustomerOrder.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getCustomer() != null) {
			criteria = criteria.add(Restrictions.eq("customer.id", instance
					.getCustomer().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListOrderItems();

		addDefaultAssociations();

		//wire();
	}

	protected List<com.oreon.proj.onepack.OrderItem> listOrderItems = new ArrayList<com.oreon.proj.onepack.OrderItem>();

	void initListOrderItems() {

		if (listOrderItems.isEmpty())
			listOrderItems.addAll(getInstance().getOrderItems());

	}

	public List<com.oreon.proj.onepack.OrderItem> getListOrderItems() {

		prePopulateListOrderItems();
		return listOrderItems;
	}

	public void prePopulateListOrderItems() {
	}

	public void setListOrderItems(
			List<com.oreon.proj.onepack.OrderItem> listOrderItems) {
		this.listOrderItems = listOrderItems;
	}

	public void deleteOrderItems(int index) {
		listOrderItems.remove(index);
	}

	@Begin(join = true)
	public void addOrderItems() {

		initListOrderItems();
		OrderItem orderItems = new OrderItem();

		orderItems.setCustomerOrder(getInstance());

		getListOrderItems().add(orderItems);

	}

	public void updateComposedAssociations() {

		if (listOrderItems != null) {
			getInstance().getOrderItems().clear();
			getInstance().getOrderItems().addAll(listOrderItems);
		}
		/*
		 */
	}

	public void clearLists() {
		listOrderItems.clear();

	}

	public String viewCustomerOrder() {
		load(currentEntityId);
		return "viewCustomerOrder";
	}

}
