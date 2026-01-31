package iuh.fit.notification;

import iuh.fit.notification.factory.MobileFactory;
import iuh.fit.notification.factory.NotificationFactory;
import iuh.fit.notification.factory.WebFactory;
import iuh.fit.notification.product.MobileEmail;
import iuh.fit.notification.product.MobileSMS;
import iuh.fit.notification.product.Notification;
import iuh.fit.notification.product.WebEmail;
import iuh.fit.notification.product.WebSMS;
import iuh.fit.notification.singleton.SettingManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class cho hệ thống gửi thông báo đa nền tảng
 */
class NotificationTest {
    
    private NotificationService service;
    private SettingManager settings;
    
    @BeforeEach
    void setUp() {
        service = new NotificationService();
        settings = SettingManager.getInstance();
        settings.setLogEnabled(false); // Tắt log để test output sạch hơn
    }
    
    // ==========================================
    // TEST SINGLETON PATTERN
    // ==========================================
    
    @Test
    @DisplayName("Test Singleton Pattern - Chỉ có một instance")
    void testSingletonPattern() {
        SettingManager instance1 = SettingManager.getInstance();
        SettingManager instance2 = SettingManager.getInstance();
        
        assertSame(instance1, instance2, "Phải là cùng một instance");
        assertEquals(instance1, instance2, "Hai instance phải bằng nhau");
    }
    
    @Test
    @DisplayName("Test Singleton Pattern - Thay đổi cài đặt ảnh hưởng đến tất cả")
    void testSingletonSettings() {
        SettingManager instance1 = SettingManager.getInstance();
        SettingManager instance2 = SettingManager.getInstance();
        
        instance1.setLogEnabled(true);
        assertTrue(instance2.isLogEnabled(), "Instance 2 phải có cùng cài đặt với instance 1");
        
        instance2.setLogEnabled(false);
        assertFalse(instance1.isLogEnabled(), "Instance 1 phải có cùng cài đặt với instance 2");
    }
    
    // ==========================================
    // TEST ABSTRACT FACTORY PATTERN
    // ==========================================
    
    @Test
    @DisplayName("Test Abstract Factory - WebFactory tạo Web products")
    void testWebFactoryCreatesWebProducts() {
        NotificationFactory webFactory = new WebFactory();
        
        Notification email = webFactory.createNotification("EMAIL");
        Notification sms = webFactory.createNotification("SMS");
        
        assertInstanceOf(WebEmail.class, email, "WebFactory phải tạo WebEmail");
        assertInstanceOf(WebSMS.class, sms, "WebFactory phải tạo WebSMS");
        
        assertEquals("WEB", email.getPlatform(), "Platform phải là WEB");
        assertEquals("WEB", sms.getPlatform(), "Platform phải là WEB");
    }
    
    @Test
    @DisplayName("Test Abstract Factory - MobileFactory tạo Mobile products")
    void testMobileFactoryCreatesMobileProducts() {
        NotificationFactory mobileFactory = new MobileFactory();
        
        Notification email = mobileFactory.createNotification("EMAIL");
        Notification sms = mobileFactory.createNotification("SMS");
        
        assertInstanceOf(MobileEmail.class, email, "MobileFactory phải tạo MobileEmail");
        assertInstanceOf(MobileSMS.class, sms, "MobileFactory phải tạo MobileSMS");
        
        assertEquals("MOBILE", email.getPlatform(), "Platform phải là MOBILE");
        assertEquals("MOBILE", sms.getPlatform(), "Platform phải là MOBILE");
    }
    
    @Test
    @DisplayName("Test Abstract Factory - Mỗi Factory có platform name riêng")
    void testFactoryPlatformNames() {
        NotificationFactory webFactory = new WebFactory();
        NotificationFactory mobileFactory = new MobileFactory();
        
        assertEquals("WEB", webFactory.getPlatformName(), "WebFactory phải có platform name là WEB");
        assertEquals("MOBILE", mobileFactory.getPlatformName(), "MobileFactory phải có platform name là MOBILE");
    }
    
    // ==========================================
    // TEST FACTORY METHOD PATTERN
    // ==========================================
    
    @Test
    @DisplayName("Test Factory Method - Tạo EMAIL notification")
    void testFactoryMethodCreateEmail() {
        NotificationFactory webFactory = new WebFactory();
        NotificationFactory mobileFactory = new MobileFactory();
        
        Notification webEmail = webFactory.createNotification("EMAIL");
        Notification mobileEmail = mobileFactory.createNotification("EMAIL");
        
        assertEquals("EMAIL", webEmail.getType(), "Type phải là EMAIL");
        assertEquals("EMAIL", mobileEmail.getType(), "Type phải là EMAIL");
    }
    
    @Test
    @DisplayName("Test Factory Method - Tạo SMS notification")
    void testFactoryMethodCreateSMS() {
        NotificationFactory webFactory = new WebFactory();
        NotificationFactory mobileFactory = new MobileFactory();
        
        Notification webSMS = webFactory.createNotification("SMS");
        Notification mobileSMS = mobileFactory.createNotification("SMS");
        
        assertEquals("SMS", webSMS.getType(), "Type phải là SMS");
        assertEquals("SMS", mobileSMS.getType(), "Type phải là SMS");
    }
    
    @Test
    @DisplayName("Test Factory Method - Không phân biệt hoa thường")
    void testFactoryMethodCaseInsensitive() {
        NotificationFactory webFactory = new WebFactory();
        
        Notification email1 = webFactory.createNotification("EMAIL");
        Notification email2 = webFactory.createNotification("email");
        Notification email3 = webFactory.createNotification("Email");
        
        assertEquals(email1.getType(), email2.getType(), "Không phân biệt hoa thường");
        assertEquals(email1.getType(), email3.getType(), "Không phân biệt hoa thường");
    }
    
    @Test
    @DisplayName("Test Factory Method - Ném exception khi type không hợp lệ")
    void testFactoryMethodInvalidType() {
        NotificationFactory webFactory = new WebFactory();
        
        assertThrows(IllegalArgumentException.class, () -> {
            webFactory.createNotification("INVALID");
        }, "Phải ném exception khi type không hợp lệ");
        
        assertThrows(IllegalArgumentException.class, () -> {
            webFactory.createNotification(null);
        }, "Phải ném exception khi type là null");
    }
    
    // ==========================================
    // TEST NOTIFICATION SERVICE
    // ==========================================
    
    @Test
    @DisplayName("Test NotificationService - Tạo Factory đúng platform")
    void testNotificationServiceCreateFactory() {
        NotificationFactory webFactory = service.createFactory("WEB");
        NotificationFactory mobileFactory = service.createFactory("MOBILE");
        
        assertInstanceOf(WebFactory.class, webFactory, "Phải tạo WebFactory");
        assertInstanceOf(MobileFactory.class, mobileFactory, "Phải tạo MobileFactory");
    }
    
    @Test
    @DisplayName("Test NotificationService - Gửi notification thành công")
    void testNotificationServiceSendNotification() {
        assertDoesNotThrow(() -> {
            service.sendNotification("WEB", "EMAIL", "Test message");
            service.sendNotification("WEB", "SMS", "Test message");
            service.sendNotification("MOBILE", "EMAIL", "Test message");
            service.sendNotification("MOBILE", "SMS", "Test message");
        }, "Phải gửi notification thành công");
    }
    
    @Test
    @DisplayName("Test NotificationService - Ném exception khi platform không hợp lệ")
    void testNotificationServiceInvalidPlatform() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.createFactory("INVALID");
        }, "Phải ném exception khi platform không hợp lệ");
    }
    
    // ==========================================
    // TEST NOTIFICATION PRODUCTS
    // ==========================================
    
    @Test
    @DisplayName("Test Notification Products - Gửi message thành công")
    void testNotificationProductsSend() {
        Notification webEmail = new WebEmail();
        Notification webSMS = new WebSMS();
        Notification mobileEmail = new MobileEmail();
        Notification mobileSMS = new MobileSMS();
        
        assertDoesNotThrow(() -> {
            webEmail.send("Test message");
            webSMS.send("Test message");
            mobileEmail.send("Test message");
            mobileSMS.send("Test message");
        }, "Tất cả products phải gửi được message");
    }
    
    @Test
    @DisplayName("Test Notification Products - Có đúng type và platform")
    void testNotificationProductsTypeAndPlatform() {
        Notification webEmail = new WebEmail();
        Notification webSMS = new WebSMS();
        Notification mobileEmail = new MobileEmail();
        Notification mobileSMS = new MobileSMS();
        
        assertEquals("EMAIL", webEmail.getType());
        assertEquals("WEB", webEmail.getPlatform());
        
        assertEquals("SMS", webSMS.getType());
        assertEquals("WEB", webSMS.getPlatform());
        
        assertEquals("EMAIL", mobileEmail.getType());
        assertEquals("MOBILE", mobileEmail.getPlatform());
        
        assertEquals("SMS", mobileSMS.getType());
        assertEquals("MOBILE", mobileSMS.getPlatform());
    }
}
