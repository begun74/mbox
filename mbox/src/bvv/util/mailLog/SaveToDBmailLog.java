package bvv.util.mailLog;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bvv.util.mailLog.view.Postfix;
import bvv.util.mailLog.view.PostfixID;
import bvv.util.mailLog.view.PostfixImpl;
import bvv.web.hibernate.DB_Bean;

public class SaveToDBmailLog extends AbstractStrategy implements StrategyImpl {
	
	private DB_Bean DB_Bean ; 
	private Session session  ;
	
	private List<PostfixImpl>	data = new ArrayList(0);
	
	SaveToDBmailLog() {
		//WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		//ApplicationContext ctx = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
		//DB_Bean DB = (DB_Bean) ctx.getBean("dB_Bean",DB_Bean.class);
		//String[] bd = ctx.getBeanDefinitionNames();
		//for(int i=0; i<= bd.length; ++i) System.out.println(bd[i]);
		session = bvv.web.hibernate.DB_Bean.getSessionFactory_maillog().getCurrentSession();

	}


	public DB_Bean getDB_Bean() {
		return DB_Bean;
	}


	public void setDB_Bean(DB_Bean dB_Bean) {
		DB_Bean = dB_Bean;
	}


	public List<PostfixImpl> getData() {
		return data;
	}


	public void setData(List<PostfixImpl> data) {
		this.data = data;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		int c=0;
		System.out.println("INFO: SaveToDBmailLog.execute()");
		session = bvv.web.hibernate.DB_Bean.getSessionFactory_maillog().getCurrentSession();
		Transaction tx = session.beginTransaction();

		Iterator<PostfixImpl> iter = data.iterator();
		PostfixImpl PI = null;
		try {
			while(iter.hasNext())
			{
				//System.out.println(iter.next());
				PI = iter.next();
				PostfixID PID = new PostfixID();
				PID.setDatahash( ((Postfix)PI).UID() );
				PID.setCountLine( ((Postfix)PI).getLineNumber() );
				//System.out.println(((Postfix)PI)+"");
				session.save(PID);

				//if((PI instanceof Qmgr))
				session.saveOrUpdate(PI);
				
				c++;
			}
			saveSkipFile();
		}
		catch(Exception exc) 
		{
			System.out.println(PI.toString());
			this.countLine = ((Postfix)PI).getLineNumber();
			saveSkipFile();
			tx.rollback();
			exc.printStackTrace();
		}
		tx.commit();
		System.out.println("INFO: SaveToDBmailLog save - "+c);
	}

}
