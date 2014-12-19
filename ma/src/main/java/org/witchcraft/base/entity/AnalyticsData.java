package org.witchcraft.base.entity;

public class AnalyticsData {
	
	
	private String name;
	private Integer size;
	
	public AnalyticsData(String name, Integer size) {
		super();
		this.name = name;
		this.size = size;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return getName().equals( ((AnalyticsData)obj).getName());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " " + size;
	}

}
