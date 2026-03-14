package composite1;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite (Hợp phần): Thư mục — có thể chứa File và Folder con.
 */
public class Folder implements FileSystemComponent {

    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
        System.out.println("[LOG] Folder được tạo: \"" + name + "\"");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void showDetails() {
        System.out.println("[LOG] Folder.showDetails() được gọi -> \"" + name + "\" (số con: " + children.size() + ")");
        System.out.println("[Folder] " + name);
        for (FileSystemComponent child : children) {
            child.showDetails();
        }
    }

    @Override
    public long getSize() {
        System.out.println("[LOG] Folder.getSize() được gọi -> \"" + name + "\", duyệt " + children.size() + " con");
        long total = 0;
        for (FileSystemComponent child : children) {
            total += child.getSize();
        }
        System.out.println("[LOG] Folder \"" + name + "\" tổng size = " + total);
        return total;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
        System.out.println("[LOG] Folder \"" + name + "\" add: " + component.getName() + " (số con hiện tại: " + children.size() + ")");
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    public List<FileSystemComponent> getChildren() {
        return new ArrayList<>(children);
    }
}
