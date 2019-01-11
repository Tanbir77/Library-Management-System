package LibraryData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import libraryDataModel.Book;

public class BookIO {

	public boolean writeBookList(String path, Book serObj) {
		try {

			boolean exists = new File(path).exists();
			File file = new File(path);
			FileOutputStream fos = new FileOutputStream(file, true);
			ObjectOutputStream oos = exists ? new ObjectOutputStream(fos) {
				protected void writeStreamHeader() throws IOException {
					reset();
				}
			} : new ObjectOutputStream(fos);

			oos.writeObject(serObj);
			oos.flush();
			oos.close();

			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return false;

	}

	public Book[] readBookList(String path, int capacity) {
		Book[] bookList = new Book[capacity];
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {

			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);

			Object obj = null;

			boolean isExist = true;
			int i = 0;
			while (isExist) {
				if (fis.available() != 0) {
					bookList[i++] = (Book) ois.readObject();
				} else {
					isExist = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			fis.close();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookList;

	}

	public void updateBookList(String bookListPath, Book[] bookList, int boundary) {
		File file = new File(bookListPath);
		if (file.exists())
			file.delete();
		try {
			for (int i = 0; i < boundary; i++) {
				this.writeBookList(bookListPath, bookList[i]);
			}
		} catch (Exception e) {

		}

	}

}
