package com.oreon.proj.questionnaire;

import javax.persistence.*;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@MappedSuperclass
public class AnswerBase extends BaseEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false, updatable = true, insertable = true)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Question question

	;

	@Size(min = 10)
	@Lob
	@Column(name = "text", unique = false)
	private String text

	;

	@Column(name = "score", unique = false)
	private Integer score

	;

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getScore() {
		return score;
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
