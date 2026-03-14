package composite1;

/**
 * Component (Thành phần chung): Giao diện cho cả File và Folder.
 * Client gọi showDetails() / getSize() mà không cần biết là File hay Folder.
 */
public interface FileSystemComponent {

    String getName();

    /** In thông tin: với File in tên + dung lượng; với Folder in tên rồi đệ quy từng con. */
    void showDetails();

    /** Dung lượng: File trả size của mình; Folder trả tổng size của tất cả con. */
    long getSize();
}
