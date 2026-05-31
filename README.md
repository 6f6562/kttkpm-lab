# Bài tập thực hành — Design Patterns (KTPM)

Repository chứa 2 bài lab minh họa các Design Pattern trong Java.

| Bài | Thư mục | Patterns |
|-----|---------|----------|
| Bài 1 | [`singleton_factory/`](singleton_factory/) | Singleton, Abstract Factory, Factory Method |
| Bài 2 | [`State_Strategy_Decorator/`](State_Strategy_Decorator/) | State, Strategy, Decorator |

**Yêu cầu:** Java 21+, Maven 3.6+

---

## Bài 1 — Hệ thống gửi thông báo đa nền tảng

> Chi tiết: [`singleton_factory/README.md`](singleton_factory/README.md)

Hệ thống gửi thông báo Email/SMS trên nền tảng Web và Mobile, kết hợp 3 pattern:

- **Singleton** — `SettingManager`: quản lý cấu hình tập trung, thread-safe
- **Abstract Factory** — `WebFactory`, `MobileFactory`: tạo nhóm sản phẩm theo nền tảng
- **Factory Method** — `createNotification(type)`: chọn loại thông báo (EMAIL/SMS)

```java
NotificationService service = new NotificationService();
service.sendNotification("WEB", "EMAIL", "Xin chào từ Web!");
service.sendNotification("MOBILE", "SMS", "Xin chào từ Mobile!");
```

**Chạy thử:**

```bash
cd singleton_factory
mvn clean compile
mvn exec:java -Dexec.mainClass="iuh.fit.notification.demo.NotificationDemo"
mvn test
```

---

## Bài 2 — Hệ thống thương mại điện tử

> Chi tiết: [`State_Strategy_Decorator/README.md`](State_Strategy_Decorator/README.md)

Minh họa 3 pattern trong bối cảnh e-commerce:

- **State** — quản lý trạng thái đơn hàng: New → Processing → Delivered / Canceled
- **Strategy** — tính thuế linh hoạt: VAT (10%), Consumption (20%), Luxury (30%)
- **Decorator** — thanh toán mở rộng: phí xử lý, mã giảm giá trên CreditCard/PayPal

```java
Order order = new Order("ORD-001");
order.addProduct(new Product("Laptop", 15000000, new VatTax()));
order.nextStep(); // → Processing
order.nextStep(); // → Delivered

Payment payment = new CreditCardPayment(1000000);
payment = new ProcessingFeeDecorator(payment, 50000);
payment = new DiscountDecorator(payment, 100000);
```

**Chạy thử:**

```bash
cd State_Strategy_Decorator
mvn clean compile
mvn exec:java -Dexec.mainClass="iuh.fit.Demo"
mvn test
```

---

## Minh chứng

### Bài 1

**1. RabbitMQ Management — queue `order_queue`**

![RabbitMQ order_queue](img/1_1.png)

**2. ProducerApp — gửi 3 message Order**

![ProducerApp output](img/1_2.png)

**3. ConsumerApp — nhận và xử lý message**

![ConsumerApp output](img/1_3.png)

**4. Docker — container RabbitMQ đang chạy**

![Docker RabbitMQ](img/1_4.png)

**5. Message Queue Simulator — gửi/nhận message qua giao diện web**

![Queue Simulator - Peek](img/1_5.png)

**6. Message Queue Simulator — consume message thành công**

![Queue Simulator - Consume](img/1_6.png)

### Bài 2

**1. Đăng nhập admin — `POST /auth/login`**

![Login admin](img/2_1.png)

**2. Truy cập profile — `GET /auth/profile` (Bearer Token)**

![Profile admin](img/2_2.png)

**3. Truy cập route admin — `GET /auth/admin` (200 OK)**

![Admin route](img/2_3.png)

**4. Refresh token — `POST /auth/refresh`**

![Refresh token](img/2_4.png)

**5. Đăng nhập guest — `POST /auth/login` (role guest)**

![Login guest](img/2_5.png)

**6. Profile guest — `GET /auth/profile`**

![Profile guest](img/2_6.png)

**7. Từ chối truy cập admin — `GET /auth/admin` (403 Forbidden)**

![Access denied](img/2_7.png)

---

## Tác giả

**Nguyễn Gia Bảo** — MSSV: 22691861  
Môn học: Kiến trúc và Thiết kế Phần mềm (KTPM)
