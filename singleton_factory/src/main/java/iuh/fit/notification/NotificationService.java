package iuh.fit.notification;

import iuh.fit.notification.factory.MobileFactory;
import iuh.fit.notification.factory.NotificationFactory;
import iuh.fit.notification.factory.WebFactory;
import iuh.fit.notification.product.Notification;
import iuh.fit.notification.singleton.SettingManager;

/**
 * Service class để quản lý việc gửi thông báo
 * Sử dụng Factory để tạo các đối tượng Notification
 */
public class NotificationService {
    private final SettingManager settings;
    
    public NotificationService() {
        this.settings = SettingManager.getInstance();
    }
    
    /**
     * Tạo Factory dựa trên nền tảng
     * 
     * @param platform Nền tảng: "WEB" hoặc "MOBILE"
     * @return NotificationFactory tương ứng
     * @throws IllegalArgumentException nếu platform không hợp lệ
     */
    public NotificationFactory createFactory(String platform) {
        if (platform == null) {
            throw new IllegalArgumentException("Platform cannot be null");
        }
        
        String upperPlatform = platform.toUpperCase().trim();
        
        switch (upperPlatform) {
            case "WEB":
                return new WebFactory();
            case "MOBILE":
                return new MobileFactory();
            default:
                throw new IllegalArgumentException(
                    String.format("Invalid platform: %s. Supported platforms: WEB, MOBILE", platform)
                );
        }
    }
    
    /**
     * Gửi thông báo qua nền tảng và loại cụ thể
     * 
     * @param platform Nền tảng: "WEB" hoặc "MOBILE"
     * @param type Loại thông báo: "EMAIL" hoặc "SMS"
     * @param message Nội dung thông báo
     */
    public void sendNotification(String platform, String type, String message) {
        if (settings.isLogEnabled()) {
            System.out.println("\n=== Sending Notification ===");
            System.out.println("Platform: " + platform);
            System.out.println("Type: " + type);
            System.out.println("Message: " + message);
        }
        
        NotificationFactory factory = createFactory(platform);
        Notification notification = factory.createNotification(type);
        notification.send(message);
        
        if (settings.isLogEnabled()) {
            System.out.println("=== Notification Sent ===\n");
        }
    }
}
