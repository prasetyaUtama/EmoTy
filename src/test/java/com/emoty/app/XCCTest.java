package com.emoty.app;

import javax.xml.bind.JAXBContext;

import com.emoty.app.config.DatabaseConnection;
import com.emoty.app.model.CategoryOfImage;
import com.emoty.app.utils.TutorialUtil;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.Format;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.StringHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.MatchLocation;
import com.marklogic.client.query.MatchSnippet;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;

public class XCCTest {

	public static void main(String[] args) throws Exception {
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

		//TutorialUtil.displayJSONResultDocs(resultsHandle, con.getConnection());
		// Format the results
		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();
		JAXBContext context;
		CategoryOfImage category;
		context = JAXBContext.newInstance(CategoryOfImage.class);
		// System.out.println(resultsHandle);
	MatchDocumentSummary[] results = resultsHandle.getMatchResults();
		int i = 1;
		for (MatchDocumentSummary result : results) {
			if(i % 2 !=0){
				JAXBHandle handle = new JAXBHandle(context);
				docMgr.read(result.getUri(), handle);
				category = (CategoryOfImage)handle.get();
				System.out.println(category.getCategory());
				
			}
			i++;
			
		
		}
		con.getConnection().release();
	}
}
