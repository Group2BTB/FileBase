package file.base;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MAIN {
	
		
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
//		String str="123\n456";
//		//str.matches(".*" + "@@@" + ".*");
//		str = str.replaceAll("\\n", "\\\\n"); //\n
//		System.out.println(str);
//		System.exit(0);
		
		Process pro = new Process();		
		pro.showManu();
	}
}
