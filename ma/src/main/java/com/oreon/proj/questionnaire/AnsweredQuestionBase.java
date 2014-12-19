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

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "answer_id", nullable = true, updatable = true, insertable = true)
	private Answer answer;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("id DESC")
	private List<Answer> answers;

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

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void addAnswer(Answer answer) {

		if (answers == null) {
			answers = new ArrayList<com.oreon.proj.questionnaire.Answer>();
		}

		this.answers.add(answer);
	}

	@Transient
	public String createListAnswersAsString() {
		return listAsString(answers);
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
