package iuh.fit.decorator;

/**
 * Decorator Pattern - Concrete Component
 * Thanh toán bằng PayPal
 */
public class PayPalPayment implements Payment {
    private double amount;
    
    public PayPalPayment(double amount) {
        this.amount = amount;
    }
    
    @Override
    public double cost() {
        return amount;
    }
    
    @Override
    public String getDescription() {
        return "Thanh toán bằng PayPal";
    }
    
    public double getAmount() {
        return amount;
    }
}
