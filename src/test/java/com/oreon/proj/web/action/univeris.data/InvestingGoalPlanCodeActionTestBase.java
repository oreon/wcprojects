package com.oreon.proj.web.action.univeris.data;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.proj.univeris.data.InvestingGoalPlanCode;

public class InvestingGoalPlanCodeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<InvestingGoalPlanCode> {

	InvestingGoalPlanCodeAction investingGoalPlanCodeAction = new InvestingGoalPlanCodeAction();

	@Override
	public BaseAction<InvestingGoalPlanCode> getAction() {
		return investingGoalPlanCodeAction;
	}

}
