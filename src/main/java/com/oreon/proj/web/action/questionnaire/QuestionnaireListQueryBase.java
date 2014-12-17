package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.Questionnaire;

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

import com.oreon.proj.questionnaire.Questionnaire;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class QuestionnaireListQueryBase
		extends
			BaseQuery<Questionnaire, Long> {

	private static final String EJBQL = "select questionnaire from Questionnaire questionnaire";

	protected Questionnaire questionnaire = new Questionnaire();

	public QuestionnaireListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Questionnaire getInstance() {
		return getQuestionnaire();
	}

	@Override
	//@Restrict("#{s:hasPermission('questionnaire', 'view')}")
	public List<Questionnaire> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Questionnaire> getEntityClass() {
		return Questionnaire.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"questionnaire.id = #{questionnaireList.questionnaire.id}",

			"questionnaire.archived = #{questionnaireList.questionnaire.archived}",

			"lower(questionnaire.name) like concat(lower(#{questionnaireList.questionnaire.name}),'%')",

			"questionnaire.dateCreated <= #{questionnaireList.dateCreatedRange.end}",
			"questionnaire.dateCreated >= #{questionnaireList.dateCreatedRange.begin}",};

	@Observer("archivedQuestionnaire")
	public void onArchive() {
		refresh();
	}

}
