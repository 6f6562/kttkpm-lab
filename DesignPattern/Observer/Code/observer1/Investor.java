package observer1;

/**
 * Concrete Observer - Nhà đầu tư.
 * Nhận thông báo khi giá cổ phiếu thay đổi.
 */
public class Investor implements Observer {

    private final String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(Object context) {
        System.out.println("[Investor-" + name + "] update() được gọi với context = " + context);
        if (context instanceof Stock stock) {
            System.out.println("[Investor-" + name + "] ==> Nhận thông báo: Mã "
                    + stock.getSymbol() + " đã đổi giá, hiện tại = " + stock.getPrice());
            System.out.println("[Investor-" + name + "] ==> Quyết định: kiểm tra lại danh mục đầu tư.\n");
        }
    }

    @Override
    public String toString() {
        return "Investor{name='" + name + "'}";
    }
}
