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
public class AnswersProvidedBase extends BaseEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "answeredQuestion_id", nullable = false, updatable = true, insertable = true)
	private AnsweredQuestion answeredQuestion;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "answer_id", nullable = false, updatable = true, insertable = true)
	private Answer answer;

	public void setAnsweredQuestion(AnsweredQuestion answeredQuestion) {
		this.answeredQuestion = answeredQuestion;
	}

	public AnsweredQuestion getAnsweredQuestion() {
		return answeredQuestion;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Answer getAnswer() {
		return answer;
	}

	@Transient
	public String getDisplayName() {
		try {
			return getAnswer().getText();
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
