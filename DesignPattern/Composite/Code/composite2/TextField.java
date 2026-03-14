package composite2;

public class TextField implements UIComponent {
    private final String name;

    public TextField(String name) {
        this.name = name;
    }

    @Override
    public void render() {
        System.out.println("[LOG] Render TextField: " + name);
    }

    @Override
    public void click() {
        System.out.println("[LOG] Focus TextField: " + name);
    }

    @Override
    public void setTheme(String theme) {
        System.out.println("[LOG] Apply theme '" + theme + "' to TextField: " + name);
    }
}

