package file.base;

import java.io.File;
import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

/*
 * 
 */

public class Process extends OperationFile{
	
	private ArrayList<Article> arrList = new ArrayList<Article>();
	ArrayList<Integer> indexSearch = new ArrayList<Integer>();
	public static byte actSearch = 0;
	File file = new File("file_backup.bac");
	public Process() {
		// TODO Auto-generated constructor stub
		//arrList = 

		for(int i=1; i<=1000000;i++){
			arrList.add(new Article(i, "title", "content", "author", "2015-01-01"));
		}
		
		OperationFile ope =new OperationFile();
		ope.writeFile(arrList);
		//ope.readFile(arrList);
		//ope.checkTempFile(arrList);				

	}
	public  Integer Increase(){
		if(arrList.size()==0)
			return 0;
		else
			return arrList.get(arrList.size()-1).getId();
		
	}
	public void showManu() {
		Scanner scan = new Scanner(System.in);
		UI ui = new UI();
		Validation val = new Validation();
		Pagination pagin = new Pagination();
		
		Filter filter = new Filter();
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
						
						OperationFile.writeFile(arrList);
						//new OperationFile().clearFile();
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
						indexSearch = filter.searchArticle(this.arrList);
						pagin.showPage(this.arrList, indexSearch, 1, "M", ui);
						break;
					// Add Article
					case "A":
						addArticle(arrList);
						pagin.showPage(this.arrList, 1, "M", ui);
						break;
					// Edit article
					case "E":
						try{
							updateArticle(arrList, Integer.parseInt(strAct[1]));
						}catch (NumberFormatException e) {
							// TODO: handle exception
							System.out.println("Invalid Keyword! Please input keyword 'E' or 'e' and id you want to Edit!");
						}
						
						break;
					// Delete Article
					case "D":
						try{
							deleteArticle(arrList, Integer.parseInt(strAct[1]));
						}catch (NumberFormatException e) {
							// TODO: handle exception
							System.out.println("Invalid Keyword! Please input keyword 'D' or 'd' and id you want to Delete!");
						}
						
						break;
					// Delete all
					case "CA":
						if(deleteAll(arrList))
							arrList = new ArrayList<Article>();
						break;
					// Read by Article
					case "RD":
						readArticel(arrList, Integer.parseInt(strAct[1]));
						break;
					// Restore Article
					case "SB":
						callRestore();
						OperationFile.readFile(arrList);
						break;
					// Back Article
					case "B":
						OperationFile.backupFile(file.toString());
						break;
					// Error Syntax
					case "SO": 
						callSort();
						pagin.showPage(arrList, 1, "HM", ui);
						break;
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
					new OperationFile().writeFile(arrList);
					//new OperationFile().clearFile();
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
					addArticle(arrList);
					pagin.showPage(this.arrList, 1, "M", ui);
					break;
				case "E":
					updateArticle(arrList, Integer.parseInt(strAct[1]));
					break;
				case "D":
					deleteArticle(arrList, Integer.parseInt(strAct[1]));
					break;
				case "CA":
					deleteAll(arrList);
					break;
				case "RD":
					readArticel(arrList, Integer.parseInt(strAct[1]));
					break;
				// Restore Article
				case "SB":
					callRestore();
					break;
				// Back Article
				case "B":
					OperationFile.backupFile(file.toString());
					break;
				case "SO": 
					callSort();
					pagin.showPage(arrList, 1, "HM", ui);
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
	public void callSort(){
		Filter filter = new Filter();
		System.out.print("Sort [Id|Title|Author]: ");
		Scanner scan = new Scanner(System.in);
		String option = scan.next();
		if(option.equalsIgnoreCase("id")){
			this.arrList = filter.sortArticleById(arrList);
		}else if(option.equalsIgnoreCase("title")){
			this.arrList = filter.sortArticleByTitle(arrList);
		}else if(option.equalsIgnoreCase("author")){
			this.arrList = filter.sortArticleByAuthor(arrList);
		}else{
			System.out.println("Invalid Keyword!");
		}
	}
	
	public void callRestore(){
		System.out.println("Are you sure want to restore data? [y/n]: ");
		Scanner scan = new Scanner(System.in);
		String choice  = scan.next();
		if(choice.equalsIgnoreCase("y")){
			OperationFile.restoreFile(file.toString());
		}else{
			System.out.println("Restore file was cancel!");
		}
	}
}