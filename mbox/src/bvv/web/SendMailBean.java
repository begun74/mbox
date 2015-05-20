package bvv.web;

//import java.util.Properties;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class SendMailBean {
	
	
	private Properties props = new Properties();
	private String host;
	private Session session;
	private Transport transport;
	private Authenticator  auth;
	
	//public SendMailBean() {}
	
	
	public void send(String user,String password,String to,String msgText) {
		//System.out.println("send  - " + getProps().toString() + " password - " +password);
		
		auth = new SMTPAuthenticator(user, password);
		session = Session.getInstance(getProps(), auth);
		session.setDebug(new Boolean(getProps().getProperty("mail.debug")));
		
		try {

			transport = session.getTransport();
		    // create a message
		    MimeMessage msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress(user));
		    
		    //to=user;
		    
		    if(getProps().getProperty("mail.cc.to") != null) {
		    	 InternetAddress[] address = {new InternetAddress(to),new InternetAddress(getProps().getProperty("mail.cc.to"))};
		    	 msg.setRecipients(Message.RecipientType.TO, address);
		    	 
		    	 
		    }
		    else {
		    	InternetAddress[] address = {new InternetAddress(to)};
		    	msg.setRecipients(Message.RecipientType.TO, address);
		    	
		    }
		    
		    msg.setSubject("Create mailbox");
		    msg.setSentDate(new Date());
		    // If the desired charset is known, you can use
		    // setText(text, charset)
		    msg.setText(msgText);
		    transport.connect();
		    transport.sendMessage(msg,msg.getRecipients(Message.RecipientType.TO));;
		    
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
	
	
	
	private class SMTPAuthenticator extends javax.mail.Authenticator {

        String username = "";
        String password = "";
        
		public SMTPAuthenticator(String user, String password) {
		        username = user;
		        this.password = password;
		}
		
		
		
	    public PasswordAuthentication getPasswordAuthentication() {
	           return new PasswordAuthentication(username, password);
	    }
	      
	        
	}
	
	
	
}
