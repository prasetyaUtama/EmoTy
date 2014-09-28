package com.emoty.app.administratorEmoty.controller;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emoty.app.config.DatabaseConnection;
import com.emoty.app.model.MainCategory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;

@Controller
@RequestMapping("/adminPage/mainCategory")
public class MainCategoryController {

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody
	String getAllMainCategory() {
		DatabaseConnection con = new DatabaseConnection();
		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();
		JAXBContext context;
		MainCategory categories = null;
		try {
			context = JAXBContext.newInstance(MainCategory.class);
			JAXBHandle handle = new JAXBHandle(context);
			docMgr.read("/mainCategory/MainCategory.xml", handle);
			categories = (MainCategory) handle.get();
			// System.out.println(categories.getTypeCategory().get(0));
			con.getConnection().release();

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		JSONObject mainObj = new JSONObject();
		JSONObject obj;

		StringWriter out = new StringWriter();
		try {
			for (int i = 0; i < categories.getTypeCategory().size(); i++) {
				obj = new JSONObject();				
				obj.put("name", categories.getTypeCategory().get(i).toString());
				mainObj.append("getAll", obj);
			}

			
			mainObj.write(out);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return out.toString();

	}

}
