package nowchallenge;

public class Action {
	private String type;
	private Customer customer;
	private Driver driver;
	private Ride ride;
	
	Action() {
		type = "";
	}
	
	Action(String t, Customer cust, Driver drv, Ride r) {
		type = t;
		customer = cust;
		driver = drv;
		ride = r;
	}
	
	Action(String t, Customer cust) {
		type = t;
		customer = cust;
	}
	
	Action(String t, Driver drv) {
		type = t;
		driver = drv;
	}
	
	Action(String t, Ride r) {
		type = t;
		ride = r;
	}
	
	public String getType() {
		return type;
	}
	
	public Driver getDriver() {
		return driver;
	}
	
	public Customer getCustomer() {
		return customer;
	}
}
