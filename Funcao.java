
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
    
    public Funcao(String nome, LinkedList<TpPrimitivo> argumentos, int linha){
        this.nome = nome;
        this.linha = linha;
        this.parametros = new LinkedList<>();
        for (TpPrimitivo argumento : argumentos) {
            this.parametros.add(argumento);
        }
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getAridade(){
        return parametros.size();
    }
    
    public void setRetorno(TpPrimitivo retorno){
        this.retorno = retorno;
    }
    
    public TpPrimitivo getRetorno(){
        return this.retorno;
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

    public int getLinha() {
        return this.linha;
    }
}
