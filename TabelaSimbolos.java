
import java.util.HashMap;
import java.util.LinkedList;

public class TabelaSimbolos<T> {

    private final HashMap<Integer, LinkedList<T>> tabela;
    private int contagem = 0;

    public TabelaSimbolos() {
        this.tabela = new HashMap<>();
    }

    public boolean add(T s) {
        if (!this.lookUp(s)) {
            int indice = s.hashCode();
            LinkedList<T> lista = this.tabela.get(indice);
            if (lista == null) {
                lista = new LinkedList<>();
                tabela.put(indice, lista);
            }
            lista.add(s);
            this.contagem++;
        }else{
            return false;
        }
        return true;
    }

    public T get(T s){
        int indice = s.hashCode();
        if(tabela.get(indice) == null) return null;
        for (T obj : tabela.get(indice)) {
            if(obj.equals(s)){
                return obj;
            }
        }
        return null;
    }
    
    public void remove(T s) {
        if (this.lookUp(s)) {
            int indice = s.hashCode();
            LinkedList<T> lista = this.tabela.get(indice);
            lista.remove(s);
            this.contagem--;
        }
    }

    public boolean lookUp(T s) {
        int indice = s.hashCode();
        LinkedList<T> lista = this.tabela.get(indice);
        if (lista == null) {
            return false;
        }

        return lista.contains(s);
    }
    
    public LinkedList<T> pegaTodos() {
        LinkedList<T> symbollist = new LinkedList<>();

        for (int i = 0; i < this.tabela.size(); i++) {
            symbollist.addAll(this.tabela.get(i));
        }
        return symbollist;
    }

    public int contagem() {
        return this.contagem;
    }
}
