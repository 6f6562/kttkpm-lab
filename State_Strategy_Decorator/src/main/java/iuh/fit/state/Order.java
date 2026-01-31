package iuh.fit.state;

import iuh.fit.strategy.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * State Pattern - Context
 * Lớp Order đại diện cho đơn hàng, giữ tham chiếu đến trạng thái hiện tại
 */
public class Order {
    private String orderId;
    private OrderState currentState;
    private double totalAmount;
    private List<Product> products;
    private String paymentMethod;
    private String trackingNumber;
    
    public Order(String orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
        this.currentState = new NewOrderState();
        this.totalAmount = 0.0;
    }
    
    /**
     * Chuyển sang bước tiếp theo của đơn hàng
     */
    public void nextStep() {
        if (currentState != null) {
            currentState.handle(this);
        }
    }
    
    /**
     * Hủy đơn hàng
     */
    public void cancel() {
        if (currentState instanceof NewOrderState || currentState instanceof ProcessingState) {
            setState(new CanceledState());
            currentState.handle(this);
        } else {
            System.out.println("Không thể hủy đơn hàng ở trạng thái: " + currentState.getStateName());
        }
    }
    
    /**
     * Hủy đơn hàng với lý do
     */
    public void cancel(String reason) {
        if (currentState instanceof NewOrderState || currentState instanceof ProcessingState) {
            setState(new CanceledState(reason));
            currentState.handle(this);
        } else {
            System.out.println("Không thể hủy đơn hàng ở trạng thái: " + currentState.getStateName());
        }
    }
    
    /**
     * Thêm sản phẩm vào đơn hàng
     */
    public void addProduct(Product product) {
        products.add(product);
        calculateTotal();
    }
    
    /**
     * Tính tổng tiền đơn hàng
     */
    private void calculateTotal() {
        totalAmount = products.stream()
                .mapToDouble(Product::getFinalPrice)
                .sum();
    }
    
    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }
    
    public OrderState getCurrentState() {
        return currentState;
    }
    
    public void setState(OrderState state) {
        this.currentState = state;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getTrackingNumber() {
        return trackingNumber;
    }
    
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
