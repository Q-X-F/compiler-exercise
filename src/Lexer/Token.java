package Lexer;

public class Token {
    public final Tag tag;
    public Token(Tag tag) {
        this.tag = tag;
    }
    public void printToken() {
        if (this.tag == Tag.ADD) System.out.print("ADD");
        if (this.tag == Tag.SUB) System.out.print("SUB");
        if (this.tag == Tag.POWER) System.out.print("POWER");
        if (this.tag == Tag.COS) System.out.print("COS");
        if (this.tag == Tag.FACT) System.out.print("FACT");
    }
}
