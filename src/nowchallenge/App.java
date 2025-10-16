package nowchallenge;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class App {
	private HashMap<String, Customer> customers;
	private PriorityQueue<Driver> drivers;
	private Queue<Ride> rides;
	private Queue<Ride> orderPending;
	private Stack<Action> actions;

	App() {
		customers = new HashMap<>();
		drivers = new PriorityQueue<>((a, b) -> Double.compare(b.getRating(), a.getRating()));
		rides = new LinkedList<>();
		orderPending = new LinkedList<>();
		actions = new Stack<>();
	}
	
	public void loadData() {
		customers.put("C1", new Customer("C1","Hoa", new Location(3,3,"Q1")));
		customers.put("C2", new Customer("C2","Minh", new Location(6,2,"Q3")));
		
		drivers.add(new Driver("1", "An", 4.8, new Location(2.1, 2)));
	    drivers.add(new Driver("2", "Bình", 4.9, new Location(4, 1)));
	    drivers.add(new Driver("3", "Cường", 4.5, new Location(1, 3)));
	    drivers.add(new Driver("4", "Dũng", 4.7, new Location(5, 4)));
	    
	    rides.add(new Ride("1", "C1", "2", 5.2, 40000, "Đã xác nhận"));
	    rides.add(new Ride("2", "C2", "3", 3.5, 25000, "Đã xác nhận"));
	    
		System.out.println("✅ Đã khởi tạo danh sách ban đầu cho MinRide");
	}
	
	// Undo
	public void undo() {
		if (actions.size() == 0) {
			System.out.println("\n❎ Không có hành động XÓA nào được thực hiện gần đây");
			return;
		}
		Action latestAction = actions.pop();
		switch(latestAction.getType()) {
			case "DELETE_CUST":
				drivers.add(latestAction.getDriver());
				System.out.println("\n✅ Hoàn tác thao tác xóa tài xế thành công");
				break;
			
			case "DELETE_DRV":
				drivers.add(latestAction.getDriver());
				System.out.println("\n✅ Hoàn tác thao tác xóa tài xế thành công");
				break;
			
			default:	
				System.out.println("\n❎ Thao tác này không được hỗ trợ để hoàn tác");
				break;
		}
	}

	// Driver
	public void printAllDrivers() {
		System.out.println("\n✅ Danh sách tài xế: ");
		int index = 1;
		for(Driver d: drivers) {
			System.out.print(index++ + ". ");
			d.printInfo();
		}
	}
	
	public void showTopKDriver(int k) {
		if (drivers.isEmpty()) {
			System.out.println("\nChưa có tài xế nào!");
			return;
		}

		PriorityQueue<Driver> temp = new PriorityQueue<>(drivers);

		System.out.println("\nTop " + k + " tài xế có rating cao nhất:");
		int count = 1;
		while(!temp.isEmpty() && count <= k) {
			System.out.print(count++ + ". ");
			Driver top = temp.poll();
			top.printInfo();
		}
	}

	public void addDriver(Driver driver) {
		if (drivers.contains(driver)) {
			System.out.println("\n❎ Tài xế này đã tồn tại!");
            return;
		}

		drivers.add(driver);
		System.out.println("\n✅ Thêm tài xế thành công!");
	}

	public void updateInfoForDriver(Scanner scan, String name) {
		ArrayList<Driver> driversFoundByName = new ArrayList<>();

		for (Driver drv : drivers) {
			if (drv.getName().equalsIgnoreCase(name)) {
				driversFoundByName.add(drv);
			}
		}

		int size = driversFoundByName.size();

		if (size > 1) {
			System.out.println("\nCó " + size + " tài xế cùng có tên " + name + " trong danh sách...");
			System.out.print("Vui lòng nhập thêm id của tài xế: ");
			String id = scan.nextLine();

			Driver driverFound = null;
			for (Driver drv : driversFoundByName) {
				if (drv.getId().equals(id)) {
					driverFound = drv;
					break;
				}
			}

			if (driverFound == null) {
				System.out.print("Không tìm thấy tài xế có id: " + id + " và có tên: " + name + " trong danh sách");
				return;
			}
			
			System.out.println("\nĐã tìm thấy tài xế " + driverFound.getId());
			driverFound.printInfo();
			System.out.println();
			System.out.println("Nhập thông tin mới cho tài xế (để trống nếu không muốn thay đổi)");

			System.out.print("Nhập rating mới (rating cũ " + driverFound.getRating() + "): ");
			String ratingInput = scan.nextLine();
			double newRating = driverFound.getRating();
			if (!ratingInput.trim().isEmpty()) {
				newRating = Double.parseDouble(ratingInput);
			}

			System.out.print("Nhập địa chỉ X mới (X cũ " + driverFound.getLocate().getX() + "): ");
			String xInput = scan.nextLine();
			double newX = driverFound.getLocate().getX();
			if (!xInput.trim().isEmpty()) {
				newX = Integer.parseInt(xInput);
			}

			System.out.print("Nhập địa chỉ Y mới (Y cũ " + driverFound.getLocate().getY() + "): ");
			String yInput = scan.nextLine();
			double newY = driverFound.getLocate().getY();
			if (!yInput.trim().isEmpty()) {
				newY = Integer.parseInt(yInput);
			}

			Location newLocation = new Location(newX, newY);

			drivers.remove(driverFound);
			driverFound.setRating(newRating);
			driverFound.setLocate(newLocation);
			drivers.add(driverFound);

			System.out.println("✅ Cập nhật thông tin tài xế thành công!");
		} else if (size == 1) {
			Driver driverFound = driversFoundByName.get(0);

			System.out.println("\nĐã tìm thấy tài xế " + driverFound.getName());
			driverFound.printInfo();
			System.out.println();
			System.out.println("\nNhập thông tin mới cho tài xế (để trống nếu không muốn thay đổi)");

			System.out.print("Nhập rating mới (rating cũ " + driverFound.getRating() + "): ");
			String ratingInput = scan.nextLine();
			double newRating = driverFound.getRating();
			if (!ratingInput.trim().isEmpty()) {
				newRating = Double.parseDouble(ratingInput);
			}

			System.out.print("Nhập tên quận cho địa chỉ mới (Tên cũ " + driverFound.getLocate().getDistrict() + "): ");
			String districtInput = scan.nextLine();
			String newDistrict = driverFound.getLocate().getDistrict();
			if (!districtInput.trim().isEmpty()) {
				newDistrict = districtInput;
			}

			System.out.print("Nhập X mới (X cũ " + driverFound.getLocate().getX() + "): ");
			String xInput = scan.nextLine();
			double newX = driverFound.getLocate().getX();
			if (!xInput.trim().isEmpty()) {
				newX = Integer.parseInt(xInput);
			}

			System.out.print("Nhập Y mới (Y cũ " + driverFound.getLocate().getY() + "): ");
			String yInput = scan.nextLine();
			double newY = driverFound.getLocate().getY();
			if (!yInput.trim().isEmpty()) {
				newY = Integer.parseInt(yInput);
			}

			Location newLocation = new Location(newX, newY, newDistrict);

			drivers.remove(driverFound);
			driverFound.setRating(newRating);
			driverFound.setLocate(newLocation);
			drivers.add(driverFound);

			System.out.println("\n✅ Cập nhật thông tin tài xế thành công!");
		} else {
			System.out.println("\n❎ Không tồn tại tài xế có tên " + name + " trong hệ thống!");
		}
	}

	public void removeDriverById(String id) {
		Driver removedDriver = null;
		for (Driver d : drivers) {
	        if (d.getId().equals(id)) {
	            removedDriver = d;
	            break;
	        }
	    }
		
	    if (removedDriver != null) {
	    	drivers.remove(removedDriver);
	        System.out.println("✅ Đã xóa tài xế có ID " + id);
			actions.add(new Action("DELETE_DRV", removedDriver));
	    } else {
	    	System.out.println("❎ Không tồn tại tài xế có ID " + id);
	    	return;
	    }
	}

	public Driver findDriverById(String id) {
		for (Driver d : drivers) {
	        if (d.getId().equals(id)) return d;
	    }
	    return null;
	}

	public ArrayList<Driver> findDriverByName(String name) {
		ArrayList<Driver> list = new ArrayList<>();
	    for (Driver d : drivers) {
	        if (d.getName().toLowerCase().contains(name.toLowerCase())) {
	            list.add(d);
	        }
	    }
	    return list;
	}
	
	public ArrayList<Driver> getSortedDrivers() {
	    ArrayList<Driver> sorted = new ArrayList<>();
	    PriorityQueue<Driver> temp = new PriorityQueue<>(drivers);
	    while (!temp.isEmpty()) {
	        sorted.add(temp.poll());
	    }
	    return sorted;
	}

	public ArrayList<Driver> sortDriversByRatingDescending() {
		ArrayList<Driver> listDriver = new ArrayList<>(drivers);

		mergeSortForDriver(listDriver, 0, listDriver.size());

		return listDriver;
	}

	private static void mergeSortForDriver(ArrayList<Driver> arr, int l, int r) {
		if (l < r) {
			int m = l + (r - l) / 2;

			mergeSortForDriver(arr, l, m);
			mergeSortForDriver(arr, m + 1, r);

			mergeForDriver(arr, l, m, r);
		}
	}

	private static void mergeForDriver(ArrayList<Driver> arr, int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		Driver L[] = new Driver[n1];
		Driver R[] = new Driver[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr.get(l + i);
		for (int j = 0; j < n2; ++j)
			R[j] = arr.get(m + 1 + j);

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L[i].getRating() <= R[j].getRating()) {
				arr.set(k, L[i]);
				i++;
			} else {
				arr.set(k, R[j]);
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr.set(k, L[i]);
			i++;
			k++;
		}

		while (j < n2) {
			arr.set(k, R[j]);
			j++;
			k++;
		}
	}

	// Customer
	public void printAllCustomers() {
		System.out.println("\n✅ Danh sách khách hàng: ");
		int index = 1;
		for(Customer c: customers.values()) {
			System.out.print(index++ + ". ");
			c.printInfo();
		}
	}
	
	public void showTopKCustomer(int k, boolean isTop) {
		ArrayList<Customer> listCustomer = new ArrayList<>(customers.values());
		if (listCustomer.isEmpty()) {
			System.out.println("❎ Dữ liệu khách hàng trống!");
			return;
		}
		listCustomer.sort((c1, c2) -> c1.getId().compareTo(c2.getId()));

		String tmp = isTop ? "đầu" : "cuối";
		System.out.println("Danh sách " + k + " top " + tmp + " danh sách: ");

		if (isTop) {
			for (int i = 0; i < Math.min(k, listCustomer.size()); i++) {
				System.out.println(i + ". ");
				listCustomer.get(i).printInfo();
			}
		} else {
			for (int i = listCustomer.size() - k; i < listCustomer.size(); i++) {
				System.out.println(i + ". ");
				listCustomer.get(i).printInfo();
			}
		}
	}

	public void addCustomer(Customer customer) {
		if (customers.containsKey(customer.getId())) {
			System.out.println("\n❎ ID " + customer.getId() + " đã tồn tại!");
			return;
		}

		customers.put(customer.getId(), customer);
		System.out.println("\n✅ Thêm khách hàng thành công!");
	}

	public void updateInfoForCustomer(Scanner scan, String id) {
		Customer customerFound = customers.get(id);
		if (customerFound == null) {
			System.out.println("\n❎ ID " + id + " không tồn tại!");
			return;
		}

		System.out.println("\nNhập thông tin địa chỉ mới cho khách hàng: ");

		System.out.print("Nhập tên quận cho địa chỉ mới (Tên cũ " + customerFound.getLocate().getDistrict() + "): ");
		String districtInput = scan.nextLine();
		String newDistrict = customerFound.getLocate().getDistrict();
		if (!districtInput.trim().isEmpty()) {
			newDistrict = districtInput;
		}

		System.out.print("Nhập địa chỉ X mới (X cũ: " + customerFound.getLocate().getX() + "): ");
		String xInput = scan.nextLine();
		double newX = customerFound.getLocate().getX();
		if (!xInput.trim().isEmpty()) {
			newX = Integer.parseInt(xInput);
		}

		System.out.print("Nhập địa chỉ Y mới (Y cũ " + customerFound.getLocate().getY() + "): ");
		String yInput = scan.nextLine();
		double newY = customerFound.getLocate().getY();
		if (!yInput.trim().isEmpty()) {
			newY = Integer.parseInt(yInput);
		}

		Location newLocation = new Location(newX, newY, newDistrict);
		customerFound.setLocate(newLocation);
		System.out.println("\n✅ Cập nhật thông tin cho khách hàng thành công!");
	}

	public void removeCustomerById(String id) {
		if (!customers.containsKey(id)) {
			System.out.println("\n❎ ID " + id + " không tồn tại!");
			return;
		}
		
		actions.add(new Action("DELETE_CUST", customers.get(id)));
		customers.remove(id);
		System.out.println("\n✅ Xóa khách hàng " + id + " thành công!");
	}

	public Customer findCustomerById(String id) {
		Customer customerFound = customers.get(id);

		if (customerFound == null) {
			System.out.println("\n❎ Không tìm thấy khách hàng " + id + "!");
		}
		return customerFound;
	}

	public ArrayList<Customer> findCustomerByName(String name) {
		ArrayList<Customer> listCustomer = new ArrayList<>();

		for (Customer cs : customers.values()) {
			if (cs.getName().equalsIgnoreCase(name)) {
				listCustomer.add(cs);
			}
		}

		return listCustomer;
	}

	public void showCustomerInDistrict(Scanner scan, String district) {
		ArrayList<Customer> listCustomer = new ArrayList<>();

		for (Customer cs : customers.values()) {
			if (cs.getLocate().getDistrict().equalsIgnoreCase(district)) {
				listCustomer.add(cs);
			}
		}
		int size = listCustomer.size();

		if (size == 0) {
			System.out.println("\n❎ Không có khách hàng nào đang ở " + district);
			return;
		}

		System.out.println("\n✅ Có " + size + " khách hàng đang ở " + district);

		if (size <= 10) {
			for (int i = 0; i < size; i++) {
				System.out.print((i + 1) + ". ");
				listCustomer.get(i).printInfo();
			}
		} else {
			int start = 0;
			int end = 10;

			while (true) {
				for (int i = start; i < end && i < size; i++) {
					System.out.print((i + 1) + ". ");
					listCustomer.get(i).printInfo();
				}

				if (end >= size) {
					break;
				}

				System.out.print("\n👉 Nhập '1' để xem thêm hoặc '0' để thoát: ");
				String choice = scan.nextLine().trim().toLowerCase();

				if (choice.equals("1")) {
					start = end;
					end = Math.min(end + 10, size);
				} else if (choice.equals("0")) {
					System.out.println("\n👋 Thoát hiển thị danh sách.");
					break;
				} else {
					System.out.println("⚠️ Lựa chọn không hợp lệ. Vui lòng nhập lại!");
				}
			}
		}
	}

	// Ride
	public void showRidesByDriverId(String driverId) {
		Queue<Ride> tempRides = new LinkedList<>(rides);
		ArrayList<Ride> listRide = new ArrayList<>();

		while (!tempRides.isEmpty()) {
			Ride ride = tempRides.peek();
			if (ride.getDriverId().equals(driverId)) {
				listRide.add(ride);
			}
			tempRides.poll();
		}

		if (listRide.isEmpty()) {
			System.out.println("\n❎ Tài xế chưa thực hiện bất kì chuyến đi nào.");
			return;
		}

		System.out.println("\n✅ Danh sách chuyến đi của tài xế " + driverId + ": ");
		System.out.println("Tổng số chuyến: " + listRide.size());

		int index = 1;
		for (Ride ride : listRide) {
			System.out.print(index++ + ". ");
			ride.printInfo();
		}
	}
	
	public void showConfirmedRides() {
		Queue<Ride> tempRides = new LinkedList<>(rides);
		ArrayList<Ride> listRide = new ArrayList<>();

		while (!tempRides.isEmpty()) {
			Ride ride = tempRides.peek();
			if (ride.getStatus().equals("Đã xác nhận")) {
				listRide.add(ride);
			}
			tempRides.poll();
		}

		if (listRide.isEmpty()) {
			System.out.println("\n❎ Không có chuyến đi nào đã được xác nhận.");
			return;
		}

		System.out.println("\n✅ Danh sách chuyến đi đã được xác nhận: ");

		int index = 1;
		for (Ride ride : listRide) {
			System.out.print(index++ + ". ");
			ride.printInfo();
		}
	}
	
	public void showPendingRides() {
		Queue<Ride> tempRides = new LinkedList<>(rides);
		ArrayList<Ride> listRide = new ArrayList<>();

		while (!tempRides.isEmpty()) {
			Ride ride = tempRides.peek();
			if (ride.getStatus().equals("Chưa xác nhận")) {
				listRide.add(ride);
			}
			tempRides.poll();
		}

		if (listRide.isEmpty()) {
			System.out.println("\n❎ Không có chuyến đi nào đã được xác nhận.");
			return;
		}

		System.out.println("\n✅ Danh sách chuyến đi đã được xác nhận: ");

		int index = 1;
		for (Ride ride : listRide) {
			System.out.print(index++ + ". ");
			ride.printInfo();
		}
	}
	
	public void showCanceledRides() {
		Queue<Ride> tempRides = new LinkedList<>(rides);
		ArrayList<Ride> listRide = new ArrayList<>();

		while (!tempRides.isEmpty()) {
			Ride ride = tempRides.peek();
			if (ride.getStatus().equals("Đã hủy")) {
				listRide.add(ride);
			}
			tempRides.poll();
		}

		if (listRide.isEmpty()) {
			System.out.println("\n❎ Không có chuyến đi nào đã được xác nhận.");
			return;
		}

		System.out.println("\n✅ Danh sách chuyến đi đã được xác nhận: ");

		int index = 1;
		for (Ride ride : listRide) {
			System.out.print(index++ + ". ");
			ride.printInfo();
		}
	}

	public ArrayList<Driver> findAvailableDriver(String customerId, double R) {
		Customer customer = this.findCustomerById(customerId);
		ArrayList<Driver> listDriver = new ArrayList<>();

		if (customer == null) {
			System.out.println("\n❎ Không tìm thấy khách hàng trên hệ thống!");
			return listDriver;
		}

		for (Driver drv : drivers) {
			double d = drv.getLocate().distanceTo(customer.getLocate());

			if (d <= R) {
				listDriver.add(drv);
			}
		}

		if (listDriver.isEmpty()) {
			System.out.println("\n❎ Không tìm thấy tài xế phù hợp trong phạm vi " + R + "km");
			return listDriver;
		}

		listDriver.sort((d1, d2) -> {
			double dist1 = d1.getLocate().distanceTo(customer.getLocate());
			double dist2 = d2.getLocate().distanceTo(customer.getLocate());

			int cmp = Double.compare(dist1, dist2);
			if (cmp != 0)
				return cmp;

			return Double.compare(d2.getRating(), d1.getRating());
		});

		return listDriver;
	}

	public void orderGrab(Scanner scan, String customerId, String driverId, double distance) {
		Driver drv = this.findDriverById(driverId);
		Customer customer = this.findCustomerById(customerId);

		if (drv == null || customer == null) {
			return;
		}

		double distFromDriverToCustomer = drv.getLocate().distanceTo(customer.getLocate());

		double finalDistance = distance + distFromDriverToCustomer;

		double fare = finalDistance * 12000;

		System.out.println("\nTổng quảng đường đi: " + String.format("%.2f", finalDistance));
		System.out.println("Tổng tiền: " + String.format("%.2f", fare) + "VNĐ");
		
		String newId = Integer.toString(Math.max(rides.size(), orderPending.size())+1);
		orderPending.add(new Ride(newId, customerId, driverId, finalDistance, fare));
		
		System.out.println("\nĐã thêm chuyến đi " + newId + " vào hàng đợi!");
	}
	
	public void showAllPending() {
		if (orderPending.isEmpty()) {
			System.out.println("\n❎ Không có chuyến đi nào đang chờ!");
			return;
		}
		System.out.println("Danh sách các chuyến đang chờ: ");
		for(Ride p: orderPending) {
			p.printInfo();
		}
	}
	
	public void cancelRide(String rideId) {
		if (orderPending.isEmpty()) {
			System.out.println("\n❎ Không có chuyến đi nào đang chờ!");
			return;
		}
		
		Queue<Ride> tmp = new LinkedList<>();
	    boolean found = false;
	    
		while (!orderPending.isEmpty()) {
			Ride r = orderPending.poll();

			if (r.getId().equals(rideId)) {
				r.setStatus("Đã hủy");
				rides.add(r);

				System.out.println("✅ Đã hủy chuyến đi có ID: " + rideId);
				found = true;
				break;
			} else {
				tmp.add(r);
			}
		}
		
		while (!orderPending.isEmpty()) {
	        tmp.add(orderPending.poll());
	    }
		orderPending = tmp;

	    if (!found) {
	        System.out.println("❎ Không tìm thấy chuyến đi đang chờ có ID: " + rideId);
	    }
	}
	
	public void confirmAllRides() {
		if (orderPending.isEmpty()) {
			System.out.println("\n❎ Không có chuyến đi nào đang chờ!");
			return;
		}
		
		System.out.println("\nBắt đầu xác nhận các chuyến đi...");
		
		while(!orderPending.isEmpty()) {
			Ride ride = orderPending.poll();
			ride.setStatus("Đã xác nhận");
			rides.add(ride);
			System.out.println("Đã xác nhận chuyến đi " + ride.getId());
		}
		
		System.out.println("\n✅ Đã xác nhận tất các chuyến đi đang chờ");
	}
	
	public void showAllRides() {
		if (rides.isEmpty()) {
			System.out.println("\n❎ Lịch sử chuyến đi trống!");
			return;
		}
		
		System.out.println("\nLịch sử các chuyến đi: ");
		for(Ride rd: rides) {
			rd.printInfo();
		}
		for(Ride rd: orderPending) {
			rd.printInfo();
		}
	}

	public void autoMatch(Scanner scan, String customerId, Location dest) {
		Customer customer = this.findCustomerById(customerId);
		if (customer == null) {
			System.out.println("\n❎ Không tìm thấy khách hàng");
			return;
		}
		
		Location customerLocation = customer.getLocate();
		
		ArrayList<Driver> driverList = new ArrayList<>();
		// Ban đầu tìm tài xế ở bán kinh 5km, nếu không có sẽ tăng dần thêm 5km nữa. Nếu quá 50km -> không có tx
		int distToFindDriver = 5;
		
		while(driverList.isEmpty() && distToFindDriver <= 50) {
			driverList = this.findAvailableDriver(customerId, distToFindDriver);
			distToFindDriver += 5;
		}
		
		if (driverList.isEmpty()) {
			System.out.println("\n❎ Không tìm thấy tài xế phù hợp cho khách hàng!");
			return;
		}
		
		Driver optimalDriver = driverList.get(0);
		
		double dist = optimalDriver.getLocate().distanceTo(customerLocation);
		
		this.orderGrab(scan, customerId, optimalDriver.getId(), dist);
		
		System.out.println("\n✅ Đã ghép cặp tài xế cho khách hàng thành công");
	}
	
}