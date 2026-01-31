package iuh.fit.decorator;

/**
 * Decorator Pattern - Concrete Decorator
 * Áp dụng mã giảm giá vào phương thức thanh toán
 */
public class DiscountDecorator extends PaymentDecorator {
    private double discount;
    
    public DiscountDecorator(Payment payment, double discount) {
        super(payment);
        this.discount = discount;
    }
    
    @Override
    public double cost() {
        double total = payment.cost() - discount;
        // Đảm bảo không âm
        return Math.max(0, total);
    }
    
    @Override
    public String getDescription() {
        return payment.getDescription() + " - Giảm giá: " + String.format("%,.0f", discount) + " VNĐ";
    }
    
    public double getDiscount() {
        return discount;
    }
}
