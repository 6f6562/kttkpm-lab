package iuh.fit.notification.product;

import iuh.fit.notification.singleton.SettingManager;

/**
 * Concrete Product: MobileEmail
 * Thông báo Email cho nền tảng Mobile
 */
public class MobileEmail implements Notification {
    private final SettingManager settings;
    
    public MobileEmail() {
        this.settings = SettingManager.getInstance();
    }
    
    @Override
    public void send(String message) {
        if (settings.isLogEnabled()) {
            System.out.println("[MobileEmail] Preparing native mobile email...");
        }
        
        // Định dạng thông báo cho Mobile (native format, có thể push notification)
        String mobileFormattedMessage = String.format(
            "📱 Mobile Email Notification\n%s",
            message
        );
        
        System.out.println("📧 [MOBILE EMAIL] Sending email:");
        System.out.println("   Format: Native Mobile");
        System.out.println("   Content: " + message);
        System.out.println("   Native Format: " + mobileFormattedMessage);
        System.out.println("   Push Notification: Enabled");
        
        if (settings.isLogEnabled()) {
            System.out.println("[MobileEmail] Email sent successfully via Mobile platform");
        }
    }
    
    @Override
    public String getType() {
        return "EMAIL";
    }
    
    @Override
    public String getPlatform() {
        return "MOBILE";
    }
}
