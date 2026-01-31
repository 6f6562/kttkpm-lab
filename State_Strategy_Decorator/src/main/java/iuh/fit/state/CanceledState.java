package iuh.fit.state;

/**
 * State Pattern - Concrete State
 * Trạng thái đã hủy: Đơn hàng đã bị hủy
 */
public class CanceledState implements OrderState {
    
    private String reason;
    
    public CanceledState() {
        this.reason = "Khách hàng yêu cầu";
    }
    
    public CanceledState(String reason) {
        this.reason = reason;
    }
    
    @Override
    public void handle(Order order) {
        System.out.println("=== Xử lý đơn hàng ở trạng thái ĐÃ HỦY ===");
        System.out.println("Đơn hàng #" + order.getOrderId());
        System.out.println("Lý do hủy: " + reason);
        
        // Hoàn tiền cho khách hàng
        refundPayment(order);
        
        // Cập nhật lại kho hàng
        restoreInventory(order);
        
        // Gửi email thông báo
        sendCancellationEmail(order);
        
        System.out.println("✓ Đơn hàng đã được hủy thành công");
        System.out.println("→ Đây là trạng thái cuối cùng\n");
    }
    
    /**
     * Hoàn tiền cho khách hàng
     */
    private void refundPayment(Order order) {
        System.out.println("💰 Hoàn tiền cho khách hàng...");
        System.out.println("  → Số tiền hoàn lại: " + String.format("%,.0f", order.getTotalAmount()) + " VNĐ");
        System.out.println("  → Phương thức: " + order.getPaymentMethod());
        System.out.println("  ✓ Hoàn tiền thành công");
    }
    
    /**
     * Cập nhật lại kho hàng (trả lại sản phẩm)
     */
    private void restoreInventory(Order order) {
        System.out.println("📦 Cập nhật lại kho hàng...");
        for (var product : order.getProducts()) {
            System.out.println("  - Trả lại: " + product.getName());
        }
        System.out.println("✓ Kho hàng đã được cập nhật");
    }
    
    /**
     * Gửi email thông báo hủy đơn hàng
     */
    private void sendCancellationEmail(Order order) {
        System.out.println("📧 Gửi email thông báo hủy đơn hàng...");
        System.out.println("  → Email đã được gửi đến khách hàng");
    }
    
    @Override
    public String getStateName() {
        return "ĐÃ HỦY";
    }
}
