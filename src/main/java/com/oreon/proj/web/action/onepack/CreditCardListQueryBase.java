package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.CreditCard;

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

import com.oreon.proj.onepack.CreditCard;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CreditCardListQueryBase
		extends
			BaseQuery<CreditCard, Long> {

	private static final String EJBQL = "select creditCard from CreditCard creditCard";

	protected CreditCard creditCard = new CreditCard();

	public CreditCardListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public CreditCard getInstance() {
		return getCreditCard();
	}

	@Override
	//@Restrict("#{s:hasPermission('creditCard', 'view')}")
	public List<CreditCard> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<CreditCard> getEntityClass() {
		return CreditCard.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> expiryRange = new Range<Date>();

	public Range<Date> getExpiryRange() {
		return expiryRange;
	}
	public void setExpiry(Range<Date> expiryRange) {
		this.expiryRange = expiryRange;
	}

	private static final String[] RESTRICTIONS = {
			"creditCard.id = #{creditCardList.creditCard.id}",

			"creditCard.archived = #{creditCardList.creditCard.archived}",

			"lower(creditCard.accountAddress) like concat(lower(#{creditCardList.creditCard.accountAddress}),'%')",

			"lower(creditCard.ccNumber) like concat(lower(#{creditCardList.creditCard.ccNumber}),'%')",

			"creditCard.expiry >= #{creditCardList.expiryRange.begin}",
			"creditCard.expiry <= #{creditCardList.expiryRange.end}",

			"creditCard.dateCreated <= #{creditCardList.dateCreatedRange.end}",
			"creditCard.dateCreated >= #{creditCardList.dateCreatedRange.begin}",};

	@Observer("archivedCreditCard")
	public void onArchive() {
		refresh();
	}

}
