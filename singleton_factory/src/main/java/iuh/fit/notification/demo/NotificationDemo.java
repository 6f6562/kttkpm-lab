package iuh.fit.notification.demo;

import iuh.fit.notification.NotificationService;
import iuh.fit.notification.factory.MobileFactory;
import iuh.fit.notification.factory.NotificationFactory;
import iuh.fit.notification.factory.WebFactory;
import iuh.fit.notification.product.Notification;
import iuh.fit.notification.singleton.SettingManager;

/**
 * Demo class để chứng minh hoạt động của hệ thống
 * Bao gồm:
 * - Singleton Pattern: SettingManager
 * - Abstract Factory Pattern: Platform Factory
 * - Factory Method Pattern: Notification Type Selection
 */
public class NotificationDemo {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   HỆ THỐNG GỬI THÔNG BÁO ĐA NỀN TẢNG - DEMO                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
        
        // ==========================================
        // 1. DEMO SINGLETON PATTERN
        // ==========================================
        demonstrateSingletonPattern();
        
        // ==========================================
        // 2. DEMO ABSTRACT FACTORY PATTERN
        // ==========================================
        demonstrateAbstractFactoryPattern();
        
        // ==========================================
        // 3. DEMO FACTORY METHOD PATTERN
        // ==========================================
        demonstrateFactoryMethodPattern();
        
        // ==========================================
        // 4. DEMO TÍCH HỢP TẤT CẢ PATTERNS
        // ==========================================
        demonstrateIntegratedSystem();
        
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    DEMO HOÀN TẤT                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Demo Singleton Pattern: Chứng minh chỉ có một instance của SettingManager
     */
    private static void demonstrateSingletonPattern() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("1. DEMO SINGLETON PATTERN - SettingManager");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        
        // Lấy instance lần 1
        SettingManager settings1 = SettingManager.getInstance();
        System.out.println("✓ Lấy instance lần 1: " + settings1.getSettingsInfo());
        
        // Lấy instance lần 2
        SettingManager settings2 = SettingManager.getInstance();
        System.out.println("✓ Lấy instance lần 2: " + settings2.getSettingsInfo());
        
        // Kiểm tra xem có phải cùng một instance không
        if (settings1 == settings2) {
            System.out.println("✓ XÁC NHẬN: Cả hai đều trỏ đến cùng một instance (Singleton Pattern hoạt động đúng)");
        }
        
        // Thay đổi cài đặt
        System.out.println("\n→ Thay đổi cài đặt log:");
        settings1.setLogEnabled(false);
        System.out.println("✓ Instance 1: " + settings1.getSettingsInfo());
        System.out.println("✓ Instance 2: " + settings2.getSettingsInfo());
        System.out.println("✓ XÁC NHẬN: Thay đổi ở instance 1 cũng ảnh hưởng đến instance 2 (cùng một object)");
        
        // Khôi phục lại
        settings1.setLogEnabled(true);
    }
    
    /**
     * Demo Abstract Factory Pattern: Chứng minh các Factory tạo ra các nhóm sản phẩm phù hợp
     */
    private static void demonstrateAbstractFactoryPattern() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("2. DEMO ABSTRACT FACTORY PATTERN - Platform Factory");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        
        // Tạo Web Factory
        NotificationFactory webFactory = new WebFactory();
        System.out.println("✓ Tạo WebFactory: " + webFactory.getPlatformName());
        
        // Tạo Mobile Factory
        NotificationFactory mobileFactory = new MobileFactory();
        System.out.println("✓ Tạo MobileFactory: " + mobileFactory.getPlatformName());
        
        // Web Factory tạo Web products
        Notification webEmail = webFactory.createNotification("EMAIL");
        Notification webSMS = webFactory.createNotification("SMS");
        
        System.out.println("\n✓ WebFactory tạo ra:");
        System.out.println("  - " + webEmail.getPlatform() + " " + webEmail.getType());
        System.out.println("  - " + webSMS.getPlatform() + " " + webSMS.getType());
        
        // Mobile Factory tạo Mobile products
        Notification mobileEmail = mobileFactory.createNotification("EMAIL");
        Notification mobileSMS = mobileFactory.createNotification("SMS");
        
        System.out.println("\n✓ MobileFactory tạo ra:");
        System.out.println("  - " + mobileEmail.getPlatform() + " " + mobileEmail.getType());
        System.out.println("  - " + mobileSMS.getPlatform() + " " + mobileSMS.getType());
        
        System.out.println("\n✓ XÁC NHẬN: Mỗi Factory chỉ tạo ra các sản phẩm thuộc nền tảng của nó");
    }
    
    /**
     * Demo Factory Method Pattern: Chứng minh cách Factory chọn loại thông báo
     */
    private static void demonstrateFactoryMethodPattern() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("3. DEMO FACTORY METHOD PATTERN - Notification Type Selection");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        
        NotificationFactory webFactory = new WebFactory();
        
        System.out.println("→ Sử dụng Factory Method với type = 'EMAIL':");
        Notification email1 = webFactory.createNotification("EMAIL");
        System.out.println("  ✓ Tạo được: " + email1.getPlatform() + " " + email1.getType());
        
        System.out.println("\n→ Sử dụng Factory Method với type = 'SMS':");
        Notification sms1 = webFactory.createNotification("SMS");
        System.out.println("  ✓ Tạo được: " + sms1.getPlatform() + " " + sms1.getType());
        
        System.out.println("\n→ Sử dụng Factory Method với type không phân biệt hoa thường:");
        Notification email2 = webFactory.createNotification("email");
        Notification sms2 = webFactory.createNotification("sms");
        System.out.println("  ✓ 'email' → " + email2.getPlatform() + " " + email2.getType());
        System.out.println("  ✓ 'sms' → " + sms2.getPlatform() + " " + sms2.getType());
        
        System.out.println("\n✓ XÁC NHẬN: Factory Method cho phép chọn loại thông báo linh hoạt");
    }
    
    /**
     * Demo hệ thống tích hợp: Sử dụng tất cả các patterns cùng lúc
     */
    private static void demonstrateIntegratedSystem() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("4. DEMO TÍCH HỢP - Sử dụng tất cả Patterns cùng lúc");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        
        NotificationService service = new NotificationService();
        
        // Gửi thông báo qua Web Email
        System.out.println("📌 Test 1: Gửi Email qua Web Platform");
        service.sendNotification("WEB", "EMAIL", "Chào mừng bạn đến với hệ thống Web!");
        
        // Gửi thông báo qua Web SMS
        System.out.println("📌 Test 2: Gửi SMS qua Web Platform");
        service.sendNotification("WEB", "SMS", "Bạn có một tin nhắn mới từ hệ thống Web");
        
        // Gửi thông báo qua Mobile Email
        System.out.println("📌 Test 3: Gửi Email qua Mobile Platform");
        service.sendNotification("MOBILE", "EMAIL", "Chào mừng bạn đến với ứng dụng Mobile!");
        
        // Gửi thông báo qua Mobile SMS
        System.out.println("📌 Test 4: Gửi SMS qua Mobile Platform");
        service.sendNotification("MOBILE", "SMS", "Bạn có một tin nhắn mới từ ứng dụng Mobile");
        
        // Test với các case khác nhau
        System.out.println("📌 Test 5: Gửi với các format khác nhau (không phân biệt hoa thường)");
        service.sendNotification("web", "email", "Test không phân biệt hoa thường");
        service.sendNotification("mobile", "sms", "Test không phân biệt hoa thường");
        
        System.out.println("\n✓ XÁC NHẬN: Hệ thống hoạt động tốt với tất cả các patterns tích hợp");
    }
}
