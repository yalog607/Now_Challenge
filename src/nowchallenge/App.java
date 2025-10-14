package nowchallenge;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

public class App {
	private HashMap<String, Customer> customers;
	private PriorityQueue<Driver> drivers;
	private Queue<Ride> rides;

	App() {
		customers = new HashMap<>();
		drivers = new PriorityQueue<>((a, b) -> Double.compare(b.getRating(), a.getRating()));
		rides = new LinkedList<>();
	}

	// Driver
	public void showTopKDriver(int k) {
		if (drivers.isEmpty()) {
			System.out.println("\nChưa có tài xế nào!");
			return;
		}

		PriorityQueue<Driver> temp = new PriorityQueue<>(drivers);

		System.out.println("\nTop " + k + " tài xế có rating cao nhất:");
		int count = 0;
		while(!temp.isEmpty() && count < k) {
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

			System.out.print("Nhập tên quận cho địa chỉ mới (Tên cũ " + driverFound.getLocate().getDistrict() + "): ");
			String districtInput = scan.nextLine();
			String newDistrict = driverFound.getLocate().getDistrict();
			if (!districtInput.trim().isEmpty()) {
				newDistrict = districtInput;
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

			Location newLocation = new Location(newX, newY, newDistrict);

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
		boolean removed = drivers.removeIf(d -> d.getId().equals(id));

	    if (removed)
	        System.out.println("✅ Đã xóa tài xế có ID " + id);
	    else
	        System.out.println("❎ Không tồn tại tài xế có ID " + id);
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
					System.out.println("\n🚪 Thoát hiển thị danh sách.");
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
			System.out.print(index + ". ");
			ride.printInfo();
		}
	}

	public void findAvailableDriver(String customerId, double R) {
		Customer customer = this.findCustomerById(customerId);
		ArrayList<Driver> listDriver = new ArrayList<>();

		if (customer == null) {
			return;
		}

		for (Driver drv : drivers) {
			double d = Math.sqrt(Math.pow(drv.getLocate().getX() - customer.getLocate().getX(), 2)
					+ Math.pow(drv.getLocate().getY() - customer.getLocate().getY(), 2));

			if (d <= R) {
				listDriver.add(drv);
			}
		}

		if (listDriver.isEmpty()) {
			System.out.println("Không tìm thấy tài xế phù hợp trong phạm vi " + R + "km");
			return;
		}

		listDriver.sort((d1, d2) -> {
			double dist1 = d1.getLocate().distanceTo(customer.getLocate());
			double dist2 = d2.getLocate().distanceTo(customer.getLocate());

			int cmp = Double.compare(dist1, dist2);
			if (cmp != 0)
				return cmp;

			return Double.compare(d2.getRating(), d1.getRating());
		});

		System.out.println("Danh sách tài xế phù hợp nhất: ");

		int index = 1;
		for (Driver drv : listDriver) {
			System.out.print(index + ". ");
			drv.printInfo();
		}
	}

	public void orderGrab(Scanner scan, String customerId, String driverId, double distance) {
		Driver drv = this.findDriverById(driverId);
		Customer customer = this.findCustomerById(customerId);

		if (drv == null || customer == null) {
			return;
		}

		double distFromDriverToCustomer = Math.sqrt(Math.pow(drv.getLocate().getX() - customer.getLocate().getX(), 2)
				+ Math.pow(drv.getLocate().getY() - customer.getLocate().getY(), 2));

		double finalDistance = distance + distFromDriverToCustomer * 2;

		double fare = finalDistance * 12000;

		System.out.println("\nTổng quảng đường đi: " + finalDistance);
		System.out.println("Tổng tiền: " + fare);

		while (true) {
			System.out.println("\n1. Xác nhận chuyến đi");
			System.out.println("2. Hủy chuyến đi");
			System.out.print("Nhập lựa chọn: ");

			int choice = Integer.parseInt(scan.nextLine().trim().toLowerCase());

			if (choice == 1) {
				Ride latestRide = rides.peek();
				String newId = "";
				if (latestRide == null)
					newId = "1";
				else {
					String latestId = latestRide.getId();
					newId = Integer.toString(Integer.parseInt(latestId) + 1);
				}
				rides.add(new Ride(newId, customerId, driverId, finalDistance, fare));

				System.out.println("\n✅ Đã xác nhận chuyến đi!");
			} else if (choice == 2) {
				System.out.println("\n✅ Hủy chuyến đi thành công!");
				break;
			} else {
				System.out.println("\n❎ Lựa chọn không hợp lệ. Vui lòng nhập lại!");
			}
		}
	}

	public void autoMatching(Scanner scan, String customerId, Location dist) {
		Customer customer = this.findCustomerById(customerId);
		Location locate = customer.getLocate();
		double R = 5;

		ArrayList<Driver> availableDriver = new ArrayList<>();
		boolean isChange = false;

		while (availableDriver.isEmpty() || !isChange) {
			for (Driver drv : drivers) {
				double d = Math.sqrt(Math.pow(drv.getLocate().getX() - customer.getLocate().getX(), 2)
						+ Math.pow(drv.getLocate().getY() - customer.getLocate().getY(), 2));
				if (d <= R) {
					availableDriver.add(drv);
					isChange = true;
				}
			}
			if (!isChange && R < 20) {
				System.out.println("\nKhông tìm thấy tài xế trong phạm vi " + R + "km...");
				R += 5;
				System.out.println("Mở rộng tìm kiếm thành " + R + "km.");
			}
		}

		if (availableDriver.isEmpty()) {
			System.out.println("\n❎ Không có tài xế nào khả dụng...");
			return;
		}

		availableDriver.sort((d1, d2) -> {
			double dist1 = d1.getLocate().distanceTo(customer.getLocate());
			double dist2 = d2.getLocate().distanceTo(customer.getLocate());

			int cmp = Double.compare(dist1, dist2);
			if (cmp != 0)
				return cmp;

			return Double.compare(d2.getRating(), d1.getRating());
		});

		Driver drv = availableDriver.get(0);

		double distFromDriverToCustomer = Math.sqrt(Math.pow(drv.getLocate().getX() - customer.getLocate().getX(), 2)
				+ Math.pow(drv.getLocate().getY() - customer.getLocate().getY(), 2));

		double distance = Math.sqrt(Math.pow(dist.getX() - customer.getLocate().getX(), 2)
				+ Math.pow(dist.getY() - customer.getLocate().getY(), 2));

		double finalDistance = distance + distFromDriverToCustomer * 2;

		double fare = finalDistance * 12000;

		System.out.println("\nTổng quảng đường đi: " + finalDistance);
		System.out.println("Tổng tiền: " + fare);

		while (true) {
			System.out.println("\n1. Xác nhận chuyến đi");
			System.out.println("2. Hủy chuyến đi");
			System.out.print("Nhập lựa chọn: ");

			int choice = Integer.parseInt(scan.nextLine().trim().toLowerCase());

			if (choice == 1) {
				Ride latestRide = rides.peek();
				String newId = "";
				if (latestRide == null)
					newId = "1";
				else {
					String latestId = latestRide.getId();
					newId = Integer.toString(Integer.parseInt(latestId) + 1);
				}
				rides.add(new Ride(newId, customerId, drv.getId(), finalDistance, fare));

				System.out.println("\n✅ Đã xác nhận chuyến đi!");
			} else if (choice == 2) {
				System.out.println("\n✅ Hủy chuyến đi thành công!");
				break;
			} else {
				System.out.println("\n❎ Lựa chọn không hợp lệ. Vui lòng nhập lại!");
			}
		}
	}
}