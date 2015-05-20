package bvv.util.mailLog;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bvv.util.mailLog.view.Postfix;
import bvv.util.mailLog.view.PostfixImpl;

import com.jcraft.jsch.*;

public class ParseMailLogFile extends AbstractStrategy implements StrategyImpl {
	private String sshUser;
	private String sshPass;
	private String sshHost;
	private Session session;
	
	private String remoteFile;
	private String localFile; 
	
	private List<PatternImpl> listPatternImpl;
	private List<PostfixImpl> data = new ArrayList(0);
	
	private String prefix=null;
	boolean flag = false;
	FileOutputStream fos=null;
	
	
	int skipLine = 0;//кол-во строк пропускаем в файле
	
	//final static Charset ENCODING = StandardCharsets.UTF_8;
	
	ParseMailLogFile() {	
		System.out.println("user.dir  - " +currentDir);
	}
	
	public boolean getMailLogFile() {
		try { 
			System.out.print("INFO: Connect to "+sshHost+" ... ");
			JSch jsch=new JSch();
		 
			session = jsch.getSession(sshUser, sshHost, 22);
			session.setPassword(sshPass);
			
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(30000);
			
			String command="scp -f "+remoteFile;
			Channel channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand(command); 
			
			 // get I/O streams for remote scp
			OutputStream out=channel.getOutputStream();
			InputStream in=channel.getInputStream(); 
			
			channel.connect();
			
			System.out.println("Ok");
			System.out.print("INFO: Load file "+remoteFile+" to " + localFile+" ... ");

			byte[] buf=new byte[1024];
			  
			// send '\0'
			buf[0]=0; out.write(buf, 0, 1); out.flush();
			  
			while(true)
			{
				int c=checkAck(in);
				
				if(c!='C')
				{
					break;
				}
			  
				// read '0644 '
				in.read(buf, 0, 5);
			  
				long filesize=0L;
				while(true)
				{
					if(in.read(buf, 0, 1)<0){
						// 	error
						break;
					}
					if(buf[0]==' ')break;
						filesize=filesize*10L+(long)(buf[0]-'0');
				}
			  
			 String file=null;

			 for(int i=0;;i++){
				 	in.read(buf, i, 1);
			 
				 	if(buf[i]==(byte)0x0a){
				 		file=new String(buf, 0, i);
				 		break;
				 	}
			 }
			  
			 //System.out.println("filesize="+filesize+", file="+file);
			  
			 // send '\0'
			 buf[0]=0; out.write(buf, 0, 1); out.flush();
			  
			 // read a content of localFile
			 fos=new FileOutputStream(prefix==null ? localFile : prefix+file);
			 int foo;
			 while(true){
				 if(buf.length<filesize) foo=buf.length;
				 else foo=(int)filesize;
				 
				 foo=in.read(buf, 0, foo);
				 if(foo<0){
					 // 	error
					 break;
				 }
				 
				 fos.write(buf, 0, foo);
				 filesize-=foo;
				 
				 if(filesize==0L) break;
			 }
			 
			 fos.close();
			 fos=null;
			  
			 if(checkAck(in)!=0){
				 //System.exit(0);
				 throw new IOException("IO Error");
			 }
			  
			 // send '\0'
			 buf[0]=0; out.write(buf, 0, 1); out.flush();
			 
			 System.out.println("Ok");
			 flag = true;
		} 
			
		} catch (JSchException e) {
			System.err.println("\n!!!  ParseMailLogFile ERROR:");
			e.printStackTrace();
		} catch (IOException e) {
			//System.err.println("\n!!!  ParseMailLogFile ERROR:");
			//e.printStackTrace();
		} 
		finally{
			if(session != null) session.disconnect();
		}
		
		return flag;
	}
	
	void parseMailLog() {
		try 
	    {
			FileReader fReader = null;
			LineNumberReader lineReader = null;

			String line = null;
			//String hashCode = "";
			//int countLine=1; //кол-во строк в файле
			
		try 
	    {
		
		fReader = new FileReader(currentDir);
		lineReader = new LineNumberReader(fReader);
	    
	      
	      while ((line = lineReader.readLine()) != null ) {
	    	  String[] result = line.split("\\s");
//	    	     for (int x=0; x<result.length; x++)
//    	         System.out.println(result[x]);
	    	     hashCode=result[0];
	    		  skipLine = Integer.valueOf(result[1]);
	    	  }
	    	  
	      }
	      catch(Exception exc) {
	    		  //ignore
	      }
		  
		
	      System.out.print("INFO: Parse Mail Log ... ");
	      
	      fReader = new FileReader(localFile);
	      lineReader = new LineNumberReader(fReader);	
	      

	      
	      Iterator<PatternImpl> iter = getListPatternImpl().iterator(); //список шаблонов строк для postfix/*
	      List<PostfixImpl> result = new ArrayList<PostfixImpl>(0);// коллекция класса Postfix (Qmgr,Smtp,SmtpD...)
	      
	      while ((line = lineReader.readLine()) != null ) {
	    	  	    	  
	    	  if(countLine ==1 && !hashCode.equals(line.hashCode()+"")) {
	    		  hashCode=(line.hashCode())+"";
	    		  skipLine=0;
	    	  }
	    	  
	    	  iter = getListPatternImpl().iterator();
		      
		      while(countLine>=skipLine && iter.hasNext() ) 
		      {
		    	  PatternImpl p = iter.next(); 
		    	  
		    	  if(p.findPattern(line) != null)
		    	  {	
		    		  //System.out.println(countLine+"  "+line);
		    		  Postfix psfx = p.findPattern(line);
		    		  psfx.setLineNumber(countLine);
		    		  result.add(psfx);
		    	  }
		      }

		     countLine++;
	      } 

	      lineReader.close();
	      fReader.close();
	      
	/*     
	      FileWriter fw = new FileWriter(currentDir);
	      BufferedWriter bw = new BufferedWriter(fw);
	      bw.write(hashCode+" "+countLine);
	      bw.close();
		*/
	      System.out.println("Ok"); 
	      System.out.println("" +result.size() + "   "+countLine);
	      
	      setData(result);
	    }    
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
		
	}

	 static int checkAck(InputStream in) throws IOException{
		 int b=in.read();
		 		// b may be 0 for success,
		 		// 1 for error,
		 		// 2 for fatal error,
		 		// -1
		 if(b==0) return b;
		 if(b==-1) return b;
		  
		 if(b==1 || b==2){
			 	StringBuffer sb=new StringBuffer();
			 	int c;
			 	do {
			 		c=in.read();
			 		sb.append((char)c);
			 	}
			 	while(c!='\n');
			 	
			 	if(b==1){ // error
			 		System.out.print(sb.toString());
			 	}
			 	if(b==2){ // fatal error
			 		System.out.print(sb.toString());
			 	}
		 }
		 return b;
	} 

	public void setSshUser(String sshUser) {
		this.sshUser = sshUser;
	}


	public void setSshPass(String sshPass) {
		this.sshPass = sshPass;
	}

	public String getSshHost() {
		return sshHost;
	}

	public void setSshHost(String sshHost) {
		this.sshHost = sshHost;
	}


	public String getRemoteFile() {
		return remoteFile;
	}

	public void setRemoteFile(String remoteFile) {
		this.remoteFile = remoteFile;
	}

	@Override
	public void execute() throws Exception {
		System.out.println("INFO: ParseMailLogFile.execute()");
		if(getListPatternImpl()== null) throw new Exception("\nParseMailLogFile:  Добавьте шаблоны PatternImpl !");
		if(!getMailLogFile()) throw new Exception("\nParseMailLogFile:  Ошибка загрузки mail log !");
		
		parseMailLog();
		
	}

	public String getLocalFile() {
		return localFile;
	}

	public void setLocalFile(String localFile) {
		this.localFile = localFile;
	}

	
	public List<PatternImpl> getListPatternImpl() {
		return listPatternImpl;
	}

	public void setListPatternImpl(List<PatternImpl> listPatternImpl) {
		this.listPatternImpl = listPatternImpl;
	}

	public List<PostfixImpl> getData() {
		return data;
	}

	public void setData(List<PostfixImpl> data) {
		this.data = data;
	}

	public static void main(String[] args) throws Exception{
		ParseMailLogFile PMLF = new ParseMailLogFile();
		
	      PatternImpl post_smtp = new Postfix_smtp();
	      PatternImpl post_smtpD = new Postfix_smtpD();
	      PatternImpl post_qmgr = new Postfix_qmgr();
	      PatternImpl post_qmgr2 = new Postfix_qmgr();
	      post_qmgr2.setPattern("([A-za-z]{3}[\\s]{1,2}[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}).+ (postfix/qmgr\\[[0-9]+\\]): ([A-Z0-9]{7,11}):{1} removed");
	      PatternImpl post_cleanup = new Postfix_cleanup();
	      
	      PMLF.setListPatternImpl(new ArrayList<PatternImpl>());
	      PMLF.getListPatternImpl().add(post_smtp);
	      PMLF.getListPatternImpl().add(post_smtpD);
	      PMLF.getListPatternImpl().add(post_qmgr);
	      PMLF.getListPatternImpl().add(post_qmgr2);
	      PMLF.getListPatternImpl().add(post_cleanup);
		
	      PMLF.setSshHost("mail.minsk.gov.by");
	      PMLF.setSshUser("root");
	      PMLF.setSshPass("7S4s472");
	      PMLF.setRemoteFile("/var/log/maillog");

	      PMLF.setLocalFile("d:/temp/maillog");
	      //PMLF.setLocalFile("/tmp/maillog");
		
	      PMLF.execute();
	      //PMLF.parseMailLog();
	}

}
