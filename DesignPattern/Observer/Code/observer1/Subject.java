package observer1;

import java.util.List;

/**
 * Subject (Publisher) - phía phát tin.
 * Cho phép Observer đăng ký / hủy đăng ký và thông báo khi trạng thái thay đổi.
 */
public interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();
}
