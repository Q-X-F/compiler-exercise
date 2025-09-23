package Parser;
import Lexer.Token;

// Node in the parse tree. If terminal, token is set. If non-terminal, type is set.
public class Node {
    public final boolean terminal;
    public final Token token;
    public final char type;
    public final Node optChildA;
    public final Node optChildB;
    public final Node optChildC;

    public Node(Token token) {
        this.terminal = true;
        this.token = token;
        this.type = '\0';
        this.optChildA = null;
        this.optChildB = null;
        this.optChildC = null;
    }

    public Node(char type, Node optChildA) {
        this.terminal = false;
        this.token = null;
        this.type = type;
        this.optChildA = optChildA;
        this.optChildB = null;
        this.optChildC = null;
    }

    public Node(char type, Node optChildA, Node optChildB) {
        this.terminal = false;
        this.token = null;
        this.type = type;
        this.optChildA = optChildA;
        this.optChildB = optChildB;
        this.optChildC = null;
    }

    public Node(char type, Node optChildA, Node optChildB, Node optChildC) {
        this.terminal = false;
        this.token = null;
        this.type = type;
        this.optChildA = optChildA;
        this.optChildB = optChildB;
        this.optChildC = optChildC;
    }
}
