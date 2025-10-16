# 🚖 MinRide

## 🧩 Giới thiệu
**MinRide** là ứng dụng mô phỏng hệ thống đặt xe được xây dựng bằng ngôn ngữ **Java**, nhằm giúp sinh viên vận dụng và lựa chọn cấu trúc dữ liệu và thuật toán phù hợp trong quá trình xử lý dữ liệu.

---

## 🎯 Mục tiêu dự án
- **Mục tiêu:** Xây dựng ứng dụng mô phỏng hệ thống đặt xe bằng Java.  
- **Môn học:** Cấu trúc dữ liệu và giải thuật  
- **Ngôn ngữ lập trình:** Java  
- **Thành viên nhóm:**  
  - 1.  Nguyễn Thiện Nhân | 24110291
  - 2.  Trần Văn Nhựt Hưng | 24110232
  - 3.  Đinh Phú Sỹ | 24162109
  - 4.  Trần Anh Tuấn | 24162139
  - 5.  Phạm Thị Cẩm Thúy | 24162125

---

## ⚙️ Các chức năng chính
1. **Quản lý tài xế (Drivers)**
   - Hiển thị top K tài xế có rating cao nhất hoặc thấp nhất    
   - Thêm, cập nhật, xóa tài xế  
   - Tìm kiếm tài xế theo ID hoặc tên  
   - Sắp xếp danh sách tài xế theo rating
   - Hiển thị danh sách tất cả tài xế

2. **Quản lý khách hàng (Customers)**
   - Hiển thị top K khách hàng đầu/cuối danh sách  
   - Thêm, cập nhật, xóa khách hàng  
   - Tìm kiếm khách hàng theo ID hoặc tên  
   - Liệt kê danh sách khách hàng trong một quận cụ thể  
      - Hiển thị danh sách tất cả khách hàng

3. **Quản lý chuyến đi (Rides)**
   - Hiển thị toàn bộ danh sách các chuyến đi của tài xế
   - Hiển thị toàn bộ danh sách các chuyến đi
   - Chỉ hiển thị các chuyến đi được xác nhận
   - Chỉ hiển thị các chuyến đi đang chờ xác nhận
   - Chỉ hiển thị các chuyến đi đã hủy
   - Hủy chuyến đi bằng Id
   - Xác nhận tất cả chuyến đi

4. **Tìm tài xế phù hợp**
   - Nhập ID khách hàng và bán kính R  
   - Trả về danh sách tài xế phù hợp nhất

5. **Đặt xe**
   - Đặt xe thủ công (nhập ID tài xế, ID khách hàng và khoảng cách)

6. **Tự động ghép cặp**
   - Hệ thống tự động chọn tài xế phù hợp nhất (gần nhất, rating cao nhất) cho khách hàng  

7. **Hoàn tác thao tác**
   - Hỗ trợ **Undo** thao tác **xóa tài xế hoặc khách hàng** vừa thực hiện (dùng Stack)
---

## 🧠 Cấu trúc dữ liệu & thuật toán đã sử dụng

| Cấu trúc dữ liệu / Thuật toán | Mục đích sử dụng |
|-------------------------------|------------------|
| `HashMap` | Quản lý danh sách khách hàng (theo ID) |
| `PriorityQueue` | Quản lý danh sách tài xế theo `rating` |
| `Queue (LinkedList)` | Quản lý danh sách chuyến đi theo thứ tự thời gian |
| `Stack` | Hỗ trợ hoàn tác thao tác (undo) |
| `MergeSort` | Sắp xếp danh sách tài xế theo điểm đánh giá |
| `Linear Search` | Tìm kiếm tài xế, khách hàng hoặc chuyến đi theo tên/ID |

---

## 📂 Cấu trúc thư mục
MinRide
    /src
        /nowchallenge
            Action.java
            App.java
            Customer.java
            Driver.java
            Location.java
            Main.java
            Ride.java
        module-info.java
    README.md

---

## 🚀 Cách chạy chương trình
1. **Khuyến nghị:** sử dụng **Eclipse** hoặc **IntelliJ IDEA**  
2. Mở project → Chạy file **`Main.java`**  
3. Giao diện **menu hiển thị trên terminal**, nhập số tương ứng để chọn tính năng  
4. Dữ liệu ban đầu được nạp từ hàm **`loadData()`** trong lớp `App`  

---

## 📊 Đánh giá & Nhận xét

### ✅ Ưu điểm:
- Lựa chọn cấu trúc dữ liệu hợp lý → dễ quản lý, dễ mở rộng.  
- Dễ thao tác thêm, xóa, tìm kiếm, sắp xếp.  
- Mô phỏng hệ thống đặt xe thực tế một cách rõ ràng.

### ⚠️ Nhược điểm:
- Chạy trong **terminal**, việc tương tác còn hạn chế.  
- Chưa có lưu trữ dữ liệu tự động ra file sau khi thao tác.

### 🔮 Hướng phát triển:
- Xây dựng giao diện web bằng **MERN Stack (MongoDB, Express, React, Node.js)**.  
- Mở rộng hệ thống tính phí, thêm bản đồ trực quan.  
- Thêm tính năng phân tích lịch sử chuyến đi.