package com.emoty.app.administratorEmoty.controller;

import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emoty.app.config.DatabaseConnection;
import com.emoty.app.model.CategoryOfImage;
import com.emoty.app.model.MainCategory;
import com.emoty.app.utils.Today;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;

@Controller
@RequestMapping("/adminPage/categoryOfImage")
public class CategoryOfImageController {
	private static final Logger logger = LoggerFactory
			.getLogger(CategoryOfImageController.class);

	@RequestMapping(value = "/create")
	public ModelAndView create(Model model) {

		return new ModelAndView("createCategoryOfImage");

	}

	@RequestMapping(value = "/save", headers = "Accept=application/json", method = RequestMethod.POST)
	public @ResponseBody
	String create(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CategoryOfImage categoryOfImage) throws Exception {
		// connection to database
		DatabaseConnection con = new DatabaseConnection();
		// get sever date

		// md5 encryption
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytesOfMessage = Today.getDate().toString().getBytes("UTF-8");
		byte[] thedigest = md.digest(bytesOfMessage);

		// Starting to write document to server
		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();
		JAXBContext context;

		try {
			context = JAXBContext.newInstance(CategoryOfImage.class);
			// create a handle on the POJOs for writing to the database
			JAXBHandle writeHandle = new JAXBHandle(context);

			// provide a handle for the POJO
			writeHandle.set(categoryOfImage);

			// write the POJO to the database
			docMgr.write("/categories/" + thedigest.toString() + ".xml",
					writeHandle);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.getConnection().release();
		// stoping connection
		// logger.info(categoryOfImsayaage.toString());
		// System.out.println(thedigest.toString());
		return "0";
		// Request processing
	}

	@RequestMapping(value = "/view/{type}", method = RequestMethod.GET)
	public @ResponseBody
	String read(@PathVariable String type) throws JAXBException, JSONException {
		DatabaseConnection con = new DatabaseConnection();

		// Format the results
		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();
		JAXBContext context;
		CategoryOfImage category;
		context = JAXBContext.newInstance(CategoryOfImage.class);
		JAXBHandle handle = new JAXBHandle(context);

		// Json Object
		JSONObject mainObj = new JSONObject();
		JSONObject obj;

		StringWriter out = new StringWriter();
		// System.out.println(resultsHandle);
		MatchDocumentSummary[] results = this.getAllCategories();
		int i = 1;
		if (type.equals("getAll")) {
			for (MatchDocumentSummary result : results) {

				obj = new JSONObject();

				docMgr.read(result.getUri(), handle);
				category = (CategoryOfImage) handle.get();
				
				obj.put("name", category.getCategory().toString() + " - "
						+ category.getMainCategory().toString());
				mainObj.append("getAll", obj);
			

			}
		} else if (type.equals("gridView")) {
			for (MatchDocumentSummary result : results) {

				obj = new JSONObject();

				docMgr.read(result.getUri(), handle);
				category = (CategoryOfImage) handle.get();
				obj.put("no", i);
				obj.put("mainCategory", category.getMainCategory().toString());
				obj.put("category", category.getCategory().toString());
				mainObj.append("categories", obj);
				i++;

			}
		}

		
		//System.out.print(type);
		mainObj.write(out);
		return out.toString();

	}

	private MatchDocumentSummary[] getAllCategories() throws JAXBException {
		DatabaseConnection con = new DatabaseConnection();

		/* perform query select all */
		QueryManager queryMgr = con.getConnection().newQueryManager();
		StringQueryDefinition query = queryMgr.newStringDefinition();
		query.setDirectory("/categories/");

		query.setCriteria("");

		// create a handle for the search results
		SearchHandle resultsHandle = new SearchHandle();

		// run the search
		queryMgr.search(query, resultsHandle);

		// System.out.println(resultsHandle);
		MatchDocumentSummary[] results = resultsHandle.getMatchResults();
		return results;
	}

}
