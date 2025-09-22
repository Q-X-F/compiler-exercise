import Lexer.Lexer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Lexer l = new Lexer();
        try {
            while (true) {
                l.nextToken().printToken();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}