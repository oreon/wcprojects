package com.oreon.proj.web.action.onepack;

import org.jboss.seam.annotations.Name;

import com.oreon.proj.onepack.Department;

//@Scope(ScopeType.CONVERSATION)
@Name("employeeAction")
public class EmployeeAction extends EmployeeActionBase
		implements
			java.io.Serializable {
	
	public void setDepartment(Department department){
		getInstance().setDepartment(department);
	}

}
