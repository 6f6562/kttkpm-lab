package iuh.fit.state;

/**
 * State Pattern - Concrete State
 * Trạng thái mới: Đơn hàng vừa được tạo, đang chờ kiểm tra và xác thực
 */
public class NewOrderState implements OrderState {
    
    @Override
    public void handle(Order order) {
        System.out.println("=== Xử lý đơn hàng ở trạng thái MỚI ===");
        System.out.println("Đơn hàng #" + order.getOrderId());
        
        // Kiểm tra thông tin đơn hàng
        if (validateOrder(order)) {
            System.out.println("✓ Thông tin đơn hàng hợp lệ");
            System.out.println("✓ Xác thực thanh toán thành công");
            System.out.println("✓ Kiểm tra số lượng sản phẩm trong kho");
            
            // Chuyển sang trạng thái đang xử lý
            order.setState(new ProcessingState());
            System.out.println("→ Đơn hàng chuyển sang trạng thái: ĐANG XỬ LÝ\n");
        } else {
            System.out.println("✗ Thông tin đơn hàng không hợp lệ");
            System.out.println("→ Đơn hàng giữ nguyên trạng thái: MỚI\n");
        }
    }
    
    /**
     * Kiểm tra tính hợp lệ của đơn hàng
     */
    private boolean validateOrder(Order order) {
        // Kiểm tra thông tin cơ bản
        if (order.getOrderId() == null || order.getOrderId().isEmpty()) {
            return false;
        }
        
        // Kiểm tra tổng tiền
        if (order.getTotalAmount() <= 0) {
            return false;
        }
        
        // Kiểm tra có sản phẩm không
        if (order.getProducts().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String getStateName() {
        return "MỚI";
    }
}
