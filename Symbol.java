public class Symbol {
    
    public enum SymbolType{
    REAL,CARACTERE,LITERAL,LOGICO;
    }
    
    private String palavra;
    private SymbolType tipo;
    private int linha;

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public SymbolType getTipo() {
        return tipo;
    }

    public void setTipo(SymbolType tipo) {
        this.tipo = tipo;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
    
    
    
    
}