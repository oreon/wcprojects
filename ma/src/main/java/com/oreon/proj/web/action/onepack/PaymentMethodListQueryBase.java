package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.PaymentMethod;

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

import com.oreon.proj.onepack.PaymentMethod;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PaymentMethodListQueryBase
		extends
			BaseQuery<PaymentMethod, Long> {

	private static final String EJBQL = "select paymentMethod from PaymentMethod paymentMethod";

	protected PaymentMethod paymentMethod = new PaymentMethod();

	public PaymentMethodListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public PaymentMethod getInstance() {
		return getPaymentMethod();
	}

	@Override
	//@Restrict("#{s:hasPermission('paymentMethod', 'view')}")
	public List<PaymentMethod> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PaymentMethod> getEntityClass() {
		return PaymentMethod.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"paymentMethod.id = #{paymentMethodList.paymentMethod.id}",

			"paymentMethod.archived = #{paymentMethodList.paymentMethod.archived}",

			"lower(paymentMethod.accountAddress) like concat(lower(#{paymentMethodList.paymentMethod.accountAddress}),'%')",

			"paymentMethod.dateCreated <= #{paymentMethodList.dateCreatedRange.end}",
			"paymentMethod.dateCreated >= #{paymentMethodList.dateCreatedRange.begin}",};

	@Observer("archivedPaymentMethod")
	public void onArchive() {
		refresh();
	}

}
