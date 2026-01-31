package iuh.fit.state;

import iuh.fit.state.Order;
import iuh.fit.state.NewOrderState;
import iuh.fit.state.ProcessingState;
import iuh.fit.state.DeliveredState;
import iuh.fit.state.CanceledState;
import iuh.fit.strategy.Product;
import iuh.fit.strategy.VatTax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderStateTest {
    private Order order;
    
    @BeforeEach
    void setUp() {
        order = new Order("ORD-001");
        order.addProduct(new Product("Sản phẩm A", 100000, new VatTax()));
    }
    
    @Test
    void testNewOrderState() {
        assertTrue(order.getCurrentState() instanceof NewOrderState);
        assertEquals("MỚI", order.getCurrentState().getStateName());
    }
    
    @Test
    void testTransitionToProcessingState() {
        // Chuyển sang ProcessingState
        order.nextStep();
        assertTrue(order.getCurrentState() instanceof ProcessingState);
        assertEquals("ĐANG XỬ LÝ", order.getCurrentState().getStateName());
    }
    
    @Test
    void testTransitionToDeliveredState() {
        // Chuyển sang ProcessingState
        order.nextStep();
        // Chuyển sang DeliveredState
        order.nextStep();
        assertTrue(order.getCurrentState() instanceof DeliveredState);
        assertEquals("ĐÃ GIAO", order.getCurrentState().getStateName());
    }
    
    @Test
    void testCancelOrderFromNewState() {
        order.cancel();
        assertTrue(order.getCurrentState() instanceof CanceledState);
        assertEquals("ĐÃ HỦY", order.getCurrentState().getStateName());
    }
    
    @Test
    void testCancelOrderFromProcessingState() {
        order.nextStep(); // Chuyển sang ProcessingState
        order.cancel("Hết hàng");
        assertTrue(order.getCurrentState() instanceof CanceledState);
    }
    
    @Test
    void testCannotCancelDeliveredOrder() {
        order.nextStep(); // ProcessingState
        order.nextStep(); // DeliveredState
        
        // Thử hủy đơn hàng đã giao
        order.cancel();
        // Vẫn ở DeliveredState
        assertTrue(order.getCurrentState() instanceof DeliveredState);
    }
    
    @Test
    void testOrderTotalCalculation() {
        order.addProduct(new Product("Sản phẩm B", 200000, new VatTax()));
        assertTrue(order.getTotalAmount() > 0);
    }
}
