package iuh.fit.notification.factory;

import iuh.fit.notification.product.Notification;

/**
 * Abstract Factory: NotificationFactory
 * Định nghĩa interface để tạo các đối tượng Notification
 */
public interface NotificationFactory {
    /**
     * Factory Method: Tạo đối tượng Notification dựa trên loại thông báo
     * 
     * @param type Loại thông báo: "EMAIL" hoặc "SMS"
     * @return Đối tượng Notification tương ứng với nền tảng và loại
     * @throws IllegalArgumentException nếu type không hợp lệ
     */
    Notification createNotification(String type);
    
    /**
     * Lấy tên nền tảng của Factory
     * 
     * @return Tên nền tảng (WEB hoặc MOBILE)
     */
    String getPlatformName();
}
