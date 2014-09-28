package com.emoty.app.config;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;


public class DatabaseConnection {
private DatabaseClient client;
	/**
	 * @param args
	 */
	public DatabaseClient getConnection(){
		this.client = DatabaseClientFactory.newClient(Config.host, Config.port, Config.writerUser, Config.writerPassword, Config.authenticType);
		return this.client;
	}

}
