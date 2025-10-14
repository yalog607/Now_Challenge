package nowchallenge;

public class Driver {
	private String id;
	private String name;
	private double rating;
	private Location locate;
	
	Driver() {
		this.id = "";
		this.name = "";
		this.rating = 0.0;
		this.locate = new Location();
	}
	
	Driver(String id, String name, double rating, Location locate) {
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.locate = locate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setLocate(Location locate) {
		this.locate = locate;
	}

	public double getRating() {
		return rating;
	}

	public Location getLocate() {
		return locate;
	}
	
	public double calculateDistanceToCustomer() {
		return 1.1;
	}

	public void printInfo() {
		System.out.println("ID: " + this.id
					+ " | Tên: " + this.name
					+ " | Đánh giá: " + this.rating
					+ " | Địa chỉ: (" + this.locate.getX() + ", " + this.locate.getY() + ")");
	}
}
