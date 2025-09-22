package Lexer;

import java.io.IOException;

public class Lexer {
    public int line;
    private char peek;
    public Lexer() {
        this.line = 1;
        this.peek = ' ';
    }

    // Returns next Token from input stream
    public Token nextToken() throws IOException {
        // Ignoring whitespace
        while (true) {
            if (this.peek == '\n') line++;
            else if (this.peek != ' ' && this.peek != '\t') break;
            this.peek = (char)System.in.read();
        }

        // NUM detected
        if (Character.isDigit(this.peek)) {
            return new Num(scanNum());
        }
        // '+' could be ADD or part of NUM
        if (this.peek == '+') {
            this.peek = (char)System.in.read();
            if (Character.isDigit(this.peek)) {
                return new Num(scanNum());
            } else {
                return new Token(Tag.ADD);
            }
        }
        // '-' could be SUB or part of NUM
        if (this.peek == '-') {
            this.peek = (char)System.in.read();
            if (Character.isDigit(this.peek)) {
                return new Num(-scanNum());
            } else {
                return new Token(Tag.SUB);
            }
        }
        // POWER detected
        if (this.peek == '^') {
            this.peek = (char)System.in.read();
            return new Token(Tag.POWER);
        }
        // COS detected
        if (this.peek == 'c') {
            this.peek = (char)System.in.read();
            if (this.peek != 'o') {
                throw new IOException("Unexpected 'c'.");
            }
            this.peek = (char)System.in.read();
            if (this.peek != 's') {
                throw new IOException("Unexpected 'co'.");
            }
            this.peek = (char)System.in.read();
            return new Token(Tag.COS);
        }
        // FACT detected
        if (this.peek == '!') {
            this.peek = (char)System.in.read();
            return new Token(Tag.FACT);
        }
        //
        if (this.peek == (char)-1) return new Token(Tag.END);
        throw new IOException("Unexpected " + this.peek + ".");
    }

    // Returns the value of a NUM from the input stream
    public double scanNum() throws IOException {
        double v = 0.0;
        do {
            v = 10 * v + Character.digit(this.peek, 10);
            this.peek = (char)System.in.read();
        } while (Character.isDigit(this.peek));
        //
        if (this.peek == '.') {
            double place = 0.1;
            this.peek = (char)System.in.read();
            if (!Character.isDigit(this.peek)) {
                throw new IOException("Expected digit after decimal point.");
            }
            do {
                v += place * Character.digit(this.peek, 10);
                place /= 10;
                this.peek = (char)System.in.read();
            } while (Character.isDigit(this.peek));
        }
        //
        if (this.peek == 'e') {
            int expSign = 1;
            this.peek = (char)System.in.read();
            if (this.peek == '+') {
                this.peek = (char)System.in.read();
            } else if (this.peek == '-') {
                expSign = -1;
                this.peek = (char)System.in.read();
            }
            if (!Character.isDigit(this.peek)) {
                throw new IOException("Expected integer for exponential form.");
            }
            int exp = 0;
            do {
                exp = 10 * exp + Character.digit(this.peek, 10);
                this.peek = (char)System.in.read();
            } while (Character.isDigit(this.peek));
            exp *= expSign;
            v *= Math.pow(10, exp);
        }
        //
        return v;
    }
}
