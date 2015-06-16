package file.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * Class Process use to store all methods 
 */

public class Process extends OperationFile{
	
	private List<Article> arrList;
	private Article art;
	
	public Process() {
		// TODO Auto-generated constructor stub
		arrList = new ArrayList<Article>();
		art = new Article();
	}
	
	public void callMethod(){
		addArticle(arrList, art);
	}
	
	public void display(){
		try{
		for(Article arts : arrList){
			System.out.println(arts.getId()+" "+arts.getTitle()+" "+arts.getContent()+" "+arts.getAuthor()+ ""+ arts.getDate());
		}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("No content to read!");
		}
		
	}
	
}