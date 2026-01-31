package iuh.fit.notification.singleton;

/**
 * Singleton Pattern: SettingManager
 * Đảm bảo toàn bộ ứng dụng chỉ có một đối tượng quản lý cài đặt hệ thống
 */
public class SettingManager {
    // Instance duy nhất
    private static SettingManager instance;
    
    // Cài đặt hệ thống
    private boolean isLogEnabled;
    
    /**
     * Constructor private để ngăn việc tạo instance từ bên ngoài
     */
    private SettingManager() {
        this.isLogEnabled = true; // Mặc định bật log
    }
    
    /**
     * Lấy instance duy nhất của SettingManager
     * Thread-safe với double-checked locking
     * 
     * @return Instance duy nhất của SettingManager
     */
    public static SettingManager getInstance() {
        if (instance == null) {
            synchronized (SettingManager.class) {
                if (instance == null) {
                    instance = new SettingManager();
                }
            }
        }
        return instance;
    }
    
    /**
     * Kiểm tra xem log có được bật không
     * 
     * @return true nếu log được bật, false nếu không
     */
    public boolean isLogEnabled() {
        return isLogEnabled;
    }
    
    /**
     * Thiết lập trạng thái log
     * 
     * @param enabled true để bật log, false để tắt
     */
    public void setLogEnabled(boolean enabled) {
        this.isLogEnabled = enabled;
        if (isLogEnabled) {
            System.out.println("[SettingManager] Logging enabled");
        } else {
            System.out.println("[SettingManager] Logging disabled");
        }
    }
    
    /**
     * Lấy thông tin cài đặt hiện tại
     * 
     * @return Chuỗi mô tả cài đặt hiện tại
     */
    public String getSettingsInfo() {
        return String.format("SettingManager [LogEnabled: %s]", isLogEnabled);
    }
}
