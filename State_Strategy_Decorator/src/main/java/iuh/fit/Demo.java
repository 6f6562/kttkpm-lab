package iuh.fit;

import iuh.fit.decorator.*;
import iuh.fit.state.Order;
import iuh.fit.strategy.*;

/**
 * Demo class để minh họa các Design Patterns:
 * - State Pattern: Quản lý trạng thái đơn hàng
 * - Strategy Pattern: Tính toán thuế
 * - Decorator Pattern: Hệ thống thanh toán
 */
public class Demo {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("    DEMO DESIGN PATTERNS: STATE, STRATEGY, DECORATOR");
        System.out.println("═══════════════════════════════════════════════════════════\n");
        
        demoStatePattern();
        System.out.println("\n");
        
        demoStrategyPattern();
        System.out.println("\n");
        
        demoDecoratorPattern();
        System.out.println("\n");
        
        demoIntegration();
    }
    
    /**
     * Demo State Pattern - Quản lý trạng thái đơn hàng
     */
    private static void demoStatePattern() {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║          DEMO 1: STATE PATTERN - QUẢN LÝ ĐƠN HÀNG         ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        
        // Tạo đơn hàng mới
        Order order = new Order("ORD-2026-001");
        System.out.println("✓ Tạo đơn hàng mới: " + order.getOrderId());
        System.out.println("  Trạng thái hiện tại: " + order.getCurrentState().getStateName() + "\n");
        
        // Thêm sản phẩm
        order.addProduct(new Product("Laptop Dell", 15000000, new VatTax()));
        order.addProduct(new Product("Chuột không dây", 500000, new VatTax()));
        System.out.println("✓ Đã thêm sản phẩm vào đơn hàng");
        System.out.println("  Tổng tiền: " + String.format("%,.0f", order.getTotalAmount()) + " VNĐ\n");
        
        // Chuyển sang bước tiếp theo
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Bước 1: Xử lý đơn hàng mới");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        order.nextStep();
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Bước 2: Đóng gói và xử lý");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        order.nextStep();
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Bước 3: Hoàn thành giao hàng");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        order.nextStep();
        
        // Demo hủy đơn hàng
        System.out.println("\n");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("DEMO: Hủy đơn hàng");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Order canceledOrder = new Order("ORD-2026-002");
        canceledOrder.addProduct(new Product("Sản phẩm", 1000000, new VatTax()));
        canceledOrder.nextStep(); // Chuyển sang ProcessingState
        canceledOrder.cancel("Khách hàng yêu cầu hủy");
    }
    
    /**
     * Demo Strategy Pattern - Tính toán thuế
     */
    private static void demoStrategyPattern() {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║        DEMO 2: STRATEGY PATTERN - TÍNH TOÁN THUẾ          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        
        double basePrice = 1000000;
        System.out.println("Giá gốc sản phẩm: " + String.format("%,.0f", basePrice) + " VNĐ\n");
        
        // Demo với các loại thuế khác nhau
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("1. Sản phẩm thông thường (VAT 10%)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Product normalProduct = new Product("Sản phẩm thông thường", basePrice, new VatTax());
        System.out.println("  Thuế: " + normalProduct.getTaxInfo());
        System.out.println("  Số tiền thuế: " + String.format("%,.0f", normalProduct.getTaxAmount()) + " VNĐ");
        System.out.println("  Giá sau thuế: " + String.format("%,.0f", normalProduct.getFinalPrice()) + " VNĐ\n");
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("2. Rượu (Thuế tiêu thụ đặc biệt 20%)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Product alcoholProduct = new Product("Rượu cao cấp", basePrice, new ConsumptionTax());
        System.out.println("  Thuế: " + alcoholProduct.getTaxInfo());
        System.out.println("  Số tiền thuế: " + String.format("%,.0f", alcoholProduct.getTaxAmount()) + " VNĐ");
        System.out.println("  Giá sau thuế: " + String.format("%,.0f", alcoholProduct.getFinalPrice()) + " VNĐ\n");
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("3. Đồ xa xỉ (Thuế xa xỉ 30%)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Product luxuryProduct = new Product("Đồng hồ Rolex", basePrice, new LuxuryTax());
        System.out.println("  Thuế: " + luxuryProduct.getTaxInfo());
        System.out.println("  Số tiền thuế: " + String.format("%,.0f", luxuryProduct.getTaxAmount()) + " VNĐ");
        System.out.println("  Giá sau thuế: " + String.format("%,.0f", luxuryProduct.getFinalPrice()) + " VNĐ\n");
        
        // Demo thay đổi Strategy tại runtime
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("4. Thay đổi loại thuế tại runtime");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Product flexibleProduct = new Product("Sản phẩm linh hoạt", basePrice, new VatTax());
        System.out.println("  Ban đầu: " + flexibleProduct.getTaxInfo());
        System.out.println("  Giá: " + String.format("%,.0f", flexibleProduct.getFinalPrice()) + " VNĐ");
        
        flexibleProduct.setTaxStrategy(new LuxuryTax());
        System.out.println("  Sau khi đổi: " + flexibleProduct.getTaxInfo());
        System.out.println("  Giá mới: " + String.format("%,.0f", flexibleProduct.getFinalPrice()) + " VNĐ");
    }
    
    /**
     * Demo Decorator Pattern - Hệ thống thanh toán
     */
    private static void demoDecoratorPattern() {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║      DEMO 3: DECORATOR PATTERN - HỆ THỐNG THANH TOÁN      ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        
        double orderAmount = 1000000;
        
        // Demo 1: Thanh toán cơ bản
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("1. Thanh toán bằng thẻ tín dụng (cơ bản)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Payment creditCard = new CreditCardPayment(orderAmount);
        System.out.println("  " + creditCard.getDescription());
        System.out.println("  Chi phí: " + String.format("%,.0f", creditCard.cost()) + " VNĐ\n");
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("2. Thanh toán bằng PayPal (cơ bản)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Payment paypal = new PayPalPayment(orderAmount);
        System.out.println("  " + paypal.getDescription());
        System.out.println("  Chi phí: " + String.format("%,.0f", paypal.cost()) + " VNĐ\n");
        
        // Demo 2: Thêm phí xử lý
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("3. Thanh toán + Phí xử lý");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Payment withFee = new ProcessingFeeDecorator(creditCard, 50000);
        System.out.println("  " + withFee.getDescription());
        System.out.println("  Chi phí: " + String.format("%,.0f", withFee.cost()) + " VNĐ\n");
        
        // Demo 3: Áp dụng mã giảm giá
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("4. Thanh toán + Mã giảm giá");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Payment withDiscount = new DiscountDecorator(creditCard, 100000);
        System.out.println("  " + withDiscount.getDescription());
        System.out.println("  Chi phí: " + String.format("%,.0f", withDiscount.cost()) + " VNĐ\n");
        
        // Demo 4: Kết hợp nhiều decorator
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("5. Thanh toán + Phí xử lý + Mã giảm giá");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        Payment complexPayment = new DiscountDecorator(
            new ProcessingFeeDecorator(
                new PayPalPayment(orderAmount), 
                50000
            ), 
            100000
        );
        System.out.println("  " + complexPayment.getDescription());
        System.out.println("  Chi phí cuối cùng: " + String.format("%,.0f", complexPayment.cost()) + " VNĐ");
        System.out.println("  (Giải thích: " + String.format("%,.0f", orderAmount) + " + 50,000 - 100,000 = " + 
                          String.format("%,.0f", complexPayment.cost()) + ")\n");
    }
    
    /**
     * Demo tích hợp tất cả các patterns
     */
    private static void demoIntegration() {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║          DEMO 4: TÍCH HỢP TẤT CẢ CÁC PATTERNS             ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        
        // Tạo đơn hàng với nhiều sản phẩm có thuế khác nhau
        Order order = new Order("ORD-2026-003");
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Bước 1: Thêm sản phẩm vào đơn hàng");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        Product product1 = new Product("Laptop", 15000000, new VatTax());
        Product product2 = new Product("Rượu Whisky", 2000000, new ConsumptionTax());
        Product product3 = new Product("Đồng hồ Rolex", 50000000, new LuxuryTax());
        
        order.addProduct(product1);
        order.addProduct(product2);
        order.addProduct(product3);
        
        System.out.println("  ✓ " + product1.getName() + " - " + product1.getTaxInfo());
        System.out.println("  ✓ " + product2.getName() + " - " + product2.getTaxInfo());
        System.out.println("  ✓ " + product3.getName() + " - " + product3.getTaxInfo());
        System.out.println("\n  Tổng tiền đơn hàng: " + String.format("%,.0f", order.getTotalAmount()) + " VNĐ\n");
        
        // Xử lý đơn hàng (State Pattern)
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Bước 2: Xử lý đơn hàng (State Pattern)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        order.nextStep(); // NewOrderState -> ProcessingState
        
        // Thanh toán với Decorator Pattern
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Bước 3: Thanh toán (Decorator Pattern)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        Payment basePayment = new CreditCardPayment(order.getTotalAmount());
        Payment withFee = new ProcessingFeeDecorator(basePayment, 100000);
        Payment finalPayment = new DiscountDecorator(withFee, 500000);
        
        order.setPaymentMethod(finalPayment.getDescription());
        
        System.out.println("  " + finalPayment.getDescription());
        System.out.println("  Chi phí thanh toán: " + String.format("%,.0f", finalPayment.cost()) + " VNĐ\n");
        
        // Tiếp tục xử lý đơn hàng
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Bước 4: Hoàn tất đơn hàng");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        order.nextStep(); // ProcessingState -> DeliveredState
        order.nextStep(); // DeliveredState (hoàn thành)
        
        System.out.println("\n✓ Đơn hàng đã được xử lý hoàn tất!");
        System.out.println("✓ Tất cả các Design Patterns đã hoạt động cùng nhau!");
    }
}
