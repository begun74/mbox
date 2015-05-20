package bvv.util.mailLog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bvv.util.mailLog.view.*;

public class Postfix_smtp implements PatternImpl {

	private String pattern;

	//default
	private Pattern regexp = Pattern.compile("([A-za-z]{3}[\\s]{1,2}[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}).+ (postfix/smtp\\[[0-9]+\\]): ([A-Z0-9]{7,11}):{1} to=<(.+)>, relay=(.+), delay=(.+), delays=(.+), dsn=(.+), status=(.+)");
	
	private Matcher matcher;
	
	
	public Postfix_smtp() {
		
	}
	
	@Override
	public Postfix findPattern(String line) {
		Smtp smtp = null;
		
		matcher = regexp.matcher(line); //the input

  	  	while(matcher.find()) {
  	  	//System.out.println(line);
  	  		smtp = new Smtp();
  	  		smtp.setData(DataParser.dataParser(matcher.group(1)));
  	  		smtp.setProcName(matcher.group(2));
  			smtp.setN(matcher.group(3));
  			smtp.setTo(matcher.group(4));
  			smtp.setRelay(matcher.group(5));
  			smtp.setDelay(matcher.group(6));
  			smtp.setDelays(matcher.group(7));
  			smtp.setDsn(matcher.group(8));
  			smtp.setStatus(matcher.group(9));
  			
  	  		for(int gc=1; gc <= matcher.groupCount(); gc++)
  	  		{
//  	  			System.out.println(" " + matcher.group(gc));
  	  		}
  	  		
  	  	}
		
  	  return smtp;
	}

	@Override
	public void setPattern(String pattern) {
		// TODO Auto-generated method stub
		this.pattern = pattern;
		regexp = Pattern.compile(pattern);
	}

}
