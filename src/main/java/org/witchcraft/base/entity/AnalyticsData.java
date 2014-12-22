package org.witchcraft.base.entity;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.PieChartModel;

public class AnalyticsData {
	
	
	private String name;
	private Long size;
	
	private List<AnalyticsData> children = new ArrayList<AnalyticsData>();
	
	public List<AnalyticsData> getChildren() {
		return children;
	}



	public void setChildren(List<AnalyticsData> children) {
		this.children = children;
	}



	public AnalyticsData(String name, Long size) {
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
	
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
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

	private PieChartModel pieChartModel; 
	
	public PieChartModel getPieChartModel() {
		if(pieChartModel == null){
			createPieModel();
		}
		return pieChartModel;
	}



	public void setPieChartModel(PieChartModel pieChartModel) {
		this.pieChartModel = pieChartModel;
	}



	private void createPieModel() {
		pieChartModel = new PieChartModel();
		
        for (AnalyticsData childData : children) {
			pieChartModel.set(childData.getName(), childData.getSize());
		}
    }
}
