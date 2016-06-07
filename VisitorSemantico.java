
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

    private boolean verificaVariavel(Variavel a) {
        return tabelaVariaveis.lookUp(a);
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
        tipo = TpPrimitivo.translateTipo(ctx.tp_primitivo().getText()); //ESTOU CONSIDERANDO O NAO USO DE MATRIZES
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
        int linha = ctx.start.getLine();
        Variavel v = new Variavel(ctx.T_IDENTIFICADOR().getText(), TpPrimitivo.INDEFINIDO, linha);
        boolean existe = verificaVariavel(v);
        if (!existe) {
            handler.instantiateErro(v);
        }
        return tabelaVariaveis.get(v).getTipo();
    }

    @Override
    public TpPrimitivo visitStm_attr(GPortugolParser.Stm_attrContext ctx) {
        TpPrimitivo tpLvalue = visit(ctx.lvalue());
        TpPrimitivo tpExpr = visit(ctx.expr());
        TpPrimitivo result = TpPrimitivo.tabAttr(tpLvalue, tpExpr);
        int linha = ctx.start.getLine();
        if (result == TpPrimitivo.INDEFINIDO) {
            handler.instantiateErro(linha);
        }
        return result;
    }

    @Override
    public TpPrimitivo visitStm_se(GPortugolParser.Stm_seContext ctx) {
        int linha = ctx.start.getLine();
        TpPrimitivo verificacao = visit(ctx.expr());
        if (verificacao != TpPrimitivo.LOGICO) {
            handler.instantiateErro(linha);
        }
        for (ParserRuleContext stm_list : ctx.true_block().stm_list()) {
            visit(stm_list);
        }
        if (ctx.false_block() != null) {
            for (ParserRuleContext stm_list : ctx.false_block().stm_list()) {
                visit(stm_list);
            }
        }
        return verificacao;
    }

    @Override
    public TpPrimitivo visitStm_para(GPortugolParser.Stm_paraContext ctx) {
        int linha = ctx.start.getLine();
        TpPrimitivo verificacao = visit(ctx.lvalue());
        if (verificacao != TpPrimitivo.LOGICO) {
            handler.instantiateErro(linha);
        }
        visit(ctx.expr(0));
        visit(ctx.expr(1));
        if (ctx.passo() != null) {
            visit(ctx.passo());
        }
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            visit(stm_list);
        }
        return verificacao;
    }

    @Override
    public TpPrimitivo visitExprComp(GPortugolParser.ExprCompContext ctx) {
        TpPrimitivo tp1 = visit(ctx.expr(0));
        TpPrimitivo tp2 = visit(ctx.expr(1));
        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
        int linha = ctx.start.getLine();
        if (result == TpPrimitivo.INDEFINIDO) {
            handler.instantiateErro(linha);
        }
        return result;
    }

    @Override
    public TpPrimitivo visitExprBinaryOr(GPortugolParser.ExprBinaryOrContext ctx) {
        TpPrimitivo tp1 = visit(ctx.expr(0));
        TpPrimitivo tp2 = visit(ctx.expr(1));
        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
        int linha = ctx.start.getLine();
        if (result == TpPrimitivo.INDEFINIDO) {
            handler.instantiateErro(linha);
        }
        return result;
    }

    @Override
    public TpPrimitivo visitExprAddSub(GPortugolParser.ExprAddSubContext ctx) {
        TpPrimitivo tp1 = visit(ctx.expr(0));
        TpPrimitivo tp2 = visit(ctx.expr(1));
        TpPrimitivo result = TpPrimitivo.tabBasicOp(tp1, tp2);
        int linha = ctx.start.getLine();
        if (result == TpPrimitivo.INDEFINIDO) {
            handler.instantiateErro(linha);
        }
        return result;
    }

    @Override
    public TpPrimitivo visitExprTermo(GPortugolParser.ExprTermoContext ctx) {
        TpPrimitivo result = visit(ctx.termo());
        return result;
    }

    @Override
    public TpPrimitivo visitExprMultDiv(GPortugolParser.ExprMultDivContext ctx) {
        TpPrimitivo tp1 = visit(ctx.expr(0));
        TpPrimitivo tp2 = visit(ctx.expr(1));
        TpPrimitivo result = TpPrimitivo.tabBasicOp(tp1, tp2);
        int linha = ctx.start.getLine();
        if (result == TpPrimitivo.INDEFINIDO) {
            handler.instantiateErro(linha);
        }
        return result;
    }

    @Override
    public TpPrimitivo visitExprAtrrib(GPortugolParser.ExprAtrribContext ctx) {
        TpPrimitivo tp1 = visit(ctx.expr(0));
        TpPrimitivo tp2 = visit(ctx.expr(1));
        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
        int linha = ctx.start.getLine();
        if (result == TpPrimitivo.INDEFINIDO) {
            handler.instantiateErro(linha);
        }
        return result;
    }

    @Override
    public TpPrimitivo visitExprBinaryAnd(GPortugolParser.ExprBinaryAndContext ctx) {
        TpPrimitivo tp1 = visit(ctx.expr(0));
        TpPrimitivo tp2 = visit(ctx.expr(1));
        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
        int linha = ctx.start.getLine();
        if (result == TpPrimitivo.INDEFINIDO) {
            handler.instantiateErro(linha);
        }
        return result;
    }

    @Override
    public TpPrimitivo visitExprAnd(GPortugolParser.ExprAndContext ctx) {
        TpPrimitivo tp1 = visit(ctx.expr(0));
        TpPrimitivo tp2 = visit(ctx.expr(1));
        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
        int linha = ctx.start.getLine();
        if (result == TpPrimitivo.INDEFINIDO) {
            handler.instantiateErro(linha);
        }
        return result;
    }

    @Override
    public TpPrimitivo visitExprOr(GPortugolParser.ExprOrContext ctx) {
        TpPrimitivo tp1 = visit(ctx.expr(0));
        TpPrimitivo tp2 = visit(ctx.expr(1));
        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
        int linha = ctx.start.getLine();
        if (result == TpPrimitivo.INDEFINIDO) {
            handler.instantiateErro(linha);
        }
        return result;
    }

    @Override
    public TpPrimitivo visitExprPow(GPortugolParser.ExprPowContext ctx) {
        TpPrimitivo tp1 = visit(ctx.expr(0));
        TpPrimitivo tp2 = visit(ctx.expr(1));
        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
        int linha = ctx.start.getLine();
        if (result == TpPrimitivo.INDEFINIDO) {
            handler.instantiateErro(linha);
        }
        return result;
    }

    @Override
    public TpPrimitivo visitDefault_termo(GPortugolParser.Default_termoContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public TpPrimitivo visitParentesis_termo(GPortugolParser.Parentesis_termoContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public TpPrimitivo visitFcall(GPortugolParser.FcallContext ctx) {
        int linha = ctx.start.getLine();
        String nome = ctx.T_IDENTIFICADOR().getText();
        LinkedList<TpPrimitivo> tipos = new LinkedList<>();
        if (ctx.fargs() != null) {
            for (ParserRuleContext expr : ctx.fargs().expr()) {
                tipos.add(visit(expr));
            }
        }

        Funcao f = new Funcao(nome, tipos, linha);
        funcoesUsadas.add(f);

        return TpPrimitivo.INDEFINIDO;
    }

    @Override
    public TpPrimitivo visitLiteral(GPortugolParser.LiteralContext ctx) {
        return TpPrimitivo.translateTipo(ctx.getChild(0).getText());
    }

    @Override
    public TpPrimitivo visitFunc_decls(GPortugolParser.Func_declsContext ctx) {
        int linha = ctx.start.getLine();
        String nome = ctx.T_IDENTIFICADOR().getText();
        LinkedList<TpPrimitivo> params = new LinkedList<>();
        if (ctx.fparams() != null) {
            for (ParserRuleContext fparam : ctx.fparams().fparam()) {
                params.add(visit(fparam));
            }
        }
        Funcao f = new Funcao(nome, params, linha); 
        if (ctx.tp_primitivo() != null) {
            f.setRetorno(visit(ctx.tp_primitivo()));
        }
        tabelaVariaveis = new TabelaSimbolos<>();
        if (ctx.fvar_decl().getChildCount() > 0) {
            visit(ctx.fvar_decl());
        }
        visit(ctx.stm_block());

        return f.getRetorno();
    }

    @Override
    public TpPrimitivo visitFvar_decl(GPortugolParser.Fvar_declContext ctx) {
        for (ParserRuleContext var_decl : ctx.var_decl()) {
            visit(var_decl);
        }
        return TpPrimitivo.INDEFINIDO;
    }

    @Override
    public TpPrimitivo visitFparam(GPortugolParser.FparamContext ctx) {
        int linha = ctx.start.getLine();
        String nome = ctx.T_IDENTIFICADOR().getText();
        TpPrimitivo tipo = TpPrimitivo.INDEFINIDO;
        if (ctx.tp_primitivo() != null) {
            tipo = visit(ctx.tp_primitivo());
        }/* else {
            createChild(nodeNum, ctx.tp_matriz());
        }*/
        Variavel v = new Variavel(nome, tipo, linha);
        tabelaVariaveis.add(v);
        return tipo;
    }
}
