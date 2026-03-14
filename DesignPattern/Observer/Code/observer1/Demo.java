package observer1;

/**
 * Client: dựng hai kịch bản Observer (Stock + Task) và log chi tiết
 * toàn bộ quá trình attach/detach/notify/update.
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println("=========== OBSERVER PATTERN DEMO (JAVA CORE) ===========\n");

        runStockScenario();

        System.out.println("\n==========================================================\n");

        runTaskScenario();

        System.out.println("\n====================== KẾT THÚC DEMO =====================");
    }

    private static void runStockScenario() {
        System.out.println(">>> KỊCH BẢN 1: THEO DÕI CỔ PHIẾU (STOCK - INVESTOR)\n");

        // 1. Tạo Subject (Stock)
        Stock vnm = new Stock("VNM", 70_000);

        // 2. Tạo Observer (Investor)
        Investor investorA = new Investor("Nguyễn Văn A");
        Investor investorB = new Investor("Trần Thị B");

        // 3. Đăng ký observer
        System.out.println("\n[Client] Đăng ký Investor A và B theo dõi mã VNM");
        vnm.attach(investorA);
        vnm.attach(investorB);

        // 4. Thay đổi giá -> notify
        System.out.println("\n[Client] Cập nhật giá VNM lên 72,000");
        vnm.setPrice(72_000);

        // 5. Hủy đăng ký 1 observer và tiếp tục thay đổi giá
        System.out.println("\n[Client] Hủy đăng ký Investor B, rồi đổi giá xuống 71,000");
        vnm.detach(investorB);
        vnm.setPrice(71_000);
    }

    private static void runTaskScenario() {
        System.out.println(">>> KỊCH BẢN 2: THEO DÕI CÔNG VIỆC (TASK - TEAM MEMBER)\n");

        // 1. Tạo Subject (Task)
        Task task = new Task("Xây API đăng nhập", "To Do");

        // 2. Tạo Observer (TeamMember)
        TeamMember pm = new TeamMember("Mai", "PM");
        TeamMember dev = new TeamMember("Tuấn", "Developer");

        // 3. Đăng ký observer
        System.out.println("\n[Client] Đăng ký PM và Developer theo dõi task");
        task.attach(pm);
        task.attach(dev);

        // 4. Đổi trạng thái -> notify
        System.out.println("\n[Client] Chuyển trạng thái sang In Progress");
        task.setStatus("In Progress");

        // 5. Đổi tiếp sang Done
        System.out.println("\n[Client] Chuyển trạng thái sang Done");
        task.setStatus("Done");
    }
}
