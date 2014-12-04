package com.oreon.proj.web.action.onepack;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.proj.onepack.OrderItem;

public class OrderItemActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<OrderItem> {

	OrderItemAction orderItemAction = new OrderItemAction();

	@Override
	public BaseAction<OrderItem> getAction() {
		return orderItemAction;
	}

}
