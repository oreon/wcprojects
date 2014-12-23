package com.oreon.proj.web.action.onepack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.primefaces.component.chart.Chart;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.PieChartModel;
import org.witchcraft.base.entity.AnalyticsData;

@Name("customerList")
@Scope(ScopeType.CONVERSATION)
public class CustomerListQuery extends CustomerListQueryBase implements
		java.io.Serializable {

	private String[] groupByFlds = { "address.province", "address.city",
			"gender", };
	
	TreeNode root = new DefaultTreeNode(new AnalyticsData("root", 0L), null);

	private List<String> selecteGroupField = new ArrayList<String>();

	public List<String> getListGroupByFields() {
		return Arrays.asList(groupByFlds);
	}
	
	

	private static void tradd(List<Object> list, TreeNode parent) {
		if (list.size() >= 2) {

			AnalyticsData adata = null;

			String firstElement = list.get(0).toString();

			if (list.size() == 2) {
				adata = new AnalyticsData(firstElement, (Long) list.get(1));
			} else {
				adata = new AnalyticsData(firstElement, 0L);
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

		if (selecteGroupField.isEmpty()) {
			selecteGroupField.addAll(getListGroupByFields());
			// return null;
		}

		StringBuilder sb = new StringBuilder();

		for (String grpField : selecteGroupField) {
			sb.append("p." + grpField + ",");
		}

		// remove trailing comma
		sb.deleteCharAt(sb.length() - 1);

		String qry = "select  %s,  count(p.id) from " + " Customer " /*
																	 * getClass()
																	 * .
																	 * getCanonicalName
																	 * ()
																	 */+ " p "
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

	public void itemSelect(ItemSelectEvent event) {
		
		Chart chart = (Chart) event.getSource();
		
		PieChartModel model = (PieChartModel) chart.getModel();
		
		Number cData = model.getData().get(event.getItemIndex());
		
		String id = chart.getId();
		
		if(id.equals("root")){
			root.getChildren().get(event.getItemIndex());
		}else{
			
			
		}
		//model.get
		
		//UIComponent component = chart.getChildren().get(event.getItemIndex());
		
	
		
		/*
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Item selected", "Item Index: " + event.getItemIndex()
						+ ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
		*/
	}
	
	
	public List<AnalyticsData> fetchChildPieCharts(){
		return null;
	}
}
