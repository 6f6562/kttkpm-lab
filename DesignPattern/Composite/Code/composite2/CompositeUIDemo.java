package composite2;

public class CompositeUIDemo {
    public static void main(String[] args) {
        // Tạo Window chính (Composite gốc)
        Window mainWindow = new Window("Main Window");

        // Toolbar là một Panel (Composite)
        Panel toolbar = new Panel("Toolbar");
        toolbar.add(new Button("New"));
        toolbar.add(new Button("Open"));
        toolbar.add(new Button("Save"));

        // Content panel là một Panel (Composite)
        Panel contentPanel = new Panel("Content Panel");
        contentPanel.add(new TextField("Search Box"));
        contentPanel.add(new Checkbox("Show advanced options"));

        // Gắn các thành phần vào Window
        mainWindow.add(toolbar);
        mainWindow.add(contentPanel);

        System.out.println("===== RENDER UI =====");
        mainWindow.render();

        System.out.println("===== APPLY THEME DARK =====");
        mainWindow.setTheme("Dark");

        System.out.println("===== CLICK TOÀN BỘ UI =====");
        mainWindow.click();
    }
}

