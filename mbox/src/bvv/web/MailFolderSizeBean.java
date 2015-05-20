package bvv.web;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

//import org.springframework.util.FileSystemUtils;





import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import bvv.web.hibernate.Users;

@ManagedBean
@SessionScoped

public class MailFolderSizeBean implements FileFilter ,Serializable {
	
	
	private Properties props = new Properties();
	private File rootDir ;
	private long size=0;
	private String fSize="";
	private boolean fSizeable = false;
	
	public MailFolderSizeBean() {

	}

	
	public void setFoldersSize(List<Users> users) {
		Iterator<Users> iter = users.iterator();
		while(iter.hasNext()) {
			setMailFoldersSize(iter.next());
		}
	}
	

	private void setMailFoldersSize(Users u) {
		try {

			File f = new File(u.getHome(),u.getMaildir().substring(0,u.getMaildir().indexOf("@")));
			//File f = new File("D:\\mail\\", u.getMaildir().substring(0,u.getMaildir().indexOf("@")));

			//try {
			//MailboxLogListener.MESSAGE = f.toString();
			//File f = new File("c:/dell");
			//System.out.println("Folder - " + f);
		
			long size = getFileFolderSize(f);
			if (size > 0 ) {
					u.setMailFolderSize(size);
					u.setMailFolderSizeZerro(false);
			}
			//System.out.println("File - "+ f + "  " + u.getMailFolderSize());

		}		catch(NullPointerException ne) {}
	}
	
	
	public  long getSize() {
	   
		return getFileFolderSize(new File("d:/nod"));
	}
	
	public void setSize(long size) {
		this.size = size;
	}


	public String getfSize() {
		return getFileFolderSize(new File("d:/nod"))+" b.";
	}


	public void setfSize(String fSize) {
		this.fSize = fSize;
	}


	public boolean isfSizeable() {
		return fSizeable;
	}


	public void setfSizeable(boolean fSizeable) {
		this.fSizeable = fSizeable;
	}


	public Properties getProps() {
		return props;
	}


	public void setProps(Properties props) {
		this.props = props;
		
	}

	public static long getFileFolderSize(File dir) {
        long size = 0;
        
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    size += file.length();
                    //System.err.println("size - " + size);
                } else
                    size += getFileFolderSize(file);
                
                
            }
        } else if (dir.isFile()) {
        	
            size += dir.length();
        }
        //size=size/1024/1024;
        return size;
    }

	@Override
	public boolean accept(File file) {
		
		 if ( file.isFile()) 
             setSize(getSize() + file.length());
		 
         else 
             file.listFiles(this);
         
         return false;
         
         
	}

	
	
	
}
