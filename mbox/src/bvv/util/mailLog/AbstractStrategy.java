package bvv.util.mailLog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AbstractStrategy {
	static int countLine = 0; //кол-во строк в файле
	static String hashCode = "0";
	String currentDir = System.getProperty("user.dir")+File.separator+"skip";
	
	public boolean saveSkipFile() {
		boolean flag = true;

		try {
			FileWriter fw = new FileWriter(currentDir);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(hashCode+" "+countLine);
			bw.close();
			System.out.println("INFO: saveSkipFile OK"); 
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}

		
	}
}
