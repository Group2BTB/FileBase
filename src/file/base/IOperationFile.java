package file.base;

import java.util.Collection;

public interface IOperationFile {
	void addArticle(Collection<Article> arrList);
	void deleteArticle(Collection<Article> arrList, int id);
	void updateArticle(Collection<Article> arrList, int id);
	void readArticel(Collection<Article> arrList, int id);
	void deleteAll(Collection<Article> arrList);
}
