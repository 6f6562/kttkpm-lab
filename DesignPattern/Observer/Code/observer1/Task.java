package observer1;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Subject - Công việc (kịch bản 2: Task Tracking).
 * Khi trạng thái thay đổi, sẽ log quá trình và thông báo cho các TeamMember.
 */
public class Task implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private final String name;
    private String status;

    public Task(String name, String initialStatus) {
        this.name = name;
        this.status = initialStatus;
        System.out.println("[Task] Tạo task \"" + name + "\" với trạng thái ban đầu: " + status);
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        System.out.println("\n[Task] Yêu cầu cập nhật trạng thái \"" + name + "\" từ \"" + status + "\" -> \"" + newStatus + "\"");
        if (this.status.equals(newStatus)) {
            System.out.println("[Task] Trạng thái không đổi, KHÔNG notify observers.");
            return;
        }
        this.status = newStatus;
        System.out.println("[Task] Trạng thái đã đổi, bắt đầu notify observers...");
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("[Task] + attach observer: " + observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("[Task] - detach observer: " + observer);
        }
    }

    @Override
    public void notifyObservers() {
        System.out.println("[Task] -> notifyObservers(), tổng số observer: " + observers.size());
        for (Observer observer : observers) {
            System.out.println("[Task]   Gọi update() trên: " + observer);
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
