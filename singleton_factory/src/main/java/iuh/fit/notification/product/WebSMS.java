package iuh.fit.notification.product;

import iuh.fit.notification.singleton.SettingManager;

/**
 * Concrete Product: WebSMS
 * Thông báo SMS cho nền tảng Web
 */
public class WebSMS implements Notification {
    private final SettingManager settings;
    
    public WebSMS() {
        this.settings = SettingManager.getInstance();
    }
    
    @Override
    public void send(String message) {
        if (settings.isLogEnabled()) {
            System.out.println("[WebSMS] Preparing SMS for web interface...");
        }
        
        // Định dạng thông báo cho Web (có thể hiển thị trong popup hoặc notification bar)
        String webFormattedMessage = String.format(
            "[WEB SMS Notification] %s",
            message
        );
        
        System.out.println("💬 [WEB SMS] Sending SMS:");
        System.out.println("   Format: Web Notification");
        System.out.println("   Content: " + message);
        System.out.println("   Display: " + webFormattedMessage);
        
        if (settings.isLogEnabled()) {
            System.out.println("[WebSMS] SMS sent successfully via Web platform");
        }
    }
    
    @Override
    public String getType() {
        return "SMS";
    }
    
    @Override
    public String getPlatform() {
        return "WEB";
    }
}
