package com.oreon.proj.web.action.univeris.data;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.proj.univeris.data.InvestingGoal;

public class InvestingGoalActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<InvestingGoal> {

	InvestingGoalAction investingGoalAction = new InvestingGoalAction();

	@Override
	public BaseAction<InvestingGoal> getAction() {
		return investingGoalAction;
	}

}
