import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        Trab2Lexer lexer = new Trab2Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Trab2Parser parser = new Trab2Parser(tokens);
        ParseTree tree = parser.algoritmo(); // parse

        Trab2BaseVisitor visitor = new Trab2BaseVisitor();
        visitor.visit(tree);
    }
}
