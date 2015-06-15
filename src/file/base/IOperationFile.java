package file.base;

public interface IOperationFile {
	
	Article addArticle(Article art);
	void deleteArticle(Article art, int id);
	void updateArticle(Article art, int id);
	
	
}
