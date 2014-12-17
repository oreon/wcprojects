package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.AnswersProvided;

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

import com.oreon.proj.questionnaire.AnswersProvided;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AnswersProvidedListQueryBase
		extends
			BaseQuery<AnswersProvided, Long> {

	private static final String EJBQL = "select answersProvided from AnswersProvided answersProvided";

	protected AnswersProvided answersProvided = new AnswersProvided();

	public AnswersProvidedListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public AnswersProvided getAnswersProvided() {
		return answersProvided;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public AnswersProvided getInstance() {
		return getAnswersProvided();
	}

	@Override
	//@Restrict("#{s:hasPermission('answersProvided', 'view')}")
	public List<AnswersProvided> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<AnswersProvided> getEntityClass() {
		return AnswersProvided.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"answersProvided.id = #{answersProvidedList.answersProvided.id}",

			"answersProvided.archived = #{answersProvidedList.answersProvided.archived}",

			"answersProvided.answeredQuestion.id = #{answersProvidedList.answersProvided.answeredQuestion.id}",

			"answersProvided.answer.id = #{answersProvidedList.answersProvided.answer.id}",

			"answersProvided.dateCreated <= #{answersProvidedList.dateCreatedRange.end}",
			"answersProvided.dateCreated >= #{answersProvidedList.dateCreatedRange.begin}",};

	/** 
	 * List of all AnswersProvideds for the given AnsweredQuestion
	 * @param patient
	 * @return 
	 */
	public List<AnswersProvided> getAllAnswersProvidedsByAnsweredQuestion(
			final com.oreon.proj.questionnaire.AnsweredQuestion answeredQuestion) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		answersProvided.setAnsweredQuestion(answeredQuestion);
		return getResultListTable();
	}

	public LazyDataModel<AnswersProvided> getAnswersProvidedsByAnsweredQuestion(
			final com.oreon.proj.questionnaire.AnsweredQuestion answeredQuestion) {

		EntityLazyDataModel<AnswersProvided, Long> answersProvidedLazyDataModel = new EntityLazyDataModel<AnswersProvided, Long>(
				this) {

			@Override
			public List<AnswersProvided> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				answersProvided.setAnsweredQuestion(answeredQuestion);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return answersProvidedLazyDataModel;

	}

	@Observer("archivedAnswersProvided")
	public void onArchive() {
		refresh();
	}

	public void setAnsweredQuestionId(Long id) {
		if (answersProvided.getAnsweredQuestion() == null) {
			answersProvided
					.setAnsweredQuestion(new com.oreon.proj.questionnaire.AnsweredQuestion());
		}
		answersProvided.getAnsweredQuestion().setId(id);
	}

	public Long getAnsweredQuestionId() {
		return answersProvided.getAnsweredQuestion() == null
				? null
				: answersProvided.getAnsweredQuestion().getId();
	}

	public void setAnswerId(Long id) {
		if (answersProvided.getAnswer() == null) {
			answersProvided
					.setAnswer(new com.oreon.proj.questionnaire.Answer());
		}
		answersProvided.getAnswer().setId(id);
	}

	public Long getAnswerId() {
		return answersProvided.getAnswer() == null ? null : answersProvided
				.getAnswer().getId();
	}

}
