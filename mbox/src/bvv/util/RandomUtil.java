package bvv.util;
import java.util.Random;

public class RandomUtil {



	  private  Random r = new Random();
	  private  String[] array = {"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M",
			  					"q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m",
			  					"1","2","3","4","5","6","7","8","9","0"};
	  
	  public  RandomUtil(String[] array) {
		  if(array != null) this.array = array;
	  }
	  
	  private  int randomInt(int range) {
	    return(r.nextInt(range));
	  }
	  
	  private  int randomIndex(Object[] array) {
	    return(randomInt(array.length));
	  }
	  
	  public  String randomElement() {
		  int t = 0;
		  String pass ="";
		  while(t < 8 ) {
			  pass = pass + array[randomIndex(array)];
			  t= t+1;
		  }
		  System.out.println(pass);
	    return(pass);
	  }
	

}
