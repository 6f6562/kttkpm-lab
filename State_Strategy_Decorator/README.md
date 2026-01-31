# Dự án Design Patterns: State, Strategy, Decorator

## Mô tả

Dự án này minh họa việc áp dụng 3 Design Patterns trong hệ thống thương mại điện tử:
- **State Pattern**: Quản lý trạng thái đơn hàng
- **Strategy Pattern**: Tính toán thuế đa dạng
- **Decorator Pattern**: Hệ thống thanh toán với tính năng động

## Cấu trúc dự án

```
src/
├── main/java/iuh/fit/
│   ├── state/          # State Pattern
│   │   ├── OrderState.java
│   │   ├── NewOrderState.java
│   │   ├── ProcessingState.java
│   │   ├── DeliveredState.java
│   │   ├── CanceledState.java
│   │   └── Order.java
│   ├── strategy/       # Strategy Pattern
│   │   ├── TaxStrategy.java
│   │   ├── VatTax.java
│   │   ├── ConsumptionTax.java
│   │   ├── LuxuryTax.java
│   │   ├── TaxCalculator.java
│   │   └── Product.java
│   ├── decorator/      # Decorator Pattern
│   │   ├── Payment.java
│   │   ├── CreditCardPayment.java
│   │   ├── PayPalPayment.java
│   │   ├── PaymentDecorator.java
│   │   ├── ProcessingFeeDecorator.java
│   │   └── DiscountDecorator.java
│   └── Demo.java       # Demo class
└── test/java/iuh/fit/
    ├── state/
    │   └── OrderStateTest.java
    ├── strategy/
    │   └── TaxStrategyTest.java
    ├── decorator/
    │   └── PaymentDecoratorTest.java
    └── IntegrationTest.java
```

## Yêu cầu

- Java 23 hoặc cao hơn
- Maven 3.6+ (để build và test)

## Cách chạy

### 1. Compile project

```bash
mvn clean compile
```

### 2. Chạy Demo

```bash
mvn exec:java -Dexec.mainClass="iuh.fit.Demo"
```

Hoặc nếu không có Maven:

```bash
cd src/main/java
javac iuh/fit/**/*.java
java iuh.fit.Demo
```

### 3. Chạy Tests

```bash
mvn test
```

## Các tính năng chính

### State Pattern - Quản lý Đơn hàng

- **NewOrderState**: Kiểm tra và xác thực đơn hàng mới
- **ProcessingState**: Đóng gói và chuẩn bị giao hàng
- **DeliveredState**: Hoàn thành giao hàng
- **CanceledState**: Hủy đơn hàng và hoàn tiền

**Ví dụ sử dụng:**
```java
Order order = new Order("ORD-001");
order.addProduct(new Product("Laptop", 15000000, new VatTax()));
order.nextStep(); // Chuyển sang ProcessingState
order.nextStep(); // Chuyển sang DeliveredState
```

### Strategy Pattern - Tính toán Thuế

- **VatTax**: Thuế VAT 10%
- **ConsumptionTax**: Thuế tiêu thụ đặc biệt 20%
- **LuxuryTax**: Thuế xa xỉ 30%

**Ví dụ sử dụng:**
```java
Product product = new Product("Laptop", 1000000, new VatTax());
double finalPrice = product.getFinalPrice(); // 1,100,000 VNĐ

// Thay đổi loại thuế tại runtime
product.setTaxStrategy(new LuxuryTax());
double newPrice = product.getFinalPrice(); // 1,300,000 VNĐ
```

### Decorator Pattern - Hệ thống Thanh toán

- **CreditCardPayment**: Thanh toán bằng thẻ
- **PayPalPayment**: Thanh toán bằng PayPal
- **ProcessingFeeDecorator**: Thêm phí xử lý
- **DiscountDecorator**: Áp dụng mã giảm giá

**Ví dụ sử dụng:**
```java
Payment payment = new CreditCardPayment(1000000);
payment = new ProcessingFeeDecorator(payment, 50000);
payment = new DiscountDecorator(payment, 100000);
double finalCost = payment.cost(); // 950,000 VNĐ
```

## Kết quả Demo

Khi chạy `Demo.java`, bạn sẽ thấy:

1. **Demo State Pattern**: Luồng xử lý đơn hàng từ trạng thái mới đến đã giao
2. **Demo Strategy Pattern**: Tính toán thuế với các loại thuế khác nhau
3. **Demo Decorator Pattern**: Thanh toán với các decorator kết hợp
4. **Demo Integration**: Tích hợp tất cả các patterns trong một luồng hoàn chỉnh

## Test Coverage

Dự án bao gồm các unit tests cho:
- ✅ State Pattern: Chuyển đổi trạng thái, hủy đơn hàng
- ✅ Strategy Pattern: Tính toán thuế, thay đổi strategy tại runtime
- ✅ Decorator Pattern: Kết hợp decorators, tính toán chi phí
- ✅ Integration Tests: Tương tác giữa các patterns

## Tác giả

Sinh viên: [Tên sinh viên]
MSSV: [MSSV]
Môn học: Kiến trúc và Thiết kế Phần mềm

## License

Dự án này được tạo cho mục đích học tập.
