package composite1;

/**
 * Leaf (Lá): Tập tin — không chứa thành phần con.
 */
public class File implements FileSystemComponent {

    private final String name;
    private final long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
        System.out.println("[LOG] File được tạo: \"" + name + "\" (size=" + size + ")");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void showDetails() {
        System.out.println("[LOG] File.showDetails() được gọi -> \"" + name + "\"");
        System.out.println("  [File] " + name + " (" + size + " bytes)");
    }

    @Override
    public long getSize() {
        System.out.println("[LOG] File.getSize() được gọi -> \"" + name + "\" trả về " + size);
        return size;
    }
}
