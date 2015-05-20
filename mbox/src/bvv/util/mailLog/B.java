package bvv.util.mailLog;

import org.springframework.context.annotation.Scope;

import bvv.web.hibernate.DB_BeanImpl;

//@Scope("singleton") 
public final class B implements Runnable{
	
	private static B instance;
	
	private StrategyImpl parseMailLogFile = null;
	private StrategyImpl saveToDBmailLog = null;
	private DB_BeanImpl  db_BeanImpl = null;
	
	private long name = System.currentTimeMillis();
	private long sleep = 15000; //15 sec
	
	boolean begin,isWork = true;
	Thread thread = null;
	
	
	
	protected B() {

		System.out.println("B - " +name);
		
	}	

	
	public static final B getInstance()
    {
        if (instance == null)  instance = new B();
 
        return instance;
    }

    
	public DB_BeanImpl getDb_BeanImpl() {
		return db_BeanImpl;
	}


	public void setDb_BeanImpl(DB_BeanImpl db_BeanImpl) {
		this.db_BeanImpl = db_BeanImpl;
	}


	public StrategyImpl getParseMailLogFile() {
		return parseMailLogFile;
	}

	public void setParseMailLogFile(StrategyImpl parseMailLogFile) {
		this.parseMailLogFile = parseMailLogFile;
	}

	public StrategyImpl getSaveToDBmailLog() {
		return saveToDBmailLog;
	}

	public void setSaveToDBmailLog(StrategyImpl saveToDBmailLog) {
		this.saveToDBmailLog = saveToDBmailLog;
	}

    

	public long getSleep() {
		return sleep;
	}

	public void setSleep(long sleep) {
		if(sleep/1000 < 1) sleep = this.sleep; //default
		this.sleep = sleep;
		
		if(thread == null) {
			thread = new Thread(this,"Thread-B");
			thread.start();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isWork) {
			try {
				System.out.println("B.run("+sleep/1000+"s) - " +name);
				
				if(getParseMailLogFile()!= null && getSaveToDBmailLog() != null )
				{
					if(begin) { Thread.sleep(5000); begin=false;}

					getParseMailLogFile().execute();
					getSaveToDBmailLog().setData(getParseMailLogFile().getData());
					getSaveToDBmailLog().execute();

					Thread.sleep(sleep);
				}	else { 
					thread.interrupt(); 
					thread = null;
				}
				
			} catch (InterruptedException e) {
				isWork=false;
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				isWork=false;
				e.printStackTrace();
			}
		}
	}
}
