package bvv.util.mailLog.view;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class Postfix implements PostfixImpl,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1974;
	
	@Id
	@Column(name="id")
	@GeneratedValue	(strategy =GenerationType.AUTO)
	private long id;
	
	@Transient
	private int lineNumber=0;
	
	
	private String n;
	private Timestamp data;
	private String procName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}

	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	
	public long UID() {
		return getData().getTime()+getLineNumber();
		
	}
	
	
	public String toString() {
		return getLineNumber() +"  "+getN()+" "+getData()+" "+getProcName();
	}
	
	
}
