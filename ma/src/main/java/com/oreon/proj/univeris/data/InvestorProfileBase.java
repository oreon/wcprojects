package com.oreon.proj.univeris.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class InvestorProfileBase {

	private String nameEng;

	private String nameFre;

	private String descEng;

	private String descFre;

	private String imageFileUri;

	private Integer conservativeOrder;

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

	public void setDescEng(String descEng) {
		this.descEng = descEng;
	}

	public String getDescEng() {
		return descEng;
	}

	public void setDescFre(String descFre) {
		this.descFre = descFre;
	}

	public String getDescFre() {
		return descFre;
	}

	public void setImageFileUri(String imageFileUri) {
		this.imageFileUri = imageFileUri;
	}

	public String getImageFileUri() {
		return imageFileUri;
	}

	public void setConservativeOrder(Integer conservativeOrder) {
		this.conservativeOrder = conservativeOrder;
	}

	public Integer getConservativeOrder() {
		return conservativeOrder;
	}

}
