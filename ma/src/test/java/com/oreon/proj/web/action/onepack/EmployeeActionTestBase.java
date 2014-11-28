package com.oreon.proj.web.action.onepack;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.proj.onepack.Employee;

public class EmployeeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Employee> {

	EmployeeAction employeeAction = new EmployeeAction();

	@Override
	public BaseAction<Employee> getAction() {
		return employeeAction;
	}

}
