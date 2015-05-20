package bvv.web.hibernate;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity 
@Table(name="users")
@ManagedBean
@RequestScoped
public class Users implements Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8517471327985174787L;

	@Id
	@Column(name="n")
	@GeneratedValue	
	
	private int n;
	private int did=1;
	//private int quota=0;
	//private int uid=207;
	//private int gid=207;
	
	@Column(name="userid")
	private String userid;
	
	private String password;
	private String maildir = userid+ "/";
	private String home = "/var/mail/";;
	private String status = "Y";
	
	
	@Transient
	private boolean passwordEditable;
	
	@Transient
	private boolean mailFolderSizeZerro = false;
	
	@Transient
	private boolean edit;
	
	@Transient
	private long mailFolderSize = 0;
	
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "id")

	
	public Users() {
		
	}
	
	public Users(String pass) {
		this.password = pass;
	}
	
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	

	

	public String getUserid() {
		return userid;
	}


	public void setUserid(String id) {
		this.userid = id;
	}



	

	public String getPassword() {
		
		return password;
	}


	public void setPassword(String password) {
		passwordEditable = false;
		if(!this.password.equals(password )) {
			 edit = true;
		}else {
			edit = false;
		}
		this.password = password;
		//passwordEditable = false;
	}

	
	
	public String getMaildir() {
		return maildir=userid+"/";
	}

	public void setMaildir(String maildir) {
		this.maildir = userid+"/";
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = 1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
/*
	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}
*/
	public boolean isPasswordEditable() {
		return passwordEditable;
	}


	public void setPasswordEditable(boolean passwordEditable) {
		this.passwordEditable = passwordEditable;
	}

	
	

	public boolean getEdit() {
		return edit;
	}


	public void setEdit(boolean isEdit) {
		this.edit = isEdit;
	}
	
	


	public long getMailFolderSize() {
		//return mailFolderSize;
		return System.currentTimeMillis();
	}

	public void setMailFolderSize(long mailFolderSize) {
		this.mailFolderSize = mailFolderSize;
	}
	

	public boolean isMailFolderSizeZerro() {
		return mailFolderSizeZerro;
	}

	public void setMailFolderSizeZerro(boolean mailFolderSizeZerro) {
		this.mailFolderSizeZerro = mailFolderSizeZerro;
	}

	public String toString() {
		return ""+getUserid()+" " +getPassword() +"  ";
	}


}

