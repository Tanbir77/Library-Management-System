package libraryDataModel;

public interface LibraryFunctions {
	public void addNewBook(Book book);

	public boolean searchBook(Book book);

	public boolean deleteBook(String bookId);

	public String addNewBookCopy(Book book, int quantity);

	public void showLibraryInfo();
}
