
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TabelaHash<T> {

    private final HashMap<Integer, LinkedList<T>> tabela;
    private int contagem = 0;
    private int tamanho;

    public TabelaHash() {
        this.tamanho = 4039;
        this.tabela = new HashMap<>();
    }

    private int calculaIndiceDaTabela(String palavra) {
        return palavra.toLowerCase().charAt(0) % 26;
    }

    public void adiciona(T s) {
        if (!this.contem(s)) {
            int indice = this.calculaIndiceDaTabela(s.toString());
            LinkedList<T> lista = this.tabela.get(indice);
            if (lista == null) {
                lista = new LinkedList<>();
            }
            lista.add(s);
            this.contagem++;
        }
    }

    public void remove(T s) {
        if (this.contem(s)) {
            int indice = this.calculaIndiceDaTabela(s.toString());
            LinkedList<T> lista = this.tabela.get(indice);
            lista.remove(s);
            this.contagem--;
        }
    }

    public boolean contem(T s) {
        int indice = this.calculaIndiceDaTabela(s.toString());
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
