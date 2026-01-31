package iuh.fit.decorator;

/**
 * Decorator Pattern - Concrete Decorator
 * Thêm phí xử lý vào phương thức thanh toán
 */
public class ProcessingFeeDecorator extends PaymentDecorator {
    private double fee;
    
    public ProcessingFeeDecorator(Payment payment, double fee) {
        super(payment);
        this.fee = fee;
    }
    
    @Override
    public double cost() {
        return payment.cost() + fee;
    }
    
    @Override
    public String getDescription() {
        return payment.getDescription() + " + Phí xử lý: " + String.format("%,.0f", fee) + " VNĐ";
    }
    
    public double getFee() {
        return fee;
    }
}
