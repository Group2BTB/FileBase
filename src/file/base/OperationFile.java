package file.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class OperationFile implements IOperationFile {

	private static int increment = 0;

	@Override
	public void addArticle(Collection<Article> arrList, Article art) {
		// TODO Auto-generated method stub
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
		
		arrList.add(art);

	}

	@Override
	public void deleteArticle(ArrayList<Article> arrList, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateArticle(ArrayList<Article> art, int id) {
		// TODO Auto-generated method stub

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
		Date today = Calendar.getInstance().getTime();// Use to get time from
														// system.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// Use to
																	// change
																	// date
																	// format.
		return sdf.format(today);
	}

}
