package bvv.util.mailLog.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name="postfixid")
public class PostfixID implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6287276188225936475L;
	@Id
	@Column(name="id")
	@GeneratedValue	(strategy =GenerationType.AUTO)
	private long id;
	private long datahash;
	private String n;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDatahash() {
		return datahash;
	}
	public void setDatahash(long datahash) {
		this.datahash = datahash;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}


}
