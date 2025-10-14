package nowchallenge;

public class Location {
	private double x;
	private double y;
	private String district;

	Location() {
		x = 0;
		y = 0;
		district = "";
	}

	Location(double x, double y) {
		this.x = x;
		this.y = y;
		this.district = "";
	}
	
	Location(double x, double y, String district) {
		this.x = x;
		this.y = y;
		this.district = district;
	}

	@Override
	public String toString() {
		return district + " (" + this.x + ", " + this.y + ")";
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getDistrict() {
		return district;
	}
	
	public double distanceTo(Location other) {
	    double dx = this.x - other.x;
	    double dy = this.y - other.y;
	    return Math.sqrt(dx * dx + dy * dy);
	}
}
