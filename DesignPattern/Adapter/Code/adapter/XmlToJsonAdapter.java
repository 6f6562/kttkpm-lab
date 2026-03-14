package adapter;

/**
 * Adapter XML → JSON: nhận XML (hoặc kết quả dạng success), chuyển sang JSON.
 * Ví dụ: &lt;status&gt;success&lt;/status&gt; hoặc "success" → {"status":"success"}
 */
public class XmlToJsonAdapter implements IDataConverter {

    @Override
    public String convert(String xml) {
        System.out.println("[XmlToJsonAdapter] Nhận dữ liệu từ hệ thống XML: " + xml);
        if (xml == null || xml.trim().isEmpty()) {
            System.out.println("[XmlToJsonAdapter] Dữ liệu rỗng -> trả {}.");
            return "{}";
        }
        String json = xmlToJson(xml.trim());
        System.out.println("[XmlToJsonAdapter] Đã chuyển XML -> JSON: " + json);
        return json;
    }

    /**
     * Chuyển chuỗi XML đơn giản (hoặc plain text) sang JSON.
     */
    private String xmlToJson(String xml) {
        // Nếu là plain text (ví dụ "success"), bọc vào status
        if (!xml.contains("<") && !xml.contains(">")) {
            return "{\"status\":\"" + escapeJson(xml) + "\"}";
        }
        StringBuilder json = new StringBuilder("{");
        int i = 0;
        boolean first = true;
        while (i < xml.length()) {
            int open = xml.indexOf('<', i);
            if (open < 0) break;
            int close = xml.indexOf('>', open);
            if (close < 0) break;
            String tag = xml.substring(open + 1, close).trim();
            if (tag.startsWith("/")) {
                i = close + 1;
                continue;
            }
            int endTag = xml.indexOf("</" + tag + ">", close + 1);
            if (endTag < 0) {
                i = close + 1;
                continue;
            }
            String value = xml.substring(close + 1, endTag).trim();
            if (!first) json.append(",");
            json.append("\"").append(escapeJson(tag)).append("\":\"")
                .append(escapeJson(value)).append("\"");
            first = false;
            i = endTag + tag.length() + 3;
        }
        json.append("}");
        return json.toString();
    }

    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
