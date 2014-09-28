package com.emoty.app.model;

import java.util.ArrayList;
import java.util.List;

public class Query {
	private String query;
	private List<String> key = new ArrayList();

	public Query() {
		super();
		

	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<String> getKey() {

		return key;
	}

	public void setKey(List<String> key) {
		this.key = key;
	}

	public void extractKey(){
		String[] keys = this.query.split(" ");
		for (int i = 0; i < keys.length; i++) {
			this.key.add(keys[i]);
		}
	}
	
}
