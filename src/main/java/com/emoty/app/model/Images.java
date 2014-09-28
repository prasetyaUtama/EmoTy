package com.emoty.app.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.crypto.codec.Base64;

@XmlRootElement
public class Images {
	private String name;
	private List<CategoryOfImage> categories;
	private String date;
	private int length;
	private String type;
	private byte[] bytes;

	
	
	
	
	public Images() {
		super();
	}

	public Images(String name, List<CategoryOfImage>  categories, String date, int length,
			String type, byte[] bytes) {
		super();
		this.name = name;
		this.categories = categories;
		this.date = date;
		this.length = length;
		this.type = type;
		this.bytes = bytes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CategoryOfImage>  getCategory() {
		return categories;
	}

	public void setCategory(List<CategoryOfImage>  categories) {
		this.categories = categories;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public byte[] getBytes() {
		return bytes;
	}

	public String getImage() {
		return new String(bytes);
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	@Override
	public String toString() {
		return "Image [name=" + name + " length= "+length+" type= "+type+ " categories= "+categories+" bytes= "+new String(Base64.decode(bytes))+"]";
	}

}
