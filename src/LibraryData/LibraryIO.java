package LibraryData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import libraryDataModel.Library;

public class LibraryIO {

	public static boolean isExist(String path) {
		return new File(path).exists();
	}

	public boolean write(String path, Library lib) {

		try {

			boolean exists = new File(path).exists();
			FileOutputStream fos = new FileOutputStream(path, true);
			ObjectOutputStream oos = exists ? new ObjectOutputStream(fos) {
				protected void writeStreamHeader() throws IOException {
					reset();
				}
			} : new ObjectOutputStream(fos);

			oos.writeObject(lib);
			oos.flush();
			oos.close();

			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return false;

	}

	public Library read(String path) {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {

			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);

			Library libObj = (Library) ois.readObject();
			fis.close();
			ois.close();

			return libObj;

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getting Null");
		return null;

	}

	public void update(String Path, Library libObj) {

		File file = new File(Path);
		if (file.exists())
			file.delete();
		try {

			this.write(Path, libObj);

		} catch (Exception e) {

		}

	}

}
