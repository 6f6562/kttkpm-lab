package observer1;

/**
 * Concrete Observer - Thành viên nhóm (PM, Developer, ...).
 * Phản ứng khi Task thay đổi trạng thái.
 */
public class TeamMember implements Observer {

    private final String name;
    private final String role; // "PM", "Developer", ...

    public TeamMember(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public void update(Object context) {
        System.out.println("[TeamMember-" + name + "-" + role + "] update() được gọi với context = " + context);
        if (context instanceof Task task) {
            if ("PM".equalsIgnoreCase(role)) {
                System.out.println("[TeamMember-" + name + "-" + role + "] ==> Gửi EMAIL: Task \""
                        + task.getName() + "\" đã chuyển sang trạng thái \"" + task.getStatus() + "\".\n");
            } else if ("Developer".equalsIgnoreCase(role)) {
                System.out.println("[TeamMember-" + name + "-" + role + "] ==> Gửi SLACK: Task \""
                        + task.getName() + "\" -> " + task.getStatus() + ". Cập nhật board.\n");
            } else {
                System.out.println("[TeamMember-" + name + "-" + role + "] ==> Nhận thông báo: Task \""
                        + task.getName() + "\" có trạng thái mới: " + task.getStatus() + ".\n");
            }
        }
    }

    @Override
    public String toString() {
        return "TeamMember{name='" + name + "', role='" + role + "'}";
    }
}
