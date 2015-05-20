package bvv.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import bvv.util.RandomUtil;
import bvv.web.hibernate.DaoBean;
import bvv.web.hibernate.Users;


@ManagedBean 
//@SessionScoped 
public class NewEmail implements Serializable {
	
	private Users email = new Users("");
	
	
	
	public NewEmail() {
		email = new Users("");
		email.setPassword(new RandomUtil(null).randomElement());
		email.setUserid("");
		
		
	}

	
	
	public Users getEmail() {
		return email;
	}

	public void setEmail(Users email) {
		this.email = email;
	}

	
	public String create()  {
		ApplicationContext ctx = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
		DaoBean DB = (DaoBean) ctx.getBean("daoBean",DaoBean.class);
		//SendMailBean SMB = (SendMailBean) ctx.getBean("sendMailBean",SendMailBean.class);
		
		
		if(DB.saveUsers(email) != null) {
				Users user = (Users)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
				//SMB.send(user.getUserid(),user.getPassword(),email.getUserid(),"");
				MailboxLogListener.MESSAGE = user.getUserid()+ " CREATE USER - " + email.getUserid();
		}
		
		return "ListUser?faces-redirect=true";
	}
	
	
	
	
	
	public String cancel() {
		
		
		return "ListUser?faces-redirect=true";
	}
	
	
	  public void validateUserExists(FacesContext context,
              						UIComponent componentToValidate,
              						Object value)throws ValidatorException {
			ApplicationContext ctx = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
			DaoBean DB = (DaoBean) ctx.getBean("daoBean",DaoBean.class);
			
			if(DB.getUser(value+"") != null ) {
			
					FacesMessage message = new FacesMessage("");
		  			throw new ValidatorException(message);
			}
			
	  }

}
