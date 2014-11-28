package org.witchcraft.base.entity;

import java.io.Serializable;

public class Range<T extends Serializable> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8120395366852371051L;
	
	
	private T begin;
	private T end;
	
	public T getBegin() {
		return begin;
	}
	public void setBegin(T begin) {
		this.begin = begin;
	}
	public T getEnd() {
		return end;
	}
	public void setEnd(T end) {
		this.end = end;
	}

}
