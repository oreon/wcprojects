package com.oreon.proj.univeris.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class InvestingGoalBase {

	private String nameEng

	;

	private String nameFre

	;

	private String imageFileIri

	;

	private Boolean isDefault

	;

	private InvestingGoalPlanCode investingGoalPlanCode

	;

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameFre(String nameFre) {
		this.nameFre = nameFre;
	}

	public String getNameFre() {
		return nameFre;
	}

	public void setImageFileIri(String imageFileIri) {
		this.imageFileIri = imageFileIri;
	}

	public String getImageFileIri() {
		return imageFileIri;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setInvestingGoalPlanCode(
			InvestingGoalPlanCode investingGoalPlanCode) {
		this.investingGoalPlanCode = investingGoalPlanCode;
	}

	public InvestingGoalPlanCode getInvestingGoalPlanCode() {
		return investingGoalPlanCode;
	}

}
