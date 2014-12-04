package com.oreon.proj.web.action.onepack;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("employeeList")
@Scope(ScopeType.CONVERSATION)
public class EmployeeListQuery extends EmployeeListQueryBase
		implements
			java.io.Serializable {

}
