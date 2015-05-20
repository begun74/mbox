package bvv.web.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

import org.hibernate.Session;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import bvv.web.DBdownException;



public class DaoBean implements DB_BeanImpl {
	
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static final SessionFactory sessionFactory_maillog = buildSessionFactory2();
	Session session  ;
	
	private Users user;
	private MailboxLog userByN;
	private List users = new ArrayList();;
	private List admins;
	
	
	public DaoBean () {
		
	}	
		

	
	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory2() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("maillog.cfg.xml").buildSessionFactory();
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


	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            return new Configuration().configure().buildSessionFactory();
	        }
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	public static SessionFactory getSessionFactory() {
	        return sessionFactory;
    }
	     

		public Users getUser(String name,String pass) {
			

			if(getAdmins().contains(name)) {
			
				try {
					name = name+ "@%";
					session = getSessionFactory().getCurrentSession();
					Transaction tx = (Transaction) session.beginTransaction();
					SQLQuery query = session.createSQLQuery("select * from users where userid like :userid and password like :pass");
					query.setString("userid", name);
					query.setString("pass", pass);
					user = (Users)query.addEntity(Users.class).uniqueResult();
					session.getTransaction().commit();
					
				}
				catch (Exception exc) {
					session.getTransaction().rollback();
					exc.printStackTrace();
				}
			}
			
			return user;
		}

		public Users getUser(String name)  {
			
				Users user = null;
			
			
				try {
					
					name = name+ "@%";
					System.out.println("name =" +name);
					session = getSessionFactory().getCurrentSession();
					Transaction tx = (Transaction) session.beginTransaction();
					SQLQuery query = session.createSQLQuery("select * from users where userid like :userid");
					query.setString("userid", name);
					
					user = (Users)query.addEntity(Users.class).uniqueResult();
					session.getTransaction().commit();
					
				}
				catch (Exception exc) {
					session.getTransaction().rollback();
					//throw new DBdownException(exc.getMessage());
				}
						
			return user;
		}
		
		
		public List getUsers()   {

			
			try {
				session = getSessionFactory().getCurrentSession();
				Transaction tx = (Transaction) session.beginTransaction();
				
				users = session.createSQLQuery("select * from users order by userid").addEntity(Users.class).list();
				
				session.getTransaction().commit();
				
				//System.out.println("DB_getUsers()  - " + users);
			}
			catch (Exception exc) {
				
				exc.printStackTrace();
			}

		
		return users;
			
		}
		
		
		public Users saveUsers(Users object)   {
			Users user ;
			
			
			if(object.getEdit()) {
				
				if(object.getUserid().indexOf("@minsk.gov.by") == -1) {
					object.setMaildir(object.getUserid()+"/");
					object.setUserid(object.getUserid()+"@minsk.gov.by");
					
				}
				
				
				try {
				
					session = getSessionFactory().getCurrentSession();
					Transaction tx = (Transaction) session.beginTransaction();
				
					session.saveOrUpdate(object);
				
					session.getTransaction().commit();
					object.setEdit(false);
					
					user = object;
					return user;
				}
				catch (Exception exc) {
					session.getTransaction().rollback();
					exc.printStackTrace();
					
					//flag = false;
					

				}			
			}
		
			user = object;
			return user;
		}


	
				
		
		public List getAdmins() {
			return admins;
		}



		public void setAdmins(List admins) {
			this.admins = admins;
		}



		public MailboxLog getUserByN(String n) {
			try {
				System.out.println("n=" +n);
				session = getSessionFactory().getCurrentSession();
				Transaction tx = (Transaction) session.beginTransaction();
				SQLQuery query = session.createSQLQuery("select * from users where n=:n");
				query.setString("n", n);
				
				userByN = (MailboxLog)query.addEntity(MailboxLog.class).uniqueResult();
				session.getTransaction().commit();
			}
			catch (Exception exc) {
				
			}
			return userByN;
		}



		public void setUserByN(MailboxLog userByN) {
			this.userByN = userByN;
		}


}
