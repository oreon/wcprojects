package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.Employee;

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

//
public abstract class EmployeeActionBase extends BaseAction<Employee>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long employeeId;

	@In(create = true, value = "departmentAction")
	com.oreon.proj.web.action.onepack.DepartmentAction departmentAction;

	public void setEmployeeId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setEmployeeIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setDepartmentId(Long id) {

		if (id != null && id > 0)
			getInstance().setDepartment(departmentAction.loadFromId(id));

	}

	public Long getDepartmentId() {
		if (getInstance().getDepartment() != null)
			return getInstance().getDepartment().getId();
		return 0L;
	}

	public Long getEmployeeId() {
		return (Long) getId();
	}

	public Employee getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Employee t) {
		this.instance = t;
		loadAssociations();
	}

	public Employee getEmployee() {
		return (Employee) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('employee', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('employee', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Employee createInstance() {
		Employee instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

		com.oreon.proj.onepack.Department department = departmentAction
				.getInstance();
		if (department != null && isNew()) {
			getInstance().setDepartment(department);
		}

	}

	public Employee getDefinedInstance() {
		return (Employee) (isIdDefined() ? getInstance() : null);
	}

	public void setEmployee(Employee t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setEmployeeId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getDepartment() != null) {
			criteria = criteria.add(Restrictions.eq("department.id", instance
					.getDepartment().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		addDefaultAssociations();

		wire();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {

	}

	public void clearLists() {

	}

	public String viewEmployee() {
		load(currentEntityId);
		return "viewEmployee";
	}

}
