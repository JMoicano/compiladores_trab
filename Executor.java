
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author John
 */
public class Executor {

    private TabelaSimbolos<Variavel> tabelaVariaveis;
    private TabelaSimbolos<Funcao> tabelaFuncoes;
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

    public Executor(TabelaSimbolos<Variavel> tabelaVariaveis, TabelaSimbolos<Funcao> tabelaFuncoes) {
        this.tabelaVariaveis = tabelaVariaveis;
        this.tabelaFuncoes = tabelaFuncoes;
    }

    public void run(AST no) {
        //System.out.printf("No tipo %s, %d filhos\n", no.getType(), no.getChildCount());
        run(no, no.getType());
    }

    public void run(AST no, int tipo) {
        int tam = no.getChildCount();
        switch (tipo) {
            case GPortugolParser.T_ALGORITMO | GPortugolParser.T_ENTAO | GPortugolParser.T_SENAO: {
                for (AST child : no.getChildren()) {
                    run(child);
                }
                break;
            }
            case GPortugolParser.T_RETORNE: {
                break;
            }
            case GPortugolParser.T_ATTR: {
                String nome = no.getChild(0).getName();
                int escopo = (int) no.getChild(0).getValue();
                Variavel f = new Variavel(nome, TpPrimitivo.INDEFINIDO, -1, escopo);
                Variavel v = tabelaVariaveis.get(f);
                run(no.getChild(1));
                v.setValor(pop());
                break;
            }
            case GPortugolParser.T_SE: {
                run(no.getChild(0));
                int verificacao = (int) pop();
                if(verificacao != 0){
                   run(no.getChild(1)); 
                }else if(tam > 2){
                    run(no.getChild(2));
                }
                break;
            }
            case GPortugolParser.T_ENQUANTO:{
                run(no.getChild(0));
                int verificacao = (int)pop();
                while(verificacao != 0){
                    for(int i = 1; i < tam; i++){
                        run(no.getChild(i));
                    }
                    run(no.getChild(0));
                    verificacao = (int)pop();
                }
            }
        }
    }
}
