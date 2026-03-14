package adapter;

/**
 * Client (Hệ thống mới): Chỉ làm việc với JSON.
 * - requestConverter: chuyển JSON → XML khi gửi (sendData).
 * - responseConverter: chuyển XML → JSON khi nhận (receiveData).
 */
public class Client {

    private final IDataConverter requestConverter;  // JSON -> XML
    private final IDataConverter responseConverter; // XML -> JSON

    /** Dữ liệu giả lập từ "hệ thống XML" (Adaptee ẩn): sau khi gửi XML, nhận về kết quả dạng success/XML. */
    private String lastSentXml;
    private String simulatedXmlResponse = "success";

    public Client(IDataConverter requestConverter, IDataConverter responseConverter) {
        this.requestConverter = requestConverter;
        this.responseConverter = responseConverter;
        System.out.println("[Client] Khởi tạo với requestConverter (JSON->XML) và responseConverter (XML->JSON).");
    }

    /**
     * Gửi dữ liệu JSON: Client gọi → adapter chuyển JSON sang XML.
     * (Trong demo, XML được lưu lại để mô phỏng gửi đi; thực tế có thể gửi tới hệ thống XML.)
     */
    public String sendData(String json) {
        System.out.println("[Client] sendData(json) được gọi -> gửi JSON qua requestConverter (JSON->XML).");
        String xml = requestConverter.convert(json);
        lastSentXml = xml;
        System.out.println("[Client] Đã nhận XML từ adapter, lưu lastSentXml (giả lập gửi tới hệ thống XML).");
        return xml;
    }

    /**
     * Nhận dữ liệu: giả lập hệ thống XML trả về kết quả (success hoặc XML).
     * Adapter chuyển XML/success sang JSON và trả cho Client.
     */
    public String receiveData() {
        System.out.println("[Client] receiveData() được gọi -> lấy phản hồi giả lập từ hệ thống XML, đưa qua responseConverter (XML->JSON).");
        String xmlFromSystem = simulatedXmlResponse;
        String json = responseConverter.convert(xmlFromSystem);
        System.out.println("[Client] Đã nhận JSON từ adapter, trả về cho người gọi.");
        return json;
    }

    /** Trong demo: thiết lập phản hồi giả lập từ hệ thống XML (plain "success" hoặc chuỗi XML). */
    public void setSimulatedXmlResponse(String simulatedXmlResponse) {
        this.simulatedXmlResponse = simulatedXmlResponse != null ? simulatedXmlResponse : "success";
    }

    public String getLastSentXml() {
        return lastSentXml;
    }
}
