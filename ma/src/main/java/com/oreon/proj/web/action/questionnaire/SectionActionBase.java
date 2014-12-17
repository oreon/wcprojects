package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.Section;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.proj.questionnaire.Question;

//
public abstract class SectionActionBase extends BaseAction<Section>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long sectionId;

	public void setSectionId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setSectionIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getSectionId() {
		return (Long) getId();
	}

	public Section getSection() {
		return getEntity();
	}

	@Override
	@Restrict("#{s:hasPermission('section', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('section', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Section createInstance() {
		Section instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		if (isNew()) {
			instance = getInstance();

		}
	}

	public void wire() {
		/*
		if (isNew()){
			getInstance();
				 
					com.oreon.proj.questionnaire.Questionnaire questionnaire = questionnaireAction.getInstance();
					if (questionnaire != null  ) {
						 getInstance().setQuestionnaire(questionnaire);
					}
				 
			
		}
		 */
	}

	public Section getDefinedInstance() {
		return (Section) (isIdDefined() ? getInstance() : null);
	}

	public void setSection(Section t) {
		setEntity(t);
	}

	@Override
	public Class<Section> getEntityClass() {
		return Section.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getQuestionnaire() != null) {
			criteria = criteria.add(Restrictions.eq("questionnaire.id",
					instance.getQuestionnaire().getId()));
		}

	}

	public List<com.oreon.proj.questionnaire.Question> getListQuestions() {
		return getInstance().getQuestions();
	}

	//public void prePopulateListQuestions() {}

	public void setListQuestions(
			List<com.oreon.proj.questionnaire.Question> listQuestions) {
		//this.listQuestions = listQuestions;
	}

	@Begin(join = true, flushMode = org.jboss.seam.annotations.FlushModeType.MANUAL)
	public void deleteQuestions(int index) {
		getListQuestions().remove(index);
	}

	@Begin(join = true, flushMode = org.jboss.seam.annotations.FlushModeType.MANUAL)
	public void addQuestions() {
		getInstance().addQuestion(new com.oreon.proj.questionnaire.Question());
	}

	public String viewSection() {
		load(currentEntityId);
		return "viewSection";
	}

}
