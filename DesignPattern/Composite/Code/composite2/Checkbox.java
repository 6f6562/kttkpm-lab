package composite2;

public class Checkbox implements UIComponent {
    private final String label;

    public Checkbox(String label) {
        this.label = label;
    }

    @Override
    public void render() {
        System.out.println("[LOG] Render Checkbox: " + label);
    }

    @Override
    public void click() {
        System.out.println("[LOG] Toggle Checkbox: " + label);
    }

    @Override
    public void setTheme(String theme) {
        System.out.println("[LOG] Apply theme '" + theme + "' to Checkbox: " + label);
    }
}

