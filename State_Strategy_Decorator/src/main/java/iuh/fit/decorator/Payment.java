package iuh.fit.decorator;

/**
 * Decorator Pattern - Component Interface
 * Định nghĩa interface cho các phương thức thanh toán
 */
public interface Payment {
    /**
     * Tính tổng chi phí thanh toán
     * @return Tổng chi phí
     */
    double cost();
    
    /**
     * Lấy mô tả phương thức thanh toán
     * @return Mô tả
     */
    String getDescription();
}
