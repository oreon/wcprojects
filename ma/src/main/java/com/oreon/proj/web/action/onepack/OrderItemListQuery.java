package com.oreon.proj.web.action.onepack;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("orderItemList")
@Scope(ScopeType.CONVERSATION)
public class OrderItemListQuery extends OrderItemListQueryBase
		implements
			java.io.Serializable {

}
