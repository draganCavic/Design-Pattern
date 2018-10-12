package serialization;

public class Serializer {
private SerializeStrategy serializeStrategy; 
	
	public void save(String path) { 
		serializeStrategy.save(path);
	}
	
	public void load(String path) {
		serializeStrategy.load(path);
	}
	
	public void setSerializeStrategy(SerializeStrategy serializeStrategy) {
		this.serializeStrategy = serializeStrategy;
	}
}
