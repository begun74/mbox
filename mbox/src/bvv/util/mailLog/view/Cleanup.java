package bvv.util.mailLog.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity 
@Table(name="Cleanup")
@Inheritance(strategy=InheritanceType.JOINED)
public class Cleanup extends Postfix implements Serializable {


	private String messageId;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
		
/*	@Override
	public long UID() {
		return (serialVersionUID+(long)Long.parseLong(getN(),16)+getLineNumber());
	}
	*/
	
	public String toString(){return "Cleanup - "+super.toString();}

}
