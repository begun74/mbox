package bvv.web.hibernate;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.cfg.Configuration;

	
@ManagedBean
@SessionScoped
public class DB_Bean implements DB_BeanImpl {

	private static String cfg_xml = "maillog.cfg.xml";
	
	private static final SessionFactory sessionFactory_maillog = buildSessionFactory_maillog();
	Session session  ;
	
	
	{System.out.println(cfg_xml);}

	
	public String getCfg_xml() {
		return cfg_xml;
	}

	public void setCfg_xml(String cfg_xml) {
		this.cfg_xml = cfg_xml;
	}

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory_maillog() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure(cfg_xml).buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}

	public static SessionFactory getSessionFactory_maillog() {
        return sessionFactory_maillog;
	}

}
