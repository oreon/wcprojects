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
public class QuestionBase extends BaseEntity {

	@Lob
	@Column(name = "text", unique = false)
	private String text

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id", nullable = false, updatable = true, insertable = true)
	private Section section

	;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id DESC")
	private List<Answer> answers

	= new ArrayList<Answer>()

	;

	@Column(name = "answerType", unique = false)
	private AnswerType answerType

	;

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Section getSection() {
		return section;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void addAnswer(Answer answer) {

		answer.setQuestion((Question) this);

		if (answers == null) {
			answers = new ArrayList<com.oreon.proj.questionnaire.Answer>();
		}

		this.answers.add(answer);
	}

	@Transient
	public String createListAnswersAsString() {
		return listAsString(answers);
	}

	public void setAnswerType(AnswerType answerType) {
		this.answerType = answerType;
	}

	public AnswerType getAnswerType() {
		return answerType;
	}

	@Transient
	public String getDisplayName() {
		try {
			return text;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
