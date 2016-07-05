
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author John
 */
public class Funcao {

    private final String nome;
    private final int linha;
    private TpPrimitivo retorno;
    private final LinkedList<TpPrimitivo> parametros;
    private final int escopo;
    private final AST no;

    public AST getNo() {
        return no;
    }

    public Funcao(String nome, LinkedList<TpPrimitivo> argumentos, int linha, int escopo, AST no) {
        this.nome = nome;
        this.linha = linha;
        this.parametros = new LinkedList<>();
        if (argumentos != null) {
            for (TpPrimitivo argumento : argumentos) {
                this.parametros.add(argumento);
            }
        }
        this.escopo = escopo;
        this.no = no;
    }

    public int getEscopo() {
        return escopo;
    }

    public String getNome() {
        return this.nome;
    }

    public int getAridade() {
        return parametros.size();
    }

    public LinkedList<TpPrimitivo> getParametros() {
        return this.parametros;
    }

    public void setRetorno(TpPrimitivo retorno) {
        this.retorno = retorno;
    }

    public TpPrimitivo getRetorno() {
        return this.retorno;
    }

    @Override
    public boolean equals(Object a) {
        if (a instanceof Funcao) {
            Funcao aV = (Funcao) a;
            return aV.toString().equals(this.toString());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    public int getLinha() {
        return this.linha;
    }
}
