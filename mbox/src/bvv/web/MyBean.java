package bvv.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import bvv.web.hibernate.DaoBean;
import bvv.web.hibernate.Users;

@ManagedBean (eager=true)
@RequestScoped 
public class MyBean implements Serializable {
	
	private String  name = "";
	private String  pass = "";
	private Users	user;
	
	//private List users = new ArrayList();

	public String getName() {
		return "bvv";
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
	
	
	
	public String login()  { 
		
		ApplicationContext ctx = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
		DaoBean DB = (DaoBean) ctx.getBean("daoBean",DaoBean.class);
		
		
		user = DB.getUser(name, pass);
		
		if(user != null && pass.equals(user.getPassword())) {
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", user);
			
			MailboxLogListener.MESSAGE= " LOGIN USER - "+user.getUserid();
			
			return "ListUser?faces-redirect=true"; 
		}
		else {
			return error();
			
		}
		
	}

	

	public String login2()  { 
		
		ApplicationContext ctx = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
		DaoBean DB = (DaoBean) ctx.getBean("daoBean",DaoBean.class);
		
		
		user = DB.getUser(name, pass);
		
		if(user != null && pass.equals(user.getPassword())) {
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", user);
			
			MailboxLogListener.MESSAGE= " LOGIN USER - "+user.getUserid();
			
			return "ListUser?faces-redirect=true"; 
		}
		else {
			return error();
			
		}
		
	}

	
	 
	
	private String  error() {
		
	    FacesContext context = FacesContext.getCurrentInstance();
	    
	        
	      context.addMessage("warn", 
	          new FacesMessage("Login failure!"));
	      return null;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	


}
