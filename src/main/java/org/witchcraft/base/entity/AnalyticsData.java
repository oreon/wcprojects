package org.witchcraft.base.entity;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
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
		return getName().equals(((AnalyticsData) obj).getName());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " " + size;
	}

	private PieChartModel pieChartModel;
	
	private BarChartModel barChartModel;

	public BarChartModel getBarChartModel() {
		if (barChartModel == null) {
			createBarChartModel();
		}
		return barChartModel;
	}

	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}

	public PieChartModel getPieChartModel() {
		if (pieChartModel == null) {
			createPieModel();
		}
		return pieChartModel;
	}

	public void setPieChartModel(PieChartModel pieChartModel) {
		this.pieChartModel = pieChartModel;
	}

	private void createPieModel() {
		pieChartModel = new PieChartModel();
		pieChartModel.setLegendPosition("e");

		pieChartModel.setShowDataLabels(true);

		for (AnalyticsData childData : children) {
			pieChartModel.set(childData.getName(), childData.getSize());
		}
	}

	private void createBarChartModel() {
		barChartModel = new BarChartModel();
		
		ChartSeries series  = new ChartSeries();
		
		for (AnalyticsData childData : children) {
			series.set(childData.getName(), childData.getSize());
		}
		
		barChartModel.addSeries(series);
	}
}
