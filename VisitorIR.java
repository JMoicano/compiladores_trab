
import java.util.LinkedList;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 *
 * @author 2012100265
 */
public class VisitorIR extends GPortugolBaseVisitor<AST> {

    private TabelaSimbolos<Variavel> tabelaVariaveis;
    private final TabelaSimbolos<Funcao> tabelaFuncoes;
    private static int escopoAtual = 0;

    public VisitorIR(TabelaSimbolos<Variavel> tabelaVariaveis, TabelaSimbolos<Funcao> tabelaFuncoes) {
        this.tabelaVariaveis = tabelaVariaveis;
        this.tabelaFuncoes = tabelaFuncoes;
    }

    @Override
    public AST visitAlgoritmo(GPortugolParser.AlgoritmoContext ctx) {
        AST no = new AST(GPortugolParser.T_ALGORITMO);
        if (ctx.var_decl_block() != null) {
            for (GPortugolParser.Var_declContext var_decl : ctx.var_decl_block().var_decl()) {
                visit(var_decl);
            }
        }

        for (GPortugolParser.Stm_listContext stm_list : ctx.stm_block().stm_list()) {
            no.add(visit(stm_list));
        }

        for (GPortugolParser.Func_declsContext func_decl : ctx.func_decls()) {
            visit(func_decl);
        }
        return no;
    }

    @Override
    public AST visitVar_decl(GPortugolParser.Var_declContext ctx) {
        LinkedList<String> identificadores = new LinkedList<>();
        identificadores.add(ctx.T_IDENTIFICADOR(0).getText());
        for (int j = 1; j < ctx.T_IDENTIFICADOR().size(); j++) {
            identificadores.add(ctx.T_IDENTIFICADOR(j).getText());
        }
        TpPrimitivo tipo;   
        tipo = TpPrimitivo.translateTipo(ctx.tp_primitivo().getText());
        int lineNum = ctx.getStart().getLine();
        for (String identificador : identificadores) {
            Variavel v = new Variavel(identificador, tipo, lineNum, escopoAtual);
            tabelaVariaveis.add(v);
        }
        return null;
    }

    @Override
    public AST visitFunc_decls(GPortugolParser.Func_declsContext ctx) {
        escopoAtual++;
        int linha = ctx.start.getLine();
        String nome = ctx.T_IDENTIFICADOR().getText();
        LinkedList<TpPrimitivo> params = new LinkedList<>();
        AST no = new AST(GPortugolParser.T_ALGORITMO);
        AST paramsNo = new AST(GPortugolParser.ML_COMMENT);
        if (ctx.fparams() != null) {
            paramsNo = visit(ctx.fparams());
        }
        no.add(paramsNo);
        Funcao f = new Funcao(nome, params, linha, escopoAtual, no);
        if (ctx.tp_primitivo() != null) {
            f.setRetorno(TpPrimitivo.translateTipo(ctx.tp_primitivo().getText()));
        }
        if (ctx.fvar_decl().getChildCount() > 0) {
            visit(ctx.fvar_decl());
        }

        for (GPortugolParser.Stm_listContext stm_list : ctx.stm_block().stm_list()) {
            no.add(visit(stm_list));
        }

        tabelaFuncoes.add(f);
        return null;
    }

    @Override
    public AST visitFparam(GPortugolParser.FparamContext ctx) {
        int linhaParam = ctx.start.getLine();
        String nomeParam = ctx.T_IDENTIFICADOR().getText();
        TpPrimitivo tipo = TpPrimitivo.INDEFINIDO;
        if (ctx.tp_primitivo() != null) {
            tipo = TpPrimitivo.translateTipo(ctx.tp_primitivo().getText());
        }
        Variavel v = new Variavel(nomeParam, tipo, linhaParam, escopoAtual);
        tabelaVariaveis.add(v);
        return new AST(GPortugolParser.T_IDENTIFICADOR, nomeParam);
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
        AST no = new AST(GPortugolParser.T_RETORNE);
        if (ctx.expr() != null) {
            return (visit(ctx.expr()));
        }
        return no;
    }

    @Override
    public AST visitLvalue(GPortugolParser.LvalueContext ctx) {
        AST no = new AST(GPortugolParser.T_VARIAVEIS, ctx.T_IDENTIFICADOR().getText(), escopoAtual);
        return no;
    }

    @Override
    public AST visitStm_attr(GPortugolParser.Stm_attrContext ctx) {
        AST no = new AST(GPortugolParser.T_ATTR);
        no.add(visit(ctx.lvalue()));
        no.add(visit(ctx.expr()));
        return no;
    }

    @Override
    public AST visitTrue_block(GPortugolParser.True_blockContext ctx) {
        AST no = new AST(GPortugolParser.T_ENTAO);
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            no.add(visit(stm_list));
        }
        return no;
    }

    @Override
    public AST visitFalse_block(GPortugolParser.False_blockContext ctx) {
        AST no = new AST(GPortugolParser.T_SENAO);
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            no.add(visit(stm_list));
        }
        return no;
    }

    @Override
    public AST visitStm_se(GPortugolParser.Stm_seContext ctx) {
        AST no = new AST(GPortugolParser.T_SE);
        no.add(visit(ctx.expr()));
        no.add(visit(ctx.true_block()));
        if (ctx.false_block() != null) {
            no.add(visit(ctx.false_block()));
        }
        return no;
    }

    @Override
    public AST visitStm_enquanto(GPortugolParser.Stm_enquantoContext ctx) {
        AST no = new AST(GPortugolParser.T_ENQUANTO);
        no.add(visit(ctx.expr()));
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            no.add(visit(stm_list));
        }
        return no;
    }

    @Override
    public AST visitStm_para(GPortugolParser.Stm_paraContext ctx) {
        AST no = new AST(GPortugolParser.T_PARA);
        no.add(visit(ctx.lvalue()));
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
        AST passo = new AST(GPortugolParser.T_PASSO);
        passo.add(new AST(GPortugolParser.T_MAIS));
        passo.add(new AST(GPortugolParser.T_INT_LIT, 1));
        if (ctx.passo() != null) {
            passo = visit(ctx.passo());
        }
        no.add(passo);
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            no.add(visit(stm_list));
        }
        return no;
    }

    @Override
    public AST visitPasso(GPortugolParser.PassoContext ctx) {
        AST no = new AST(GPortugolParser.T_PASSO);
        no.setTipo(TipoAST.INT);
        no.add(new AST(ctx.op.getType()));
        no.add(new AST(GPortugolParser.T_INT_LIT, Integer.parseInt(ctx.T_INT_LIT().getText())));
        return no;
    }

    @Override
    public AST visitExprBinary(GPortugolParser.ExprBinaryContext ctx) {
        AST no = new AST(ctx.op.getType());
        no.add(visit(ctx.expr(0)));
        no.add(visit(ctx.expr(1)));
        if(no.getChild(0).getTipo() == TipoAST.DOUBLE || no.getChild(1).getTipo() == TipoAST.DOUBLE){
            no.setTipo(TipoAST.DOUBLE);
        }else{
            no.setTipo(TipoAST.INT);
        }
        return no;
    }

    @Override
    public AST visitExprTermo(GPortugolParser.ExprTermoContext ctx) {
        AST no = new AST(GPortugolParser.WS); //Gambiarra
        AST op = new AST(GPortugolParser.T_MAIS);
        if (ctx.op != null) {
            op = new AST(ctx.op.getType());
        }
        no.add(op);
        no.add(visit(ctx.termo()));
        no.setTipo(no.getChild(1).getTipo());
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
        AST no = new AST(GPortugolParser.T_FUNCAO, ctx.T_IDENTIFICADOR().getText());
        if (ctx.fargs() != null) {
            for(GPortugolParser.ExprContext expr : ctx.fargs().expr()){
                no.add(visit(expr));
            }
        }
        return no;
    }
    
    @Override
    public AST visitLitInt(GPortugolParser.LitIntContext ctx) {
        AST no = new AST(GPortugolParser.T_INT_LIT, Integer.parseInt(ctx.T_INT_LIT().getText()));
        no.setTipo(TipoAST.INT);
        return no;
    }

    @Override
    public AST visitLitReal(GPortugolParser.LitRealContext ctx) {
        AST no = new AST(GPortugolParser.T_REAL_LIT, Double.parseDouble(ctx.T_REAL_LIT().getText()));
        no.setTipo(TipoAST.DOUBLE);
        return no;
    }

    @Override
    public AST visitLitCar(GPortugolParser.LitCarContext ctx) {
        AST no =new AST(GPortugolParser.T_CARAC_LIT, ctx.T_CARAC_LIT().getText().charAt(0));
        no.setTipo(TipoAST.STRING);
        return no;
    }

    @Override
    public AST visitLitLit(GPortugolParser.LitLitContext ctx) {
        AST no = new AST(GPortugolParser.T_STRING_LIT, ctx.T_STRING_LIT().getText());;
        no.setTipo(TipoAST.STRING);
        return no;
    }

    @Override
    public AST visitLitTrue(GPortugolParser.LitTrueContext ctx) {
        AST no = new AST(GPortugolParser.T_LOGICO, 1);
        no.setTipo(TipoAST.INT);
        return no;
    }

    @Override
    public AST visitLitFalse(GPortugolParser.LitFalseContext ctx) {
        AST no = new AST(GPortugolParser.T_LOGICO, 0);
        no.setTipo(TipoAST.INT);
        return no;
    }

    @Override
    public AST visitFvar_decl(GPortugolParser.Fvar_declContext ctx) {
        for (ParserRuleContext var_decl : ctx.var_decl()) {
            visit(var_decl);
        }
        return null;
    }

    @Override
    public AST visitFparams(GPortugolParser.FparamsContext ctx) {
        AST no = new AST(GPortugolParser.ML_COMMENT); //Mais Gambiarra
        for (ParserRuleContext fparam : ctx.fparam()) {
            no.add(visit(fparam));
        }
        return no;
    }
}
