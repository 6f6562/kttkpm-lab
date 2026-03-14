package adapter;

/**
 * Target (Interface mục tiêu): Giao diện chung cho mọi adapter chuyển đổi định dạng dữ liệu.
 * Client chỉ phụ thuộc vào interface này, không phụ thuộc vào từng adapter cụ thể.
 */
public interface IDataConverter {

    /**
     * Chuyển đổi dữ liệu từ định dạng này sang định dạng khác.
     *
     * @param data Chuỗi dữ liệu đầu vào (JSON hoặc XML tùy adapter).
     * @return Chuỗi dữ liệu sau khi chuyển đổi.
     */
    String convert(String data);
}
