package file.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Filter {
	public ArrayList<Integer> searchArticle(ArrayList<Article> arrList) {

		System.out.print("Input keyword you want to search: ");
		Iterator<Article> itr = arrList.iterator();// Create object iterator from ArrayList "arrList"
		Scanner scan = new Scanner(System.in);
		String str = scan.next();

		ArrayList<Integer> arr = new ArrayList<Integer>();
		int index = 0;
		Article art;// Declare variable art as Article type to store object "itr.next()"
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

				arr.add(index);// add index of Search article to ArrayList "arr";
			}
			strBld = new StringBuilder();
			index++;// increment
		}

		return arr; // return object arr of ArrayList that store all index
		
	}
	

}
