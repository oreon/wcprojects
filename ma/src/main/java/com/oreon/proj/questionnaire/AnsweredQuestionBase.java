package com.oreon.proj.questionnaire;

import javax.persistence.*;
import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import javax.validation.constraints.*;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@MappedSuperclass
public class AnsweredQuestionBase extends BaseEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false, updatable = true, insertable = true)
	private Question question;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "answeredQuestionnaire_id", nullable = false, updatable = true, insertable = true)
	private AnsweredQuestionnaire answeredQuestionnaire;

	@OneToMany(mappedBy = "answeredQuestion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id DESC")
	private List<AnswersProvided> answersProvideds;

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}

	public void setAnsweredQuestionnaire(
			AnsweredQuestionnaire answeredQuestionnaire) {
		this.answeredQuestionnaire = answeredQuestionnaire;
	}

	public AnsweredQuestionnaire getAnsweredQuestionnaire() {
		return answeredQuestionnaire;
	}

	public void setAnswersProvideds(List<AnswersProvided> answersProvideds) {
		this.answersProvideds = answersProvideds;
	}

	public List<AnswersProvided> getAnswersProvideds() {
		return answersProvideds;
	}

	public void addAnswersProvided(AnswersProvided answersProvided) {

		answersProvided.setAnsweredQuestion((AnsweredQuestion) this);

		if (answersProvideds == null) {
			answersProvideds = new ArrayList<com.oreon.proj.questionnaire.AnswersProvided>();
		}

		this.answersProvideds.add(answersProvided);
	}

	@Transient
	public String createListAnswersProvidedsAsString() {
		return listAsString(answersProvideds);
	}

	@Transient
	public String getDisplayName() {
		try {
			return question + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
