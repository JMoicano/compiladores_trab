import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Trab2 {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        String outputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        if ( args.length>1 ) outputFile = args[1];
        InputStream is = System.in;
        OutputStream os = System.out;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        if ( outputFile !=null) os = new FileOutputStream(outputFile);
        PrintStream ops = new PrintStream(os);
        System.setOut(ops);
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