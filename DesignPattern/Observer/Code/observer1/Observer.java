package observer1;

/**
 * Observer (Subscriber) - phía nhận tin.
 * Mọi lớp muốn nhận thông báo từ Subject phải implement interface này.
 */
public interface Observer {

    /**
     * Được Subject gọi khi có thay đổi.
     * context có thể là Stock, Task hoặc một DTO chứa thông tin cập nhật.
     */
    void update(Object context);
}
