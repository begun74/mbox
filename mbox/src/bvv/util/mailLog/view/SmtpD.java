package bvv.util.mailLog.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity 
@Table(name="smtpD")
@Inheritance(strategy=InheritanceType.JOINED)
public class SmtpD extends Postfix implements Serializable{

	/**
	 * 
	 */
	private static final int serialVersionUID = 2;
	private String client;
	
	
	
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

/*	@Override
	public long UID() {
		return (serialVersionUID+(long)Long.parseLong(getN(),16)+getLineNumber()+getData().hashCode());

	}
*/
	
	public String toString() {return "SmtpD - "+super.toString()+" "+getClient();}
	

}
