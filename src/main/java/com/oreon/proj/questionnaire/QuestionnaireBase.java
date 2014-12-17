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
public class QuestionnaireBase extends BaseEntity {

	//@Unique(entityName = "com.oreon.proj.questionnaire.Questionnaire", fieldName = "name")

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "name", unique = true)
	private String name;

	@OneToMany(mappedBy = "questionnaire", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("id DESC")
	private List<Section> sections;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void addSection(Section section) {

		section.setQuestionnaire((Questionnaire) this);

		if (sections == null) {
			sections = new ArrayList<com.oreon.proj.questionnaire.Section>();
		}

		this.sections.add(section);
	}

	@Transient
	public String createListSectionsAsString() {
		return listAsString(sections);
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
