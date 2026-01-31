package iuh.fit.decorator;

/**
 * Decorator Pattern - Concrete Component
 * Thanh toán bằng thẻ tín dụng
 */
public class CreditCardPayment implements Payment {
    private double amount;
    
    public CreditCardPayment(double amount) {
        this.amount = amount;
    }
    
    @Override
    public double cost() {
        return amount;
    }
    
    @Override
    public String getDescription() {
        return "Thanh toán bằng thẻ tín dụng";
    }
    
    public double getAmount() {
        return amount;
    }
}
