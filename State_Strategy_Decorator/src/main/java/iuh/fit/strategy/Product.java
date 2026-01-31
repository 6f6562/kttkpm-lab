package iuh.fit.strategy;

/**
 * Strategy Pattern - Sử dụng TaxStrategy
 * Lớp Product đại diện cho sản phẩm, sử dụng TaxStrategy để tính giá sau thuế
 */
public class Product {
    private String name;
    private double basePrice;
    private TaxStrategy taxStrategy;
    
    public Product(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
        // Mặc định sử dụng VAT
        this.taxStrategy = new VatTax();
    }
    
    public Product(String name, double basePrice, TaxStrategy taxStrategy) {
        this.name = name;
        this.basePrice = basePrice;
        this.taxStrategy = taxStrategy;
    }
    
    /**
     * Thiết lập chiến lược tính thuế
     * @param taxStrategy Chiến lược tính thuế
     */
    public void setTaxStrategy(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }
    
    /**
     * Tính giá sau thuế
     * @return Giá sau thuế
     */
    public double getFinalPrice() {
        if (taxStrategy == null) {
            return basePrice;
        }
        TaxCalculator calculator = new TaxCalculator(taxStrategy);
        return calculator.calculatePriceWithTax(basePrice);
    }
    
    /**
     * Tính số tiền thuế
     * @return Số tiền thuế
     */
    public double getTaxAmount() {
        if (taxStrategy == null) {
            return 0;
        }
        TaxCalculator calculator = new TaxCalculator(taxStrategy);
        return calculator.calculateTax(basePrice);
    }
    
    /**
     * Lấy thông tin thuế
     */
    public String getTaxInfo() {
        if (taxStrategy == null) {
            return "Không có thuế";
        }
        return taxStrategy.getTaxName() + " (" + taxStrategy.getTaxRate() + "%)";
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getBasePrice() {
        return basePrice;
    }
    
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    
    public TaxStrategy getTaxStrategy() {
        return taxStrategy;
    }
}
