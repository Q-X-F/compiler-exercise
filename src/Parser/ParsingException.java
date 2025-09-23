package Parser;

public class ParsingException extends RuntimeException {
    String message;
    public ParsingException() {
        this.message = "";
    }
    public ParsingException(String message) {
        this.message = message;
    }
}
