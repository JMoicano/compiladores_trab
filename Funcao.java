
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
    
    public Funcao(String nome, LinkedList<TpPrimitivo> argumentos, int linha, int escopo){
        this.nome = nome;
        this.linha = linha;
        this.parametros = new LinkedList<>();
        for (TpPrimitivo argumento : argumentos) {
            this.parametros.add(argumento);
        }
        this.escopo = escopo;
    }

    public int getEscopo() {
        return escopo;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getAridade(){
        return parametros.size();
    }
    
    public LinkedList<TpPrimitivo> getParametros(){
        return this.parametros;
    }
    
    public void setRetorno(TpPrimitivo retorno){
        this.retorno = retorno;
    }
    
    public TpPrimitivo getRetorno(){
        return this.retorno;
    }
    
    @Override
    public boolean equals(Object a){
                if(a instanceof Funcao){
            Funcao aV = (Funcao)a;
        return ((aV.toString().equals(this.toString()) && aV.getEscopo() == this.escopo));
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + this.escopo;
        return hash;
    }

    public int getLinha() {
        return this.linha;
    }
}
