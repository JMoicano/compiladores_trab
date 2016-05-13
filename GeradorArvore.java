import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class GeradorArvore {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        GPortugolLexer lexer = new GPortugolLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GPortugolParser parser = new GPortugolParser(tokens);
        ParseTree tree = parser.algoritmo(); // parse

        System.out.println("digraph {\n" +
                            "graph [ordering=\"out\"];");
        
        VisitorArvore visitor = new VisitorArvore();
        visitor.visit(tree);
        
        System.out.println("}");
    }
}