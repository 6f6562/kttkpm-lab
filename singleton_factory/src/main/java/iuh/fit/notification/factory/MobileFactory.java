package iuh.fit.notification.factory;

import iuh.fit.notification.product.MobileEmail;
import iuh.fit.notification.product.MobileSMS;
import iuh.fit.notification.product.Notification;

/**
 * Concrete Factory: MobileFactory
 * Tạo ra các đối tượng Notification cho nền tảng Mobile
 */
public class MobileFactory implements NotificationFactory {
    
    /**
     * Factory Method: Tạo đối tượng Notification cho Mobile platform
     * 
     * @param type Loại thông báo: "EMAIL" hoặc "SMS"
     * @return MobileEmail nếu type = "EMAIL", MobileSMS nếu type = "SMS"
     * @throws IllegalArgumentException nếu type không hợp lệ
     */
    @Override
    public Notification createNotification(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Notification type cannot be null");
        }
        
        String upperType = type.toUpperCase().trim();
        
        switch (upperType) {
            case "EMAIL":
                return new MobileEmail();
            case "SMS":
                return new MobileSMS();
            default:
                throw new IllegalArgumentException(
                    String.format("Invalid notification type: %s. Supported types: EMAIL, SMS", type)
                );
        }
    }
    
    @Override
    public String getPlatformName() {
        return "MOBILE";
    }
}
