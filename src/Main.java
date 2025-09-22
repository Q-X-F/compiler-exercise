import Lexer.Lexer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Lexer l = new Lexer();
        try {
            for (int i = 1; i < 100; i++) {
                l.nextToken().printToken();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}