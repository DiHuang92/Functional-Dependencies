
public class Attribute {

	String name;
	
	public Attribute(String name) {
		this.name = name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(Object other) {
		if(other == null || !(other instanceof Attribute))
			return false;
		return name.equals(((Attribute)other).name);
	}
	
	public String toString() {
		return name;
	}
}