package bvv.bean.app;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped 

public class AuthorizationBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7184930716686026630L;
	
	private boolean login = false;
	
	
	public boolean isLogin(String name,String pass) {
		login = false;
		if(name.equals("222") || name.equals("111")) 
		{
			login=true;
		}
		System.out.println("AuthorizationBean.login (" + name.toUpperCase() +") - " + login);
		return login;
	}


}
