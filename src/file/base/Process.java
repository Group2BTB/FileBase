package file.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/*
 * Class Process use to store all methods 
 */

public class Process {
	/*
	 * @param increment is used to store value of id's increment
	 * 
	 * @param arrList is used to store all data from Article object
	 * 
	 * @param indexSeach is use to store all index that get from searchArticle
	 * Method method addArticle() use to add Article object to arrList of
	 * ArrayList<Article>
	 * 
	 * @code Variable actSearch for check action search or not
	 */
	private static int increment = 0;

	ArrayList<Article> arrList = new ArrayList<Article>();

	ArrayList<Integer> indexSearch = new ArrayList<Integer>();
	public static int actSearch = 0;

	public Process() {
		System.out.println("Adding content ...");
		long s = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			Article atr = new Article();
			atr.setId(increment++);
			atr.setTitle("Title " + i+1);
			atr.setContent("content " + i+1);
			atr.setAuthor("author " + i+1);
			atr.setDate(autoSetDate());
			arrList.add(atr);
		}
		long st = System.currentTimeMillis(); 
		System.out.println((st - s) / 1000.00);
	}

	public void addArticle() {

		Article art = new Article();
		Scanner scan = new Scanner(System.in);
		art.setId(++increment);
		System.out.print("Enter Title:");
		art.setTitle(scan.next());

		System.out.print("Enter Content: ");
		art.setContent(getMiltiLineString());// set content to object Article by
												// calling method
												// getMiltiLineString();
		System.out.print("Enter Author: ");
		art.setAuthor(scan.next());

		art.setDate(autoSetDate());// set Date by calling method autoSetDate();

		String option;
		int choice = 0;
		do {
			System.out.print("What do you want to save: 1.[Save] 2.[Save-New] 3.[Cancel]\t:");
			option = scan.next();
			option = option + scan.nextLine();

			if (isInteger(option) == true) {
				choice = Integer.parseInt(option);// Convert from String to int type
			}

			if (choice == 1) {
				arrList.add(art);// add object art of Article to ArrayList
				System.out.println("Article saved...");
				
			} else if (choice == 2) {
				arrList.add(art);// add object art of Article to ArrayList
				System.out.println("Article saved...");
				addArticle();// Call function addArticle again

			} else if (choice == 3) {
				System.out.println("Record cancelled!");
				return;// Leave from the function 

			} else if (choice < 1 || choice > 3) {
				System.out.println("Invalid keyword! Please try again! ");

			}

		} while (choice < 1 || choice > 3);
	}

	public void deleteArticle(/**/ArrayList<Article> arrList, int id) {
		UI ui = new UI(); //Create new object UI
		try {	
			//use Collections.binarySearch() method to get the index of Article
		int index = Collections.binarySearch(arrList, new Article(id, null,
				null, null, null), new Comparator<Article>() {

			@Override
			// compare index and id
			public int compare(Article art1, Article art2) {
				return art1.getId().compareTo(art2.getId());
			}
		});
		Scanner scan = new Scanner(System.in);
		ui.table_head();// call method table_head() for Class UI
		String[] str ={""+arrList.get(index).getId(),arrList.get(index).getTitle(),arrList.get(index).getAuthor(),arrList.get(index).getDate()};
		ui.tbl_row(str);// Set row to display			
		String option; 
			do{
				System.out.print("Are you sure to delete this Article? (y/n): ");
				option = scan.next();
				if (option.matches("y")) {
					System.out.println("Delete successfully!");
					arrList.remove(index);//Call method remove() form ArrayList to remove article content by index.
				} else if (option.matches("n")) {
					System.out.println("Delete was canceled!");
				}else{
					System.out.println("Invalid keyword! Please try again!");
				}
			}while(option == "y" || option == "n");
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid ID!");
		}
		
	}
	
	public void deleteAll(ArrayList<Article> arrList){
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
	
	/*
	 * @param art of Object Article to store " itr.next()".
	 * 
	 * @param itrIndex use to store index when condition
	 * "{str1.toUpperCase().matches(".*"+str.toUpperCase()+".*"}" is true
	 * 
	 * @param opt is used to check option 1.Search 2.New Search 3.Cancel
	 */
	public ArrayList<Integer> searchArticle(ArrayList<Article> arrList) {

		System.out.println("Input keyword you want to search: ");
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
		// if (strBld.toString().toUpperCase().matches(regx) == false) {
		// System.out.println("Sorry invalid keyword!");
		// }
		/*
		 * long st = System.currentTimeMillis();
		 * 
		 * System.out.println((st-s)/1000.00);
		 */
	}

	public void ReadArticle(ArrayList<Article> arrayList, int id) {

		int index = Collections.binarySearch(this.arrList, new Article(id,
				null, null, null, null), new Comparator<Article>() {

			@Override
			public int compare(Article art1, Article art2) {
				// TODO Auto-generated method stub
				return art1.getId().compareTo(art2.getId());
			}
		});
		try {
			if (arrayList.get(index).getId() == id) {
				System.out.println("ID     : " + arrayList.get(index).getId());
				System.out.println("Title  : "
						+ arrayList.get(index).getTitle());
				System.out.println("Content: "
						+ arrayList.get(index).getContent());
				System.out.println("Author : "
						+ arrayList.get(index).getAuthor());
				System.out
						.println("Date   : " + arrayList.get(index).getDate());
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Id you input is invalid!");
		}

	}

	/*
	 *@method IsInteger() is used to validate that the parameter option is Integer or
	 * not.
	 */
	public boolean isInteger(String option) {
		try {
			Integer.parseInt(option);
			return true;

		} catch (NumberFormatException nfe) {

			return false;
		}
	}

	public String autoSetDate() {
		Date today = Calendar.getInstance().getTime();// Use to get time from system.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// Use to change date format.
		return sdf.format(today);
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

	public void showManu() {
		Scanner scan = new Scanner(System.in);
		UI ui = new UI();
		//Process pro = new Process();
		Validation val = new Validation();
		Pagination pagin = new Pagination();
		// display head content
		ui.head();
		// display list article
		pagin.showPage(this.arrList, 1, "HM", ui);
		while (true) {
			// display menu
			ui.menu();
			String part = scan.next().trim().toUpperCase();
			part = part + scan.nextLine();
			String[] strAct = val.checkNull(part);
			part = strAct[0];

			if (actSearch == 0) {

				switch (part) {
				// main page
				case "HM":
					actSearch = 0;
					pagin.showPage(this.arrList, 1, "HM", ui);
					break;
				// Goto page
				case "G":
					pagin.showPage(this.arrList, Integer.parseInt(strAct[1]),
							"G", ui);
					break;
				// Last page
				case "L":
					pagin.showPage(this.arrList, 1, "L", ui);
					break;
				// Previous page
				case "P":
					pagin.showPage(this.arrList, 1, "P", ui);
					break;
				// Next page
				case "N":
					pagin.showPage(this.arrList, 1, "N", ui);
					break;
				// Fisrt page
				case "F":
					pagin.showPage(this.arrList, 1, "F", ui);
					break;
				// Set Row
				case "R":
					pagin.showPage(this.arrList, Integer.parseInt(strAct[1]),
							"R", ui);
					break;
				// Exit
				case "X":
					ui.thanks();
					System.exit(1);
					break;
				// Help
				case "H":
					ui.help();
					break;
				// Search
				case "S":
					actSearch = 1;
					indexSearch = this.searchArticle(this.arrList);
					pagin.showPage(this.arrList, indexSearch, 1, "M", ui);
					break;
				// Add Article
				case "A":
					this.addArticle();
					pagin.showPage(this.arrList, 1, "M", ui);
					break;
				// Edit article
				case "E":
					System.out.print("Please Input ID: ");
					String option = scan.next();
					if (isInteger(option) == true) {
						this.UpdateArticle(this.arrList,
								Integer.parseInt(option));
					} else {
						System.out.println("ID is not valid!!!");
					}
					break;
				// Delete Article
				case "D":
					System.out.print("Please Input ID: ");
					String choice = scan.next();
					if (isInteger(choice) == true) {
						this.deleteArticle(this.arrList, Integer.parseInt(choice));
					} else {
						System.out.println("ID is not valid!!!");
					}
					
					break;
				// Delete all
				case "DL":
					this.deleteAll(this.arrList);
					break;
				// Read by Article
				case "RD":
					ReadArticle(arrList, Integer.parseInt(strAct[1]));
					break;
				// Error Syntax
				case "Error":
					System.out.println("Syntax Error!");
					break;
				default:
					continue;
				}
			} else {
				switch (part) {
				case "HM":
					actSearch = 0;
					pagin.showPage(this.arrList, 1, "HM", ui);
					break;
				case "G":
					pagin.showPage(this.arrList, this.indexSearch,
							Integer.parseInt(strAct[1]), "G", ui);
					break;
				case "L":
					pagin.showPage(this.arrList, this.indexSearch, 1, "L", ui);
					break;
				case "P":
					pagin.showPage(this.arrList, this.indexSearch, 1, "P", ui);
					break;
				case "N":
					pagin.showPage(this.arrList, this.indexSearch, 1, "N", ui);
					break;
				case "F":
					pagin.showPage(this.arrList, this.indexSearch, 1, "F", ui);
					break;
				case "R":
					pagin.showPage(this.arrList, this.indexSearch,
							Integer.parseInt(strAct[1]), "R", ui);
					break;
				case "X":
					ui.thanks();
					System.exit(1);
					break;
				case "H":
					ui.help();
					break;
				case "S":
					actSearch = 1;
					pagin.showPage(this.arrList,
					this.searchArticle(this.arrList), 1, "M", ui);
					break;
				case "A":
					this.addArticle();
					pagin.showPage(this.arrList, 1, "M", ui);
					break;
				case "E":
					System.out.print("Please Input ID: ");
					String option = scan.next();
					if (isInteger(option) == true) {
						this.UpdateArticle(this.arrList,
								Integer.parseInt(option));
					} else {
						System.out.println("ID is not valid!!!");
					}

					break;
				case "D":
					System.out.print("Please Input ID: ");
					String choice = scan.next();
					if (isInteger(choice) == true) {
						this.deleteArticle(this.arrList, Integer.parseInt(choice));
					} else {
						System.out.println("ID is not valid!!!");
					}
					break;
				case "DL":
					this.deleteAll(this.arrList);
					break;
				case "RD":
					ReadArticle(arrList, Integer.parseInt(strAct[1]));
					break;
				case "Error":
					System.out.println("Syntax Error!");
					break;
				default:
					continue;
				}
			}
		}
	}

	/**
	 * This method is used for Update Data int updateWord is used for update
	 * words int idUpdate use for update Title, Author, content by input number
	 * 1,2,3 int index that get index/id from Arraylist by compare input id and
	 * Article id if match return true in block do while is used for check
	 * validate (user can input only number 1,2 and 3)
	 * 
	 * @param list
	 *            is used for create object
	 */

	public void UpdateArticle(ArrayList<Article> list, int id) {
		Scanner scan = new Scanner(System.in);
		String idUpdate;
		int index = Collections.binarySearch(list, new Article(id, null, null,
				null, null), new Comparator<Article>() {

			@Override
			// Compare ID that input with ID in Artcle
			public int compare(Article art1, Article art2) {
				// TODO Auto-generated method stub
				return art1.getId().compareTo(art2.getId());
			}
		});
		// check condition if number less 1 and over 3, it is not valid (input
		// again)
		try {
			if (arrList.get(index).getId() == id) {
				System.out
						.print("What you want to update: [1.Title] [2.Author] [3.Content]     : ");
				idUpdate = scan.next();
				if (isInteger(idUpdate) == true) {
					do {
						int choice = Integer.parseInt(idUpdate);
						if (choice == 1) {
							END: while (true) {
								System.out
										.print("Are you sure You want to update "
												+ "<<"
												+ list.get(index).getTitle()
												+ ">>" + "???" + "(Y/N) : ");
								String option = scan.next();
								switch (option.toLowerCase()) {
								case "y":
									System.out.print("Enter New Title : ");
									String updateWord = scan.next();
									list.get(index).setTitle(updateWord);
									System.out
											.println("Update sucessfully (0_0) !");
									break END;
								case "n":
									System.out.println(list.get(index)
											.getTitle() + " is not update...");
									break END;
								default:
									System.out
											.println("\nInput is not valid!!!\n\nPlease Input again...");
									break;
								}
							}
							// Update Author
						} else if (choice == 2) {
							END: while (true) {
								System.out
										.print("Are you sure You want to update "
												+ "<<"
												+ list.get(index).getTitle()
												+ ">>" + "???" + "(Y/N) : ");
								String option = scan.next();
								switch (option.toLowerCase()) {
								case "y":
									System.out.print("Enter New Author : ");
									String updateWord = scan.next();
									list.get(index).setAuthor(updateWord);
									System.out
											.println("Update sucessfully (0_0) !");
									break END;
								case "n":
									System.out.println(list.get(index)
											.getAuthor() + " is not update...");
									break END;
								default:
									System.out
											.println("\nInput is not valid!!!\n\nPlease Input again...");
								}
							}

							// Update Contents
						} else if (choice == 3) {
							END: while (true) {
								System.out
										.print("Are you sure You want to update "
												+ "<<"
												+ list.get(index).getTitle()
												+ ">>" + "???" + "(Y/N) : ");

								String option = scan.next();
								// clear one line coz nextline is Enter one line
								// automatic
								switch (option.toLowerCase()) {
								case "y":

									list.get(index).setContent(
											getMiltiLineString());
									System.out
											.println("Update sucessfully (0_0) !");

									break END;
								case "n":
									System.out
											.println(list.get(index)
													.getContent()
													+ " is not update...");
									break END;
								default:
									System.out
											.println("\nInput is not valid!!!\n\nPlease Input again...");
								}
							}
						} else {

							System.out
									.println("\n-----Please check and input again!!----- \n");
						}

					} while (Integer.parseInt(idUpdate) < 1
							|| Integer.parseInt(idUpdate) > 3);
				} else {
					System.out.println("Id is invalid!");
				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("\n*** Input ID is not found!!!***\n");
		}

	}

}
