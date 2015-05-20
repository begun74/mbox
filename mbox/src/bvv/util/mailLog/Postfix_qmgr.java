package bvv.util.mailLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bvv.util.mailLog.view.Postfix;
import bvv.util.mailLog.view.Qmgr;
import bvv.util.mailLog.view.SmtpD;

public class Postfix_qmgr implements PatternImpl {

	private String pattern;

	//default
	Pattern regexp = Pattern.compile("([A-za-z]{3}[\\s]{1,2}[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}).+ (postfix/qmgr\\[[0-9]+\\]): ([A-Z0-9]{7,11}):{1} from=<(.+)>, size=(.+), nrcpt=(.+)$");
	//Pattern regexp = Pattern.compile("([A-za-z]{3}[\\s]{1,2}[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}).+ (postfix/qmgr\\[[0-9]+\\]): ([A-Z0-9]{7,11}):{1} removed");
	private Matcher matcher;
	
	
	public Postfix_qmgr() {
		
	}
	
	@Override
	public Postfix findPattern(String line) {
		Qmgr Qmgr = null;
		
		matcher = regexp.matcher(line); //the input

  	  	while(matcher.find()) {
  	  	//System.out.println(line);
  	  	Qmgr = new Qmgr();
  	  	Qmgr.setData(DataParser.dataParser(matcher.group(1)));
  	  	Qmgr.setProcName(matcher.group(2));
  	  	Qmgr.setN(matcher.group(3));
  	  	
  	  	
  	  	switch(matcher.groupCount()) {
  	  	
  	  		case 4:
  	  	  		Qmgr.setFrom(matcher.group(4));
  	  			break;
  	  			
  	  		case 6:
  	  	  		Qmgr.setFrom(matcher.group(4));
  	  	  		Qmgr.setSize(matcher.group(5));
  	  			break;

  	  	}

  	  	}
		
  	  return Qmgr;
	}

	@Override
	public void setPattern(String pattern) {
		// TODO Auto-generated method stub
		this.pattern = pattern;
		this.regexp = Pattern.compile(pattern);
	}

}
