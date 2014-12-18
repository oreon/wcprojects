package com.oreon.proj.web.action.onepack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class Gropuer {

	public static void main(String[] args) {
		
		String[][] arr = { { "ON", "Toronto", "F", "1" },
				{ "ON", "York", "M", "3" },
				
				{ "AB", "Calgary", "F", "1", }};

		/*
		String[][] arr = { { "ON", "Toronto", "F", "1" },
				{ "ON", "Toronto", "M", "1" },
				{ "ON", "Brampton", "M", "1" },
				{ "ON", "scarborough", "F", "1", },
				{ "AB", "Calgary", "F", "1", },
				
		};*/

		//Node root = new Node("root");

		
		
		List<List<String>> tuples = new ArrayList<List<String>>();
		
		for (String[] strings : arr) {
			 tuples.add(Arrays.asList(strings));
			 //addto(myroot, Arrays.asList(strings));
			 //myroot.put(strings[0], strings[1] +  strings[2]);
		}
		
		
		
		ListMultimap myroot = ArrayListMultimap.create();
		
		addthis(myroot, tuples);
		

		for (Object key : myroot.keySet()) {
			List<String> vals = (List<String>)myroot.get(key);
			
			//ListMultimap result = add((String) key, myroot, (List<String>) vals);
			//myroot.put(key, result);
			System.out.println(key + ": " + vals);
		}
		
		System.out.println(myroot);

	}
	
	private static ListMultimap addthis(ListMultimap myroot, List<List<String>> tuples) {
		
		ListMultimap child = ArrayListMultimap.create();
		
		for (List<String> list : tuples) {
			if(list.size() > 1 && tuples.size() > 1)
				myroot.put((String)list.get(0),   addthis(child, tuples.subList(1, tuples.size()  ) )   );
		}
		
		return child;
		
	}

	@SuppressWarnings("unchecked")
	private static ListMultimap add(String key, @SuppressWarnings("rawtypes") ListMultimap myroot, List<String> list) {
		ListMultimap child = ArrayListMultimap.create();
		child.put(list.get(0), list.subList(1, list.size()));
		
		System.out.println(child);
		return child;
		
		/*
		for (Object firstName : myroot.keySet()) {
			Object vals = myroot.get(firstName);
			add(child, (List<String>) vals);
			System.out.println(firstName + ": " + vals);
		}*/
	}

	public static void add(ListMultimap myroot, List<String[]> tuples, int start){
		if(tuples.isEmpty())
			return;
		
		ListMultimap child = ArrayListMultimap.create();
		
		int newstart = start + 1;
		if( newstart < tuples.get(0).length - 1 )
			add (child, tuples, newstart);
		
		for (String[] strings : tuples) {
			for(int i = start; i < strings.length; i++){
				//if(!child.isEmpty())
				myroot.put(strings[start],  child);
			}
		}
		
		//System.out.println(myroot);
		
		
		
	}
	
	/*
	private static void add(ListMultimap<String, String> map,  String[][] children){
		
		
		for (String child : children) {
			map.put(root, child);
			ListMultimap<String, String> chldmap = ArrayListMultimap.create();
			add(chldmap , children);
		}
		
		

	}*/

}