
import java.util.HashMap;
import java.util.LinkedList;

public class TabelaSimbolos<T> {

    private final HashMap<Integer, LinkedList<T>> tabela;
    private int contagem = 0;
    private final int tamanho;

    public TabelaSimbolos() {
        this.tamanho = 3571;
        this.tabela = new HashMap<>();
    }

    private int hash(String palavra) {
        int s1 = 1;
        int s2 = 0;

        for (int i = 0; i < palavra.length(); i++) {
            s1 = (s1 + palavra.charAt(i)) % 65521;
            s2 = (s1 + s2) % 65521;
        }

        return ((s2 << 16) | s1) % tamanho;
    }

    public boolean add(T s) {
        if (!this.lookUp(s.toString())) {
            int indice = this.hash(s.toString());
            LinkedList<T> lista = this.tabela.get(indice);
            if (lista == null) {
                lista = new LinkedList<>();
            } else {
                if (lista.contains(s)) {
                    return false;
                }
            }
            lista.add(s);
            this.contagem++;
        }
        return true;
    }

    public void remove(T s) {
        if (this.lookUp(s.toString())) {
            int indice = this.hash(s.toString());
            LinkedList<T> lista = this.tabela.get(indice);
            lista.remove(s);
            this.contagem--;
        }
    }

    public boolean lookUp(String s) {
        int indice = this.hash(s);
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
