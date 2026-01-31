package iuh.fit.strategy;

/**
 * Strategy Pattern - Strategy Interface
 * Định nghĩa interface cho các chiến lược tính thuế
 */
public interface TaxStrategy {
    /**
     * Tính số tiền thuế
     * @param price Giá gốc của sản phẩm
     * @return Số tiền thuế phải trả
     */
    double calculateTax(double price);
    
    /**
     * Lấy tên loại thuế
     * @return Tên loại thuế
     */
    String getTaxName();
    
    /**
     * Lấy tỷ lệ thuế
     * @return Tỷ lệ thuế (dưới dạng phần trăm)
     */
    double getTaxRate();
}
