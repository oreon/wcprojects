package com.oreon.proj.web.action.onepack;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("departmentList")
@Scope(ScopeType.CONVERSATION)
public class DepartmentListQuery extends DepartmentListQueryBase
		implements
			java.io.Serializable {

}
