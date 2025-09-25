import Lexer.Lexer;
import Lexer.Token;
import Lexer.Tag;
import Parser.Parser;

import java.io.IOException;

public class Main {

    // Test function
    public static void main(String[] args) {
        Parser p = new Parser();
        try {
            p.printParseTree(p.generateParseTree());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}