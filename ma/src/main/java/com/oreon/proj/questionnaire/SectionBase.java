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
public class SectionBase extends BaseEntity {

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "name", unique = false)
	private String name;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "questionnaire_id", nullable = false, updatable = true, insertable = true)
	private Questionnaire questionnaire;

	@OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id DESC")
	private List<Question> questions;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void addQuestion(Question question) {

		question.setSection((Section) this);

		if (questions == null) {
			questions = new ArrayList<com.oreon.proj.questionnaire.Question>();
		}

		this.questions.add(question);
	}

	@Transient
	public String createListQuestionsAsString() {
		return listAsString(questions);
	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
