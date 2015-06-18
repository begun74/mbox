package bvv.bean.request;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import bvv.view.*;
import bvv.bean.session.NavBean;


@ManagedBean(name = "loginBean")

public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2782760829974279371L;

	private String name;
	private String pass;
	
	@ManagedProperty(value="#{navBean}")
	private NavBean navBean;

	
	public LoginBean() {
		System.out.println("LoginBean");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public NavBean getNavBean() {
		return navBean;
	}

	public void setNavBean(NavBean navBean) {
		this.navBean = navBean;
	}

	public String login() {
		
		
		if(getNavBean().getAuthBean().isLogin(getName(),getPass()))
		{
			User user = new User();
			user.setName(name);
			user.setPass(pass);
			getNavBean().setUser(user);
			return "a/1?faces-redirect=true";
		}
		else 
			return error();

	}
	
	private String  error() {
		String errorMessage = FacesContext.getCurrentInstance().getApplication().
				getResourceBundle(FacesContext.getCurrentInstance(),"msg").getString("loginFailure");
				
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,errorMessage);
				
	    FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage("warn", message);
	      return null;
	}

}
