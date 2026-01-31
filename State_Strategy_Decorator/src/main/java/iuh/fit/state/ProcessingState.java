package iuh.fit.state;

/**
 * State Pattern - Concrete State
 * Trạng thái đang xử lý: Đơn hàng đã được xác thực, đang được đóng gói
 */
public class ProcessingState implements OrderState {
    
    @Override
    public void handle(Order order) {
        System.out.println("=== Xử lý đơn hàng ở trạng thái ĐANG XỬ LÝ ===");
        System.out.println("Đơn hàng #" + order.getOrderId());
        
        // Đóng gói sản phẩm
        packageOrder(order);
        
        // Cập nhật kho hàng
        updateInventory(order);
        
        // Tạo mã vận đơn
        String trackingNumber = generateTrackingNumber(order);
        order.setTrackingNumber(trackingNumber);
        System.out.println("✓ Mã vận đơn: " + trackingNumber);
        
        // Chuyển sang trạng thái đã giao
        order.setState(new DeliveredState());
        System.out.println("→ Đơn hàng chuyển sang trạng thái: ĐÃ GIAO\n");
    }
    
    /**
     * Đóng gói sản phẩm
     */
    private void packageOrder(Order order) {
        System.out.println("📦 Đang đóng gói " + order.getProducts().size() + " sản phẩm...");
        for (var product : order.getProducts()) {
            System.out.println("  - Đóng gói: " + product.getName());
        }
        System.out.println("✓ Đóng gói hoàn tất");
    }
    
    /**
     * Cập nhật kho hàng
     */
    private void updateInventory(Order order) {
        System.out.println("📊 Cập nhật kho hàng...");
        for (var product : order.getProducts()) {
            System.out.println("  - Giảm số lượng: " + product.getName());
        }
        System.out.println("✓ Kho hàng đã được cập nhật");
    }
    
    /**
     * Tạo mã vận đơn
     */
    private String generateTrackingNumber(Order order) {
        return "TRACK-" + order.getOrderId() + "-" + System.currentTimeMillis();
    }
    
    @Override
    public String getStateName() {
        return "ĐANG XỬ LÝ";
    }
}
