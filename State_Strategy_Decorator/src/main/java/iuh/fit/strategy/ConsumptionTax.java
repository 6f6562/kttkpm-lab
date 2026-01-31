package iuh.fit.strategy;

/**
 * Strategy Pattern - Concrete Strategy
 * Thuế tiêu thụ đặc biệt - 20%
 * Áp dụng cho: Rượu, thuốc lá, xe máy trên 175cc
 */
public class ConsumptionTax implements TaxStrategy {
    private static final double RATE = 0.20; // 20%
    
    @Override
    public double calculateTax(double price) {
        return price * RATE;
    }
    
    @Override
    public String getTaxName() {
        return "Thuế tiêu thụ đặc biệt";
    }
    
    @Override
    public double getTaxRate() {
        return RATE * 100;
    }
}
