package converter;

public class JsonElement {

    private final String element;
    private String key;
    private String value;

    public JsonElement(String element) {
        this.element = element;
        parseKeyValue();
    }

    private void parseKeyValue() {
        String[] keyValue = element.replace("{", "").replace("}", "").replace("\"", "").split(":");
        key = keyValue[0].trim();
        if (element.contains(String.format("\"%s\"", keyValue[1].trim()))) {
            value = keyValue[1].trim();
        } else {
            value = null;
        }
    }

    public String toXml() {
        if (value == null) {
            return String.format("<%s />", key);
        }
        return String.format("<%s>%s</%s>", key, value, key);
    }
}
