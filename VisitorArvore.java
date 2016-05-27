import java.util.LinkedList;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author jsilva
 */
public class VisitorArvore extends GPortugolBaseVisitor<Integer> {

    private static int nodeCount;
    private static TabelaSimbolos<Variavel> tabelaVariaveis;
    private static TabelaSimbolos<Funcao> tabelaFuncoes;
    private static LinkedList<Funcao> funcoesUsadas;

    private int createNode(String label) {
        System.out.println("node" + nodeCount + "[label=\"" + label + "\"];");
        return nodeCount++;
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

    private void createChild(int p, ParseTree c) {
        int childNum = visit(c);
        createChild(p, childNum);
    }

    private void createChild(int p, TerminalNode c) {
        createChild(p, c.getText());
    }

    private TpPrimitivo translateTipo(String entrada){
        switch(entrada){
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
        }
        return TpPrimitivo.INTEIRO;
    }
    
    @Override
    public Integer visitAlgoritmo(GPortugolParser.AlgoritmoContext ctx) {
        nodeCount = 0;
        tabelaVariaveis = new TabelaSimbolos<>();
        int nodeNum = createNode("algoritmo_goal");
        createChild(nodeNum, ctx.declaracao_algoritmo());
        if (ctx.var_decl_block() != null) {
            createChild(nodeNum, ctx.var_decl_block());
        }
        createChild(nodeNum, ctx.stm_block());
        for (ParserRuleContext func_decls : ctx.func_decls()) {
            createChild(nodeNum, func_decls);
        }
        //TODO: VERIFICAR SE TODAS AS FUNCOES EM FUNCOESLIDAS ESTAO CORRETAS COM O TABELAFUNCOES
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
        createChild(nodeNum, "fim_variaveis");
        return nodeNum;
    }

    @Override
    public Integer visitVar_decl(GPortugolParser.Var_declContext ctx) {
        int nodeNum = createNode("var_decl");
        LinkedList<String> identificadores = new LinkedList<>();
        createChild(nodeNum, ctx.T_IDENTIFICADOR(0));
        identificadores.add(ctx.T_IDENTIFICADOR(0).getText());
        for (int j = 1; j < ctx.T_IDENTIFICADOR().size(); j++) {
            createChild(nodeNum, ",");
            createChild(nodeNum, ctx.T_IDENTIFICADOR(j));
            identificadores.add(ctx.T_IDENTIFICADOR(j).getText());
        }
        createChild(nodeNum, ":");
        TpPrimitivo tipo = null;
        if (ctx.tp_primitivo() != null) {
            createChild(nodeNum, ctx.tp_primitivo());
            tipo = translateTipo(ctx.tp_primitivo().getText());
        } else {
            createChild(nodeNum, ctx.tp_matriz());
        }
        for (String identificadore : identificadores) {
            Variavel v = new Variavel(identificadore, tipo, nodeNum); //TODO: PEGAR NUMERO DA LINHA
            tabelaVariaveis.add(v);
        }
        return nodeNum;
    }

    @Override
    public Integer visitTp_primitivo(GPortugolParser.Tp_primitivoContext ctx) {
        int nodeNum = createNode("tp_primitivo");
        createChild(nodeNum, ctx.getText());
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
        createChild(nodeNum, "de");
        createChild(nodeNum, ctx.tp_prim_pl());
        return nodeNum;
    }

    @Override
    public Integer visitTp_prim_pl(GPortugolParser.Tp_prim_plContext ctx) {
        int nodeNum = createNode("tp_prim_pl");
        createChild(nodeNum, ctx.getText());
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
        createChild(nodeNum, ctx.getChild(0));
        return nodeNum;
    }

    @Override
    public Integer visitSemicolon_stm_list(GPortugolParser.Semicolon_stm_listContext ctx) {
        int nodeNum = createNode("stm_list");
        createChild(nodeNum, ctx.getChild(0));
        createChild(nodeNum, ";");
        return nodeNum;
    }

    @Override
    public Integer visitStm_ret(GPortugolParser.Stm_retContext ctx) {
        int nodeNum = createNode("stm_ret");
        createChild(nodeNum, "retorne");
        if (ctx.expr() != null) {
            createChild(nodeNum, ctx.expr());
        }
        return nodeNum;
    }

    @Override
    public Integer visitLvalue(GPortugolParser.LvalueContext ctx) {
        int nodeNum = createNode("lvalue");
        createChild(nodeNum, ctx.T_IDENTIFICADOR());
        tabelaVariaveis.lookUp(ctx.T_IDENTIFICADOR().toString()); //TODO: TRATAR O LOOKUP (VER SE A VARIAVEL USADA FOI DECLARADA)
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
        //TODO: VERIFICAR TIPO
        return nodeNum;
    }

    @Override
    public Integer visitStm_se(GPortugolParser.Stm_seContext ctx) {
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
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprBinaryOr(GPortugolParser.ExprBinaryOrContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, "|");
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprAddSub(GPortugolParser.ExprAddSubContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprTermo(GPortugolParser.ExprTermoContext ctx) {
        int nodeNum = createNode("expr");
        if (ctx.op != null) {
            createChild(nodeNum, ctx.op.getText());
        }
        createChild(nodeNum, ctx.termo());
        return nodeNum;
    }

    @Override
    public Integer visitExprMultDiv(GPortugolParser.ExprMultDivContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprAtrrib(GPortugolParser.ExprAtrribContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprBinaryAnd(GPortugolParser.ExprBinaryAndContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, "&");
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprAnd(GPortugolParser.ExprAndContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprOr(GPortugolParser.ExprOrContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, ctx.op.getText());
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitExprPow(GPortugolParser.ExprPowContext ctx) {
        int nodeNum = createNode("expr");
        createChild(nodeNum, ctx.expr(0));
        createChild(nodeNum, "^");
        createChild(nodeNum, ctx.expr(1));
        return nodeNum;
    }

    @Override
    public Integer visitDefault_termo(GPortugolParser.Default_termoContext ctx) {
        int nodeNum = createNode("termo");
        createChild(nodeNum, ctx.getChild(0));
        return nodeNum;
    }

    @Override
    public Integer visitParentesis_termo(GPortugolParser.Parentesis_termoContext ctx) {
        int nodeNum = createNode("termo");
        createChild(nodeNum, "(");
        createChild(nodeNum, ctx.expr());
        createChild(nodeNum, ")");
        return nodeNum;
    }

    @Override
    public Integer visitFcall(GPortugolParser.FcallContext ctx) {
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
    public Integer visitFargs(GPortugolParser.FargsContext ctx) {
        int nodeNum = createNode("fargs");
        createChild(nodeNum, ctx.expr(0));
        for (int j = 1; j < ctx.expr().size(); j++) {
            createChild(nodeNum, ctx.expr(j));
        }
        return nodeNum;
    }

    @Override
    public Integer visitLiteral(GPortugolParser.LiteralContext ctx) {
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
    public Integer visitFunc_decls(GPortugolParser.Func_declsContext ctx) {
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
    public Integer visitFvar_decl(GPortugolParser.Fvar_declContext ctx) {
        int nodeNum = createNode("fvar_decl");
        for (ParserRuleContext var_decl : ctx.var_decl()) {
            createChild(nodeNum, var_decl);
            createChild(nodeNum, ";");
        }
        return nodeNum;
    }

    @Override
    public Integer visitFparams(GPortugolParser.FparamsContext ctx) {
        int nodeNum = createNode("fparams");
        createChild(nodeNum, ctx.fparam(0));
        for (int j = 1; j < ctx.fparam().size(); j++) {
            createChild(nodeNum, ",");
            createChild(nodeNum, ctx.fparam(j));
        }
        return nodeNum;
    }

    @Override
    public Integer visitFparam(GPortugolParser.FparamContext ctx) {
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
