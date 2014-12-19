package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.AnsweredQuestionnaire;

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

import com.oreon.proj.questionnaire.AnsweredQuestionnaire;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AnsweredQuestionnaireListQueryBase
		extends
			BaseQuery<AnsweredQuestionnaire, Long> {

	private static final String EJBQL = "select answeredQuestionnaire from AnsweredQuestionnaire answeredQuestionnaire";

	protected AnsweredQuestionnaire answeredQuestionnaire = new AnsweredQuestionnaire();

	public AnsweredQuestionnaireListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public AnsweredQuestionnaire getAnsweredQuestionnaire() {
		return answeredQuestionnaire;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public AnsweredQuestionnaire getInstance() {
		return getAnsweredQuestionnaire();
	}

	@Override
	//@Restrict("#{s:hasPermission('answeredQuestionnaire', 'view')}")
	public List<AnsweredQuestionnaire> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<AnsweredQuestionnaire> getEntityClass() {
		return AnsweredQuestionnaire.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"answeredQuestionnaire.id = #{answeredQuestionnaireList.answeredQuestionnaire.id}",

			"answeredQuestionnaire.archived = #{answeredQuestionnaireList.answeredQuestionnaire.archived}",

			"answeredQuestionnaire.questionnaire.id = #{answeredQuestionnaireList.answeredQuestionnaire.questionnaire.id}",

			"answeredQuestionnaire.customer.id = #{answeredQuestionnaireList.answeredQuestionnaire.customer.id}",

			"lower(answeredQuestionnaire.remarks) like concat(lower(#{answeredQuestionnaireList.answeredQuestionnaire.remarks}),'%')",

			"answeredQuestionnaire.dateCreated <= #{answeredQuestionnaireList.dateCreatedRange.end}",
			"answeredQuestionnaire.dateCreated >= #{answeredQuestionnaireList.dateCreatedRange.begin}",};

	@Observer("archivedAnsweredQuestionnaire")
	public void onArchive() {
		refresh();
	}

	public void setQuestionnaireId(Long id) {
		if (answeredQuestionnaire.getQuestionnaire() == null) {
			answeredQuestionnaire
					.setQuestionnaire(new com.oreon.proj.questionnaire.Questionnaire());
		}
		answeredQuestionnaire.getQuestionnaire().setId(id);
	}

	public Long getQuestionnaireId() {
		return answeredQuestionnaire.getQuestionnaire() == null
				? null
				: answeredQuestionnaire.getQuestionnaire().getId();
	}

	public void setCustomerId(Long id) {
		if (answeredQuestionnaire.getCustomer() == null) {
			answeredQuestionnaire
					.setCustomer(new com.oreon.proj.onepack.Customer());
		}
		answeredQuestionnaire.getCustomer().setId(id);
	}

	public Long getCustomerId() {
		return answeredQuestionnaire.getCustomer() == null
				? null
				: answeredQuestionnaire.getCustomer().getId();
	}

}
