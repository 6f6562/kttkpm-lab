package composite2;

public class Button implements UIComponent {
    private final String label;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public void render() {
        System.out.println("[LOG] Render Button: " + label);
    }

    @Override
    public void click() {
        System.out.println("[LOG] Click Button: " + label);
    }

    @Override
    public void setTheme(String theme) {
        System.out.println("[LOG] Apply theme '" + theme + "' to Button: " + label);
    }
}

