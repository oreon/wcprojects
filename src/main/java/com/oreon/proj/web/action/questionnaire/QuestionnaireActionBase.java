package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.Questionnaire;

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

import com.oreon.proj.questionnaire.Section;

//
public abstract class QuestionnaireActionBase extends BaseAction<Questionnaire>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long questionnaireId;

	public void setQuestionnaireId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setQuestionnaireIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getQuestionnaireId() {
		return (Long) getId();
	}

	public Questionnaire getQuestionnaire() {
		return getEntity();
	}

	@Override
	@Restrict("#{s:hasPermission('questionnaire', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('questionnaire', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Questionnaire createInstance() {
		Questionnaire instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		if (isNew()) {
			instance = getInstance();

			if (instance.getSections().isEmpty()) {
				for (int i = 0; i < 1; i++) {
					com.oreon.proj.questionnaire.Section item = new com.oreon.proj.questionnaire.Section();
					item.setQuestionnaire(getInstance());
					getListSections().add(item);
				}
			}

		}
	}

	public void wire() {
		/*
		if (isNew()){
			getInstance();
			
		}
		 */
	}

	public Questionnaire getDefinedInstance() {
		return (Questionnaire) (isIdDefined() ? getInstance() : null);
	}

	public void setQuestionnaire(Questionnaire t) {
		setEntity(t);
	}

	@Override
	public Class<Questionnaire> getEntityClass() {
		return Questionnaire.class;
	}

	public com.oreon.proj.questionnaire.Questionnaire findByUnqName(String name) {
		return executeSingleResultNamedQuery("questionnaire.findByUnqName",
				name);
	}

	public List<com.oreon.proj.questionnaire.Section> getListSections() {
		return getInstance().getSections();
	}

	//public void prePopulateListSections() {}

	public void setListSections(
			List<com.oreon.proj.questionnaire.Section> listSections) {
		//this.listSections = listSections;
	}

	@Begin(join = true, flushMode = org.jboss.seam.annotations.FlushModeType.MANUAL)
	public void deleteSections(int index) {
		getListSections().remove(index);
	}

	@Begin(join = true, flushMode = org.jboss.seam.annotations.FlushModeType.MANUAL)
	public void addSections() {
		getInstance().addSection(new com.oreon.proj.questionnaire.Section());
	}

	public String viewQuestionnaire() {
		load(currentEntityId);
		return "viewQuestionnaire";
	}

}
