package com.emoty.app;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

import com.marklogic.client.io.marker.XMLWriteHandle;

@XmlRootElement
public class ImageTest  {
	private  String hello = "muah";

	private  String id = "image1";
	
	private  Weeks weeks;

	
	public String getHello() {
		return hello;
	}



	public Weeks getWeeks() {
		return weeks;
	}




	@XmlElement
	public String getId() {
		return id;
	}
	
	

	public void setWeeks(Weeks weeks) {
		this.weeks = weeks;
	}


	public void setHello(String hello) {
		this.hello = hello;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Image [name="+hello+"]";
	}
}
