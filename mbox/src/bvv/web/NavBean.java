package bvv.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import bvv.web.hibernate.DaoBean;
import bvv.web.hibernate.MailboxLog;
import bvv.web.hibernate.Users;

@ManagedBean(name = "navBean", eager = true)
//Only RequestScope т.к. есть @ManagedProperty(value="#{param.n}")

public class NavBean implements Serializable {

	
@ManagedProperty(value="#{param.n}")
private String n;


private MailboxLog mailbox = new MailboxLog();
	
/*	
	public String edit () {
		
		
		 if(getN() != null){
			 
		
			 ApplicationContext ctx = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
				DaoBean DB = (DaoBean) ctx.getBean("daoBean",DaoBean.class);
				setMailbox((MailBox)DB.getUserByN(n));
			 return "editUser?faces-redirect=true";
		 }
		 else {
			 return null;
		 }
	}

*/

	public String getN() {
		return n;
	}


	public void setN(String n) {
		this.n = n;
	}
	
	
	public String logout() {
        
        MailboxLogListener.MESSAGE = " LOGOUT "+ ((Users)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username")).getUserid();
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout?faces-redirect=true";
    }


	public MailboxLog getMailbox() {
		return mailbox;
	}


	public void setMailbox(MailboxLog mailbox) {
		this.mailbox = mailbox;
	}
}
