package bvv.util.mailLog;

import java.util.List;

import bvv.util.mailLog.view.Postfix;
import bvv.util.mailLog.view.PostfixImpl;

public interface PatternImpl {
	
	void setPattern(String pattern);
	Postfix findPattern(String line);
}
