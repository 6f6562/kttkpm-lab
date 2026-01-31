package iuh.fit.notification.product;

/**
 * Abstract Product: Notification
 * Interface định nghĩa các phương thức chung cho tất cả các loại thông báo
 */
public interface Notification {
    /**
     * Gửi thông báo
     * 
     * @param message Nội dung thông báo cần gửi
     */
    void send(String message);
    
    /**
     * Lấy loại thông báo (EMAIL hoặc SMS)
     * 
     * @return Loại thông báo
     */
    String getType();
    
    /**
     * Lấy nền tảng của thông báo (WEB hoặc MOBILE)
     * 
     * @return Nền tảng
     */
    String getPlatform();
}
