package com.oreon.proj.web.action.onepack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.eclipse.persistence.descriptors.SelectedFieldsLockingPolicy;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.base.entity.AnalyticsData;
import org.witchcraft.base.entity.Range;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;

import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;

import javax.faces.model.DataModel;
import javax.persistence.Query;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

@Name("customerList")
@Scope(ScopeType.CONVERSATION)
public class CustomerListQuery extends CustomerListQueryBase implements
		java.io.Serializable {

	private String[] groupByFlds = { "gender", "address.city",
			"address.province" };

	private List<String> selecteGroupField = new ArrayList<String>();

	public List<String> getListGroupByFields() {
		return Arrays.asList(groupByFlds);
	}

	private static void tradd(List<Object> list, TreeNode parent) {
		if (list.size() >= 2) {

			AnalyticsData adata = null;

			if (list.size() == 2) {
				adata = new AnalyticsData((String) list.get(0),
						(Long) list.get(1));
			} else {
				adata = new AnalyticsData(list.get(0).toString(), 0L);
			}

			TreeNode child = findElement(parent.getChildren(), adata);

			if (child == null) {
				child = new DefaultTreeNode(adata, parent);
				// System.out.println(" adding " + adata.getName() + " " +
				// parent);
			}

			tradd(list.subList(1, list.size()), child);

			updateTotals(parent);
			
		}
	}

	private static void updateTotals(TreeNode current) {
		List<TreeNode> children = current.getChildren();
		List<AnalyticsData> dataChildren = new ArrayList<AnalyticsData>();
		AnalyticsData currentData = (AnalyticsData) current.getData();

		long total = 0;

		for (TreeNode treeNode : children) {

			AnalyticsData childData = ((AnalyticsData) treeNode.getData());
			total += childData.getSize();
			dataChildren.add(childData);
		}

		currentData.setSize(total);
		currentData.setChildren(dataChildren);
	}

	private static TreeNode findElement(List<TreeNode> list,
			AnalyticsData dataToFind) {
		for (TreeNode treeNode : list) {
			AnalyticsData current = (AnalyticsData) treeNode.getData();

			if ((current).equals(dataToFind)) {

				return treeNode;
			}
		}
		return null;
	}

	public TreeNode getTree() {

		TreeNode root = new DefaultTreeNode(new AnalyticsData("root", 0L), null);

		List<List<Object>> mytuples = findGroupedRecords();

		if (mytuples != null) {

			for (List<Object> list : mytuples) {
				tradd(list, root);	
			}

		}

		return root;
	}

	public List<String> getSelecteGroupField() {
		return selecteGroupField;
	}

	public void setSelecteGroupField(List<String> selecteGroupField) {
		this.selecteGroupField = selecteGroupField;
	}

	public List<List<Object>> findGroupedRecords() {

		if (selecteGroupField.isEmpty()){
			selecteGroupField.addAll(getListGroupByFields());
			//return null;
		}

		StringBuilder sb = new StringBuilder();

		for (String grpField : selecteGroupField) {
			sb.append("p." + grpField + ",");
		}

		// remove trailing comma
		sb.deleteCharAt(sb.length() - 1);

		String qry = "select  %s,  count(p.id) from "
				+ " Customer "  /*getClass().getCanonicalName()*/ + " p "
				+ " group by %s order by %s  ";

		qry = String.format(qry, sb.toString(), sb.toString(), sb.toString());

		String datepart = " where u.dateCreated > '20131001'";

		Query query = getEntityManager().createQuery(qry);

		List<Object[]> listExpected = query.getResultList();

		List<List<Object>> mytuples = new ArrayList<List<Object>>();

		for (Object[] strings : listExpected) {
			mytuples.add(Arrays.asList(strings));
		}

		return mytuples;

	}
}
