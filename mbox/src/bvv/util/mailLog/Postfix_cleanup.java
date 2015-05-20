package bvv.util.mailLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bvv.util.mailLog.view.Cleanup;
import bvv.util.mailLog.view.Postfix;
import bvv.util.mailLog.view.Qmgr;

public class Postfix_cleanup implements PatternImpl {

	private String pattern;

	//default
	Pattern regexp = Pattern.compile("([A-za-z]{3}[\\s]{1,2}[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}).+ (postfix/cleanup\\[[0-9]+\\]): ([A-Z0-9]{7,11}):{1} (.+)");
	
	private Matcher matcher;
	
	
	public Postfix_cleanup() {
		
	}
	
	@Override
	public Postfix findPattern(String line) {
		Cleanup Cleanup = null;
		matcher = regexp.matcher(line); //the input
		

  	  	while(matcher.find()) {
  	  		//System.out.println(line);
  	  		Cleanup = new Cleanup();
  	  		Cleanup.setData(DataParser.dataParser(matcher.group(1)));
  	  		Cleanup.setProcName(matcher.group(2));
  	  		Cleanup.setN(matcher.group(3));
  	  		Cleanup.setMessageId(matcher.group(4));
  	  		
  	  	}
		
  	  return Cleanup;
	}

	@Override
	public void setPattern(String pattern) {
		// TODO Auto-generated method stub
		this.pattern = pattern;
		regexp = Pattern.compile(pattern);
	}

}
