package iuh.fit.notification.factory;

import iuh.fit.notification.product.Notification;
import iuh.fit.notification.product.WebEmail;
import iuh.fit.notification.product.WebSMS;

/**
 * Concrete Factory: WebFactory
 * Tạo ra các đối tượng Notification cho nền tảng Web
 */
public class WebFactory implements NotificationFactory {
    
    /**
     * Factory Method: Tạo đối tượng Notification cho Web platform
     * 
     * @param type Loại thông báo: "EMAIL" hoặc "SMS"
     * @return WebEmail nếu type = "EMAIL", WebSMS nếu type = "SMS"
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
                return new WebEmail();
            case "SMS":
                return new WebSMS();
            default:
                throw new IllegalArgumentException(
                    String.format("Invalid notification type: %s. Supported types: EMAIL, SMS", type)
                );
        }
    }
    
    @Override
    public String getPlatformName() {
        return "WEB";
    }
}
