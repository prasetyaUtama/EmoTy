package com.emoty.app;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.w3c.dom.Document;

import com.emoty.app.config.DatabaseConnection;
import com.emoty.app.model.MainCategory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.JAXBHandle;

public class ReadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseConnection con = new DatabaseConnection();
		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(MainCategory.class);
			JAXBHandle handle = new JAXBHandle(context);
			docMgr.read("/categories/MainCategory.xml", handle);
			MainCategory categories = (MainCategory) handle.get();
			System.out.println(categories.getTypeCategory().get(0));
			con.getConnection().release();
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
