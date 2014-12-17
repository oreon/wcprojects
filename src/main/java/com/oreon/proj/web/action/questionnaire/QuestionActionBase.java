package com.oreon.proj.web.action.questionnaire;

import com.oreon.proj.questionnaire.Question;

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

import com.oreon.proj.questionnaire.Answer;

//
public abstract class QuestionActionBase extends BaseAction<Question>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long questionId;

	public void setQuestionId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setQuestionIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getQuestionId() {
		return (Long) getId();
	}

	public Question getQuestion() {
		return getEntity();
	}

	@Override
	@Restrict("#{s:hasPermission('question', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('question', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Question createInstance() {
		Question instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		if (isNew()) {
			instance = getInstance();

			if (instance.getAnswers().isEmpty()) {
				for (int i = 0; i < 1; i++) {
					com.oreon.proj.questionnaire.Answer item = new com.oreon.proj.questionnaire.Answer();
					item.setQuestion(getInstance());
					getListAnswers().add(item);
				}
			}

		}
	}

	public void wire() {
		/*
		if (isNew()){
			getInstance();
				 
					com.oreon.proj.questionnaire.Section section = sectionAction.getInstance();
					if (section != null  ) {
						 getInstance().setSection(section);
					}
				 
			
		}
		 */
	}

	public Question getDefinedInstance() {
		return (Question) (isIdDefined() ? getInstance() : null);
	}

	public void setQuestion(Question t) {
		setEntity(t);
	}

	@Override
	public Class<Question> getEntityClass() {
		return Question.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getSection() != null) {
			criteria = criteria.add(Restrictions.eq("section.id", instance
					.getSection().getId()));
		}

	}

	public List<com.oreon.proj.questionnaire.Answer> getListAnswers() {
		return getInstance().getAnswers();
	}

	//public void prePopulateListAnswers() {}

	public void setListAnswers(
			List<com.oreon.proj.questionnaire.Answer> listAnswers) {
		//this.listAnswers = listAnswers;
	}

	@Begin(join = true, flushMode = org.jboss.seam.annotations.FlushModeType.MANUAL)
	public void deleteAnswers(int index) {
		getListAnswers().remove(index);
	}

	@Begin(join = true, flushMode = org.jboss.seam.annotations.FlushModeType.MANUAL)
	public void addAnswers() {
		getInstance().addAnswer(new com.oreon.proj.questionnaire.Answer());
	}

	public String viewQuestion() {
		load(currentEntityId);
		return "viewQuestion";
	}

}
