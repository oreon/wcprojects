package com.oreon.proj.web.action.univeris.data;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.proj.univeris.data.InvestorProfile;

public class InvestorProfileActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<InvestorProfile> {

	InvestorProfileAction investorProfileAction = new InvestorProfileAction();

	@Override
	public BaseAction<InvestorProfile> getAction() {
		return investorProfileAction;
	}

}
