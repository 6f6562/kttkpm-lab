package adapter;

/**
 * Demo Adapter Design Pattern: Client chỉ làm việc với JSON,
 * hai adapter (JsonToXmlAdapter, XmlToJsonAdapter) chuyển đổi qua lại với XML.
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println("========== DEMO ADAPTER PATTERN (có log quá trình) ==========\n");

        // 1. Tạo hai adapter cùng implement IDataConverter
        System.out.println("--- Bước 1: Tạo adapter ---");
        IDataConverter requestConverter = new JsonToXmlAdapter();   // JSON -> XML
        IDataConverter responseConverter = new XmlToJsonAdapter();   // XML -> JSON

        // 2. Client chỉ phụ thuộc vào interface IDataConverter
        System.out.println("--- Bước 2: Tạo Client ---");
        Client client = new Client(requestConverter, responseConverter);
        System.out.println();

        // ----- Chiều đi: Client gửi JSON -----
        String jsonRequest = "{\"user\":\"admin\",\"action\":\"login\"}";
        System.out.println("=== Chiều đi (JSON -> XML) ===");
        System.out.println("[Demo] Gọi client.sendData(json): " + jsonRequest);

        String xmlSent = client.sendData(jsonRequest);
        System.out.println("[Demo] Kết quả XML nhận được: " + xmlSent);
        System.out.println();

        // ----- Chiều về: Client nhận kết quả (giả lập hệ thống XML trả "success") -----
        System.out.println("=== Chiều về (XML -> JSON) ===");
        System.out.println("[Demo] Giả lập hệ thống XML trả về: success");
        System.out.println("[Demo] Gọi client.receiveData()");

        String jsonResponse = client.receiveData();
        System.out.println("[Demo] Kết quả JSON nhận được: " + jsonResponse);
        System.out.println();

        // ----- Demo thêm: hệ thống XML trả về dạng thẻ XML -----
        System.out.println("=== Chiều về (lần 2, phản hồi dạng XML) ===");
        client.setSimulatedXmlResponse("<status>ok</status><message>Welcome</message>");
        System.out.println("[Demo] Gọi client.receiveData() với phản hồi XML đã đổi.");

        String jsonResponse2 = client.receiveData();
        System.out.println("[Demo] Kết quả JSON: " + jsonResponse2);
        System.out.println("\n========== KẾT THÚC DEMO ==========");
    }
}
