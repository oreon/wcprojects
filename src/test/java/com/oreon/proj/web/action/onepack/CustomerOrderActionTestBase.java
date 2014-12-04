package com.oreon.proj.web.action.onepack;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.proj.onepack.CustomerOrder;

public class CustomerOrderActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<CustomerOrder> {

	CustomerOrderAction customerOrderAction = new CustomerOrderAction();

	@Override
	public BaseAction<CustomerOrder> getAction() {
		return customerOrderAction;
	}

}
