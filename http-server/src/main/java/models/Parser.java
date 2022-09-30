package models;

public class Parser {
    public Parser() {
    }

    public String getEndpoint(String string) {
        String[] parts = string.split(" ");
        return parts[1];
    }
}
