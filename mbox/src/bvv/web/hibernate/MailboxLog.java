package bvv.web.hibernate;


import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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



@Entity
@Table(name="mailbox_log")
public class MailboxLog implements Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8517471327985174787L;

	@Id
	@Column(name="id")
	@GeneratedValue	
	private int id;
	
	private int users_n; //—сылка на пользовател€ который входил в приложение
	private String event;
	private Date data;
	
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "id")

	
	public MailboxLog() {
		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	public int getUsers_n() {
		return users_n;
	}

	public void setUsers_n(int users_n) {
		this.users_n = users_n;
	}

	
	
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
	
	
	

	

	
}

