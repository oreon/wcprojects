package com.oreon.proj.web.action.onepack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

@Name("customerList")
@Scope(ScopeType.CONVERSATION)
public class CustomerListQuery extends CustomerListQueryBase
		implements
			java.io.Serializable {
	
	private String[] groupByFlds = {"gender","address.city","address.province"};
	
	private List<String> selecteGroupField = new ArrayList<String>();
	
	public List<String> getListGroupByFields(){
		return Arrays.asList(groupByFlds);
	}
	
	
	private static void tradd(List<String> list, TreeNode parent) {
		if (list.size() >= 2) {
			
			AnalyticsData adata = null;
			
			if(list.size() == 2 ){
				 adata = new AnalyticsData(list.get(0) , Integer.parseInt(list.get(1)) );
			}else{
				 adata = new AnalyticsData(list.get(0) , 0  );
			}
			
			TreeNode child = findElement(parent.getChildren(), adata);
			
			if (child == null) {
				child = new DefaultTreeNode(adata, parent);
				//System.out.println(" adding " +  adata.getName() + " " + parent);
			}
			
			tradd(list.subList(1, list.size()), child);
			
			updateTotals(parent);
		}
	}

	private static void updateTotals(TreeNode current) {
		List<TreeNode> children = current.getChildren();
		AnalyticsData currentData = (AnalyticsData) current.getData();
		
		int total = 0;
		
		for (TreeNode treeNode : children) {
			
			AnalyticsData childData = ((AnalyticsData)treeNode.getData()); 
			total += childData.getSize(); 
		}
		
		currentData.setSize(total);
	}

	private static TreeNode findElement(List<TreeNode> list, AnalyticsData dataToFind) {
		for (TreeNode treeNode : list) {
			AnalyticsData current = (AnalyticsData)treeNode.getData(); 
			
			if ( (current).equals(dataToFind)){
				
				return treeNode;
			}
		}
		return null;
	}

	public TreeNode getTree() {
		
		String[][] arr2 = { { "ON", "Scarborough", "M", "11" }, 
				{ "ON", "Toronto", "M", "1" }, { "ON", "Brampton", "M", "1" }, { "ON", "Toronto", "F", "7" },
				{ "ON", "Brampton", "F", "1" },
			  { "ON", "Scarborough","F", "1", },
			  { "AB", "Calgary", "F", "1", },
		};
		
		
		TreeNode root = new DefaultTreeNode(new AnalyticsData("root", 0), null);

		List<List<String>> tuples = new ArrayList<List<String>>();

		for (String[] strings : arr2) {
			tuples.add(Arrays.asList(strings));
		}


		for (List<String> list : tuples) {
			tradd(list, root);
		}

		return root;
	}


	public List<String> getSelecteGroupField() {
		return selecteGroupField;
	}


	public void setSelecteGroupField(List<String> selecteGroupField) {
		this.selecteGroupField = selecteGroupField;
	}


}
