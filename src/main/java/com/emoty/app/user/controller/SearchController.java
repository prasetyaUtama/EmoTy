package com.emoty.app.user.controller;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emoty.app.config.DatabaseConnection;
import com.emoty.app.model.Images;
import com.emoty.app.model.Query;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;

@Controller
public class SearchController {

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ModelAndView home(Model model, HttpServletRequest request,
			HttpServletResponse response, Query query, BindException errors) throws JAXBException {
		//System.out.println(query.getQuery()+" "+query.getKey());
		DatabaseConnection con = new DatabaseConnection();
		Images img;
		List<Images> imgs = new ArrayList();

		String myQuery = "";
		query.extractKey();
		for(int i = 0; i < query.getKey().size();i++){
			myQuery += query.getKey().get(i);
			if(i < query.getKey().size()-1){
				myQuery+= " OR ";
			}
		}
		//System.out.println(myQuery);
		/* perform query select all */
		QueryManager queryMgr = con.getConnection().newQueryManager();
		StringQueryDefinition queries = queryMgr.newStringDefinition();
		queries.setDirectory("/images/");

		
		queries.setCriteria(myQuery);

		// create a handle for the search results
		SearchHandle resultsHandle = new SearchHandle();

		// run the search
		queryMgr.search(queries, resultsHandle);
		queryMgr.setPageLength(5);

		// System.out.println(resultsHandle);
		MatchDocumentSummary[] results = resultsHandle.getMatchResults();
		
		

		// Format the results
		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();
		JAXBContext context;
		context = JAXBContext.newInstance(Images.class);
		JAXBHandle handle = new JAXBHandle(context);
		
		for (MatchDocumentSummary result : results) {
			docMgr.read(result.getUri(), handle);
			img = (Images) handle.get();
			imgs.add(img);
		}
//System.out.println(imgs.size());
		model.addAttribute("images", imgs);
		return new ModelAndView("resultSearching");
	}

}
