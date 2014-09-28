package com.emoty.app.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Today {

	public static String getDate(){
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
		
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}

}
