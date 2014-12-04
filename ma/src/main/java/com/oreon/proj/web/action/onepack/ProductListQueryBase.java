package com.oreon.proj.web.action.onepack;

import com.oreon.proj.onepack.Product;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.proj.onepack.Product;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ProductListQueryBase extends BaseQuery<Product, Long> {

	private static final String EJBQL = "select product from Product product";

	protected Product product = new Product();

	public ProductListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Product getProduct() {
		return product;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Product getInstance() {
		return getProduct();
	}

	@Override
	//@Restrict("#{s:hasPermission('product', 'view')}")
	public List<Product> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"product.id = #{productList.product.id}",

			"product.archived = #{productList.product.archived}",

			"lower(product.name) like concat(lower(#{productList.product.name}),'%')",

			"product.dateCreated <= #{productList.dateCreatedRange.end}",
			"product.dateCreated >= #{productList.dateCreatedRange.begin}",};

	@Observer("archivedProduct")
	public void onArchive() {
		refresh();
	}

}
