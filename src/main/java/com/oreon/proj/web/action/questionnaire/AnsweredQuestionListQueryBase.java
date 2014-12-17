package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.AnsweredQuestion;

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

import com.oreon.proj.questionnaire.AnsweredQuestion;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AnsweredQuestionListQueryBase
		extends
			BaseQuery<AnsweredQuestion, Long> {

	private static final String EJBQL = "select answeredQuestion from AnsweredQuestion answeredQuestion";

	protected AnsweredQuestion answeredQuestion = new AnsweredQuestion();

	public AnsweredQuestionListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public AnsweredQuestion getAnsweredQuestion() {
		return answeredQuestion;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public AnsweredQuestion getInstance() {
		return getAnsweredQuestion();
	}

	@Override
	//@Restrict("#{s:hasPermission('answeredQuestion', 'view')}")
	public List<AnsweredQuestion> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<AnsweredQuestion> getEntityClass() {
		return AnsweredQuestion.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"answeredQuestion.id = #{answeredQuestionList.answeredQuestion.id}",

			"answeredQuestion.archived = #{answeredQuestionList.answeredQuestion.archived}",

			"answeredQuestion.question.id = #{answeredQuestionList.answeredQuestion.question.id}",

			"answeredQuestion.answeredQuestionnaire.id = #{answeredQuestionList.answeredQuestion.answeredQuestionnaire.id}",

			"answeredQuestion.dateCreated <= #{answeredQuestionList.dateCreatedRange.end}",
			"answeredQuestion.dateCreated >= #{answeredQuestionList.dateCreatedRange.begin}",};

	/** 
	 * List of all AnsweredQuestions for the given AnsweredQuestionnaire
	 * @param patient
	 * @return 
	 */
	public List<AnsweredQuestion> getAllAnsweredQuestionsByAnsweredQuestionnaire(
			final com.oreon.proj.questionnaire.AnsweredQuestionnaire answeredQuestionnaire) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		answeredQuestion.setAnsweredQuestionnaire(answeredQuestionnaire);
		return getResultListTable();
	}

	public LazyDataModel<AnsweredQuestion> getAnsweredQuestionsByAnsweredQuestionnaire(
			final com.oreon.proj.questionnaire.AnsweredQuestionnaire answeredQuestionnaire) {

		EntityLazyDataModel<AnsweredQuestion, Long> answeredQuestionLazyDataModel = new EntityLazyDataModel<AnsweredQuestion, Long>(
				this) {

			@Override
			public List<AnsweredQuestion> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				answeredQuestion
						.setAnsweredQuestionnaire(answeredQuestionnaire);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return answeredQuestionLazyDataModel;

	}

	@Observer("archivedAnsweredQuestion")
	public void onArchive() {
		refresh();
	}

	public void setQuestionId(Long id) {
		if (answeredQuestion.getQuestion() == null) {
			answeredQuestion
					.setQuestion(new com.oreon.proj.questionnaire.Question());
		}
		answeredQuestion.getQuestion().setId(id);
	}

	public Long getQuestionId() {
		return answeredQuestion.getQuestion() == null ? null : answeredQuestion
				.getQuestion().getId();
	}

	public void setAnsweredQuestionnaireId(Long id) {
		if (answeredQuestion.getAnsweredQuestionnaire() == null) {
			answeredQuestion
					.setAnsweredQuestionnaire(new com.oreon.proj.questionnaire.AnsweredQuestionnaire());
		}
		answeredQuestion.getAnsweredQuestionnaire().setId(id);
	}

	public Long getAnsweredQuestionnaireId() {
		return answeredQuestion.getAnsweredQuestionnaire() == null
				? null
				: answeredQuestion.getAnsweredQuestionnaire().getId();
	}

}
