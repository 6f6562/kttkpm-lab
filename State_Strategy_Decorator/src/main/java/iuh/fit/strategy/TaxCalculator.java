package iuh.fit.strategy;

/**
 * Strategy Pattern - Context
 * Lớp tính toán thuế, sử dụng TaxStrategy để tính thuế
 */
public class TaxCalculator {
    private TaxStrategy strategy;
    
    public TaxCalculator() {
        // Mặc định sử dụng VAT
        this.strategy = new VatTax();
    }
    
    public TaxCalculator(TaxStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Thiết lập chiến lược tính thuế
     * @param strategy Chiến lược tính thuế
     */
    public void setStrategy(TaxStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Tính số tiền thuế
     * @param price Giá gốc
     * @return Số tiền thuế
     */
    public double calculateTax(double price) {
        if (strategy == null) {
            throw new IllegalStateException("Tax strategy chưa được thiết lập");
        }
        return strategy.calculateTax(price);
    }
    
    /**
     * Tính giá sau thuế
     * @param price Giá gốc
     * @return Giá sau thuế
     */
    public double calculatePriceWithTax(double price) {
        return price + calculateTax(price);
    }
    
    /**
     * Lấy thông tin chiến lược hiện tại
     */
    public String getStrategyInfo() {
        if (strategy == null) {
            return "Chưa có chiến lược";
        }
        return strategy.getTaxName() + " (" + strategy.getTaxRate() + "%)";
    }
}
