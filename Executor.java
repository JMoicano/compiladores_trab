
import java.util.Stack;

/**
 *
 * @author John
 */
public class Executor {

    private TabelaSimbolos<Variavel> tabelaVariaveis;
    private TabelaSimbolos<Funcao> tabelaFuncoes;
    private static final Stack<Double> pilha = new Stack<>();

    private double pop() {
        return pilha.pop();
    }

    private void push(double item) {
        pilha.push(item);
    }

    private Variavel getVariavel(AST no) {
        String nome = no.getName();
        int escopo = (int) no.getValue();
        Variavel f = new Variavel(nome, TpPrimitivo.INDEFINIDO, -1, escopo);
        return tabelaVariaveis.get(f);
    }

    private Funcao getFuncao(AST no) {
        String nome = no.getName();
        int escopo = (int) no.getValue();
        Funcao f = new Funcao(nome, null, escopo, escopo, no);
        return tabelaFuncoes.get(f);
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
            case GPortugolParser.T_ALGORITMO:
            case GPortugolParser.T_ENTAO:
            case GPortugolParser.T_SENAO:
            case GPortugolParser.T_RETORNE: {
                for (AST child : no.getChildren()) {
                    run(child);
                }
                break;
            }
            case GPortugolParser.T_ATTR: {
                Variavel v = getVariavel(no.getChild(0));
                run(no.getChild(1));
                if (v.getTipo() == TpPrimitivo.INTEIRO) {
                    v.setValor((int) pop());
                } else {
                    v.setValor(pop());
                }
                break;
            }
            case GPortugolParser.T_SE: {
                run(no.getChild(0));
                int verificacao = (int) pop();
                if (verificacao != 0) {
                    run(no.getChild(1));
                } else if (tam > 2) {
                    run(no.getChild(2));
                }
                break;
            }
            case GPortugolParser.T_ENQUANTO: {
                run(no.getChild(0));
                int verificacao = (int) pop();
                while (verificacao != 0) {
                    for (int i = 1; i < tam; i++) {
                        run(no.getChild(i));
                    }
                    run(no.getChild(0));
                    verificacao = (int) pop();
                }
                break;
            }
            case GPortugolParser.T_PARA: {
                Variavel v = getVariavel(no.getChild(0));
                run(no.getChild(1));
                if (v.getTipo() == TpPrimitivo.INTEIRO) {
                    v.setValor((int) pop());
                    run(no.getChild(2));
                    int limite = (int) pop();
                    int passo = (int) pop();
                    while ((int) v.getValor() != limite) {
                        for (int i = 4; i < tam; i++) {
                            run(no.getChild(i));
                        }
                        v.setValor(v.getValor() + passo);
                    }
                } else {
                    v.setValor(pop());
                    run(no.getChild(2));
                    double limite = pop();
                    run(no.getChild(3));
                    double passo = pop();
                    while ((int) v.getValor() != limite) {
                        for (int i = 4; i < tam; i++) {
                            run(no.getChild(i));
                        }
                        v.setValor(v.getValor() + passo);
                    }
                }
                break;
            }
            case GPortugolParser.T_PASSO: {
                run(no.getChild(1));
                double passo = pop();
                if (no.getChild(0).getType() == GPortugolParser.T_MENOS) {
                    push(-passo);
                } else {
                    push(passo);
                }
                break;
            }
            case GPortugolParser.T_OU: {
                run(no.getChild(0));
                int l = (int) pop();
                run(no.getChild(1));
                int r = (int) pop();
                boolean b = (l == 1) || (r == 1);
                push(b ? 1 : 0);
                break;
            }
            case GPortugolParser.T_E: {
                run(no.getChild(0));
                int l = (int) pop();
                run(no.getChild(1));
                int r = (int) pop();
                boolean b = (l == 1) && (r == 1);
                push(b ? 1 : 0);
                break;
            }
            case GPortugolParser.T_IGUAL: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                boolean b = l == r;
                push(b ? 1 : 0);
                break;
            }
            case GPortugolParser.T_DIFERENTE: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                boolean b = l != r;
                push(b ? 1 : 0);
                break;
            }
            case GPortugolParser.T_MAIOR: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                boolean b = l > r;
                push(b ? 1 : 0);
                break;
            }
            case GPortugolParser.T_MAIORIGUAL: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                boolean b = l >= r;
                push(b ? 1 : 0);
                break;
            }
            case GPortugolParser.T_MENOR: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                boolean b = l < r;
                push(b ? 1 : 0);
                break;
            }
            case GPortugolParser.T_MENORIGUAL: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                boolean b = l <= r;
                push(b ? 1 : 0);
                break;
            }
            case GPortugolParser.T_MAIS: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                push(l + r);
                break;
            }
            case GPortugolParser.T_MENOS: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                push(l - r);
                break;
            }
            case GPortugolParser.T_MULT: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                push(l * r);
                break;
            }
            case GPortugolParser.T_DIV: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                push(l / r);
                break;
            }
            case GPortugolParser.T_MOD: {
                run(no.getChild(0));
                double l = pop();
                run(no.getChild(1));
                double r = pop();
                push(l % r);
                break;
            }
            case GPortugolParser.WS: { //Gambiarra
                run(no.getChild(1));
                double v = pop();
                switch (no.getChild(0).getType()) {
                    case GPortugolParser.T_MAIS: {
                        push(v);
                        break;
                    }
                    case GPortugolParser.T_MENOS: {
                        push(-v);
                        break;
                    }
                    case GPortugolParser.T_NAO: {
                        boolean b = !(v == 1);
                        push(b ? 1 : 0);
                    }
                }
                break;
            }
            case GPortugolParser.T_FUNCAO: {
                Funcao f = getFuncao(no);
                for (int i = tam - 1; i >= 0; i--) {
                    run(no.getChild(i));
                }
                if (no.getName().equals("imprime")) {
                    String out = "";
                    for (int i = 0; i < tam; i++) {
                        out += pop() + "";
                    }
                    System.out.println(out);
                } else {
                    AST funNo = f.getNo();
                    AST paramsNo = funNo.getChild(0);
                    for (AST param : paramsNo.getChildren()) {
                        String nome = param.getName();
                        int escopo = f.getEscopo();
                        Variavel p = new Variavel(nome, TpPrimitivo.REAL, tipo, escopo);
                        Variavel a = tabelaVariaveis.get(p);
                        a.setValor(pop());
                    }
                    run(funNo);
                }
                break;
            }
            case GPortugolParser.ML_COMMENT: {
                break;
            }
            case GPortugolParser.T_INT_LIT: {
                push(no.getValue());
                break;
            }
            case GPortugolParser.T_REAL_LIT: {
                push(no.getValue());
                break;
            }
            case GPortugolParser.T_LOGICO: {
                push(no.getValue());
                break;
            }
        }
    }
}
