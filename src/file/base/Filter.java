package file.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Filter {
	private Scanner scan;
	private static String sId = "D";
	private static String sTitle = "D";
	private static String sAuthor = "D";
	public Filter() {
		scan = new Scanner(System.in);// create new object scanner to get data
										// from user
	}

	public ArrayList<Integer> searchArticle(ArrayList<Article> arrList) {

		System.out.print("Input keyword you want to search: ");
		Iterator<Article> itr = arrList.iterator();// Create object iterator
													// from ArrayList "arrList"
		Scanner scan = new Scanner(System.in);
		String str = scan.next();

		ArrayList<Integer> arr = new ArrayList<Integer>();
		int index = 0;
		Article art; /*Declare variable art as Article type to store object "itr.next()"*/
		StringBuilder strBld = new StringBuilder();
		String regx = ".*" + str.trim().toUpperCase() + ".*";
		// long s = System.currentTimeMillis();

		while (itr.hasNext()) {

			art = itr.next();
			strBld.append(art.getTitle());
			strBld.append(" ");
			strBld.append(art.getAuthor());
			strBld.append(" ");
			strBld.append(art.getContent());

			// Variable str1 use to concate title,
			// author, content together

			if (strBld.toString().toUpperCase().matches(regx)) {

				arr.add(index);// add index of Search article to ArrayList
								// "arr";
			}
			strBld = new StringBuilder();
			index++;// increment
		}

		return arr; // return object arr of ArrayList that store all index

	}

	public ArrayList<Article> sortArticleByTitle(ArrayList<Article> arrList) {
		Collections.sort(arrList, new Comparator<Article>() {

			@Override
			public int compare(Article art1, Article art2) {
				// TODO Auto-generated method stub
				return art1.getTitle().compareTo(art2.getTitle());
			}
		});
		System.out
				.print("Sort article by Ascending 'A' or Descending 'D': ");
		String option = scan.next();
		if (option.equalsIgnoreCase("A")) {//check
			if(sTitle=="D"){
				Collections.reverse(arrList);
			}
			return arrList;
		} else if (option.equalsIgnoreCase("D")) {
			if(sTitle=="A"){
				Collections.reverse(arrList);
			}
		}

		return arrList;

	}

	public ArrayList<Article> sortArticleByAuthor(ArrayList<Article> arrList) {
		/* sort to ascending order*/
		Collections.sort(arrList, new Comparator<Article>() {

			@Override
			public int compare(Article art1, Article art2) {
				// TODO Auto-generated method stub
				return art1.getAuthor().compareTo(art2.getAuthor());
			}
		}); 

		System.out
				.print("Sort article by Ascending 'A' or Descending 'D': ");
		String option = scan.next();// Ask user option
		if (option.equalsIgnoreCase("A")) {//check 
			if(sAuthor=="D"){
				Collections.reverse(arrList);
			}
			return arrList;
		} else if (option.equalsIgnoreCase("D")) {
			if(sAuthor=="A"){
				Collections.reverse(arrList);
			}
		}
		return arrList;

	}

	public ArrayList<Article> sortArticleById(ArrayList<Article> arrList) {
		Collections.sort(arrList, new Comparator<Article>() {

			@Override
			public int compare(Article art1, Article art2) {
				// TODO Auto-generated method stub
				return art1.getId().compareTo(art2.getId());
			}
		});
		System.out
				.print("Sort article by Ascending 'A' or Descending 'D': ");
		String option = scan.next();
		if (option.equalsIgnoreCase("A")) {//check
			if(sId=="D"){
				Collections.reverse(arrList);
			}
			return arrList;
		} else if (option.equalsIgnoreCase("D")) {
			if(sId=="A"){
				Collections.reverse(arrList);
			}
		}
		
		return arrList;
	}

}
