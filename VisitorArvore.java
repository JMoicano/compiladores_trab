
import javax.swing.JTree;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jsilva
 */
public class VisitorArvore extends GPortugolBaseVisitor<Integer> {

    private static int i;

    private int createNode(String label) {
        System.out.println("node" + i + "[label=\"" + label + "\"];");
        return i++;
    }

    private void createChild(int p, int c) {
        System.out.println("node" + p + " -> node" + c + ";");
    }

    private void createChild(int p, String c) {
        createChild(p, createNode(c));
    }

    private void createChild(int p, ParserRuleContext c) {
        int childNum = visit(c);
        createChild(p, childNum);
    }

    private void createChild(int p, TerminalNode c) {
        createChild(p, c.getText());
    }

    @Override
    public Integer visitAlgoritmo(GPortugolParser.AlgoritmoContext ctx) {
        i = 0;
        int nodeNum = createNode("algoritmo_goal");
        createChild(nodeNum, ctx.declaracao_algoritmo());
        if (ctx.var_decl_block() != null) {
            createChild(nodeNum, ctx.var_decl_block());
        }
        createChild(nodeNum, ctx.stm_block());
        for (ParserRuleContext func_decls : ctx.func_decls()) {
            createChild(nodeNum, func_decls);
        }
        return nodeNum;
    }

    @Override
    public Integer visitDeclaracao_algoritmo(GPortugolParser.Declaracao_algoritmoContext ctx) {
        int nodeNum = createNode("algoritmo_decl");
        createChild(nodeNum, "algoritmo");
        createChild(nodeNum, ctx.T_IDENTIFICADOR());
        createChild(nodeNum, ";");
        return nodeNum;
    }

    @Override
    public Integer visitVar_decl_block(GPortugolParser.Var_decl_blockContext ctx) {
        int nodeNum = createNode("var_decl_block");
        createChild(nodeNum, "variaveis");
        for (ParserRuleContext var_decl : ctx.var_decl()) {
            createChild(nodeNum, var_decl);
            createChild(nodeNum, ";");
        }
        return nodeNum;
    }

    @Override
    public Integer visitVar_decl(GPortugolParser.Var_declContext ctx) {
        int nodeNum = createNode("var_decl");
        createChild(nodeNum, ctx.T_IDENTIFICADOR(0));
        for (int j = 1; j < ctx.T_IDENTIFICADOR().size(); j++) {
            createChild(nodeNum, ",");
            createChild(nodeNum, ctx.T_IDENTIFICADOR(j));
        }
        createChild(nodeNum, ":");
        if (ctx.tp_primitivo() != null) {
            createChild(nodeNum, ctx.tp_primitivo());
        } else {
            createChild(nodeNum, ctx.tp_matriz());
        }
        return nodeNum;
    }

    @Override
    public Integer visitTp_primitivo(GPortugolParser.Tp_primitivoContext ctx) {
        int nodeNum = createNode("tp_primitivo");
        createNode(ctx.getText());
        return nodeNum;
    }

    @Override
    public Integer visitTp_matriz(GPortugolParser.Tp_matrizContext ctx) {
        int nodeNum = createNode("tp_matriz");
        createChild(nodeNum, "matriz");
        for (TerminalNode int_lit : ctx.T_INT_LIT()) {
            createChild(nodeNum, "[");
            createChild(nodeNum, int_lit);
            createChild(nodeNum, "]");
        }
        return nodeNum;
    }

    @Override
    public Integer visitTp_prim_pl(GPortugolParser.Tp_prim_plContext ctx) {
        int nodeNum = createNode("tp_prim_pl");
        createNode(ctx.getText());
        return nodeNum;
    }

    @Override
    public Integer visitStm_block(GPortugolParser.Stm_blockContext ctx) {
        int nodeNum = createNode("stm_block");
        createChild(nodeNum, "inicio");
        for (ParserRuleContext child : ctx.stm_list()) {
            createChild(nodeNum, child);
        }
        createChild(nodeNum, "fim");
        return nodeNum;
    }

    @Override
    public Integer visitDefault_stm_list(GPortugolParser.Default_stm_listContext ctx) {
        int nodeNum = createNode("stm_list");
        visit(ctx.getChild(0));
        return nodeNum;
    }

    @Override
    public Integer visitSemicolon_stm_list(GPortugolParser.Semicolon_stm_listContext ctx) {
        int nodeNum = createNode("stm_list");
        visit(ctx.getChild(0));
        createChild(nodeNum, ";");
        return nodeNum;
    }

    @Override
    public Integer visitStm_ret(GPortugolParser.Stm_retContext ctx) {
        int nodeNum = createNode("stm_ret");
        if (ctx.expr() != null) {
            createChild(nodeNum, ctx.expr());
        }
        return nodeNum;
    }

    @Override
    public Integer visitLvalue(GPortugolParser.LvalueContext ctx) {
        int nodeNum = createNode("lvalue");
        createChild(nodeNum, ctx.T_IDENTIFICADOR());
        for (ParserRuleContext expr : ctx.expr()) {
            createChild(nodeNum, "[");
            createChild(nodeNum, expr);
            createChild(nodeNum, "]");
        }
        return nodeNum;
    }

    @Override
    public Integer visitStm_attr(GPortugolParser.Stm_attrContext ctx) {
        int nodeNum = createNode("stm_attr");
        createChild(nodeNum, ctx.lvalue());
        createChild(nodeNum, ":=");
        createChild(nodeNum, ctx.expr());
        createChild(nodeNum, ";");
        return nodeNum;
    }

    @Override
    public Integer visitStm_se(GPortugolParser.Stm_seContext ctx) {
        int nodeNum = createNode("stm_se");
        int entao_count = 0, senao_count = 0;
        boolean entao = false;
        boolean senao = false;
        for (ParseTree aux : ctx.children) {
            if (entao) {
                entao_count++;
            } else if (senao) {
                senao_count++;
            }
            if (aux.getText().equals("entao")) {
                entao = true;
            } else if (aux.getText().equals("senao")) {
                entao = false;
                senao = true;
            } else if (aux.getText().equals("fim_se")) {
                senao = false;
                entao = false;
            }
        }
        createChild(nodeNum, "se");
        createChild(nodeNum, ctx.expr());
        createChild(nodeNum, "entao");
        int stm_list_count;
        for (stm_list_count = 0; stm_list_count < entao_count; stm_list_count++) {
            createChild(nodeNum, ctx.stm_list(stm_list_count));
        }
        if (senao_count > 0) {
            createChild(nodeNum, "senao");
            for (; stm_list_count < ctx.stm_list().size(); stm_list_count++) {
                createChild(nodeNum, ctx.stm_list(stm_list_count));

            }
        }
        createChild(nodeNum, "fim_se");
        return nodeNum;
    }

    @Override
    public Integer visitStm_enquanto(GPortugolParser.Stm_enquantoContext ctx) {
        int nodeNum = createNode("stm_enquanto");
        createChild(nodeNum, "enquanto");
        createChild(nodeNum, ctx.expr());
        createChild(nodeNum, "faca");
        for (ParserRuleContext stm_list : ctx.stm_list()) {
            createChild(nodeNum, stm_list);
        }
        createChild(nodeNum, "fim_enquanto");
        return nodeNum;
    }

    @Override
    public Integer visitStm_para(GPortugolParser.Stm_paraContext ctx) {
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
    public Integer visitPasso(GPortugolParser.PassoContext ctx) {
        int nodeNum = createNode("passo");
        createChild(nodeNum, "passo");
        if (ctx.op != null) {
            createChild(nodeNum, ctx.op.getText());
        }
        createChild(nodeNum, ctx.T_INT_LIT());
        return nodeNum;
    }

    @Override
    public Integer visitExprComp(GPortugolParser.ExprCompContext ctx) {
        int nodeNum = createNode("exprComp");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprBinaryOr(GPortugolParser.ExprBinaryOrContext ctx) {
        int nodeNum = createNode("exprBinaryOr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, "|");
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprAddSub(GPortugolParser.ExprAddSubContext ctx) {
        int nodeNum = createNode("exprAddSub");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprTermo(GPortugolParser.ExprTermoContext ctx) {
        int nodeNum = createNode("exprTermo");
        if (ctx.op != null) {
            createChild(nodeNum, ctx.op.getText());
        }
        createChild(nodeNum, ctx.termo());
        return nodeNum;
    }

    @Override
    public Integer visitExprMultDiv(GPortugolParser.ExprMultDivContext ctx) {
        int nodeNum = createNode("exprMultDiv");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprAtrrib(GPortugolParser.ExprAtrribContext ctx) {
        int nodeNum = createNode("exprAtrrib");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprBinaryAnd(GPortugolParser.ExprBinaryAndContext ctx) {
        int nodeNum = createNode("exprBinaryAnd");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, "&");
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprAnd(GPortugolParser.ExprAndContext ctx) {
        int nodeNum = createNode("exprAnd");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprOr(GPortugolParser.ExprOrContext ctx) {
        int nodeNum = createNode("exprOr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprPow(GPortugolParser.ExprPowContext ctx) {
        int nodeNum = createNode("exprPow");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, "^");
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }
    /*

    @Override
    public Integer visitTermo(GPortugolParser.TermoContext ctx) {
        int nodeNum = createNode("termo");
        return nodeNum;
    }

    @Override
    public Integer visitFcall(GPortugolParser.FcallContext ctx) {
        int nodeNum = createNode("fcall");
        return nodeNum;
    }

    @Override
    public Integer visitFargs(GPortugolParser.FargsContext ctx) {
        int nodeNum = createNode("fargs");
        return nodeNum;
    }

    @Override
    public Integer visitLiteral(GPortugolParser.LiteralContext ctx) {
        int nodeNum = createNode("literal");
        return nodeNum;
    }

    @Override
    public Integer visitFunc_decls(GPortugolParser.Func_declsContext ctx) {
        int nodeNum = createNode("func_decls");
        return nodeNum;
    }

    @Override
    public Integer visitFvar_decl(GPortugolParser.Fvar_declContext ctx) {
        int nodeNum = createNode("fvar_decl");
        return nodeNum;
    }

    @Override
    public Integer visitFparams(GPortugolParser.FparamsContext ctx) {
        int nodeNum = createNode("fparams");
        return nodeNum;
    }

    @Override
    public Integer visitFparam(GPortugolParser.FparamContext ctx) {
        int nodeNum = createNode("fparam");
        return nodeNum;
    }*/
}
