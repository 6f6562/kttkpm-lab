package composite2;

import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer implements UIComponent {
    protected final String name;
    protected final List<UIComponent> children = new ArrayList<>();

    protected UIContainer(String name) {
        this.name = name;
    }

    public void add(UIComponent child) {
        children.add(child);
        System.out.println("[LOG] Add " + child.getClass().getSimpleName()
                + " vào " + getClass().getSimpleName() + " '" + name + "'");
    }

    public void remove(UIComponent child) {
        children.remove(child);
        System.out.println("[LOG] Remove " + child.getClass().getSimpleName()
                + " khỏi " + getClass().getSimpleName() + " '" + name + "'");
    }

    @Override
    public void render() {
        System.out.println("[LOG] Render " + getClass().getSimpleName() + ": " + name);
        for (UIComponent child : children) {
            child.render();
        }
    }

    @Override
    public void click() {
        System.out.println("[LOG] Click trên container " + getClass().getSimpleName() + ": " + name);
        for (UIComponent child : children) {
            child.click();
        }
    }

    @Override
    public void setTheme(String theme) {
        System.out.println("[LOG] Apply theme '" + theme + "' cho container " + getClass().getSimpleName()
                + ": " + name);
        for (UIComponent child : children) {
            child.setTheme(theme);
        }
    }
}

