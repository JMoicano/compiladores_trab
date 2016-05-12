
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

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
    
    
    private int createNode(String label){
        System.out.println("node" + i + "[label=\"" + label + "\"];");
        return i++;
    }
    private void createChild(int p, int c){
        System.out.println("node" + p + " -> node" + c +";");
    }
    private Integer creatChildren(String parentName, int parentNum, ParserRuleContext parent){
        createNode(parentName);
        for(ParseTree child : parent.children){
            int childNum = visit(child);
            createChild(parentNum, childNum);
        }
        return parentNum;
    }
    
    
    @Override
    public Integer visitAlgoritmo(GPortugolParser.AlgoritmoContext ctx) {
        i = 0;
        return creatChildren("algoritmo_goal", i, ctx);
    }

    @Override
    public Integer visitDeclaracao_algoritmo(GPortugolParser.Declaracao_algoritmoContext ctx) {
        int nodeNum = i;
        createChild(nodeNum, createNode("algoritmo"));
        createChild(nodeNum, createNode(ctx.T_IDENTIFICADOR().getText()));
        createChild(nodeNum, createNode(";"));
        return nodeNum;
    }

    @Override
    public Integer visitVar_decl_block(GPortugolParser.Var_decl_blockContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitVar_decl(GPortugolParser.Var_declContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitTp_primitivo(GPortugolParser.Tp_primitivoContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitTp_matriz(GPortugolParser.Tp_matrizContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitTp_prim_pl(GPortugolParser.Tp_prim_plContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitStm_block(GPortugolParser.Stm_blockContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitStm_list(GPortugolParser.Stm_listContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitStm_ret(GPortugolParser.Stm_retContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitLvalue(GPortugolParser.LvalueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitStm_attr(GPortugolParser.Stm_attrContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitStm_se(GPortugolParser.Stm_seContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitStm_enquanto(GPortugolParser.Stm_enquantoContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitStm_para(GPortugolParser.Stm_paraContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitPasso(GPortugolParser.PassoContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitExprComp(GPortugolParser.ExprCompContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitExprBinaryOr(GPortugolParser.ExprBinaryOrContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitExprAddSub(GPortugolParser.ExprAddSubContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitExprTermo(GPortugolParser.ExprTermoContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitExprMultDiv(GPortugolParser.ExprMultDivContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitExprAtrrib(GPortugolParser.ExprAtrribContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitExprBinaryAnd(GPortugolParser.ExprBinaryAndContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitExprAnd(GPortugolParser.ExprAndContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitExprOr(GPortugolParser.ExprOrContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitExprPow(GPortugolParser.ExprPowContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitTermo(GPortugolParser.TermoContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitFcall(GPortugolParser.FcallContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitFargs(GPortugolParser.FargsContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitLiteral(GPortugolParser.LiteralContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitFunc_decls(GPortugolParser.Func_declsContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitFvar_decl(GPortugolParser.Fvar_declContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitFparams(GPortugolParser.FparamsContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Integer visitFparam(GPortugolParser.FparamContext ctx) {
        return visitChildren(ctx);
    }
}
