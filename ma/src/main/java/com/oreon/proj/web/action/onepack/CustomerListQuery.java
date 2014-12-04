package com.oreon.proj.web.action.onepack;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("customerList")
@Scope(ScopeType.CONVERSATION)
public class CustomerListQuery extends CustomerListQueryBase
		implements
			java.io.Serializable {

}
