package com.oreon.proj.web.action.onepack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Name;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

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
			String data = list.size() == 2 ? list.get(0) + " " + list.get(1)
					: list.get(0);

			TreeNode child = findElement(parent.getChildren(), data);
			
			if (child == null) {
				child = new DefaultTreeNode(data, parent);
				System.out.println(" adding " + list.get(0) + " " + parent);
			}
			

			tradd(list.subList(1, list.size()), child);
		}
	}

	private static TreeNode findElement(List<TreeNode> list, String dataToFind) {
		for (TreeNode treeNode : list) {
			if (treeNode.getData().equals(dataToFind))
				return treeNode;
		}
		return null;
	}

	public TreeNode getTree() {
		
		String[][] arr2 = { { "ON", "Scarborough", "M", "11" }, 
				{ "ON", "Toronto", "M", "1" }, { "ON", "Brampton", "M", "1" }, { "ON", "Toronto", "F", "7" }, { "ON", "Brampton", "F", "1" },
			  { "ON", "Scarborough","F", "1", },
			  { "AB", "Calgary", "F", "1", },
		};
		
		
		TreeNode root = new DefaultTreeNode("", null);

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
