package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.Question;

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

import com.oreon.proj.questionnaire.Question;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class QuestionListQueryBase extends BaseQuery<Question, Long> {

	private static final String EJBQL = "select question from Question question";

	protected Question question = new Question();

	public QuestionListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Question getQuestion() {
		return question;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Question getInstance() {
		return getQuestion();
	}

	@Override
	//@Restrict("#{s:hasPermission('question', 'view')}")
	public List<Question> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Question> getEntityClass() {
		return Question.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"question.id = #{questionList.question.id}",

			"question.archived = #{questionList.question.archived}",

			"lower(question.text) like concat(lower(#{questionList.question.text}),'%')",

			"question.section.id = #{questionList.question.section.id}",

			"question.answerType = #{questionList.question.answerType}",

			"question.dateCreated <= #{questionList.dateCreatedRange.end}",
			"question.dateCreated >= #{questionList.dateCreatedRange.begin}",};

	/** 
	 * List of all Questions for the given Section
	 * @param patient
	 * @return 
	 */
	public List<Question> getAllQuestionsBySection(
			final com.oreon.proj.questionnaire.Section section) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		question.setSection(section);
		return getResultListTable();
	}

	public LazyDataModel<Question> getQuestionsBySection(
			final com.oreon.proj.questionnaire.Section section) {

		EntityLazyDataModel<Question, Long> questionLazyDataModel = new EntityLazyDataModel<Question, Long>(
				this) {

			@Override
			public List<Question> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				question.setSection(section);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return questionLazyDataModel;

	}

	@Observer("archivedQuestion")
	public void onArchive() {
		refresh();
	}

	public void setSectionId(Long id) {
		if (question.getSection() == null) {
			question.setSection(new com.oreon.proj.questionnaire.Section());
		}
		question.getSection().setId(id);
	}

	public Long getSectionId() {
		return question.getSection() == null ? null : question.getSection()
				.getId();
	}

}
