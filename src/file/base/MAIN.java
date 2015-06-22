package file.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class MAIN {
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
				
//		String str="123\n456";
//		//str.matches(".*" + "@@@" + ".*");
//		str = str.replaceAll("\\n", "\\\\n"); //\n
//		System.out.println(str);
//		System.exit(0);
//		File file  =new File("D:\\file.txt");
//		System.out.println(Files.size(file.toPath()));
		
		OperationFile.backupFile("newfile.txt");
		OperationFile.restoreFile("newfile.txt");
		System.out.println(OperationFile.file.toString());
//		
//		
//		System.exit(0);;
		Process pro = new Process();		
		pro.showManu();
	}
}
