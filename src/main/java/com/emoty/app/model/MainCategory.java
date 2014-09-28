package com.emoty.app.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class MainCategory {
	private List<String> typeCategory;
	public MainCategory(){
		super();
	}
	public MainCategory(MainCategory mainCategory){
		this.typeCategory = mainCategory.getTypeCategory();
	}
	


	public List<String> getTypeCategory() {
		return typeCategory;
	}

	public void setTypeCategory(List<String> typeCategory) {
		this.typeCategory = typeCategory;
	}
	
	@Override
	public String toString() {
		return "MainCategories [typeCategories=" + typeCategory + "]";
	}
	

}
