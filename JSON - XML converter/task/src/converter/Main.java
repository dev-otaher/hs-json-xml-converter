package converter;

import java.util.Scanner;

public class Main {
    private static boolean isXMLDocument(String input) {
        return input.startsWith("<");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (isXMLDocument(input)) {
            System.out.println(new XmlElement(input).toJson());
        } else {
            System.out.println(new JsonElement(input).toXml());
        }
    }
}