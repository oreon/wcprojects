package org.witchcraft.base.entity;

import java.io.Serializable;
import java.util.Stack;

import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * This allows for views the be accessed from various places and still return to
 * the correct parent view when the user is done with them, which having to pass
 * around parent view id as a page paramter. It is controlled from
 * WEB-INF/pages.xml
 */
@Name("pageViewStack")
@Scope(ScopeType.CONVERSATION)
@AutoCreate
public class PageViewStack implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6485856537830412866L;
	int count = 0;

	public PageViewStack() {
		views = new Stack<String>();
	}

	public void push() {
		String a = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		if (a == null || isEditPage(a)) {
			return;
		}
		
		
		if (views.empty() || !views.peek().equals(a)) {
			
			views.push(a);
			count++;
		}
	}
	
	private boolean isEditPage(String pagename){
		String[] arr = pagename.split("/");
		if(arr[arr.length -1].startsWith("edit"))
			return true;
		return false;
	}

	/*
	 * This pops the top item and then returns the next item This is because the
	 * top item is always the active page, and we will be trying to move the the
	 * active pages parent
	 */
	public String pop() {
		if (count > 1) {
			views.pop();
			count--;
		}
		return views.peek();
	}

	Stack<String> views;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
