package com.oreon.proj.web.action.onepack;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.proj.onepack.Product;

public class ProductActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Product> {

	ProductAction productAction = new ProductAction();

	@Override
	public BaseAction<Product> getAction() {
		return productAction;
	}

}
