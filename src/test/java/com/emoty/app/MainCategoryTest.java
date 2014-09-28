package com.emoty.app;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.emoty.app.config.DatabaseConnection;
import com.emoty.app.model.MainCategory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;

public class MainCategoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseConnection con = new DatabaseConnection();

		
		List<String> typeCategory = new ArrayList();
		typeCategory.add("Skin Color");
		typeCategory.add("Expression");
		typeCategory.add("Ethnicity");
		
		MainCategory categories= new MainCategory();
		categories.setTypeCategory(typeCategory);
		
		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();

		// initialize JAXB for processing the POJO class
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(MainCategory.class);
			// create a handle on the POJOs for writing to the database
			JAXBHandle writeHandle = new JAXBHandle(context);
			Unmarshaller u = context.createUnmarshaller();
			

			// provide a handle for the POJO
			writeHandle.set(categories);

			// write the POJO to the database
			//docMgr.delete("/categories/MainCategory.xml");
			docMgr.write("/mainCategory/MainCategory.xml", writeHandle);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
con.getConnection().release();
	}

}
