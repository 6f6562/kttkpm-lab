package iuh.fit.strategy;

/**
 * Strategy Pattern - Concrete Strategy
 * Thuế VAT (Thuế giá trị gia tăng) - 10%
 */
public class VatTax implements TaxStrategy {
    private static final double RATE = 0.10; // 10%
    
    @Override
    public double calculateTax(double price) {
        return price * RATE;
    }
    
    @Override
    public String getTaxName() {
        return "VAT";
    }
    
    @Override
    public double getTaxRate() {
        return RATE * 100;
    }
}
