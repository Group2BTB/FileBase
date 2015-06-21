package file.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;


public class OperationFile implements IOperationFile {
	
	static File file = new File("D:\\file.txt");
	static File TempFile = new File("D:\\TempData"); //this is location of temp file
	
	private static int increment = 0;

	private static ArrayList<Article> arr; 

	@Override
	public void addArticle(Collection<Article> arrList) {
		// TODO Auto-generated method stub
		Article art = new Article();
		Scanner scan = new Scanner(System.in);
		art.setId(++increment);
		System.out.print("Enter Title:");
		art.setTitle(scan.next());

		System.out.print("Enter Content: ");
		art.setContent(getMiltiLineString());
		/*
		 * set content to object Article by calling method getMiltiLineString();
		 */
		System.out.print("Enter Author: ");
		art.setAuthor(scan.next());

		art.setDate(autoSetDate());// set Date by calling method autoSetDate();

		String option;
		int choice = 0;
		do {
			System.out
					.print("What do you want to save: [1.Save|2.Save-New|3.Cancel]: ");
			/* Ask user to choose the option that they want */
			option = scan.next();
			option = option + scan.nextLine();
			/* use to check value of variable option is number or not */
			if (isInteger(option) == true) {
				choice = Integer.parseInt(option);
			}

			if (choice == 1) {
				arrList.add(art);// add object art of Article to ArrayList
				System.out.println("Article saved...");
				writeLogFile("Add ", "Add ID="+increment+" ", "Sucessfuly");

			} else if (choice == 2) {
				arrList.add(art);// add object art of Article to ArrayList
				System.out.println("Article saved...");
				writeLogFile("Add ", "Add ID="+increment+" ", "Sucessfuly");
				addArticle(arrList);// Call function addArticle again

			} else if (choice == 3) {
				System.out.println("Record cancelled!");
				return;

			} else if (choice < 1 || choice > 3) {
				System.out.println("Invalid keyword! Please try again! ");
			}

		} while (choice < 1 || choice > 3);

	}

	@Override
	public void deleteArticle(Collection<Article> List, int id) {
		// TODO Auto-generated method stub
		ArrayList<Article> arrList = (ArrayList<Article>) List;
		int index = Collections.binarySearch(arrList, new Article(id, null,
				null, null, null), new Comparator<Article>() {

			@Override
			// compare index and id
			public int compare(Article art1, Article art2) {
				return art1.getId().compareTo(art2.getId());
			}
		});
		if (arrList.get(index).getId() == id) {
			Scanner scan = new Scanner(System.in);
			System.out.println(arrList.get(index).getId() + " "
					+ arrList.get(index).getTitle() + " "
					+ arrList.get(index).getContent() + " "
					+ arrList.get(index).getAuthor() + " "
					+ arrList.get(index).getDate());
			System.out.print("Are you sure to delete this Article? [y/n]: ");
			String option = scan.nextLine();
			try {
				if (option.matches("y")) {
					if (arrList.get(index).getId() == id) {
						arrList.remove(id - 1);
						System.out.println("Delete successfully!");
						writeLogFile("Delete ", "Delete ID="+id+" ", "Sucessfuly");
					}
				} else if (option.matches("n")) {
					System.out.println("Delete was canceled!");
					writeLogFile("Delete ", "Delete ID="+id+" ", "Not");
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid ID!");
				writeLogFile("Delete ", "Delete ID="+id+" "+e.getMessage(), "Error");
			}
		}

	}
	
	@Override
	public void deleteAll(Collection<Article> arrList){
		Scanner scan = new Scanner(System.in);
		System.out.print("Are you sure to delete this Article? (y/n): ");
		String option = scan.next();
		if (option.matches("y")) {
			arrList.clear();// call method clear() of ArrayList
			System.out.println("All of the article content was clear!");
		} else if (option.matches("n")) {
			System.out.println("Delete was canceled!");
		}else{
			System.out.println("Invalid keyword! Please try again!");
		}
		
	}

	@Override
	public void updateArticle(Collection<Article> list, int id){
		// TODO Auto-generated method stub
		ArrayList<Article> arrList = (ArrayList<Article>) list;
		Scanner scan = new Scanner(System.in);
		String idUpdate;
		int index = Collections.binarySearch(arrList, new Article(id, null,
				null, null, null), new Comparator<Article>() {
			@Override
			// Compare ID that input with ID in Article
			public int compare(Article art1, Article art2) {
				// TODO Auto-generated method stub
				return art1.getId().compareTo(art2.getId());
			}
		});
		// check condition if number less 1 and over 3, it is not valid (input
		// again)
		try {
			System.out
					.print("What you want to update: [1.Title|2.Author|3.Content]: ");
			String option = scan.next();
			if (isInteger(option) == true) {
				int choice = Integer.parseInt(option);
				if (choice == 1) {
					System.out.print("Enter Title: ");
					arrList.get(index).setTitle(scan.next());
					System.out.println("Saved!...");
				} else if (choice == 2) {
					System.out.print("Enter Author: ");
					arrList.get(index).setAuthor(scan.next());
					System.out.println("Saved!...");
				} else if (choice == 3) {
					System.out.print("Enter Content: ");
					arrList.get(index).setContent(scan.next());
					System.out.println("Saved!...");
				} else {
					System.out.println("Invalid Option!");
				}
			}
			writeLogFile("Update ", "Update ID="+id+" ", "Sucessfuly");

		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("\n*** Input ID is not found!!!***\n");
			writeLogFile("Update ", "Update ID="+id+" "+e.getMessage(), "Error");
		}
	}

	public String getMiltiLineString(/* String msg */) {
		Scanner scan = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		System.out
				.println(/* msg */" (Press Enter the content + '.' + Enter to Finish) "
						+ " => ");
		while (true) {
			String imsi = scan.nextLine();
			if (imsi != null && imsi.trim().length() == 1
					&& imsi.trim().charAt(0) == '.')
				break;
			if (imsi == null)
				imsi = "";
			sb.append(imsi);
		}
		return sb.toString();
	}

	public boolean isInteger(String option) {
		try {
			Integer.parseInt(option);
			return true;

		} catch (NumberFormatException nfe) {

			return false;
		}
	}

	public String autoSetDate() {
		/*Use to get time from system.*/
		Date today = Calendar.getInstance().getTime(); 
		
		 /*Use to change date format.*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		return sdf.format(today);
	}

	@Override
	public void readArticel(Collection<Article> List, int id) {
		// TODO Auto-generated method stub
		ArrayList<Article> arrList = (ArrayList<Article>) List;
		int index = Collections.binarySearch(arrList, new Article(id, null,
				null, null, null), new Comparator<Article>() {
			@Override
			public int compare(Article art1, Article art2) {
				// TODO Auto-generated method stub
				return art1.getId().compareTo(art2.getId());
			}
		});
		try {
			if (arrList.get(index).getId() == id) {
				System.out.println("ID     : " + arrList.get(index).getId());
				System.out.println("Title  : " + arrList.get(index).getTitle());
				System.out.println("Content: "
						+ arrList.get(index).getContent());
				System.out
						.println("Author : " + arrList.get(index).getAuthor());
				System.out.println("Date   : " + arrList.get(index).getDate());
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Id you input is invalid!");
		}

	}
	public void writeLogFile(String action, String desc, String status){
		try(FileWriter fw=new FileWriter("logfile.log", true)){
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String ass="\n" + sdf.format(today) +"\t\t"+ action +"\t\t"+ desc +"\t\t\t\t"+ status;			
			fw.append(ass);			
			
		}catch(IOException e){
			
		}
		
	}
	
	public static void writeFile(ArrayList<Article> arr){
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));) {
			checkTempFile(arr);			
			oos.writeObject(arr);
			TempFile.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void readFile(ArrayList<Article> arr){
		OperationFile.arr = arr;
		
		if(TempFile.exists() == false)
			return;
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));) {
			
			if(arr.size()>0){
				arr.clear();
			}
			arr.addAll((ArrayList<Article>)ois.readObject());
			
			if(TempFile.exists()){				
				
				checkTempFile(arr);
				TempFile.delete();
				writeFile(arr);
				return;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			arr.clear();
			TempFile.delete();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*@param num is a number represent type of value. 1 for ID, 2 for title, 3 for Content, 4 for Author, 5 for Date
	 * @value is a value to concatenate    
	 * */
	static String concatenate(int num, String value){
				
		if(value.matches(".*" + "@@@" + ".*")){
			System.out.println("Invalid content! @@@ will be invisible or empty.");
			value = value.replaceAll("@@@", "");
		}
		String str = "@@@"+num+value+"@@@"+(num+1);  
		return str;
	}
	
	public static void writeTempAdd(int id, String title, String content, String author, String date){
				
		String add = "add ";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(TempFile,true));) {
			
			bw.write(add + concatenate(1, id + "") + concatenate(2, title) + concatenate(3, content) + concatenate(4, author) + concatenate(5, date));			
			bw.newLine();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
	public static void writeTempUpdate(int index, String title, String content, String author){
		
		String update = "upd ";
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(TempFile,true));) {
			
			bw.write(update + concatenate(1, index + "") + concatenate(2, title) + concatenate(3, content) + concatenate(4, author));			
			bw.newLine();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
	public static void writeTempDelete(int index){
		
		String delete = "del ";
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(TempFile,true));) {
			
			bw.write(delete + concatenate(1,index + ""));
			bw.newLine();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void checkTempFile(ArrayList<Article> lst){
		
		String str;
		int start;
		int stop;
		
		String opera;  //operation, add, del, or upd
		int index;
		String title; String content; String author; String date;		
		
		if(new File(TempFile.getAbsolutePath()).exists() == false)
			return;		
		
		try(BufferedReader br = new BufferedReader(new FileReader(TempFile));) {
			
			while((str = br.readLine())!=null){				
				opera = str.substring(0,3);
				
				if(opera.equals("add")){
					 start = str.lastIndexOf("@@@1")+4;
					 stop = str.indexOf("@@@2");
					 index = Integer.parseInt(str.substring(start, stop));
					 start = str.lastIndexOf("@@@2")+4;
					 stop = str.indexOf("@@@3");
					 title = str.substring(start, stop).trim();
					 
					 start = str.lastIndexOf("@@@3")+4;
					 stop = str.indexOf("@@@4");
					 content = str.substring(start, stop).trim();
					 
					 start = str.lastIndexOf("@@@4")+4;
					 stop = str.indexOf("@@@5");
					 author = str.substring(start, stop).trim();
					 
					 start = str.lastIndexOf("@@@5")+4;
					 stop = str.indexOf("@@@6");
					 date = str.substring(start, stop).trim();
					 
					 lst.add(new Article(index, title, content, author, date));				
				}
				else if(opera.equals("upd")){
					
					 start = str.lastIndexOf("@@@1")+4;
					 stop = str.indexOf("@@@2");
					 index = Integer.parseInt(str.substring(start, stop));
					 
					 start = str.lastIndexOf("@@@2")+4;
					 stop = str.indexOf("@@@3");
					 title = str.substring(start, stop).trim();
					 
					 start = str.lastIndexOf("@@@3")+4;
					 stop = str.indexOf("@@@4");
					 content = str.substring(start, stop).trim();
					 
					 start = str.lastIndexOf("@@@4")+4;
					 stop = str.indexOf("@@@5");
					 author = str.substring(start, stop).trim();
					 
					 lst.get(index).setTitle(title);
					 lst.get(index).setContent(content);
					 lst.get(index).setAuthor(author);
					 
				}
				else if(opera.equals("del")){
					
					 start = str.lastIndexOf("@@@1")+4;
					 stop = str.indexOf("@@@2");
					 index = Integer.parseInt(str.substring(start, stop));
					 
					 lst.remove(index);				
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
