package Parser;

import java.io.IOException;
import java.util.Stack;
import Lexer.Token;
import Lexer.Tag;
import Lexer.Lexer;

public class Parser {
    private Stack<Node> nodeStack;
    private Stack<Integer> stateStack;
    private Token nextToken;

    public Parser() {
        this.nodeStack = new Stack<>();
        this.stateStack = new Stack<>();
        this.stateStack.push(0);
        this.nextToken = null;
    }

    public Node generateParseTree() throws IOException, ParsingException {
        boolean shifted = true;
        while (true) {
            Lexer l = new Lexer();
            if (shifted) this.nextToken = l.nextToken();
            if (
                stateStack.peek() == 0 && nodeStack.peek().terminal 
                && nodeStack.peek().token.tag == Tag.END
            ) {
                // Accepted!
                nodeStack.pop();
                return nodeStack.pop();
            }
            shifted = action();
        }
    }


    // Returns true if shifted and false otherwise.
    public boolean action() throws ParsingException {
        switch (stateStack.peek()) {
            case 0:
            case 4:
            case 7:
            case 8:
            case 9:
                switch (nextToken.tag) {
                    case Tag.NUM:
                        stateStack.push(6);
                        nodeStack.push(new Node(nextToken));
                        return true;
                    case Tag.COS:
                        stateStack.push(4);
                        nodeStack.push(new Node(nextToken));
                        return true;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            case 1:
                switch (nextToken.tag) {
                    case Tag.ADD:
                        shift(7);
                        return true;
                    case Tag.SUB:
                        shift(8);
                        return true;
                    case Tag.END:
                        // TODO: Accept
                        nodeStack.push(new Node(nextToken));
                        return true;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            case 2:
                switch (nextToken.tag) {
                    case Tag.ADD:
                    case Tag.SUB:
                    case Tag.END:
                        reduce(3);
                        return false;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            case 3:
                switch (nextToken.tag) {
                    case Tag.ADD:
                    case Tag.SUB:
                    case Tag.END:
                        reduce(5);
                        return false;
                    case Tag.POWER:
                        shift(9);
                        return true;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            case 5:
                switch (nextToken.tag) {
                    case Tag.ADD:
                    case Tag.SUB:
                    case Tag.POWER:
                    case Tag.END:
                        reduce(7);
                        return false;
                    case Tag.FACT:
                        shift(11);
                        return true;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            case 6:
                switch (nextToken.tag) {
                    case Tag.ADD:
                    case Tag.SUB:
                    case Tag.POWER:
                    case Tag.FACT:
                    case Tag.END:
                        reduce(9);
                        return false;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            case 10:
                switch (nextToken.tag) {
                    case Tag.ADD:
                    case Tag.SUB:
                    case Tag.POWER:
                    case Tag.END:
                        reduce(7);
                        return false;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            case 11:
                switch (nextToken.tag) {
                    case Tag.ADD:
                    case Tag.SUB:
                    case Tag.POWER:
                    case Tag.FACT:
                    case Tag.END:
                        reduce(8);
                        return false;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            case 12:
                switch (nextToken.tag) {
                    case Tag.ADD:
                    case Tag.SUB:
                    case Tag.END:
                        reduce(1);
                        return false;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            case 13:
                switch (nextToken.tag) {
                    case Tag.ADD:
                    case Tag.SUB:
                    case Tag.END:
                        reduce(2);
                        return false;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            case 14:
                switch (nextToken.tag) {
                    case Tag.ADD:
                    case Tag.SUB:
                    case Tag.END:
                        reduce(4);
                        return false;
                    default:
                        throw new ParsingException("Invalid action.");
                }
            default:
                throw new ParsingException("State error.");
        }
    }

    public int goTo() throws ParsingException {
        switch (this.stateStack.peek()) {
            case 0:
                switch (this.nodeStack.peek().type) {
                    case 'E': return 1;
                    case 'T': return 2;
                    case 'B': return 3;
                    case 'A': return 5;
                    default:
                        throw new ParsingException("GOTO " + this.stateStack.peek() + " " + this.nodeStack.peek().type + " failed.");
                }
            case 7:
                switch (this.nodeStack.peek().type) {
                    case 'T': return 12;
                    case 'B': return 3;
                    case 'A': return 5;
                    default:
                        throw new ParsingException("GOTO " + this.stateStack.peek() + " " + this.nodeStack.peek().type + " failed.");
                }
            case 8:
                switch (this.nodeStack.peek().type) {
                    case 'T': return 13;
                    case 'B': return 3;
                    case 'A': return 5;
                    default:
                        throw new ParsingException("GOTO " + this.stateStack.peek() + " " + this.nodeStack.peek().type + " failed.");
                }
            case 9:
                switch (this.nodeStack.peek().type) {
                    case 'T': return 14;
                    case 'B': return 3;
                    case 'A': return 5;
                    default:
                        throw new ParsingException("GOTO " + this.stateStack.peek() + " " + this.nodeStack.peek().type + " failed.");
                }
            default:
                throw new ParsingException("GOTO " + this.stateStack.peek() + " " + this.nodeStack.peek().type + " failed.");
        }
    }

    public void shift(int state) {
        stateStack.push(state);
        nodeStack.push(new Node(nextToken));
    }

    public void reduce(int rule) {
        Node e, t, b, a, id, add, sub, power, cos, fact;
        switch (rule) {
            case 1:
                t = nodeStack.pop();
                stateStack.pop();
                add = nodeStack.pop();
                stateStack.pop();
                e = nodeStack.pop();
                stateStack.pop();
                if (e.type == 'E' && add.token.tag == Tag.ADD && t.type == 'T') {
                    nodeStack.push(new Node('E', e, add, t));
                } else throw new ParsingException("Reduce 1 verification failed.");
                break;
            case 2:
                t = nodeStack.pop();
                stateStack.pop();
                sub = nodeStack.pop();
                stateStack.pop();
                e = nodeStack.pop();
                stateStack.pop();
                if (e.type == 'E' && sub.token.tag == Tag.SUB && t.type == 'T') {
                    nodeStack.push(new Node('E', e, sub, t));
                } else throw new ParsingException("Reduce 2 verification failed.");
                break;
            case 3:
                t = nodeStack.pop();
                stateStack.pop();
                if (t.type == 'T') {
                    nodeStack.push(new Node('E', t));
                } else throw new ParsingException("Reduce 3 verification failed.");
                break;
            case 4:
                t = nodeStack.pop();
                stateStack.pop();
                power = nodeStack.pop();
                stateStack.pop();
                b = nodeStack.pop();
                stateStack.pop();
                if (b.type == 'B' && power.token.tag == Tag.POWER && t.type == 'T') {
                    nodeStack.push(new Node('T', b, power, t));
                } else throw new ParsingException("Reduce 4 verification failed.");
                break;
            case 5:
                b = nodeStack.pop();
                stateStack.pop();
                if (b.type == 'B') {
                    nodeStack.push(new Node('T', b));
                } else throw new ParsingException("Reduce 5 verification failed.");
                break;
            case 6:
                b = nodeStack.pop();
                stateStack.pop();
                cos = nodeStack.pop();
                stateStack.pop();
                if (cos.token.tag == Tag.COS && b.type == 'B') {
                    nodeStack.push(new Node('B', cos, b));
                } else throw new ParsingException("Reduce 6 verification failed.");
                break;
            case 7:
                a = nodeStack.pop();
                stateStack.pop();
                if (a.type == 'A') {
                    nodeStack.push(new Node('B', a));
                } else throw new ParsingException("Reduce 7 verification failed.");
                break;
            case 8:
                fact = nodeStack.pop();
                stateStack.pop();
                a = nodeStack.pop();
                stateStack.pop();
                if (a.type == 'A' && fact.token.tag == Tag.FACT) {
                    nodeStack.push(new Node('A', a, fact));
                } else throw new ParsingException("Reduce 8 verification failed.");
                break;
            case 9:
                id = nodeStack.pop();
                stateStack.pop();
                if (id.token.tag == Tag.NUM) {
                    nodeStack.push(new Node('A', id));
                } else throw new ParsingException("Reduce 9 verification failed.");
                break;
            default:
                throw new ParsingException("Invalide rule number.");
        }
        goTo();
    }
}
