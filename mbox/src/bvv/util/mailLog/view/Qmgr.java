package bvv.util.mailLog.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity 
@Table(name="Qmgr")
@Inheritance(strategy=InheritanceType.JOINED)
public class Qmgr extends Postfix implements Serializable {
	
	/**
	 * 
	 */
	private static final int serialVersionUID = 1;
	@Column(name="from_")
	private String from;
	private String size;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	
/*	@Override
	public long UID() {
		return (serialVersionUID+(long)Long.parseLong(getN(),16)+getLineNumber()+(getFrom()!= null?getFrom().hashCode():0)+(getSize()!= null?getSize().hashCode():0));

	}*/

	public String toString(){return "Qmgr - "+super.toString()+" "+getFrom()+" "+getSize();}

}
