package parkhausprototype;

public interface PersisterIF {
	public boolean saveToFile(Parkhaus parkhaus);
	public Parkhaus loadFromFile();
}
