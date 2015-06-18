package bvv.web;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import bvv.view.User;

public class AuthorizationListener implements PhaseListener {
	 
/**
	 * 
	 */
	private static final long serialVersionUID = -4262922656197664710L;
	@ManagedProperty(value="#{user}")
	private User user;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();
	
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		
		boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);

		try {
			User user = (User)session.getAttribute("user");
			if(!isLoginPage && (user == null || user.getName()== null)) 
			{
				/*Enumeration<String> en = session.getAttributeNames();
				while (en.hasMoreElements())
				
				System.out.println("en.nextElement() - " + en.nextElement());*/
			
				NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
				nh.handleNavigation(facesContext, null, "loginPage");	

					System.out.println("To loginPage");
			}
		}
		catch(Exception exc) {
			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			nh.handleNavigation(facesContext, null, "loginPage");	
			
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		//System.out.println("beforePhase - '" + event.getPhaseId() + "'  '" + event.getSource());
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return  PhaseId.ANY_PHASE;
	}

	
	
	
	private bvv.view.User getUser() {
		return user;
	}


}