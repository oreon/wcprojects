package com.oreon.proj.web.action.onepack;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.proj.onepack.Customer;

public class CustomerActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Customer> {

	CustomerAction customerAction = new CustomerAction();

	@Override
	public BaseAction<Customer> getAction() {
		return customerAction;
	}

}
