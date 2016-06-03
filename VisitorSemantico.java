
import java.util.LinkedList;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author jsilva
 */
public class VisitorSemantico extends GPortugolBaseVisitor<TpPrimitivo> {

    private static TabelaSimbolos<Variavel> tabelaVariaveis;
    private static TabelaSimbolos<Funcao> tabelaFuncoes;
    private static LinkedList<Funcao> funcoesUsadas;
    ErrorHandler handler;

    private TpPrimitivo translateTipo(String entrada) {
        switch (entrada) {
            case "inteiro":
                return TpPrimitivo.INTEIRO;
            case "real":
                return TpPrimitivo.REAL;
            case "caractere":
                return TpPrimitivo.CARACTERE;
            case "literal":
                return TpPrimitivo.LITERAL;
            case "logico":
                return TpPrimitivo.LOGICO;
            default:
                return TpPrimitivo.INDEFINIDO;
        }
    }

    private void verificaFuncao(Funcao funcaoUsada, Funcao funcaoDeclarada) {
        if (funcaoDeclarada == null) {
            handler.instantiateErro(funcaoUsada);
        }
        int parametros = funcaoDeclarada.getAridade();
        int argumentos = funcaoUsada.getAridade();
        if (parametros != argumentos) {
            handler.instantiateErro(funcaoUsada, argumentos, parametros);
        }
        //TODO: VER TIPOS NOS ARGUMENTOS
    }

    @Override
    public TpPrimitivo visitAlgoritmo(GPortugolParser.AlgoritmoContext ctx) {
        tabelaVariaveis = new TabelaSimbolos<>();
        visit(ctx.declaracao_algoritmo());
        if (ctx.var_decl_block() != null) {
            visit(ctx.var_decl_block());
        }
        visit(ctx.stm_block());
        for (ParserRuleContext func_decls : ctx.func_decls()) {
            visit(func_decls);
        }
        for (Funcao funcaoUsada : funcoesUsadas) {
            Funcao funcaoDeclarada = tabelaFuncoes.get(funcaoUsada);
            verificaFuncao(funcaoUsada, funcaoDeclarada);
        }
        return TpPrimitivo.INDEFINIDO;
    }

    @Override
    public TpPrimitivo visitVar_decl_block(GPortugolParser.Var_decl_blockContext ctx) {
        for (ParserRuleContext var_decl : ctx.var_decl()) {
            visit(var_decl);
        }
        return TpPrimitivo.INDEFINIDO;
    }

    @Override
    public TpPrimitivo visitVar_decl(GPortugolParser.Var_declContext ctx) {
        LinkedList<String> identificadores = new LinkedList<>();
        identificadores.add(ctx.T_IDENTIFICADOR(0).getText());
        for (int j = 1; j < ctx.T_IDENTIFICADOR().size(); j++) {
            identificadores.add(ctx.T_IDENTIFICADOR(j).getText());
        }
        TpPrimitivo tipo;
        tipo = translateTipo(ctx.tp_primitivo().getText()); //ESTOU CONSIDERANDO O NAO USO DE MATRIZES
        int lineNum = ctx.getStart().getLine();
        for (String identificador : identificadores) {
            Variavel v = new Variavel(identificador, tipo, lineNum);
            tabelaVariaveis.add(v);
        }
        return tipo;
    }

    @Override
    public TpPrimitivo visitStm_block(GPortugolParser.Stm_blockContext ctx) {
        for (ParserRuleContext child : ctx.stm_list()) {
            visit(child);
        }
        return TpPrimitivo.INDEFINIDO;
    }

    @Override
    public TpPrimitivo visitDefault_stm_list(GPortugolParser.Default_stm_listContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public TpPrimitivo visitSemicolon_stm_list(GPortugolParser.Semicolon_stm_listContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public TpPrimitivo visitStm_ret(GPortugolParser.Stm_retContext ctx) {
        if (ctx.expr() != null) {
            return visit(ctx.expr());
        }
        return TpPrimitivo.INDEFINIDO;
    }

    @Override
    public TpPrimitivo visitLvalue(GPortugolParser.LvalueContext ctx) {
//        createChild(nodeNum, ctx.T_IDENTIFICADOR());
        boolean existe = tabelaVariaveis.lookUp(ctx.T_IDENTIFICADOR().toString());
        for (ParserRuleContext expr : ctx.expr()) {
            createChild(nodeNum, "[");
            createChild(nodeNum, expr);
            createChild(nodeNum, "]");
        }
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitStm_attr(GPortugolParser.Stm_attrContext ctx) {
        int nodeNum = createNode("stm_attr");
        createChild(nodeNum, ctx.lvalue());
        createChild(nodeNum, ":=");
        createChild(nodeNum, ctx.expr());
        createChild(nodeNum, ";");
        //TODO: VERIFICAR TIPO
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitStm_se(GPortugolParser.Stm_seContext ctx) {
        int nodeNum = createNode("stm_se");
        createChild(nodeNum, "se");
        createChild(nodeNum, ctx.expr());
        createChild(nodeNum, "entao");
        for (ParserRuleContext stm_list : ctx.true_block().stm_list()) {
            createChild(nodeNum, stm_list);
        }
        if (ctx.false_block() != null) {
            createChild(nodeNum, "senao");
            for (ParserRuleContext stm_list : ctx.false_block().stm_list()) {
                createChild(nodeNum, stm_list);
            }
        }
        createChild(nodeNum, "fim_se");

        return nodeNum;
    }

    @Override
    public TpPrimitivo visitStm_para(GPortugolParser.Stm_paraContext ctx) {
        int nodeNum = createNode("stm_para");
        createChild(nodeNum, "para");
        createChild(nodeNum, ctx.lvalue());
        createChild(nodeNum, "de");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, "ate");
        createChild(nodeNum, ctx.expr(1));
        if (ctx.passo() != null) {
            createChild(nodeNum, ctx.passo());
        }
        createChild(nodeNum, "faca");
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            createChild(nodeNum, stm_list);
        }
        createChild(nodeNum, "fim_para");
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitPasso(GPortugolParser.PassoContext ctx) {
        int nodeNum = createNode("passo");
        createChild(nodeNum, "passo");
        if (ctx.op != null) {
            createChild(nodeNum, ctx.op.getText());
        }
        createChild(nodeNum, ctx.T_INT_LIT());
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitExprComp(GPortugolParser.ExprCompContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitExprBinaryOr(GPortugolParser.ExprBinaryOrContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, "|");
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitExprAddSub(GPortugolParser.ExprAddSubContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitExprTermo(GPortugolParser.ExprTermoContext ctx) {
        int nodeNum = createNode("expr");
        if (ctx.op != null) {
            createChild(nodeNum, ctx.op.getText());
        }
        createChild(nodeNum, ctx.termo());
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitExprMultDiv(GPortugolParser.ExprMultDivContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitExprAtrrib(GPortugolParser.ExprAtrribContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitExprBinaryAnd(GPortugolParser.ExprBinaryAndContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, "&");
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitExprAnd(GPortugolParser.ExprAndContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitExprOr(GPortugolParser.ExprOrContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitExprPow(GPortugolParser.ExprPowContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, "^");
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitDefault_termo(GPortugolParser.Default_termoContext ctx) {
        int nodeNum = createNode("termo");
        createChild(nodeNum, ctx.getChild(0));
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitParentesis_termo(GPortugolParser.Parentesis_termoContext ctx) {
        int nodeNum = createNode("termo");
        createChild(nodeNum, "(");
        createChild(nodeNum, ctx.expr());
        createChild(nodeNum, ")");
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitFcall(GPortugolParser.FcallContext ctx) {
        int nodeNum = createNode("fcall");
        createChild(nodeNum, ctx.T_IDENTIFICADOR());
        String nome = ctx.T_IDENTIFICADOR().getText();
        createChild(nodeNum, "(");
        LinkedList<TpPrimitivo> tipos = new LinkedList<>();
        if (ctx.fargs() != null) {
            createChild(nodeNum, ctx.fargs());
            tipos.add(translateTipo(nome)); //TODO: AJEITAR PRA VISITAR O FARGS E RETORNAR OS TIPOS
        }
        createChild(nodeNum, ")");

        Funcao f = new Funcao(nome, tipos, nodeNum); //TODO: NUMERO DA LINHA
        funcoesUsadas.add(f);

        return nodeNum;
    }

    @Override
    public TpPrimitivo visitFargs(GPortugolParser.FargsContext ctx) {
        int nodeNum = createNode("fargs");
        createChild(nodeNum, ctx.expr(0));
        for (int j = 1; j < ctx.expr().size(); j++) {
            createChild(nodeNum, ctx.expr(j));
        }
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitLiteral(GPortugolParser.LiteralContext ctx) {
        int nodeNum = createNode("literal");
        String child;
        if (ctx.T_STRING_LIT() != null || ctx.T_CARAC_LIT() != null) {
            child = ctx.getChild(0).getText();
            String car;
            if (ctx.T_CARAC_LIT() != null) {
                car = "\\\'";
            } else {
                car = "\\\"";
            }
            child = car + child.substring(1, child.length() - 1) + car;
        } else {
            child = ctx.getChild(0).getText();
        }
        createChild(nodeNum, child);
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitFunc_decls(GPortugolParser.Func_declsContext ctx) {
        int nodeNum = createNode("func_decls");
        createChild(nodeNum, "funcao");
        createChild(nodeNum, ctx.T_IDENTIFICADOR());
        String nome = ctx.T_IDENTIFICADOR().getText();
        createChild(nodeNum, "(");
        LinkedList<TpPrimitivo> params = new LinkedList<>();
        if (ctx.fparams() != null) {
            createChild(nodeNum, ctx.fparams());
            params.add(translateTipo(nome)); //TODO: AJEITAR PRA VISITAR O FPARAMS E RETORNAR OS TIPOS
        }
        createChild(nodeNum, ")");
        Funcao f = new Funcao(nome, params, nodeNum); //TODO: NUMERO DA LINHA
        if (ctx.tp_primitivo() != null) {
            createChild(nodeNum, ":");
            createChild(nodeNum, ctx.tp_primitivo());
            f.setRetorno(translateTipo(ctx.tp_primitivo().getText()));
        }
        if (ctx.fvar_decl().getChildCount() > 0) {
            createChild(nodeNum, ctx.fvar_decl()); //TODO: DAR UM JEITO DE CRIAR UMA TABELA DE VARIAVEIS QUE SOH VALHA PRO STM_BLOCK DAQUI
        }
        createChild(nodeNum, ctx.stm_block());

        return nodeNum;
    }

    @Override
    public TpPrimitivo visitFvar_decl(GPortugolParser.Fvar_declContext ctx) {
        int nodeNum = createNode("fvar_decl");
        for (ParserRuleContext var_decl : ctx.var_decl()) {
            createChild(nodeNum, var_decl);
            createChild(nodeNum, ";");
        }
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitFparams(GPortugolParser.FparamsContext ctx) {
        int nodeNum = createNode("fparams");
        createChild(nodeNum, ctx.fparam(0));
        for (int j = 1; j < ctx.fparam().size(); j++) {
            createChild(nodeNum, ",");
            createChild(nodeNum, ctx.fparam(j));
        }
        return nodeNum;
    }

    @Override
    public TpPrimitivo visitFparam(GPortugolParser.FparamContext ctx) {
        int nodeNum = createNode("fparam");
        createChild(nodeNum, ctx.T_IDENTIFICADOR());
        createChild(nodeNum, ":");
        if (ctx.tp_primitivo() != null) {
            createChild(nodeNum, ctx.tp_primitivo());
        } else {
            createChild(nodeNum, ctx.tp_matriz());
        }
        return nodeNum;
    }
}
