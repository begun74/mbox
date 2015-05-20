package bvv.util.mailLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataParser {

	{}
	
	public static java.sql.Timestamp dataParser(String datetimeStr){
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp sd = null;
		//System.out.println(calendar.get(Calendar.YEAR));
		datetimeStr = datetimeStr +" "+ calendar.get(Calendar.YEAR);
        SimpleDateFormat format = new SimpleDateFormat("MMM dd HH:mm:ss yyyy",Locale.ENGLISH);
        Date parseDate = null;
     try {
          parseDate = format.parse(datetimeStr);
          //System.out.println(parseDate.toString());
          sd = new java.sql.Timestamp(parseDate.getTime());
         //System.out.println(parseDate.toString() + " - "+ sd.hashCode());
     } 
     catch (ParseException e) {
         e.printStackTrace();;
     }
     return sd;
	}
}
