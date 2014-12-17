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
public class AnsweredQuestionnaireBase extends BaseEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "questionnaire_id", nullable = false, updatable = true, insertable = true)
	private Questionnaire questionnaire;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false, updatable = true, insertable = true)
	private com.oreon.proj.onepack.Customer customer;

	@Lob
	@Column(name = "remarks", unique = false)
	private String remarks;

	@Transient
	private Integer defaultScore;

	@OneToMany(mappedBy = "answeredQuestionnaire", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id DESC")
	private List<AnsweredQuestion> answeredQuestions;

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setCustomer(com.oreon.proj.onepack.Customer customer) {
		this.customer = customer;
	}

	public com.oreon.proj.onepack.Customer getCustomer() {
		return customer;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setDefaultScore(Integer defaultScore) {
		this.defaultScore = defaultScore;
	}

	public Integer getDefaultScore() {
		return defaultScore;
	}

	public void setAnsweredQuestions(List<AnsweredQuestion> answeredQuestions) {
		this.answeredQuestions = answeredQuestions;
	}

	public List<AnsweredQuestion> getAnsweredQuestions() {
		return answeredQuestions;
	}

	public void addAnsweredQuestion(AnsweredQuestion answeredQuestion) {

		answeredQuestion.setAnsweredQuestionnaire((AnsweredQuestionnaire) this);

		if (answeredQuestions == null) {
			answeredQuestions = new ArrayList<com.oreon.proj.questionnaire.AnsweredQuestion>();
		}

		this.answeredQuestions.add(answeredQuestion);
	}

	@Transient
	public String createListAnsweredQuestionsAsString() {
		return listAsString(answeredQuestions);
	}

	@Transient
	public String getDisplayName() {
		try {
			return questionnaire.getName();
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
