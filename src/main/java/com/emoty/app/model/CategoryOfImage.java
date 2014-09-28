package com.emoty.app.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CategoryOfImage {
	private String mainCategory;
	private String category;
	

	
	public CategoryOfImage(String mainCategory, String category) {
		super();
		this.mainCategory = mainCategory;
		this.category = category;
	}
	public CategoryOfImage(){
		super();
	}
	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
