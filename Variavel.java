
import java.util.Objects;

public class Variavel {

    private final String nome;
    private final TpPrimitivo tipo;
    private final int linha;

    public Variavel(String nome, TpPrimitivo tp, int linha){
        this.nome = nome;
        this.tipo = tp;
        this.linha = linha;
    }
    public int getLinha() {
        return linha;
    }

    public TpPrimitivo getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString(){
        return nome;
    }
    
    @Override
    public boolean equals(Object a){
        return ((a instanceof Variavel || a instanceof String)
                && a.toString().equals(this.toString()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nome);
        return hash;
    }
}
