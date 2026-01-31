package iuh.fit.notification.product;

import iuh.fit.notification.singleton.SettingManager;

/**
 * Concrete Product: WebEmail
 * Thông báo Email cho nền tảng Web
 */
public class WebEmail implements Notification {
    private final SettingManager settings;
    
    public WebEmail() {
        this.settings = SettingManager.getInstance();
    }
    
    @Override
    public void send(String message) {
        if (settings.isLogEnabled()) {
            System.out.println("[WebEmail] Preparing HTML email...");
        }
        
        // Định dạng thông báo cho Web (HTML format)
        String htmlMessage = String.format(
            "<html><body><h2>Email Notification</h2><p>%s</p></body></html>",
            message
        );
        
        System.out.println("📧 [WEB EMAIL] Sending email:");
        System.out.println("   Format: HTML");
        System.out.println("   Content: " + message);
        System.out.println("   HTML Body: " + htmlMessage);
        
        if (settings.isLogEnabled()) {
            System.out.println("[WebEmail] Email sent successfully via Web platform");
        }
    }
    
    @Override
    public String getType() {
        return "EMAIL";
    }
    
    @Override
    public String getPlatform() {
        return "WEB";
    }
}
