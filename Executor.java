
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author John
 */
public class Executor {

    private static TabelaSimbolos<Variavel> tabelaVariaveis;
    private static TabelaSimbolos<Funcao> tabelaFuncoes;
    private static LinkedList<Funcao> funcoesUsadas;
    private static final ErrorHandler handler = new ErrorHandler();
    private static final Stack<Double> pilha = new Stack<>();

    private boolean verificaVariavel(Variavel a) {
        return tabelaVariaveis.lookUp(a);
    }

    private double pop() {
        return pilha.pop();
    }

    private void push(double item) {
        pilha.push(item);
    }

    public Executor() {
    }

    public void run(AST no) {
        run(no, no.getType());
    }

    public void run(AST no, int tipo) {
        int tam = no.getChildCount();
        switch (tipo) {
            case 1: {
                int i;
                tabelaFuncoes = new TabelaSimbolos<>();
                for (i = tam - 1; i >= 0; i--) {
                    if (no.getChild(i).getType() != 54) {
                        break;
                    }
                    run(no.getChild(i));
                }
                tabelaVariaveis = new TabelaSimbolos<>();
                int j;
                for (j = 0; j < i; j++) {
                    run(no.getChild(j));
                }
                run(no.getChild(i));
                break;
            }
            case 4: {
                TpPrimitivo tp = TpPrimitivo.translateTipo(no.getChild(0).getType());
                for (int i = 1; i < tam; i++) {
                    AST filho = no.getChild(i);
                    int lineNum = filho.getLine();
                    Variavel v = new Variavel(filho.getName(), tp, lineNum);
                    if (!tabelaVariaveis.add(v)) {
                        Variavel original = tabelaVariaveis.get(v);
                        handler.instantiateErro(original, lineNum);
                    }
                }
                break;
            }
            case 21: {
                for (int i = 0; i < tam; i++) {
                    run(no.getChild(i));
                }
                break;
            }
            case 23: {
                if (tam > 0) {
                    run(no.getChild(0));
                }
                break;
            }
            case 24: {
                AST variavel = no.getChild(0);
                Variavel v = new Variavel(variavel.getName(), TpPrimitivo.INDEFINIDO, variavel.getLine());
                boolean existe = verificaVariavel(v);
                if (!existe) {
                    handler.instantiateErro(v);
                }
                run(no.getChild(1));
                double valor = pop();
                v.setValor(valor);
                break;
            }
            case 25: {
                int linha = no.getLine();
                run(no.getChild(0));
                double verificacao = pop();
                if(verificacao != 0){
                    run(no.getChild(1));
                }else if(tam > 2){
                    run(no.getChild(2));
                }
//                if (verificacao != TpPrimitivo.LOGICO) {
//                    handler.instantiateErro(linha);
//                }

                break;
            }
        }
    }
}
