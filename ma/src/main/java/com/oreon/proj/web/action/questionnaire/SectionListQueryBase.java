package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.Section;

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

import com.oreon.proj.questionnaire.Section;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SectionListQueryBase extends BaseQuery<Section, Long> {

	private static final String EJBQL = "select section from Section section";

	protected Section section = new Section();

	public SectionListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Section getSection() {
		return section;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Section getInstance() {
		return getSection();
	}

	@Override
	//@Restrict("#{s:hasPermission('section', 'view')}")
	public List<Section> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Section> getEntityClass() {
		return Section.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"section.id = #{sectionList.section.id}",

			"section.archived = #{sectionList.section.archived}",

			"lower(section.name) like concat(lower(#{sectionList.section.name}),'%')",

			"section.questionnaire.id = #{sectionList.section.questionnaire.id}",

			"section.dateCreated <= #{sectionList.dateCreatedRange.end}",
			"section.dateCreated >= #{sectionList.dateCreatedRange.begin}",};

	/** 
	 * List of all Sections for the given Questionnaire
	 * @param patient
	 * @return 
	 */
	public List<Section> getAllSectionsByQuestionnaire(
			final com.oreon.proj.questionnaire.Questionnaire questionnaire) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		section.setQuestionnaire(questionnaire);
		return getResultListTable();
	}

	public LazyDataModel<Section> getSectionsByQuestionnaire(
			final com.oreon.proj.questionnaire.Questionnaire questionnaire) {

		EntityLazyDataModel<Section, Long> sectionLazyDataModel = new EntityLazyDataModel<Section, Long>(
				this) {

			@Override
			public List<Section> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				section.setQuestionnaire(questionnaire);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return sectionLazyDataModel;

	}

	@Observer("archivedSection")
	public void onArchive() {
		refresh();
	}

	public void setQuestionnaireId(Long id) {
		if (section.getQuestionnaire() == null) {
			section
					.setQuestionnaire(new com.oreon.proj.questionnaire.Questionnaire());
		}
		section.getQuestionnaire().setId(id);
	}

	public Long getQuestionnaireId() {
		return section.getQuestionnaire() == null ? null : section
				.getQuestionnaire().getId();
	}

}
