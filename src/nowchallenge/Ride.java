package nowchallenge;

public class Ride {
	private String id;
	private String customerId;
	private String driverId;
	private double distance;
	private double fare;
	private boolean isConfirmed;
	
	Ride(String id, String customerId, String driverId, double distance, double fare) {
		this.id = id;
		this.customerId = customerId;
		this.driverId = driverId;
		this.distance = distance;
		this.fare = fare;
		this.isConfirmed = false;
	}

	public String getId() {
		return id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getDriverId() {
		return driverId;
	}

	public double getDistance() {
		return distance;
	}

	public double getFare() {
		return fare;
	}
	
	public void printInfo() {
		System.out.println("ID: " + this.id
					+ " | ID khách hàng: " + this.customerId
					+ " | ID tài xế: " + this.driverId
					+ " | Khoảng cách: " + this.distance
					+ " | Tiền: " + this.fare);
	}
	
	public void confirmRide() {
		this.isConfirmed = true;
	}
}
