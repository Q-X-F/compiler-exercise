import Lexer.Lexer;
import Lexer.Token;
import Lexer.Tag;
import Parser.Parser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Lexer l = new Lexer();
        // try {
        //     Token t;
        //     for ( ; ; ) {
        //         t = l.nextToken();
        //         if (t.tag == Tag.END) return;
        //         t.printToken();
        //     }
        // } catch (IOException e) {
        //     throw new RuntimeException(e.getMessage());
        // }
        Parser p = new Parser();
        try {
            p.printParseTree(p.generateParseTree());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}