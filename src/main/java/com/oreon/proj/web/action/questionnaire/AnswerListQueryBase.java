package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.Answer;

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

import com.oreon.proj.questionnaire.Answer;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AnswerListQueryBase extends BaseQuery<Answer, Long> {

	private static final String EJBQL = "select answer from Answer answer";

	protected Answer answer = new Answer();

	public AnswerListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Answer getAnswer() {
		return answer;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Answer getInstance() {
		return getAnswer();
	}

	@Override
	//@Restrict("#{s:hasPermission('answer', 'view')}")
	public List<Answer> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Answer> getEntityClass() {
		return Answer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> scoreRange = new Range<Integer>();

	public Range<Integer> getScoreRange() {
		return scoreRange;
	}
	public void setScore(Range<Integer> scoreRange) {
		this.scoreRange = scoreRange;
	}

	private static final String[] RESTRICTIONS = {
			"answer.id = #{answerList.answer.id}",

			"answer.archived = #{answerList.answer.archived}",

			"answer.question.id = #{answerList.answer.question.id}",

			"lower(answer.text) like concat(lower(#{answerList.answer.text}),'%')",

			"answer.score >= #{answerList.scoreRange.begin}",
			"answer.score <= #{answerList.scoreRange.end}",

			"answer.answerType = #{answerList.answer.answerType}",

			"answer.dateCreated <= #{answerList.dateCreatedRange.end}",
			"answer.dateCreated >= #{answerList.dateCreatedRange.begin}",};

	/** 
	 * List of all Answers for the given Question
	 * @param patient
	 * @return 
	 */
	public List<Answer> getAllAnswersByQuestion(
			final com.oreon.proj.questionnaire.Question question) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		answer.setQuestion(question);
		return getResultListTable();
	}

	public LazyDataModel<Answer> getAnswersByQuestion(
			final com.oreon.proj.questionnaire.Question question) {

		EntityLazyDataModel<Answer, Long> answerLazyDataModel = new EntityLazyDataModel<Answer, Long>(
				this) {

			@Override
			public List<Answer> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				answer.setQuestion(question);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return answerLazyDataModel;

	}

	@Observer("archivedAnswer")
	public void onArchive() {
		refresh();
	}

	public void setQuestionId(Long id) {
		if (answer.getQuestion() == null) {
			answer.setQuestion(new com.oreon.proj.questionnaire.Question());
		}
		answer.getQuestion().setId(id);
	}

	public Long getQuestionId() {
		return answer.getQuestion() == null ? null : answer.getQuestion()
				.getId();
	}

}
