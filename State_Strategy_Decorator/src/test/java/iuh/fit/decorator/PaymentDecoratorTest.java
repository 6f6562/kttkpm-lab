package iuh.fit.decorator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentDecoratorTest {
    
    @Test
    void testCreditCardPayment() {
        Payment payment = new CreditCardPayment(1000000);
        assertEquals(1000000, payment.cost(), 0.01);
        assertTrue(payment.getDescription().contains("thẻ tín dụng"));
    }
    
    @Test
    void testPayPalPayment() {
        Payment payment = new PayPalPayment(1000000);
        assertEquals(1000000, payment.cost(), 0.01);
        assertTrue(payment.getDescription().contains("PayPal"));
    }
    
    @Test
    void testProcessingFeeDecorator() {
        Payment payment = new CreditCardPayment(1000000);
        Payment decoratedPayment = new ProcessingFeeDecorator(payment, 50000);
        
        assertEquals(1050000, decoratedPayment.cost(), 0.01);
        assertTrue(decoratedPayment.getDescription().contains("Phí xử lý"));
    }
    
    @Test
    void testDiscountDecorator() {
        Payment payment = new CreditCardPayment(1000000);
        Payment decoratedPayment = new DiscountDecorator(payment, 100000);
        
        assertEquals(900000, decoratedPayment.cost(), 0.01);
        assertTrue(decoratedPayment.getDescription().contains("Giảm giá"));
    }
    
    @Test
    void testMultipleDecorators() {
        Payment payment = new CreditCardPayment(1000000);
        Payment withFee = new ProcessingFeeDecorator(payment, 50000);
        Payment withDiscount = new DiscountDecorator(withFee, 100000);
        
        // 1000000 + 50000 - 100000 = 950000
        assertEquals(950000, withDiscount.cost(), 0.01);
    }
    
    @Test
    void testMultipleDecoratorsOrder() {
        Payment payment = new PayPalPayment(1000000);
        
        // Thêm phí xử lý trước, sau đó giảm giá
        Payment step1 = new ProcessingFeeDecorator(payment, 50000);
        Payment step2 = new DiscountDecorator(step1, 100000);
        
        assertEquals(950000, step2.cost(), 0.01);
        
        // Thử ngược lại: giảm giá trước, sau đó thêm phí
        Payment step3 = new DiscountDecorator(payment, 100000);
        Payment step4 = new ProcessingFeeDecorator(step3, 50000);
        
        assertEquals(950000, step4.cost(), 0.01);
    }
    
    @Test
    void testDiscountCannotMakeNegative() {
        Payment payment = new CreditCardPayment(100000);
        Payment decoratedPayment = new DiscountDecorator(payment, 200000);
        
        // Không được âm
        assertEquals(0, decoratedPayment.cost(), 0.01);
    }
    
    @Test
    void testComplexDecoratorChain() {
        Payment basePayment = new PayPalPayment(1000000);
        Payment withFee1 = new ProcessingFeeDecorator(basePayment, 20000);
        Payment withDiscount1 = new DiscountDecorator(withFee1, 50000);
        Payment withFee2 = new ProcessingFeeDecorator(withDiscount1, 10000);
        Payment finalPayment = new DiscountDecorator(withFee2, 30000);
        
        // 1000000 + 20000 - 50000 + 10000 - 30000 = 950000
        assertEquals(950000, finalPayment.cost(), 0.01);
    }
}
