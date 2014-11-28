package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.Department;

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

import com.oreon.proj.onepack.Employee;

//
public abstract class DepartmentActionBase extends BaseAction<Department>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long departmentId;

	@In(create = true, value = "employeeAction")
	com.oreon.proj.web.action.onepack.EmployeeAction employeesAction;

	public void setDepartmentId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDepartmentIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getDepartmentId() {
		return (Long) getId();
	}

	public Department getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Department t) {
		this.instance = t;
		loadAssociations();
	}

	public Department getDepartment() {
		return (Department) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('department', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('department', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Department createInstance() {
		Department instance = super.createInstance();

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

	}

	public Department getDefinedInstance() {
		return (Department) (isIdDefined() ? getInstance() : null);
	}

	public void setDepartment(Department t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setDepartmentId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Department> getEntityClass() {
		return Department.class;
	}

	public com.oreon.proj.onepack.Department findByUnqName(String name) {
		return executeSingleResultNamedQuery("department.findByUnqName", name);
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

	protected List<com.oreon.proj.onepack.Employee> listEmployees = new ArrayList<com.oreon.proj.onepack.Employee>();

	void initListEmployees() {

		if (listEmployees.isEmpty())
			listEmployees.addAll(getInstance().getEmployees());

	}

	public List<com.oreon.proj.onepack.Employee> getListEmployees() {

		prePopulateListEmployees();
		return listEmployees;
	}

	public void prePopulateListEmployees() {
	}

	public void setListEmployees(
			List<com.oreon.proj.onepack.Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public void deleteEmployees(int index) {
		listEmployees.remove(index);
	}

	@Begin(join = true)
	public void addEmployees() {

		initListEmployees();
		Employee employees = new Employee();

		employees.setDepartment(getInstance());

		getListEmployees().add(employees);

	}

	public void updateComposedAssociations() {

	}

	public void clearLists() {
		listEmployees.clear();

	}

	public String viewDepartment() {
		load(currentEntityId);
		return "viewDepartment";
	}

}
