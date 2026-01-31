package iuh.fit;

import iuh.fit.decorator.*;
import iuh.fit.state.Order;
import iuh.fit.strategy.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration Test - Kiểm tra sự tương tác giữa các patterns
 */
class IntegrationTest {
    
    @Test
    void testCompleteOrderFlow() {
        // Tạo đơn hàng
        Order order = new Order("ORD-001");
        
        // Thêm sản phẩm với các loại thuế khác nhau
        order.addProduct(new Product("Sản phẩm thông thường", 100000, new VatTax()));
        order.addProduct(new Product("Rượu", 200000, new ConsumptionTax()));
        order.addProduct(new Product("Đồ xa xỉ", 500000, new LuxuryTax()));
        
        // Kiểm tra tổng tiền
        assertTrue(order.getTotalAmount() > 0);
        
        // Chuyển trạng thái
        order.nextStep(); // NewOrderState -> ProcessingState
        assertTrue(order.getCurrentState() instanceof iuh.fit.state.ProcessingState);
        
        order.nextStep(); // ProcessingState -> DeliveredState
        assertTrue(order.getCurrentState() instanceof iuh.fit.state.DeliveredState);
    }
    
    @Test
    void testOrderWithPaymentDecorators() {
        Order order = new Order("ORD-002");
        order.addProduct(new Product("Sản phẩm", 1000000, new VatTax()));
        
        // Tạo payment với decorators
        Payment basePayment = new CreditCardPayment(order.getTotalAmount());
        Payment withFee = new ProcessingFeeDecorator(basePayment, 50000);
        Payment finalPayment = new DiscountDecorator(withFee, 100000);
        
        order.setPaymentMethod(finalPayment.getDescription());
        
        assertTrue(finalPayment.cost() > 0);
        assertTrue(order.getTotalAmount() > 0);
    }
    
    @Test
    void testProductTaxStrategyChange() {
        Product product = new Product("Sản phẩm", 100000);
        
        // Mặc định VAT
        double priceWithVat = product.getFinalPrice();
        
        // Đổi sang LuxuryTax
        product.setTaxStrategy(new LuxuryTax());
        double priceWithLuxury = product.getFinalPrice();
        
        assertTrue(priceWithLuxury > priceWithVat);
    }
}
