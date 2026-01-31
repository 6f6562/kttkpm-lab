package iuh.fit.notification.product;

import iuh.fit.notification.singleton.SettingManager;

/**
 * Concrete Product: MobileSMS
 * Thông báo SMS cho nền tảng Mobile
 */
public class MobileSMS implements Notification {
    private final SettingManager settings;
    
    public MobileSMS() {
        this.settings = SettingManager.getInstance();
    }
    
    @Override
    public void send(String message) {
        if (settings.isLogEnabled()) {
            System.out.println("[MobileSMS] Preparing native mobile SMS...");
        }
        
        // Định dạng thông báo cho Mobile (native SMS format)
        String mobileFormattedMessage = String.format(
            "📱 Mobile SMS\n%s",
            message
        );
        
        System.out.println("💬 [MOBILE SMS] Sending SMS:");
        System.out.println("   Format: Native SMS");
        System.out.println("   Content: " + message);
        System.out.println("   Native Format: " + mobileFormattedMessage);
        System.out.println("   SMS Gateway: Mobile Carrier");
        
        if (settings.isLogEnabled()) {
            System.out.println("[MobileSMS] SMS sent successfully via Mobile platform");
        }
    }
    
    @Override
    public String getType() {
        return "SMS";
    }
    
    @Override
    public String getPlatform() {
        return "MOBILE";
    }
}
