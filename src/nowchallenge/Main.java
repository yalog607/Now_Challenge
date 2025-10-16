package nowchallenge;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		App app = new App();
		app.loadData();
		int choice;

		do {
			printMainMenu();
			choice = getChoice(scan);

			switch (choice) {
			case 0:
				System.out.println("\n👋 MinRide xin cảm ơn.");
				break;
			case 1:
				processDriverCommand(scan, app);
				break;
			case 2:
				processCustomerCommand(scan, app);
				break;
			case 3:
				processRideCommand(scan, app);
				break;
			case 4:
				processFindDriverCommand(scan, app);
				break;
			case 5:
				processBookManual(scan, app);
				break;
			case 6:
				processBookAuto(scan, app);
				break;
			case 7:
				app.undo();
				break;
			default:
				System.out.println("\n❎ Lựa chọn không hợp lệ. Vui lòng nhập lại!");
				break;
			}
		} while (choice != 0);

	}
	
	static void processBookAuto(Scanner scan, App app) {
		System.out.println("\nNhập id khách hàng: ");
        String custId = scan.nextLine();

        System.out.println("Nhập điểm đến (x): ");
        Double x = Double.parseDouble(scan.nextLine());

        System.out.println("Nhập điểm đến (y): ");
        Double y = Double.parseDouble(scan.nextLine());
        
        app.autoMatch(scan, custId, new Location(x, y));
	}
	
	static void processBookManual(Scanner scan, App app) {
		System.out.println("\nNhập id khách hàng: ");
        String custId = scan.nextLine();

        System.out.println("Nhập id tài xế: ");
        String drvId = scan.nextLine();

        System.out.println("Nhập quãng đường đi: ");
        Double dist = Double.parseDouble(scan.nextLine());
        
        app.orderGrab(scan, custId, drvId, dist);
	}

	static void processDriverCommand(Scanner scan, App app) {
		int choice;
		do {
			printDriverMenu();
			choice = getChoice(scan);;

			switch (choice) {
			case 0:
				break;
				
			case 1:
				System.out.println("\nNhập k (số lượng hiển thị): ");
				int k = Integer.parseInt(scan.nextLine());
				app.showTopKDriver(k);
				break;

			case 2:
				System.out.println("\nNhập thông tin cho tài xế\n");

				System.out.println("Id: ");
				String idForAdd = scan.nextLine();
				System.out.println("Họ tên: ");
				String nameForAdd = scan.nextLine();
				System.out.println("Điểm đánh giá: ");
				Double ratingForAdd = Double.parseDouble(scan.nextLine());
				System.out.println("Vị trí (x): ");
				Double xForAdd = Double.parseDouble(scan.nextLine());
				System.out.println("Vị trí (y): ");
				Double yForAdd = Double.parseDouble(scan.nextLine());

				app.addDriver(new Driver(idForAdd, nameForAdd, ratingForAdd, new Location(xForAdd, yForAdd)));

				break;

			case 3:
				System.out.print("\nNhập tên tài xế muốn cập nhật thông tin: ");
				String nameForUpdate = scan.nextLine();
				app.updateInfoForDriver(scan, nameForUpdate);

				break;

			case 4:
				System.out.print("\nNhập id tài xế muốn xóa: ");
				String idForRemove = scan.nextLine();
				app.removeDriverById(idForRemove);
				break;

			case 5:
				System.out.print("\nNhập tên hoặc id của tài xế muốn tìm: ");
				String inputForFind = scan.nextLine();
				Driver driverFound = app.findDriverById(inputForFind);
				if (driverFound == null) {
					ArrayList<Driver> drvListFound = app.findDriverByName(inputForFind);
					if (drvListFound.isEmpty()) {
						System.out.print("\nKhông tìm thấy tài xế có tên hoặc id " + inputForFind);
						return;
					}
					System.out.println("\nDanh sách những tài xế có tên " + inputForFind + ": ");
					int index = 1;
					for(Driver drv: drvListFound) {
						System.out.print(index++ + ". ");
						drv.printInfo();
					}
				} else {
					System.out.print("\nĐã tìm thấy tài xế có id " + inputForFind);
					driverFound.printInfo();
				}
				break;

			case 6:
				app.sortDrivers();
				System.out.println("\n✅ Danh sách tài xế đã được sắp xếp");
				break;

			case 7:
				app.printAllDrivers();
				break;

			default:
				System.out.println("n❎ Lựa chọn không hợp lệ. Vui lòng nhập lại!");
				break;
			}
		} while (choice != 0);
	}

	static void processCustomerCommand(Scanner scan, App app) {
		int choice;
		do {
			printCustomerMenu();
			choice = getChoice(scan);;

			switch (choice) {
			case 0:
				break;

			case 1:
				System.out.print("\nNhập k (số lượng hiển thị): ");
                int k = Integer.parseInt(scan.nextLine());
                System.out.print("Hiển thị đầu danh sách (1) hay cuối danh sách (0): ");
                boolean isTop = Integer.parseInt(scan.nextLine()) == 1 ? true : false;
                app.showTopKCustomer(k, isTop);
                break;

			case 2:
				System.out.println("\nNhập thông tin khách hàng mới:");
                System.out.print("ID: ");
                String id = scan.nextLine();
                System.out.print("Tên: ");
                String name = scan.nextLine();
                System.out.print("Quận: ");
                String district = scan.nextLine();
                System.out.print("Tọa độ X: ");
                double x = Double.parseDouble(scan.nextLine());
                System.out.print("Tọa độ Y: ");
                double y = Double.parseDouble(scan.nextLine());
                app.addCustomer(new Customer(id, name, new Location(x, y, district)));
				break;

			case 3:
				System.out.print("\nNhập ID khách hàng cần cập nhật: ");
                String idForUpdate = scan.nextLine();
                app.updateInfoForCustomer(scan, idForUpdate);
                break;

			case 4:
				System.out.print("\nNhập ID khách hàng muốn xóa: ");
                String idForRemove = scan.nextLine();
                app.removeCustomerById(idForRemove);
				break;

			case 5:
				System.out.print("\nNhập tên hoặc ID khách hàng cần tìm: ");
                String input = scan.nextLine();
                Customer found = app.findCustomerById(input);
                if (found != null) {
                    System.out.println("\n✅ Đã tìm thấy khách hàng:");
                    found.printInfo();
                } else {
                    ArrayList<Customer> foundList = app.findCustomerByName(input);
                    if (foundList.isEmpty()) {
                        System.out.println("❎ Không tìm thấy khách hàng nào có tên hoặc ID " + input);
                    } else {
                        System.out.println("\nDanh sách khách hàng có tên " + input + ":");
                        int i = 1;
                        for (Customer c : foundList) {
                            System.out.print(i++ + ". ");
                            c.printInfo();
                        }
                    }
                }
				break;

			case 6:
				System.out.print("\nNhập tên quận cần liệt kê khách hàng: ");
                String dis = scan.nextLine();
                app.showCustomerInDistrict(scan, dis);
				break;

			case 7:
				app.printAllCustomers();
				break;
				
			default:
				System.out.println("n❎ Lựa chọn không hợp lệ. Vui lòng nhập lại!");
			}
		} while (choice != 0);
	}

	static void processRideCommand(Scanner scan, App app) {
		int choice;
		do {
			printRideMenu();
			choice = getChoice(scan);;

			switch (choice) {
			case 0:
				break;

			case 1:
				System.out.print("\nNhập ID tài xế: ");
                String driverId = scan.nextLine();
                app.showRidesByDriverId(driverId);
				break;
				
			case 2: 
				app.showAllRides();
				break;

			case 3:
				app.showConfirmedRides();
				break;

			case 4:
				app.showPendingRides();
				break;

			case 5:
				app.showCanceledRides();
				break;
				
			case 6:
				System.out.print("\nNhập ID chuyến đi: ");
                String rideId = scan.nextLine();
				app.cancelRide(rideId);
				break;
				
			case 7:
				app.confirmAllRides();
				break;

			default:
				System.out.println("n❎ Lựa chọn không hợp lệ. Vui lòng nhập lại!");
			}
		} while (choice != 0);
	}
	
	static void processFindDriverCommand(Scanner scan, App app) {
	    System.out.print("\nNhập ID khách hàng: ");
	    String customerId = scan.nextLine();
	    Customer customer = app.findCustomerById(customerId);
	    if (customer == null) return;

	    System.out.print("Nhập bán kính R (đơn vị km): ");
	    double radius = Double.parseDouble(scan.nextLine());

	    ArrayList<Driver> nearbyDrivers = app.findAvailableDriver(customerId, radius);

	    if (!nearbyDrivers.isEmpty()) {
	        System.out.println("\n✅ Các tài xế ở gần bạn: ");
	        int i = 1;
	        for(Driver d: nearbyDrivers) {
	        	System.out.print(i + ". ");
	        	d.printInfo();
	        }
	        return;
	    }
	}

	static void printMainMenu() {
		System.out.println("\n----- MinRide Xin Chào ------");
		System.out.println("1. Quản lý tài xế");
		System.out.println("2. Quản lý khách hàng");
		System.out.println("3. Quản lý chuyến đi");
		System.out.println("4. Tìm tài xế phù hợp cho khách hàng được chỉ định");
		System.out.println("5. Đặt xe thủ công");
		System.out.println("6. Đặt xe tự động");
		System.out.println("7. Hoàn tác thao tác XÓA vừa thực hiện");
		System.out.println("0. Thoát\n");

	}

	static void printDriverMenu() {
		System.out.println("\n----- Quản lý tài xế ------");
		System.out.println("1. Hiển thị top k danh sách");
		System.out.println("2. Thêm mới tài xế");
		System.out.println("3. Cập nhật thông tin tài xế");
		System.out.println("4. Xóa tài xế");
		System.out.println("5. Tìm kiếm tài xế");
		System.out.println("6. Sắp xếp danh sách theo điểm đánh giá");
		System.out.println("7. Hiển thị toàn bộ danh sách tài xế");
		System.out.println("0. Quay lại\n");
	}

	static void printCustomerMenu() {
		System.out.println("\n----- Quản lý khách hàng ------");
		System.out.println("1. Hiển thị top k danh sách theo 2 chiều");
		System.out.println("2. Thêm mới khách hàng");
		System.out.println("3. Cập nhật thông tin khách hàng");
		System.out.println("4. Xóa khách hàng");
		System.out.println("5. Tìm kiếm khách hàng");
		System.out.println("6. Liệt kê khách hàng trong quận cụ thể");
		System.out.println("7. Hiển thị toàn bộ danh sách khách hàng");
		System.out.println("0. Quay lại\n");
	}

	static void printRideMenu() {
		System.out.println("\n----- Quản lý chuyến đi ------");
		System.out.println("1. Hiển thị toàn bộ danh sách các chuyến đi của tài xế");
		System.out.println("2. Hiển thị toàn bộ danh sách các chuyến đi");
		System.out.println("3. Chỉ hiển thị các chuyến đi được xác nhận");
		System.out.println("4. Chỉ hiển thị các chuyến đi đang chờ xác nhận");
		System.out.println("5. Chỉ hiển thị các chuyến đi đã hủy");
		System.out.println("6. Hủy chuyến đi bằng Id");
		System.out.println("7. Xác nhận tất cả chuyến đi");
		System.out.println("0. Quay lại\n");
	}
	
	static int getChoice(Scanner scan) {
		int choice;
		while(true) {
			try {
				System.out.print("\n👉 Nhập lựa chọn: ");
				String choiceStr = scan.nextLine();
				choice = Integer.parseInt(choiceStr);
				if (choice >= 0 && choice <= 11) {
					return choice;
				} else {
					throw new NumberFormatException();
				}
			} catch(NumberFormatException e) {
				System.out.println("Nhập lại số hợp lệ từ 0 -> 7");
			}
		}
	}
}
