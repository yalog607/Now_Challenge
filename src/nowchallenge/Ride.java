package nowchallenge;

public class Ride {
	private String id;
	private String customerId;
	private String driverId;
	private double distance;
	private double fare;
	private String status;
	
	Ride(String id, String customerId, String driverId, double distance, double fare) {
		this.id = id;
		this.customerId = customerId;
		this.driverId = driverId;
		this.distance = distance;
		this.fare = fare;
		this.status = "Chưa xác nhận";
	}

	Ride(String id, String customerId, String driverId, double distance, double fare,String status) {
		this.id = id;
		this.customerId = customerId;
		this.driverId = driverId;
		this.distance = distance;
		this.fare = fare;
		this.status = status;
	}

	public String getStatus() {
		return status;
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
	
	public void setStatus(String status) {
		this.status = status;
	}

	public void printInfo() {
		System.out.println("ID: " + this.id
					+ " | ID khách hàng: " + this.customerId
					+ " | ID tài xế: " + this.driverId
					+ " | Khoảng cách: " + String.format("%.2f", this.distance)
					+ " | Tiền: " + String.format("%.2f", this.fare) + "VNĐ"
					+ " | Trạng thái: " + this.status);
	}
}
