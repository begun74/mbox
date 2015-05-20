package bvv.util.mailLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bvv.util.mailLog.view.Postfix;
import bvv.util.mailLog.view.Smtp;
import bvv.util.mailLog.view.SmtpD;

public class Postfix_smtpD implements PatternImpl {

	private String pattern;

	//default
	Pattern regexp = Pattern.compile("([A-za-z]{3}[\\s]{1,2}[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}).+ (postfix/smtpd\\[[0-9]+\\]): ([A-Z0-9]{7,11}):{1} client=(.+)");
	
	private Matcher matcher;
	
	
	public Postfix_smtpD() {
		
	}
	
	@Override
	public Postfix findPattern(String line) {
		SmtpD smtpD = null;
		
		matcher = regexp.matcher(line); //the input

  	  	while(matcher.find()) {
  	  	//System.out.println(line);
  	  		smtpD = new SmtpD();
  	  		smtpD.setData(DataParser.dataParser(matcher.group(1)));
  	  		smtpD.setProcName(matcher.group(2));
  			smtpD.setN(matcher.group(3));
  			smtpD.setClient(matcher.group(4));
/*
  	  		for(int gc=1; gc <= matcher.groupCount(); gc++)
  	  		{
  	  			System.out.println(" " + matcher.group(gc));
  	  		}
  	  		*/
  	  	}
		
  	  return smtpD;
	}

	@Override
	public void setPattern(String pattern) {
		// TODO Auto-generated method stub
		this.pattern = pattern;
		regexp = Pattern.compile(pattern);
	}

}
