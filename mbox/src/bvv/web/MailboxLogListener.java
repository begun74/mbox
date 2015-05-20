package bvv.web;


import java.util.Date;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MailboxLogListener implements PhaseListener {

	public static String MESSAGE = ""; 

	/**
	 * 
	 */
	private static final long serialVersionUID = 3489561255678721454L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		final Log LOG = LogFactory.getLog(MailboxLogListener.class);
		
		if(this.MESSAGE.length() >0 )	LOG.info("================= "+new Date().toLocaleString() + " - " + this.MESSAGE+" ======================");
		
		this.MESSAGE = "";
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.ANY_PHASE;
	}

}
