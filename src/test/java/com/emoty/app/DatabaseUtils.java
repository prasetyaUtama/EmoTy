package com.emoty.app;

import com.emoty.app.config.DatabaseConnection;
import com.marklogic.client.document.XMLDocumentManager;

public class DatabaseUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseConnection con = new DatabaseConnection();

		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();

		// write the POJO to the database
		docMgr.delete("/example/test.xml");
		docMgr.delete("/images/[B@621d02ee.xml");
		//docMgr.delete("/images/[B@6a4bbac8.xml");
		//docMgr.delete("/images/[B@2b2f428f.xml");
		//docMgr.delete("/images/[B@47c60571.xml");
		//docMgr.delete("/images/[B@5e74a3bc.xml");
		con.getConnection().release();
	}
}
