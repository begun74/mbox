package bvv.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import bvv.web.hibernate.DaoBean;
import bvv.web.hibernate.MailboxLog;
import bvv.web.hibernate.Users;


@ManagedBean 
@ApplicationScoped 
public class ListUsersBean implements Serializable {
	
	
	private List<Users> users = new ArrayList() ;
	
	public ListUsersBean()  {

			ApplicationContext ctx = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
			DaoBean DB = (DaoBean) ctx.getBean("daoBean",DaoBean.class);
			
			this.setUsers(DB.getUsers());
					
	}

	public List getUsers()  {
	
		ApplicationContext ctx = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
		DaoBean DB = (DaoBean) ctx.getBean("daoBean",DaoBean.class);
		//MailFolderSizeBean MFSB = (MailFolderSizeBean)ctx.getBean("mailFolderSizeBean",MailFolderSizeBean.class);
		//List nUsers = users.subList(0, 10);
		//System.out.println("users - " + nUsers);
		//MFSB.setFoldersSize(nUsers);
		
		if(users.size() != DB.getUsers().size()) {
			users = (DB.getUsers());
			
			
			
		}


		MailboxLogListener.MESSAGE = " GET LIST USER ";
		
		return users;
	}

	public void setUsers(List users) {

		this.users = users;
	}


	
	public void saveUsers()  {
		
		ApplicationContext ctx = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
		DaoBean DB = (DaoBean) ctx.getBean("daoBean",DaoBean.class);
		
		Iterator<Users> iter = users.iterator();
		ArrayList al = new ArrayList();
		
		while (iter.hasNext()) {
			
			Users u = iter.next();
			DB.saveUsers(u);
	
			al.add(u);
		}
		
		MailboxLogListener.MESSAGE = " SAVE USERS ";
		
	}
	
	
	public void saveUsersEvent(ActionEvent event) {
		System.out.println(" ActionEvent event - " + event.getPhaseId() );
	}

}
