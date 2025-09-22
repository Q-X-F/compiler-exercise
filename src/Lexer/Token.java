package Lexer;

public class Token {
    public final Tag tag;
    public Token(Tag tag) {
        this.tag = tag;
    }
    public void printToken() {
        if (this.tag == Tag.ADD) System.out.println("ADD");
        if (this.tag == Tag.SUB) System.out.println("SUB");
        if (this.tag == Tag.POWER) System.out.println("POWER");
        if (this.tag == Tag.COS) System.out.println("COS");
        if (this.tag == Tag.FACT) System.out.println("FACT");
    }
}
