package libraryDataModel;

import java.util.Scanner;

public class BookOperations {

	public static Book crerateBook() {

		System.out.println("Enter Book Id: ");
		String id = readString();

		System.out.println("Enter Book Title: ");
		String bookTitle = readString();

		System.out.println("Enter Author Name: ");
		String authorName = readString();

		System.out.println("Enter Book Copy");

		int bookCopy = new Scanner(System.in).nextInt();

		System.out.println("Enter Book Type");
		String bookType = readString();

		Book book = new Book(id, bookTitle, authorName, bookType, bookCopy);

		return book;

	}

	private static String readString() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

}
