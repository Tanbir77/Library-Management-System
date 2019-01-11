package libraryDataModel;

import java.io.Serializable;
import java.util.Scanner;

public class Library implements LibraryFunctions, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String libName;
	private String libAddress;
	private int capacity;
	private Book[] listOfBook;
	private int totalBook;
	private int totalNumberOFBooks;

	private static Library uniqueInstances;

	private Library() {
		this("No Library", "Dosent Exist", 0);
	}

	private Library(String libName, String libAddress, int capacity) {

		this.libName = libName;
		this.libAddress = libAddress;
		this.capacity = capacity;
		this.totalBook = 0;
		this.totalNumberOFBooks = 0;

		listOfBook = new Book[capacity];

	}

	public static Library getUniqueInstances() {
		if (uniqueInstances == null) {

			System.out.println("Enter Libray Name: ");
			String libName = readString();

			System.out.println("Enter Your Library Adress: ");
			String libAddress = readString();

			System.out.println("Enter Library Capacity");
			Scanner scanner = new Scanner(System.in);
			int capacity = scanner.nextInt();

			uniqueInstances = new Library(libName, libAddress, capacity);
		}
		return uniqueInstances;
	}

	private static String readString() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public String getLibName() {
		return libName;
	}

	public void setLibName(String libName) {
		this.libName = libName;
	}

	public String getLibAddress() {
		return libAddress;
	}

	public void setLibAddress(String libAddress) {
		this.libAddress = libAddress;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Book[] getListOfBook() {
		return listOfBook;
	}

	public void setListOfBook(Book[] listOfBook) {
		this.listOfBook = listOfBook;
	}

	public int getTotalBook() {
		return totalBook;
	}

	public void setTotalBook(int totalBook) {
		this.totalBook = totalBook;
	}

	public int getTotalNumberOFBooks() {
		return totalNumberOFBooks;
	}

	public void setTotalNumberOFBooks(int totalNumberOFBooks) {
		this.totalNumberOFBooks = totalNumberOFBooks;
	}

	@Override
	public void addNewBook(Book book) {

		if (totalNumberOFBooks < capacity) {
			if (this.searchBook(book)) {
				System.out.println("This book ia already exist. Add book copy");
				return;
			}
			listOfBook[this.totalBook++] = book;
			totalNumberOFBooks += book.getBookCopy();

		} else {
			System.out.println("Library is Full!!!");

		}

	}

	public boolean searchBook(String bookId) {

		for (int i = 0; i < totalBook; i++) {

			if (listOfBook[i].getBookId().contentEquals(bookId)) {

				return true;
			}
		}
		return false;

	}

	@Override
	public boolean searchBook(Book book) {

		for (Book b : listOfBook) {

			if (b == book) {
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean deleteBook(String bookID) {
		for (int i = 0; i < totalBook; i++) {
			if (listOfBook[i].getBookId().contentEquals(bookID)) {
				totalNumberOFBooks -= listOfBook[i].getBookCopy();
				listOfBook[i] = listOfBook[--totalBook];
				return true;
			}
		}

		return false;
	}

	@Override
	public String addNewBookCopy(Book book, int quantity) {

		if (totalNumberOFBooks + quantity > this.capacity)
			return quantity + " copies of" + book.getBookName() + " can not be added to the library";
		for (int i = 0; i < totalBook; i++) {
			if (listOfBook[i] == book) {
				totalNumberOFBooks += quantity;
				listOfBook[i].addBookCopy(quantity);
				return quantity + "copies of" + book.getBookName() + " has been succesfully added to the library";
			}
		}
		return book.getBookName() + " has not found in the library";

	}

	@Override
	public void showLibraryInfo() {

		System.out.println("Library Name :" + this.libName);
		System.out.println("Library Adress :" + this.libAddress);
		System.out.println("Capacity:" + this.capacity);
		System.out.println("Total Books:" + this.totalBook);
		System.out.println("Total Book copies:" + this.totalNumberOFBooks);
		System.out.println("\n*************Books Info***************");

		for (int i = 0; i < totalBook; i++) {
			listOfBook[i].showBookInfo();
		}
		System.out.println("**************************************");

	}

}
