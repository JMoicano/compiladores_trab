
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
    private final LinkedList<TpPrimitivo> argumentos;
    private final int aridade;
    
    public Funcao(String nome, LinkedList<TpPrimitivo> argumentos, int linha){
        this.nome = nome;
        this.linha = linha;
        this.argumentos = new LinkedList<>();
        for (TpPrimitivo argumento : argumentos) {
            this.argumentos.add(argumento);
        }
        aridade = this.argumentos.size();
    }
    
    public void setRetorno(TpPrimitivo retorno){
        this.retorno = retorno;
    }
    
    @Override
    public boolean equals(Object a){
        return ((a instanceof Funcao || a instanceof String)
                && a.toString().equals(this.toString()));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nome);
        return hash;
    }
}
