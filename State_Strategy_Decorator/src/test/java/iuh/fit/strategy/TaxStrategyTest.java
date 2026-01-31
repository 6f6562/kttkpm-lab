package iuh.fit.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxStrategyTest {
    private TaxCalculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new TaxCalculator();
    }
    
    @Test
    void testVatTax() {
        calculator.setStrategy(new VatTax());
        double price = 100000;
        double tax = calculator.calculateTax(price);
        assertEquals(10000, tax, 0.01);
        assertEquals(110000, calculator.calculatePriceWithTax(price), 0.01);
    }
    
    @Test
    void testConsumptionTax() {
        calculator.setStrategy(new ConsumptionTax());
        double price = 100000;
        double tax = calculator.calculateTax(price);
        assertEquals(20000, tax, 0.01);
        assertEquals(120000, calculator.calculatePriceWithTax(price), 0.01);
    }
    
    @Test
    void testLuxuryTax() {
        calculator.setStrategy(new LuxuryTax());
        double price = 100000;
        double tax = calculator.calculateTax(price);
        assertEquals(30000, tax, 0.01);
        assertEquals(130000, calculator.calculatePriceWithTax(price), 0.01);
    }
    
    @Test
    void testChangeStrategyAtRuntime() {
        double price = 100000;
        
        // Bắt đầu với VAT
        calculator.setStrategy(new VatTax());
        assertEquals(10000, calculator.calculateTax(price), 0.01);
        
        // Thay đổi sang LuxuryTax
        calculator.setStrategy(new LuxuryTax());
        assertEquals(30000, calculator.calculateTax(price), 0.01);
    }
    
    @Test
    void testProductWithVatTax() {
        Product product = new Product("Sản phẩm A", 100000, new VatTax());
        assertEquals(110000, product.getFinalPrice(), 0.01);
        assertEquals(10000, product.getTaxAmount(), 0.01);
    }
    
    @Test
    void testProductWithLuxuryTax() {
        Product product = new Product("Đồ xa xỉ", 100000, new LuxuryTax());
        assertEquals(130000, product.getFinalPrice(), 0.01);
        assertEquals(30000, product.getTaxAmount(), 0.01);
    }
    
    @Test
    void testProductChangeTaxStrategy() {
        Product product = new Product("Sản phẩm", 100000);
        assertEquals(110000, product.getFinalPrice(), 0.01); // Mặc định VAT
        
        product.setTaxStrategy(new LuxuryTax());
        assertEquals(130000, product.getFinalPrice(), 0.01); // Đã đổi sang LuxuryTax
    }
}
