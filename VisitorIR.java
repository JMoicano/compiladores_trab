
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author 2012100265
 */
public class VisitorIR extends GPortugolBaseVisitor<AST> {

//    private void verificaFuncao(Funcao funcaoUsada, Funcao funcaoDeclarada) {
//        if (funcaoDeclarada == null) {
//            handler.instantiateErro(funcaoUsada);
//        }
//        if (!funcaoDeclarada.getNome().equals("imprima")) {
//            int numParametros = funcaoDeclarada.getAridade();
//            int numArgumentos = funcaoUsada.getAridade();
//            if (numParametros != numArgumentos) {
//                handler.instantiateErro(funcaoUsada, numArgumentos, numParametros);
//}
//            LinkedList <TpPrimitivo> parametros = funcaoDeclarada.getParametros();
//            LinkedList <TpPrimitivo> argumentos = funcaoUsada.getParametros();
//            for (int i = 0; i < numParametros; i++) {
//                TpPrimitivo result = TpPrimitivo.tabAttr(parametros.get(i), argumentos.get(i));
//                if (result == TpPrimitivo.INDEFINIDO) {
//                    handler.instantiateErro(funcaoUsada.getLinha());
//                }
//            }
//        }
//    }
//
    @Override
    public AST visitAlgoritmo(GPortugolParser.AlgoritmoContext ctx) {
        AST no = new AST(1);
        if (ctx.var_decl_block() != null) {
            for (ParserRuleContext var_decl : ctx.var_decl_block().var_decl()) {
                no.add(visit(var_decl));
            }
        }
//        funcoesUsadas = new LinkedList<>();
        no.add(visit(ctx.stm_block()));
//        tabelaFuncoes = new TabelaSimbolos<>();
//        Funcao leia = new Funcao("leia", new LinkedList<TpPrimitivo>(), -1);
//        Funcao imprima = new Funcao("imprima", new LinkedList<TpPrimitivo>(), -1);
//        leia.setRetorno(TpPrimitivo.INDEFINIDO);
//        tabelaFuncoes.add(leia);
//        tabelaFuncoes.add(imprima);
        for (ParserRuleContext func_decl : ctx.func_decls()) {
            no.add(visit(func_decl));
        }
//        for (Funcao funcaoUsada : funcoesUsadas) {
//            Funcao funcaoDeclarada = tabelaFuncoes.get(funcaoUsada);
//            verificaFuncao(funcaoUsada, funcaoDeclarada);
//        }
        return no;
    }

    @Override
    public AST visitVar_decl(GPortugolParser.Var_declContext ctx) {
        AST no = new AST(4);
        no.add(visit(ctx.tp_primitivo()));
        int lineNum = ctx.getStart().getLine();
        for (TerminalNode T_IDENTIFICADOR : ctx.T_IDENTIFICADOR()) {
            AST filho = new AST(67, T_IDENTIFICADOR.getText(), lineNum);
            no.add(filho);
        }
        return no;
    }

    @Override
    public AST visitTp_primitivo(GPortugolParser.Tp_primitivoContext ctx) {
        int type = 7;
        switch (ctx.getText()) {
            case "inteiro":
                type = 7;
                break;
            case "real":
                type = 8;
                break;
            case "caractere":
                type = 9;
                break;
            case "literal":
                type = 10;
                break;
            case "logico":
                type = 11;
                break;
        }
        AST no = new AST(type);
        return no;
    }

    @Override
    public AST visitStm_block(GPortugolParser.Stm_blockContext ctx) {
        AST no = new AST(21);
        for (ParserRuleContext child : ctx.stm_list()) {
            no.add(visit(child));
        }
        return no;
    }

    @Override
    public AST visitDefault_stm_list(GPortugolParser.Default_stm_listContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public AST visitSemicolon_stm_list(GPortugolParser.Semicolon_stm_listContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public AST visitStm_ret(GPortugolParser.Stm_retContext ctx) {
        AST no = new AST(23);
        if (ctx.expr() != null) {
            no.add(visit(ctx.expr()));
        }
        return no;
    }

    @Override
    public AST visitLvalue(GPortugolParser.LvalueContext ctx) {
        int linha = ctx.start.getLine();
        AST no = new AST(67, ctx.T_IDENTIFICADOR().getText(), linha);
        return no;
    }

    @Override
    public AST visitStm_attr(GPortugolParser.Stm_attrContext ctx) {
        AST no = new AST(24);
        no.add(visit(ctx.lvalue()));
        no.add(visit(ctx.expr()));
//        TpPrimitivo result = TpPrimitivo.tabAttr(tpLvalue, tpExpr);
//        int linha = ctx.start.getLine();
//        if (result == TpPrimitivo.INDEFINIDO) {
//            handler.instantiateErro(linha);
//        }
        return no;
    }

    @Override
    public AST visitTrue_block(GPortugolParser.True_blockContext ctx) {
        AST no = new AST(26);
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            no.add(visit(stm_list));
        }
        return no;
    }

    @Override
    public AST visitFalse_block(GPortugolParser.False_blockContext ctx) {
        AST no = new AST(27);
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            no.add(visit(stm_list));
        }
        return no;
    }

    @Override
    public AST visitStm_se(GPortugolParser.Stm_seContext ctx) {
        int linha = ctx.start.getLine();
        AST no = new AST(25, linha);
        no.add(visit(ctx.expr()));
        no.add(visit(ctx.true_block()));
        if (ctx.false_block() != null) {
            no.add(visit(ctx.false_block()));
        }
        return no;
    }

    @Override
    public AST visitStm_enquanto(GPortugolParser.Stm_enquantoContext ctx) {
        AST no = new AST(29);
        no.add(visit(ctx.expr()));
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            no.add(visit(stm_list));
        }
        return no;
    }

    @Override
    public AST visitStm_para(GPortugolParser.Stm_paraContext ctx) {
        AST no = new AST(32);
//        int linha = ctx.start.getLine();
        no.add(visit(ctx.lvalue()));
//        if (verificacao != TpPrimitivo.LOGICO) {
//            handler.instantiateErro(linha);
//        }
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
        if (ctx.passo() != null) {
            no.add(visit(ctx.passo()));
        }
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            no.add(visit(stm_list));
        }
        return no;
    }

    @Override
    public AST visitPasso(GPortugolParser.PassoContext ctx) {
        AST no = new AST(35);
        no.add(new AST(ctx.op.getType()));
        no.add(new AST(60, Integer.parseInt(ctx.T_INT_LIT().getText())));
        return no;
    }

    @Override
    public AST visitExprComp(GPortugolParser.ExprCompContext ctx) {
        AST no = new AST(ctx.op.getType());
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
//        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
//        int linha = ctx.start.getLine();
//        if (result == TpPrimitivo.INDEFINIDO) {
//            handler.instantiateErro(linha);
//        }
        return no;
    }

    @Override
    public AST visitExprBinaryOr(GPortugolParser.ExprBinaryOrContext ctx) {
        AST no = new AST(42);
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
//        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
//        int linha = ctx.start.getLine();
//        if (result == TpPrimitivo.INDEFINIDO) {
//            handler.instantiateErro(linha);
//        }
        return no;
    }

    @Override
    public AST visitExprAddSub(GPortugolParser.ExprAddSubContext ctx) {
        AST no = new AST(ctx.op.getType());
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
//        TpPrimitivo result = TpPrimitivo.tabBasicOp(tp1, tp2);
//        int linha = ctx.start.getLine();
//        if (result == TpPrimitivo.INDEFINIDO) {
//            handler.instantiateErro(linha);
//        }
        return no;
    }

    @Override
    public AST visitExprTermo(GPortugolParser.ExprTermoContext ctx) {
        AST no = new AST(41);
        if (ctx.op != null) {
            no.add(new AST(ctx.op.getType()));
        }
        no.add(visit(ctx.termo()));
        return no;
    }

    @Override
    public AST visitExprMultDiv(GPortugolParser.ExprMultDivContext ctx) {
        AST no = new AST(ctx.op.getType());
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
//        TpPrimitivo result = TpPrimitivo.tabBasicOp(tp1, tp2);
//        int linha = ctx.start.getLine();
//        if (result == TpPrimitivo.INDEFINIDO) {
//            handler.instantiateErro(linha);
//        }
        return no;
    }

    @Override
    public AST visitExprAtrrib(GPortugolParser.ExprAtrribContext ctx) {
        AST no = new AST(ctx.op.getType());
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
//        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
//        int linha = ctx.start.getLine();
//        if (result == TpPrimitivo.INDEFINIDO) {
//            handler.instantiateErro(linha);
//        }
        return no;
    }

    @Override
    public AST visitExprBinaryAnd(GPortugolParser.ExprBinaryAndContext ctx) {
        AST no = new AST(44);
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
//        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
//        int linha = ctx.start.getLine();
//        if (result == TpPrimitivo.INDEFINIDO) {
//            handler.instantiateErro(linha);
//        }
        return no;
    }

    @Override
    public AST visitExprAnd(GPortugolParser.ExprAndContext ctx) {
        AST no = new AST(ctx.op.getType());
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
//        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
//        int linha = ctx.start.getLine();
//        if (result == TpPrimitivo.INDEFINIDO) {
//            handler.instantiateErro(linha);
//        }
        return no;
    }

    @Override
    public AST visitExprOr(GPortugolParser.ExprOrContext ctx) {
        AST no = new AST(32);
        no.add(visit(ctx.expr(0)));
        no.add(new AST(ctx.op.getType()));
        no.add(visit(ctx.expr(1)));
//        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
//        int linha = ctx.start.getLine();
//        if (result == TpPrimitivo.INDEFINIDO) {
//            handler.instantiateErro(linha);
//        }
        return no;
    }

    @Override
    public AST visitExprPow(GPortugolParser.ExprPowContext ctx) {
        AST no = new AST(43);
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
//        TpPrimitivo result = TpPrimitivo.tabComp(tp1, tp2);
//        int linha = ctx.start.getLine();
//        if (result == TpPrimitivo.INDEFINIDO) {
//            handler.instantiateErro(linha);
//        }
        return no;
    }

    @Override
    public AST visitDefault_termo(GPortugolParser.Default_termoContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public AST visitParentesis_termo(GPortugolParser.Parentesis_termoContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public AST visitFcall(GPortugolParser.FcallContext ctx) {
        AST no = new AST(30);
        int linha = ctx.start.getLine();
//        String nome = ctx.T_IDENTIFICADOR().getText();
        no.add(new AST(67, ctx.T_IDENTIFICADOR().getText(), linha));
//        LinkedList<TpPrimitivo> tipos = new LinkedList<>();
        if (ctx.fargs() != null) {
            no.add(visit(ctx.fargs()));
//            for (ParserRuleContext expr : ctx.fargs().expr()) {
//                tipos.add(visit(expr));
//            }
        }

//        Funcao f = new Funcao(nome, tipos, linha);
//        funcoesUsadas.add(f);
        return no;
    }

    @Override
    public AST visitLiteral(GPortugolParser.LiteralContext ctx) {
        if (ctx.T_CARAC_LIT() != null) {
            return new AST(63, ctx.T_CARAC_LIT().getText());
        } else if (ctx.T_INT_LIT() != null) {
            return new AST(60, Integer.parseInt(ctx.T_INT_LIT().getText()));
        } else if (ctx.T_KW_FALSO() != null) {
            return new AST(62, 0);
        } else if (ctx.T_KW_VERDADEIRO() != null) {
            return new AST(61, 1);
        } else if (ctx.T_REAL_LIT() != null) {
            return new AST(59, Double.parseDouble(ctx.T_REAL_LIT().getText()));
        } else {
            return new AST(60, 0);
        }
    }

    @Override
    public AST visitFunc_decls(GPortugolParser.Func_declsContext ctx) {
        AST no = new AST(58);
//        int linha = ctx.start.getLine();
//        String nome = ctx.T_IDENTIFICADOR().getText();
        no.add(new AST(67, ctx.T_IDENTIFICADOR().getText()));
//        LinkedList<TpPrimitivo> params = new LinkedList<>();
//        tabelaVariaveis = new TabelaSimbolos<>();
        if (ctx.fparams() != null) {
            no.add(visit(ctx.fparams()));
//            for (ParserRuleContext fparam : ctx.fparams().fparam()) {
//                params.add(visit(fparam));
//            }
        }
//        Funcao f = new Funcao(nome, params, linha);
        if (ctx.tp_primitivo() != null) {
            no.add(visit(ctx.tp_primitivo()));
//            f.setRetorno(visit(ctx.tp_primitivo()));
        }
        if (ctx.fvar_decl().getChildCount() > 0) {
            no.add(visit(ctx.fvar_decl()));
        }
        no.add(visit(ctx.stm_block()));

//        if (!tabelaFuncoes.add(f)) {
//            Funcao original = tabelaFuncoes.get(f);
//            handler.instantiateErro(original, linha);
//        }
        return no;
    }

    @Override
    public AST visitFvar_decl(GPortugolParser.Fvar_declContext ctx) {
        AST no = new AST(55);
        for (ParserRuleContext var_decl : ctx.var_decl()) {
            no.add(visit(var_decl));
        }
        return no;
    }

    @Override
    public AST visitFparams(GPortugolParser.FparamsContext ctx) {
        AST no = new AST(56);
        for (ParserRuleContext fparam : ctx.fparam()) {
            no.add(visit(fparam));
        }
        return no;
    }

    @Override
    public AST visitFparam(GPortugolParser.FparamContext ctx) {
        AST no = new AST(57);
//        int linha = ctx.start.getLine();
//        String nome = ctx.T_IDENTIFICADOR().getText();
        no.add(new AST(67, ctx.T_IDENTIFICADOR().getText()));
//        TpPrimitivo tipo = TpPrimitivo.INDEFINIDO;
        if (ctx.tp_primitivo() != null) {
            no.add(visit(ctx.tp_primitivo()));
        }
//        Variavel v = new Variavel(nome, tipo, linha);
//        tabelaVariaveis.add(v);
        return no;
    }
}
