package libraryDataModel;

import java.io.Serializable;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bookId;
	private String bookName;
	private String bookAuthor;
	private String bookType;
	private int bookCopy;

	public Book() {
		this("Null", "No Name", "Null", "Null", 0);
	}

	public Book(String bookId, String bookName, String bookAuthor, String bookType, int bookCopy) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookType = bookType;
		this.bookCopy = bookCopy;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public int getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(int bookCopy) {
		this.bookCopy = bookCopy;
	}

	public void showBookInfo() {

		System.out.println("Book Name :" + this.bookName);
		System.out.println("Book's Author :" + this.bookAuthor);
		System.out.println("Book Id :" + this.bookId);
		System.out.println("Book type :" + this.bookType);
		System.out.println("Book's Coppy :" + this.bookCopy);
		System.out.println("---------------------------------");

	}

	public void addBookCopy(int x) {
		bookCopy = this.bookCopy + x;
	}

}
