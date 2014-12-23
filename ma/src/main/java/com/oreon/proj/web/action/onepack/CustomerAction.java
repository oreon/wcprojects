package com.oreon.proj.web.action.onepack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Name;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.witchcraft.base.entity.AnalyticsData;

//@Scope(ScopeType.CONVERSATION)
@Name("customerAction")
public class CustomerAction extends CustomerActionBase implements
		java.io.Serializable {

	@Begin(pageflow = "investor", join = true)
	public void begin() {

	}
	
	

}
