package bvv.bean.session;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import bvv.bean.app.AuthorizationBean;
import bvv.view.User;

@ManagedBean(name = "navBean")
@SessionScoped 
public class NavBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4164601219192641989L;

	@ManagedProperty(value="#{authorizationBean}")
	private AuthorizationBean authBean;
	
	@ManagedProperty(value="#{user}")
	private User user;

	
	public AuthorizationBean getAuthBean() {
		return authBean;
	}

	public void setAuthBean(AuthorizationBean authBean) {
		this.authBean = authBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
	}

	public String logout() {
		System.out.println("NavBean.logout");
		HttpSession s = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		s.invalidate();
		return "/login?faces-redirect=true";
	}

}
