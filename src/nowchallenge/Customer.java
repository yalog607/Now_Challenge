package nowchallenge;

public class Customer {
	private String id;
	private String name;
	private Location locate;

	Customer(String id, String name, Location locate) {
		this.id = id;
		this.name = name;
		this.locate = locate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Location getLocate() {
		return locate;
	}

	public void setLocate(Location locate) {
		this.locate = locate;
	}
	
	public void printInfo() {
		System.out.println("ID: " + this.id
					+ " | Tên: " + this.name
					+ " | Địa chỉ: (" + this.locate.getX() + ", " + this.locate.getY() + ")");
	}
}
