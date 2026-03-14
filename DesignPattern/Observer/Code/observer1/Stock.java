package observer1;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Subject - Cổ phiếu (kịch bản 1: Stock Market).
 * Khi giá thay đổi, sẽ log quá trình và thông báo cho tất cả Investor đã đăng ký.
 */
public class Stock implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private final String symbol;
    private double price;

    public Stock(String symbol, double initialPrice) {
        this.symbol = symbol;
        this.price = initialPrice;
        System.out.println("[Stock] Tạo cổ phiếu " + symbol + " với giá ban đầu " + price);
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice) {
        System.out.println("\n[Stock] Yêu cầu cập nhật giá " + symbol + " từ " + price + " -> " + newPrice);
        if (Double.compare(this.price, newPrice) == 0) {
            System.out.println("[Stock] Giá không đổi, KHÔNG notify observers.");
            return;
        }
        this.price = newPrice;
        System.out.println("[Stock] Giá đã đổi, bắt đầu notify observers...");
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("[Stock] + attach observer: " + observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("[Stock] - detach observer: " + observer);
        }
    }

    @Override
    public void notifyObservers() {
        System.out.println("[Stock] -> notifyObservers(), tổng số observer: " + observers.size());
        for (Observer observer : observers) {
            System.out.println("[Stock]   Gọi update() trên: " + observer);
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
