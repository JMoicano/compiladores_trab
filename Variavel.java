
import java.util.Objects;

public class Variavel {

    private final String nome;
    private double valor;
    private final TpPrimitivo tipo;
    private final int linha;
    private final int escopo;

    public Variavel(String nome, TpPrimitivo tp, int linha, int escopo){
        this.nome = nome;
        this.tipo = tp;
        this.linha = linha;
        this.escopo = escopo;
    }

    public int getEscopo() {
        return escopo;
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
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString(){
        return nome;
    }
    
    @Override
    public boolean equals(Object a){
        if(a instanceof Variavel){
            Variavel aV = (Variavel)a;
        return ((aV.toString().equals(this.toString()) && aV.getEscopo() == this.escopo));
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nome);
        hash = 83 * hash + this.escopo;
        return hash;
    }

}
