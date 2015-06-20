package file.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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

	private static int increment = 0;

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

			} else if (choice == 2) {
				arrList.add(art);// add object art of Article to ArrayList
				System.out.println("Article saved...");
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
					}
				} else if (option.matches("n")) {
					System.out.println("Delete was canceled!");
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid ID!");
			}
		}

	}

	@Override
	public void updateArticle(Collection<Article> list, int id) {
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
				} else if (choice == 2) {
					System.out.print("Enter Author: ");
					arrList.get(index).setAuthor(scan.next());
				} else if (choice == 3) {
					System.out.print("Enter Content: ");
					arrList.get(index).setContent(scan.next());
				} else {
					System.out.println("Invalid Option!");
				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("\n*** Input ID is not found!!!***\n");
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
	public static void writeLogFile(String action, String desc, String status) throws IOException{
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String ass="\n" + sdf.format(today) +"\t\t"+ action +"\t\t"+ desc +"\t\t\t\t"+ status;
		FileWriter fw = new FileWriter("logfile.log", true);
		fw.append(ass);
		fw.close();
	}
	
	
	public static void writeFile(ArrayList arr){
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));) {
			oos.writeObject(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void readFile(ArrayList arr){
		
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));) {
			
			arr = (ArrayList<Article>)ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
