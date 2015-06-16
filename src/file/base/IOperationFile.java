package file.base;

import java.util.ArrayList;
import java.util.Collection;


public interface IOperationFile {
	
	void addArticle(Collection<Article> arrList,Article art);
	void deleteArticle(ArrayList<Article> arrList, int id);
	void updateArticle(ArrayList<Article> arrList, int id);
	
}
