package com.oreon.proj.web.action.onepack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.witchcraft.base.entity.AnalyticsData;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import edu.emory.mathcs.backport.java.util.Collections;

public class Gropuer {

	public static void main(String[] args) {

		new CustomerAction().getTree();

	}


	String[][] arr = { { "ON", "Toronto", "F", "1" }, 
			{ "ON", "Toronto", "M", "1" }, { "ON", "Brampton", "M", "1" }, { "ON", "Toronto", "F", "7" }, { "ON", "Brampton", "F", "1" },
		  { "ON", "scarborough","F", "1", },
		  { "AB", "Calgary", "F", "1", },
	};
	
	
	
}