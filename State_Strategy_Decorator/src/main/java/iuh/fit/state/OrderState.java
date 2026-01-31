package iuh.fit.state;

/**
 * State Pattern - State Interface
 * Định nghĩa interface cho các trạng thái của đơn hàng
 */
public interface OrderState {
    /**
     * Xử lý logic của trạng thái hiện tại và chuyển sang trạng thái tiếp theo
     * @param order Đơn hàng cần xử lý
     */
    void handle(Order order);
    
    /**
     * Lấy tên trạng thái
     * @return Tên trạng thái
     */
    String getStateName();
}
