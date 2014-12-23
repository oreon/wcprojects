package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.PayPal;

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

import com.oreon.proj.onepack.PayPal;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PayPalListQueryBase extends BaseQuery<PayPal, Long> {

	private static final String EJBQL = "select payPal from PayPal payPal";

	protected PayPal payPal = new PayPal();

	public PayPalListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PayPal getPayPal() {
		return payPal;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public PayPal getInstance() {
		return getPayPal();
	}

	@Override
	//@Restrict("#{s:hasPermission('payPal', 'view')}")
	public List<PayPal> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PayPal> getEntityClass() {
		return PayPal.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"payPal.id = #{payPalList.payPal.id}",

			"payPal.archived = #{payPalList.payPal.archived}",

			"lower(payPal.accountAddress) like concat(lower(#{payPalList.payPal.accountAddress}),'%')",

			"lower(payPal.paypalAccountNumber) like concat(lower(#{payPalList.payPal.paypalAccountNumber}),'%')",

			"payPal.dateCreated <= #{payPalList.dateCreatedRange.end}",
			"payPal.dateCreated >= #{payPalList.dateCreatedRange.begin}",};

	@Observer("archivedPayPal")
	public void onArchive() {
		refresh();
	}

}
