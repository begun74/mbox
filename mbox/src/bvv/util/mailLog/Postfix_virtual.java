package bvv.util.mailLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bvv.util.mailLog.view.Postfix;
import bvv.util.mailLog.view.Virtual;

public class Postfix_virtual implements PatternImpl {
	//private String pattern;

	//default
	private Pattern regexp = Pattern.compile("([A-za-z]{3}[\\s]{1,2}[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}).+ (postfix/virtual\\[[0-9]+\\]): ([A-Z0-9]{7,11}):{1} to=<(.+)>, relay=(virtual), delay=(.+), delays=(.+), dsn=(.+), status=(.+)");
	
	private Matcher matcher;


	@Override
	public Postfix findPattern(String line) {
		Virtual virtual = null;
		matcher = regexp.matcher(line); //the input

  	  	while(matcher.find()) {
  	  		virtual = new Virtual();

  	  		virtual.setData(DataParser.dataParser(matcher.group(1)));
  	  		virtual.setProcName(matcher.group(2));
  	  		virtual.setN(matcher.group(3));
  	  		virtual.setTo(matcher.group(4));
  	  		virtual.setRelay(matcher.group(5));
  	  		virtual.setDelay(matcher.group(6));
  	  		virtual.setDelays(matcher.group(7));
  	  		virtual.setDsn(matcher.group(8));
  	  		virtual.setStatus(matcher.group(9));
  	  	
	  		for(int gc=1; gc <= matcher.groupCount(); gc++)
	  		{
//	  			System.out.println(" " + matcher.group(gc));
	  		}
	  		
  	  	}
	  		
  	  //System.out.println("Virtual - "+virtual);
	  	return virtual;
	}


	@Override
	public void setPattern(String pattern) {
		// TODO Auto-generated method stub
		
	}
}
