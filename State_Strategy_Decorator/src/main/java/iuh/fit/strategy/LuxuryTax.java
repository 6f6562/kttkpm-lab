package iuh.fit.strategy;

/**
 * Strategy Pattern - Concrete Strategy
 * Thuế xa xỉ - 30%
 * Áp dụng cho: Đồ xa xỉ, hàng hiệu cao cấp
 */
public class LuxuryTax implements TaxStrategy {
    private static final double RATE = 0.30; // 30%
    
    @Override
    public double calculateTax(double price) {
        return price * RATE;
    }
    
    @Override
    public String getTaxName() {
        return "Thuế xa xỉ";
    }
    
    @Override
    public double getTaxRate() {
        return RATE * 100;
    }
}
