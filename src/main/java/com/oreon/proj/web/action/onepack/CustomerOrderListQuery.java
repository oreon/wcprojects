package com.oreon.proj.web.action.onepack;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("customerOrderList")
@Scope(ScopeType.CONVERSATION)
public class CustomerOrderListQuery extends CustomerOrderListQueryBase
		implements
			java.io.Serializable {

}
