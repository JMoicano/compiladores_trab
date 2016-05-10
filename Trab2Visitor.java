// Generated from Trab2.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Trab2Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Trab2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#algoritmo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlgoritmo(Trab2Parser.AlgoritmoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#declaracao_algoritmo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_algoritmo(Trab2Parser.Declaracao_algoritmoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#var_decl_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_decl_block(Trab2Parser.Var_decl_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#var_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_decl(Trab2Parser.Var_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#tp_primitivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTp_primitivo(Trab2Parser.Tp_primitivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#tp_matriz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTp_matriz(Trab2Parser.Tp_matrizContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#tp_prim_pl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTp_prim_pl(Trab2Parser.Tp_prim_plContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#stm_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStm_block(Trab2Parser.Stm_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#stm_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStm_list(Trab2Parser.Stm_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#stm_ret}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStm_ret(Trab2Parser.Stm_retContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalue(Trab2Parser.LvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#stm_attr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStm_attr(Trab2Parser.Stm_attrContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#stm_se}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStm_se(Trab2Parser.Stm_seContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#stm_enquanto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStm_enquanto(Trab2Parser.Stm_enquantoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#stm_para}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStm_para(Trab2Parser.Stm_paraContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#passo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasso(Trab2Parser.PassoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link Trab2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(Trab2Parser.ExprAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link Trab2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(Trab2Parser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPow}
	 * labeled alternative in {@link Trab2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPow(Trab2Parser.ExprPowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprBinaryOr}
	 * labeled alternative in {@link Trab2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBinaryOr(Trab2Parser.ExprBinaryOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link Trab2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOr(Trab2Parser.ExprOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprComp}
	 * labeled alternative in {@link Trab2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprComp(Trab2Parser.ExprCompContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link Trab2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultDiv(Trab2Parser.ExprMultDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprBinaryAnd}
	 * labeled alternative in {@link Trab2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBinaryAnd(Trab2Parser.ExprBinaryAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAtrrib}
	 * labeled alternative in {@link Trab2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAtrrib(Trab2Parser.ExprAtrribContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprTermo}
	 * labeled alternative in {@link Trab2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprTermo(Trab2Parser.ExprTermoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(Trab2Parser.TermoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#fcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFcall(Trab2Parser.FcallContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#fargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFargs(Trab2Parser.FargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(Trab2Parser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#func_decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_decls(Trab2Parser.Func_declsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#fvar_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFvar_decl(Trab2Parser.Fvar_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#fparams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFparams(Trab2Parser.FparamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Trab2Parser#fparam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFparam(Trab2Parser.FparamContext ctx);
}