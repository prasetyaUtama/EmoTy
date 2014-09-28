package com.emoty.app.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.marklogic.client.DatabaseClientFactory.Authentication;

public class Config {
	protected static final String fileConfig = "mydatabase.properties";
	protected static  String writerUser = openFile().getProperty("config.writer_user");
	protected static String writerPassword = openFile().getProperty("config.writer_password"); 
	protected static String adminUser = openFile().getProperty("config.admin_user");
	protected static String adminPassword = openFile().getProperty("config.admin_password");
	protected static String host = openFile().getProperty("config.host");
	protected static int port = Integer.parseInt(openFile().getProperty("config.port"));
	protected static Authentication authenticType = Authentication.valueOf(openFile().getProperty("config.authentication_type").toUpperCase());

	public Config() {
		this.openFile();
	}

	private static Properties openFile() {
		try {
			InputStream propsStream = Config.class.getClassLoader()
					.getResourceAsStream(fileConfig);
			if (propsStream == null)
				throw new IOException("Could not read config properties");

			Properties props = new Properties();
			props.load(propsStream);
			return props;
			
		
		} catch (IOException e) {
			throw new Error(e);
		}

	}



}
