import Lexer.Lexer;
import Lexer.Token;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Lexer l = new Lexer();
        try {
            Token t;
            for ( ; ; ) {
                t = l.nextToken();
                if (t == null) return;
                t.printToken();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}