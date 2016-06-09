
/**
 *
 * @author John
 */
public class ErrorHandler {

    public ErrorHandler() {
    }

    private String inicioErro(int numLinha) {
        return "Erro semantico na linha " + numLinha + ": ";
    }

    private String tipoVariavel(String nome) {
        return "variavel '" + nome + "' ";
    }

    private String tipoFuncao(String nome) {
        return "funcao '" + nome + "' ";
    }

    private String erroUtilizacao() {
        return "nao foi declarada.";
    }

    private String erroRedeclaracao(int numLinha) {
        return "ja foi declarada na linha " + numLinha + ".";
    }

    private String erroAridade(int argumentos, int parametros) {
        return "foi chamada com " + argumentos + " argumentos mas declarada com " + parametros + " parametros.";
    }

    private String erroTipo() {
        return "expressao com tipos de dados incompativeis.";
    }

    public void instantiateErro(Variavel v) {
        System.out.println(inicioErro(v.getLinha()) + tipoVariavel(v.getNome()) + erroUtilizacao());
        System.exit(1);
    }

    public void instantiateErro(Funcao f) {
        System.out.println(inicioErro(f.getLinha()) + tipoFuncao(f.getNome()) + erroUtilizacao());
        System.exit(1);
    }

    public void instantiateErro(Variavel v, int linha) {
        System.out.println(inicioErro(linha) + tipoVariavel(v.getNome()) + erroRedeclaracao(v.getLinha()));
        System.exit(1);
    }

    public void instantiateErro(Funcao f, int linha) {
        System.out.println(inicioErro(linha) + tipoFuncao(f.getNome()) + erroRedeclaracao(f.getLinha()));
        System.exit(1);
    }
    
    public void instantiateErro(Funcao f, int argumentos, int parametros) {
        System.out.println(inicioErro(f.getLinha()) + tipoFuncao(f.getNome()) + erroAridade(argumentos, parametros));
        System.exit(1);
    }

    public void instantiateErro(int linha) {
        System.out.println(inicioErro(linha) + erroTipo());
        System.exit(1);
    }
}
