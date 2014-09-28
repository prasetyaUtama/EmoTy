package com.emoty.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.emoty.app.config.Config;
import com.emoty.app.config.DatabaseConnection;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;

public class ConfigTest {

	@Test
	public void test() {

		
		
	}

	public static void main(String args[]){
		DatabaseConnection con = new DatabaseConnection();
		ImageTest img= new ImageTest();
		
		List<String> week = new ArrayList();
		week.add("asas");
		week.add("asassd");
		Weeks weeks= new Weeks();
		weeks.setWeek(week);
		img.setWeeks(weeks);
		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();

		// initialize JAXB for processing the POJO class
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(ImageTest.class);
			// create a handle on the POJOs for writing to the database
			JAXBHandle writeHandle = new JAXBHandle(context);
			Unmarshaller u = context.createUnmarshaller();
			

			// provide a handle for the POJO
			writeHandle.set(img);

			// write the POJO to the database
			docMgr.write("/example/test.xml", writeHandle);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
con.getConnection().release();
		
	}
}
