package file.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Class Process use to store all methods 
 */

public class Process extends OperationFile{
	
	private ArrayList<Article> arrList;
	ArrayList<Integer> indexSearch = new ArrayList<Integer>();
	public static byte actSearch = 0;
	
	public Process() {
		// TODO Auto-generated constructor stub
		arrList = new ArrayList<Article>();
		for(int i=1; i<=1000000;i++){
			arrList.add(new Article(i, "title"+i, "content", "author"+i, "2015-01-01"));
		}
		//art = new Article();
	}
	
	public void showManu() {
		Scanner scan = new Scanner(System.in);
		UI ui = new UI();
		Process pro = new Process();
		Validation val = new Validation();
		Pagination pagin = new Pagination();
		
		Filter filter = new Filter();
		// display head content
		ui.head();
		// display list article
		pagin.showPage(pro.arrList, 1, "HM", ui);
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
						System.out.println("s");
						actSearch = 1;
						indexSearch = filter.searchArticle(this.arrList);
						pagin.showPage(this.arrList, indexSearch, 1, "M", ui);
						break;
					// Add Article
					case "A":
						pro.addArticle(arrList);
						pagin.showPage(this.arrList, 1, "M", ui);
						break;
					// Edit article
					case "E":
						System.out.print("Please Input ID: ");
						String option = scan.next();
						if (isInteger(option) == true) {
							pro.updateArticle(this.arrList,
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
							pro.deleteArticle(this.arrList, Integer.parseInt(choice));
						} else {
							System.out.println("ID is not valid!!!");
						}
						
						break;
					// Delete all
					case "DL":
						break;
					// Read by Article
					case "RD":
						readArticel(arrList, Integer.parseInt(strAct[1]));
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
					filter.searchArticle(this.arrList), 1, "M", ui);
					break;
				case "A":
					pro.addArticle(arrList);
					pagin.showPage(this.arrList, 1, "M", ui);
					break;
				case "E":
					System.out.print("Enter id to Edit: ");
					String option = scan.next();
					if (isInteger(option) == true) {
						pro.updateArticle(this.arrList,
								Integer.parseInt(option));
					} else {
						System.out.println("ID is not valid!!!");
					}

					break;
				case "D":
					System.out.print("Please Input ID: ");
					String choice = scan.next();
					if (isInteger(choice) == true) {
						pro.deleteArticle(this.arrList, Integer.parseInt(choice));
					} else {
						System.out.println("ID is not valid!!!");
					}
					break;
				case "DL":
					break;
				case "RD":
					readArticel(arrList, Integer.parseInt(strAct[1]));
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
	
}