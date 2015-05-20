package bvv.util.mailLog.view;

import java.io.Serializable;

import javax.persistence.*;

@Entity 
@Table(name="smtp")
@Inheritance(strategy=InheritanceType.JOINED)
public class Smtp extends Postfix implements Serializable  {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6917232269005632144L;
	@Column(name="to_")
	private String to;
	private String relay;
	private String delay;
	private String delays;
	private String dsn;
	private String status;


	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getRelay() {
		return relay;
	}

	public void setRelay(String relay) {
		this.relay = relay;
	}

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	public String getDelays() {
		return delays;
	}

	public void setDelays(String delays) {
		this.delays = delays;
	}

	public String getDsn() {
		return dsn;
	}

	public void setDsn(String dsn) {
		this.dsn = dsn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
/*	@Override
	public long UID() {
		return (serialVersionUID+(long)Long.parseLong(getN(),16)+getLineNumber());
	}
	*/
	public String toString(){return "Smtp - "+super.toString()+" " +getTo()+" "+getRelay()+" "+getDelay()+" "+getStatus();}
}
