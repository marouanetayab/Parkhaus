package parkhausprototype;

import java.io.File;
import java.io.IOException;

public class Persister implements PersisterIF {

	private final File file;

	Persister(String filename) {
		this.file = new File(filename);
		try {
			//noinspection ResultOfMethodCallIgnored
			file.getParentFile().mkdirs();
			//noinspection ResultOfMethodCallIgnored
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("could not create File" + filename);
		}
	}

	@Override
	public boolean saveToFile(Parkhaus parkhaus) {
		return false;
	}

	@Override
	public Parkhaus loadFromFile() {
		return null;
	}
}
