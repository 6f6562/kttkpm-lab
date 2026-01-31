# 📢 Hệ thống Gửi thông báo Đa nền tảng

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

Hệ thống gửi thông báo đa nền tảng được thiết kế với ba Design Patterns chính: **Singleton**, **Abstract Factory**, và **Factory Method**. Hệ thống cho phép gửi thông báo qua Email và SMS trên các nền tảng Web và Mobile với cách định dạng phù hợp cho từng môi trường.

## 📋 Mục lục

- [Tính năng](#-tính-năng)
- [Kiến trúc](#-kiến-trúc)
- [Design Patterns](#-design-patterns)
- [Yêu cầu hệ thống](#-yêu-cầu-hệ-thống)
- [Cài đặt](#-cài-đặt)
- [Cách sử dụng](#-cách-sử-dụng)
- [Ví dụ](#-ví-dụ)
- [Chạy Demo và Tests](#-chạy-demo-và-tests)
- [Cấu trúc dự án](#-cấu-trúc-dự-án)
- [Tài liệu](#-tài-liệu)
- [Tác giả](#-tác-giả)

## ✨ Tính năng

- ✅ **Gửi thông báo đa nền tảng**: Hỗ trợ Web và Mobile
- ✅ **Đa kênh thông báo**: Email và SMS
- ✅ **Định dạng tự động**: Mỗi nền tảng có cách định dạng riêng
- ✅ **Quản lý cài đặt tập trung**: Singleton Pattern đảm bảo cấu hình nhất quán
- ✅ **Dễ mở rộng**: Có thể thêm nền tảng hoặc loại thông báo mới dễ dàng
- ✅ **Thread-safe**: Đảm bảo an toàn trong môi trường đa luồng
- ✅ **Validation đầy đủ**: Kiểm tra input và xử lý lỗi tốt

## 🏗️ Kiến trúc

Hệ thống được thiết kế theo kiến trúc phân lớp với các thành phần chính:

```
┌─────────────────────────────────────────┐
│           Client Layer                  │
│  (NotificationService, Demo, Tests)    │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│        Factory Layer                     │
│  (Abstract Factory + Factory Method)    │
│  - NotificationFactory                  │
│  - WebFactory, MobileFactory            │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│        Product Layer                    │
│  - Notification (Interface)             │
│  - WebEmail, WebSMS                     │
│  - MobileEmail, MobileSMS               │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│      Configuration Layer               │
│  - SettingManager (Singleton)          │
└─────────────────────────────────────────┘
```

## 🎯 Design Patterns

### 1. Singleton Pattern - SettingManager

**Mục đích**: Đảm bảo chỉ có một instance quản lý cài đặt hệ thống trong toàn bộ ứng dụng.

**Lợi ích**:
- Quản lý cấu hình tập trung và nhất quán
- Tiết kiệm bộ nhớ
- Thread-safe với double-checked locking
- Điểm truy cập toàn cục

**Ví dụ**:
```java
SettingManager settings = SettingManager.getInstance();
settings.setLogEnabled(true);
```

### 2. Abstract Factory Pattern - Platform Factory

**Mục đích**: Tạo ra các nhóm đối tượng liên quan (Email/SMS) cho từng nền tảng cụ thể.

**Cấu trúc**:
- **Abstract Factory**: `NotificationFactory` interface
- **Concrete Factories**: `WebFactory`, `MobileFactory`

**Lợi ích**:
- Đảm bảo tính nhất quán (WebEmail + WebSMS, MobileEmail + MobileSMS)
- Tách biệt logic tạo đối tượng
- Dễ mở rộng thêm nền tảng mới

**Ví dụ**:
```java
NotificationFactory webFactory = new WebFactory();
Notification email = webFactory.createNotification("EMAIL"); // → WebEmail
Notification sms = webFactory.createNotification("SMS");      // → WebSMS
```

### 3. Factory Method Pattern - Notification Type Selection

**Mục đích**: Cho phép Factory quyết định cách tạo đối tượng cụ thể dựa trên loại thông báo.

**Cơ chế**: Method `createNotification(String type)` nhận "EMAIL" hoặc "SMS" và trả về đối tượng tương ứng.

**Lợi ích**:
- Linh hoạt trong việc chọn loại sản phẩm
- Encapsulation logic tạo đối tượng
- Dễ mở rộng thêm loại thông báo mới

**Ví dụ**:
```java
// Factory Method được tích hợp trong createNotification()
NotificationFactory factory = new MobileFactory();
Notification notification = factory.createNotification("EMAIL"); // → MobileEmail
```

## 💻 Yêu cầu hệ thống

- **Java**: JDK 21 hoặc cao hơn
- **Maven**: 3.6+ (khuyến nghị) hoặc có thể compile bằng javac
- **IDE**: IntelliJ IDEA, Eclipse, VS Code (tùy chọn)

## 📦 Cài đặt

### Clone repository

```bash
git clone <repository-url>
cd lab2-nguyenggiabao-22691861-310126
```

### Compile project

**Với Maven:**
```bash
mvn clean compile
```

**Không có Maven:**
```bash
# Tạo thư mục output
mkdir -p target/classes

# Compile tất cả Java files
javac -d target/classes -sourcepath src/main/java \
  src/main/java/iuh/fit/notification/**/*.java \
  src/main/java/iuh/fit/notification/**/**/*.java
```

## 🚀 Cách sử dụng

### Sử dụng NotificationService (Khuyến nghị)

Cách đơn giản nhất để gửi thông báo:

```java
import iuh.fit.notification.NotificationService;

// Tạo service
NotificationService service = new NotificationService();

// Gửi Email qua Web
service.sendNotification("WEB", "EMAIL", "Chào mừng bạn đến với hệ thống!");

// Gửi SMS qua Mobile
service.sendNotification("MOBILE", "SMS", "Bạn có tin nhắn mới");
```

### Sử dụng Factory trực tiếp

Cho phép kiểm soát chi tiết hơn:

```java
import iuh.fit.notification.factory.WebFactory;
import iuh.fit.notification.factory.NotificationFactory;
import iuh.fit.notification.product.Notification;

// Tạo Factory cho nền tảng Web
NotificationFactory webFactory = new WebFactory();

// Tạo notification bằng Factory Method
Notification email = webFactory.createNotification("EMAIL");
Notification sms = webFactory.createNotification("SMS");

// Gửi thông báo
email.send("Nội dung email");
sms.send("Nội dung SMS");
```

### Quản lý cài đặt hệ thống

```java
import iuh.fit.notification.singleton.SettingManager;

// Lấy instance (luôn trả về cùng một object)
SettingManager settings = SettingManager.getInstance();

// Cấu hình
settings.setLogEnabled(true);   // Bật log
settings.setLogEnabled(false);  // Tắt log

// Kiểm tra cài đặt
boolean isLogEnabled = settings.isLogEnabled();

// Xem thông tin cài đặt
String info = settings.getSettingsInfo();
```

### Tạo Factory cho các nền tảng khác nhau

```java
import iuh.fit.notification.factory.WebFactory;
import iuh.fit.notification.factory.MobileFactory;

// Web Factory
NotificationFactory webFactory = new WebFactory();
Notification webEmail = webFactory.createNotification("EMAIL");
webEmail.send("Email từ Web");

// Mobile Factory
NotificationFactory mobileFactory = new MobileFactory();
Notification mobileSMS = mobileFactory.createNotification("SMS");
mobileSMS.send("SMS từ Mobile");
```

## 📝 Ví dụ

### Ví dụ 1: Gửi thông báo đơn giản

```java
NotificationService service = new NotificationService();

// Gửi Email qua Web
service.sendNotification("WEB", "EMAIL", "Xin chào từ Web!");

// Gửi SMS qua Mobile
service.sendNotification("MOBILE", "SMS", "Xin chào từ Mobile!");
```

**Kết quả:**
```
📧 [WEB EMAIL] Sending email:
   Format: HTML
   Content: Xin chào từ Web!
   HTML Body: <html><body><h2>Email Notification</h2><p>Xin chào từ Web!</p></body></html>

💬 [MOBILE SMS] Sending SMS:
   Format: Native SMS
   Content: Xin chào từ Mobile!
   Native Format: 📱 Mobile SMS
Xin chào từ Mobile!
```

### Ví dụ 2: Sử dụng Singleton để cấu hình

```java
// Cấu hình hệ thống
SettingManager settings = SettingManager.getInstance();
settings.setLogEnabled(false); // Tắt log để output sạch hơn

// Gửi thông báo
NotificationService service = new NotificationService();
service.sendNotification("WEB", "EMAIL", "Thông báo không có log");
```

### Ví dụ 3: Xử lý lỗi

```java
NotificationFactory factory = new WebFactory();

try {
    Notification notification = factory.createNotification("EMAIL");
    notification.send("Test message");
} catch (IllegalArgumentException e) {
    System.err.println("Lỗi: " + e.getMessage());
}

// Thử với type không hợp lệ
try {
    factory.createNotification("INVALID"); // Sẽ throw IllegalArgumentException
} catch (IllegalArgumentException e) {
    System.err.println("Type không hợp lệ: " + e.getMessage());
}
```

### Ví dụ 4: Không phân biệt hoa thường

```java
NotificationFactory factory = new WebFactory();

// Tất cả đều hoạt động
factory.createNotification("EMAIL");
factory.createNotification("email");
factory.createNotification("Email");
factory.createNotification("SMS");
factory.createNotification("sms");
```

## 🧪 Chạy Demo và Tests

### Chạy Demo

**Với Maven:**
```bash
mvn exec:java -Dexec.mainClass="iuh.fit.notification.demo.NotificationDemo"
```

**Không có Maven:**
```bash
# Đảm bảo đã compile trước
java -cp target/classes iuh.fit.notification.demo.NotificationDemo
```

**Demo sẽ hiển thị:**
- ✅ Chứng minh Singleton Pattern (chỉ có một instance)
- ✅ Chứng minh Abstract Factory Pattern (Factory tạo đúng products)
- ✅ Chứng minh Factory Method Pattern (chọn đúng loại thông báo)
- ✅ Demo tích hợp tất cả patterns

### Chạy Tests

**Với Maven:**
```bash
mvn test
```

**Xem test report:**
```bash
mvn surefire-report:report
# Mở target/site/surefire-report.html trong browser
```

**Test coverage bao gồm:**
- ✅ Singleton Pattern: Kiểm tra chỉ có một instance
- ✅ Abstract Factory: Kiểm tra Factory tạo đúng products
- ✅ Factory Method: Kiểm tra chọn đúng loại thông báo
- ✅ Notification Service: Kiểm tra tích hợp tất cả patterns
- ✅ Exception handling: Kiểm tra xử lý lỗi
- ✅ Validation: Kiểm tra input validation

### Package project

```bash
mvn clean package
```

File JAR sẽ được tạo tại: `target/lab2-nguyenggiabao-22691861-1.0-SNAPSHOT.jar`

## 📁 Cấu trúc dự án

```
lab2-nguyenggiabao-22691861-310126/
│
├── src/
│   ├── main/java/iuh/fit/notification/
│   │   ├── singleton/
│   │   │   └── SettingManager.java          # Singleton Pattern
│   │   │
│   │   ├── factory/
│   │   │   ├── NotificationFactory.java      # Abstract Factory Interface
│   │   │   ├── WebFactory.java              # Concrete Factory cho Web
│   │   │   └── MobileFactory.java           # Concrete Factory cho Mobile
│   │   │
│   │   ├── product/
│   │   │   ├── Notification.java            # Abstract Product Interface
│   │   │   ├── WebEmail.java                # Concrete Product - Web Email
│   │   │   ├── WebSMS.java                  # Concrete Product - Web SMS
│   │   │   ├── MobileEmail.java             # Concrete Product - Mobile Email
│   │   │   └── MobileSMS.java               # Concrete Product - Mobile SMS
│   │   │
│   │   ├── NotificationService.java         # Service class tích hợp tất cả
│   │   │
│   │   └── demo/
│   │       └── NotificationDemo.java        # Demo class với các ví dụ
│   │
│   └── test/java/iuh/fit/notification/
│       └── NotificationTest.java            # Unit tests với JUnit 5
│
├── pom.xml                                   # Maven configuration
├── README.md                                 # File này
├── DOCUMENTATION.md                          # Tài liệu chi tiết về thiết kế
└── .gitignore                               # Git ignore rules
```

### Giải thích các package

- **`singleton/`**: Chứa SettingManager - Singleton Pattern
- **`factory/`**: Chứa các Factory classes - Abstract Factory và Factory Method Patterns
- **`product/`**: Chứa các Notification products (Email, SMS cho từng nền tảng)
- **`demo/`**: Chứa demo class để chứng minh hoạt động của hệ thống
- **`test/`**: Chứa unit tests

## 📚 Tài liệu

### Tài liệu chi tiết

Xem file [DOCUMENTATION.md](DOCUMENTATION.md) để biết thêm chi tiết về:
- Mô tả bài toán và vấn đề cần giải quyết
- Lý do áp dụng từng Design Pattern
- Lợi ích chi tiết của từng pattern
- Luồng hoạt động chi tiết với sequence diagram
- Sự tương tác giữa các thành phần
- Class diagram và giải thích

### Các Design Patterns được sử dụng

1. **Singleton Pattern**
   - Đảm bảo chỉ có một instance SettingManager
   - Thread-safe với double-checked locking
   - Lazy initialization

2. **Abstract Factory Pattern**
   - Tạo các nhóm sản phẩm theo nền tảng
   - WebFactory tạo WebEmail và WebSMS
   - MobileFactory tạo MobileEmail và MobileSMS

3. **Factory Method Pattern**
   - Tích hợp trong method `createNotification(String type)`
   - Cho phép Factory quyết định tạo Product nào
   - Hỗ trợ "EMAIL" và "SMS" (không phân biệt hoa thường)

## 🔧 Troubleshooting

### Lỗi: "mvn: command not found"

**Giải pháp**: Cài đặt Maven hoặc sử dụng javac để compile thủ công.

### Lỗi: "java.lang.ClassNotFoundException"

**Giải pháp**: Đảm bảo đã compile project và classpath đúng:
```bash
java -cp target/classes:<path-to-dependencies> <main-class>
```

### Lỗi: "Invalid notification type"

**Giải pháp**: Chỉ sử dụng "EMAIL" hoặc "SMS" (không phân biệt hoa thường).

### Lỗi: "Invalid platform"

**Giải pháp**: Chỉ sử dụng "WEB" hoặc "MOBILE" (không phân biệt hoa thường).

## 🎓 Học tập

Dự án này là một ví dụ điển hình về:
- Cách kết hợp nhiều Design Patterns trong một hệ thống
- Áp dụng SOLID principles
- Thiết kế hệ thống dễ mở rộng và bảo trì
- Best practices trong Java development

## 📄 License

Dự án này được tạo cho mục đích học tập và nghiên cứu.

## 👤 Tác giả

**Nguyễn Gia Bảo**
- Mã sinh viên: 22691861
- Môn học: Kiến trúc và Thiết kế Phần mềm (KTPM)
- Lab: Lab 2 - Design Patterns

## 🙏 Lời cảm ơn

Cảm ơn thầy/cô và các bạn đã hỗ trợ trong quá trình học tập và phát triển dự án này.

---

⭐ Nếu bạn thấy dự án này hữu ích, hãy cho một star!
