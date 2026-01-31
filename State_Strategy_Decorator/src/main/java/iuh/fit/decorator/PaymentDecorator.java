package iuh.fit.decorator;

/**
 * Decorator Pattern - Decorator Abstract Class
 * Lớp trừu tượng cho các decorator, giữ tham chiếu đến Payment
 */
public abstract class PaymentDecorator implements Payment {
    protected Payment payment;
    
    public PaymentDecorator(Payment payment) {
        this.payment = payment;
    }
    
    @Override
    public double cost() {
        return payment.cost();
    }
    
    @Override
    public String getDescription() {
        return payment.getDescription();
    }
}
