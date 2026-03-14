package composite1;

/**
 * Client: Xây dựng cây thư mục và gọi showDetails/getSize trên root.
 * Không cần biết root là File hay Folder — xử lý đồng nhất (Composite).
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println("========== BẮT ĐẦU XÂY DỰNG CÂY THƯ MỤC ==========\n");

        // Root folder
        System.out.println("--- Tạo Root ---");
        Folder root = new Folder("Root");

        // File trực tiếp trong Root
        System.out.println("--- Thêm file vào Root ---");
        root.add(new File("readme.txt", 1024));
        root.add(new File("config.ini", 256));

        // Sub-folder 1
        System.out.println("--- Tạo Sub-folder 1 và thêm file ---");
        Folder subFolder1 = new Folder("Sub-folder 1");
        subFolder1.add(new File("File A", 512));
        subFolder1.add(new File("File B", 768));

        // Sub-folder 2 bên trong Sub-folder 1
        System.out.println("--- Tạo Sub-folder 2 (lồng trong Sub-folder 1) ---");
        Folder subFolder2 = new Folder("Sub-folder 2");
        subFolder2.add(new File("nested.txt", 128));
        subFolder1.add(subFolder2);

        System.out.println("--- Gắn Sub-folder 1 vào Root ---");
        root.add(subFolder1);

        System.out.println("\n========== CÂY ĐÃ XÂY XONG -> GỌI showDetails() ==========\n");
        root.showDetails();

        System.out.println("\n========== GỌI getSize() (đệ quy tổng dung lượng) ==========\n");
        long total = root.getSize();
        System.out.println("\n>>> Root total size: " + total + " bytes");
        System.out.println("\n========== KẾT THÚC ==========");
    }
}
