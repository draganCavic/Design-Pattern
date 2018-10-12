package serialization;

public interface SerializeStrategy {
	public void save(String path);
	public void load(String path);
}
