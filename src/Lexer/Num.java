package Lexer;

public class Num extends Token {
    public final double value;
    public Num(double value) {
        super(Tag.NUM);
        this.value = value;
    }

    @Override
    public void printToken() {
        System.out.print("NUM(" + this.value + ")");
    }
}
