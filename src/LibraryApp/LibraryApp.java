package LibraryApp;

import java.util.Scanner;

import LibraryData.BookIO;
import LibraryData.LibraryIO;
import libraryDataModel.Book;
import libraryDataModel.BookOperations;
import libraryDataModel.Library;

public class LibraryApp {

	static Library lib;
	static LibraryIO libIO;
	static BookIO bookIO;
	static final String BOOKLISTPATH = "booklist.txt";
	static final String LIBDATAPATH = "library.txt";

	static {

		libIO = new LibraryIO();
		bookIO = new BookIO();

	}

	public static void main(String[] args) {

		while (true) {

			System.out.println("\nPerform the following actions.Press the option number ");
			System.out.println("\n1.Create a Library");
			System.out.println("\n2.Add new book to the Library");
			System.out.println("\n3.Search Book to the Library");
			System.out.println("\n4.Delete Book");
			System.out.println("\n5.Add new book copies");
			System.out.println("\n6.Edit informations");
			System.out.println("\n7.Show Library Info");
			System.out.println("\n0.Press 0 to  exit");

			@SuppressWarnings("resource")
			int option = new Scanner(System.in).nextInt();

			switch (option) {

			case 1:

				if (!LibraryIO.isExist(LIBDATAPATH)) {
					lib = Library.getUniqueInstances();

					if (libIO.write(LIBDATAPATH, lib))
						System.out.println("Welcome!" + lib.getLibName() + " has been created Succesfully.");
					else
						System.out.println("Error Occured");

				} else {
					System.out.println("A library is already exist.");
				}

				break;

			case 2:

				if (LibraryIO.isExist(LIBDATAPATH)) {

					lib = libIO.read(LIBDATAPATH);
					Book book = BookOperations.crerateBook();
					if (!lib.searchBook(book.getBookId())) {
						bookIO.writeBookList(BOOKLISTPATH, book);
						lib.addNewBook(book);
						libIO.update(LIBDATAPATH, lib);
					}

					else
						System.out.println(book.getBookName() + " is already exist in the libray.Add "
								+ book.getBookName() + " book copies.");

				} else {
					System.out.println("Library is not exist. Create A Library.");
				}
				break;

			case 3:

				if (LibraryIO.isExist(LIBDATAPATH)) {
					lib = libIO.read(LIBDATAPATH);
					System.out.println("\nEnter Book ID");
					@SuppressWarnings("resource")
					String bookId = new Scanner(System.in).nextLine();
					if (lib.searchBook(bookId)) {

						System.out.println(bookId + " has been Found");

					} else {
						System.out.println("Invalid BookID.Book not Found.");
					}
				} else {
					System.out.println("Library is not exist. Create One.");
				}
				break;

			case 4:

				if (LibraryIO.isExist(LIBDATAPATH)) {

					lib = libIO.read(LIBDATAPATH);
					System.out.println("Enter BookID to delete a book\n  Available Book's ID:\n");
					Book[] bookList = lib.getListOfBook();
					for (int i = 0; i < lib.getTotalBook(); i++) {
						System.out.println(bookList[i].getBookId());
					}
					System.out.println("\nEnter Book ID");
					String bookId = new Scanner(System.in).nextLine();
					if (lib.searchBook(bookId)) {
						lib.deleteBook(bookId);
						bookIO.updateBookList(BOOKLISTPATH, lib.getListOfBook(), lib.getTotalBook());
						libIO.update(LIBDATAPATH, lib);
						System.out.println(bookId + " has been deleted successfully.");

					} else {
						System.out.println("Invalid BookID.Book not Found.");
					}

				} else {
					System.out.println("Library is not exist. Create One.");
				}
				break;

			case 5:

				if (LibraryIO.isExist(LIBDATAPATH)) {

					lib = libIO.read(LIBDATAPATH);
					Book[] bookList;
					System.out.println("\nEnter Book ID");
					String bookId = new Scanner(System.in).nextLine();
					int quantity;
					if (lib.searchBook(bookId)) {

						System.out.println("How many copies do you want to add");
						quantity = new Scanner(System.in).nextInt();
						bookList = lib.getListOfBook();
						for (int i = 0; i < lib.getTotalBook(); i++) {
							if (bookList[i].getBookId().contentEquals(bookId)) {

								lib.addNewBookCopy(bookList[i], quantity);
								break;
							}
						}
						bookIO.updateBookList(BOOKLISTPATH, bookList, lib.getTotalBook());
						lib.setListOfBook(bookList);
						libIO.update(LIBDATAPATH, lib);

					} else {
						System.out.println("Invalid BookID.Book not Found.");
					}

				} else {
					System.out.println("Library is not exist. Create One.");
				}
				break;

			case 7:

				if (LibraryIO.isExist(LIBDATAPATH)) {

					lib = libIO.read(LIBDATAPATH);
					lib.showLibraryInfo();

				} else {
					System.out.println("Library is not exist. Create One.");
				}
				break;

			default:
				if (option == 0)
					System.exit(option);
				else
					System.out.println("Invalid Option has been Choosen");

			}

		}

	}

}
