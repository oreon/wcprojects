package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.AnsweredQuestionnaire;

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

import com.oreon.proj.questionnaire.AnsweredQuestion;

//
public abstract class AnsweredQuestionnaireActionBase
		extends
			BaseAction<AnsweredQuestionnaire> implements java.io.Serializable {

	@RequestParameter
	protected Long answeredQuestionnaireId;

	public void setAnsweredQuestionnaireId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAnsweredQuestionnaireIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getAnsweredQuestionnaireId() {
		return (Long) getId();
	}

	public AnsweredQuestionnaire getAnsweredQuestionnaire() {
		return getEntity();
	}

	@Override
	@Restrict("#{s:hasPermission('answeredQuestionnaire', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('answeredQuestionnaire', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected AnsweredQuestionnaire createInstance() {
		AnsweredQuestionnaire instance = super.createInstance();

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
				 
				 
					com.oreon.proj.onepack.Customer customer = customerAction.getInstance();
					if (customer != null  ) {
						 getInstance().setCustomer(customer);
					}
				 
			
		}
		 */
	}

	public AnsweredQuestionnaire getDefinedInstance() {
		return (AnsweredQuestionnaire) (isIdDefined() ? getInstance() : null);
	}

	public void setAnsweredQuestionnaire(AnsweredQuestionnaire t) {
		setEntity(t);
	}

	@Override
	public Class<AnsweredQuestionnaire> getEntityClass() {
		return AnsweredQuestionnaire.class;
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

		if (instance.getCustomer() != null) {
			criteria = criteria.add(Restrictions.eq("customer.id", instance
					.getCustomer().getId()));
		}

	}

	public List<com.oreon.proj.questionnaire.AnsweredQuestion> getListAnsweredQuestions() {
		return getInstance().getAnsweredQuestions();
	}

	//public void prePopulateListAnsweredQuestions() {}

	public void setListAnsweredQuestions(
			List<com.oreon.proj.questionnaire.AnsweredQuestion> listAnsweredQuestions) {
		//this.listAnsweredQuestions = listAnsweredQuestions;
	}

	@Begin(join = true, flushMode = org.jboss.seam.annotations.FlushModeType.MANUAL)
	public void deleteAnsweredQuestions(int index) {
		getListAnsweredQuestions().remove(index);
	}

	@Begin(join = true, flushMode = org.jboss.seam.annotations.FlushModeType.MANUAL)
	public void addAnsweredQuestions() {
		getInstance().addAnsweredQuestion(
				new com.oreon.proj.questionnaire.AnsweredQuestion());
	}

	public String viewAnsweredQuestionnaire() {
		load(currentEntityId);
		return "viewAnsweredQuestionnaire";
	}

}
