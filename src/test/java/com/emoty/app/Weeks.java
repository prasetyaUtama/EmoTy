package com.emoty.app;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Weeks {

	public Weeks() {
		super();
	}
	
	private List<String> week;
	
	public List<String> getWeek() {
		return week;
	}
	public void setWeek(List<String> week) {
		this.week = week;
	}

	@Override
	public String toString() {
		return "Weeks [week=" + week + "]";
	}
}
