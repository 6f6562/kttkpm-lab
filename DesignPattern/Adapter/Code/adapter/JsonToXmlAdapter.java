package adapter;

/**
 * Adapter JSON → XML: nhận JSON từ Client, chuyển sang XML string.
 * (Java core, không dùng thư viện ngoài; logic chuyển đổi đơn giản.)
 */
public class JsonToXmlAdapter implements IDataConverter {

    @Override
    public String convert(String json) {
        System.out.println("[JsonToXmlAdapter] Nhận dữ liệu từ Client (JSON): " + json);
        if (json == null || json.trim().isEmpty()) {
            System.out.println("[JsonToXmlAdapter] JSON rỗng -> trả XML rỗng.");
            return "";
        }
        String xml = jsonToXml(json.trim());
        System.out.println("[JsonToXmlAdapter] Đã chuyển JSON -> XML: " + xml);
        return xml;
    }

    /**
     * Chuyển chuỗi JSON đơn giản sang XML.
     * Ví dụ: {"status":"success"} -> <status>success</status>
     */
    private String jsonToXml(String json) {
        StringBuilder xml = new StringBuilder();
        json = json.replaceAll("^\\s*\\{\\s*|\\s*}\\s*$", "").trim();
        if (json.isEmpty()) {
            return "<root></root>";
        }
        String[] pairs = json.split(",\\s*");
        for (String pair : pairs) {
            pair = pair.trim();
            if (pair.isEmpty()) continue;
            int colon = pair.indexOf(':');
            if (colon <= 0) continue;
            String key = pair.substring(0, colon).trim().replaceAll("^\"|\"$", "");
            String value = pair.substring(colon + 1).trim().replaceAll("^\"|\"$", "");
            xml.append("<").append(key).append(">")
               .append(escapeXml(value))
               .append("</").append(key).append(">");
        }
        return xml.length() > 0 ? xml.toString() : "<root></root>";
    }

    private static String escapeXml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
