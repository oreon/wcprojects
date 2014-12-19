package com.oreon.proj.web.action.onepack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Name;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.witchcraft.base.entity.AnalyticsData;

//@Scope(ScopeType.CONVERSATION)
@Name("customerAction")
public class CustomerAction extends CustomerActionBase implements
		java.io.Serializable {

	@Begin(pageflow = "investor", join = true)
	public void begin() {

	}

	String[][] arr = { { "ON", "Toronto", "F", "1" }, 
			{ "ON", "Toronto", "M", "1" }, { "ON", "Brampton", "M", "1" }, { "ON", "Toronto", "F", "7" }, { "ON", "Brampton", "F", "1" },
		  { "ON", "scarborough","F", "1", },
		  { "AB", "Calgary", "F", "1", },
	};
	
	
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
				System.out.println(" adding " +  adata.getName() + " " + parent);
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

}
