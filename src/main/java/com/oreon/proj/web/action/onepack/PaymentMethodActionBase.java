package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.PaymentMethod;

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

//
public abstract class PaymentMethodActionBase extends BaseAction<PaymentMethod>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long paymentMethodId;

	public void setPaymentMethodId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPaymentMethodIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getPaymentMethodId() {
		return (Long) getId();
	}

	public PaymentMethod getPaymentMethod() {
		return getEntity();
	}

	@Override
	@Restrict("#{s:hasPermission('paymentMethod', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('paymentMethod', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected PaymentMethod createInstance() {
		PaymentMethod instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		if (isNew()) {
			instance = getInstance();

		}
	}

	public void wire() {
		/*
		if (isNew()){
			getInstance();
			
		}
		 */
	}

	public PaymentMethod getDefinedInstance() {
		return (PaymentMethod) (isIdDefined() ? getInstance() : null);
	}

	public void setPaymentMethod(PaymentMethod t) {
		setEntity(t);
	}

	@Override
	public Class<PaymentMethod> getEntityClass() {
		return PaymentMethod.class;
	}

	public String viewPaymentMethod() {
		load(currentEntityId);
		return "viewPaymentMethod";
	}

}
