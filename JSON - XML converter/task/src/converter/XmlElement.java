package converter;

public class XmlElement {

    private final String element;
    private String name;
    private String content;

    public XmlElement(String element) {
        this.element = element;
        parseName();
        parseContent();
    }

    private boolean isEmptyElement() {
        int firstRightAngleBracketIndex = element.indexOf(">");
        return element.charAt(firstRightAngleBracketIndex - 1) == '/';
    }

    private void parseName() {
        int firstLeftAngleBracketIndex = element.indexOf("<");
        int firstRightAngleBracketIndex = element.indexOf(">");
        if (isEmptyElement()) {
            name = element.substring(firstLeftAngleBracketIndex + 1, firstRightAngleBracketIndex - 1);
        } else {
            name = element.substring(firstLeftAngleBracketIndex + 1, firstRightAngleBracketIndex);
        }
    }

    private void parseContent() {
        if (isEmptyElement()) {
            content = "null";
            return;
        }
        String tag = String.format("<%s>", name);
        String closingTag = String.format("</%s>", name);
        content = element.replace(tag, "").replace(closingTag, "");
    }

    public String toJson() {
        String format = "{\"%s\": \"%s\"}";
        if ("null".equals(content)) {
            format = "{\"%s\": %s}";
        }
        return String.format(format, name, content);
    }
}