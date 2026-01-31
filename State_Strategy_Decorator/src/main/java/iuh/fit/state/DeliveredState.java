package iuh.fit.state;

/**
 * State Pattern - Concrete State
 * Trạng thái đã giao: Đơn hàng đã được giao đến khách hàng
 */
public class DeliveredState implements OrderState {
    
    @Override
    public void handle(Order order) {
        System.out.println("=== Xử lý đơn hàng ở trạng thái ĐÃ GIAO ===");
        System.out.println("Đơn hàng #" + order.getOrderId());
        
        // Cập nhật trạng thái giao hàng
        System.out.println("✓ Đơn hàng đã được giao đến khách hàng");
        
        // Gửi email xác nhận
        sendConfirmationEmail(order);
        
        // Tạo hóa đơn
        generateInvoice(order);
        
        System.out.println("✓ Đơn hàng đã hoàn thành");
        System.out.println("→ Đây là trạng thái cuối cùng\n");
    }
    
    /**
     * Gửi email xác nhận
     */
    private void sendConfirmationEmail(Order order) {
        System.out.println("📧 Gửi email xác nhận giao hàng...");
        System.out.println("  → Email đã được gửi đến khách hàng");
    }
    
    /**
     * Tạo hóa đơn
     */
    private void generateInvoice(Order order) {
        System.out.println("🧾 Tạo hóa đơn điện tử...");
        System.out.println("  → Hóa đơn #INV-" + order.getOrderId());
        System.out.println("  → Tổng tiền: " + String.format("%,.0f", order.getTotalAmount()) + " VNĐ");
    }
    
    @Override
    public String getStateName() {
        return "ĐÃ GIAO";
    }
}
