package com.oreon.proj.web.action.onepack;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("productList")
@Scope(ScopeType.CONVERSATION)
public class ProductListQuery extends ProductListQueryBase
		implements
			java.io.Serializable {

}
