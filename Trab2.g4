grammar Trab2;

algoritmo			:	declaracao_algoritmo (var_decl_block)? stm_block (func_decls)* EOF
					;
declaracao_algoritmo    :	'algoritmo' T_IDENTIFICADOR ';'
					;
var_decl_block		:	'variaveis' (var_decl ';')+ 'fim_variaveis'
					;
var_decl			:	T_IDENTIFICADOR (',' T_IDENTIFICADOR)* ':' (tp_primitivo | tp_matriz)
					;
tp_primitivo		:				'inteiro'
					| 	'real'
					|   	'caractere'
					|	'literal'
					|	'logico'
					;
tp_matriz			:	'matriz' ('[' T_INT_LIT ']')+ 'de' tp_prim_pl
					;
tp_prim_pl			:			'inteiros'
					|	'reais'
					|	'caracteres'
					|	'literais'
					|	'logicos'
					;
stm_block			:	'inicio' (stm_list)* 'fim'
					;
stm_list			:	stm_attr		
					|	fcall ';'		#semicolon_stm_list
					|	stm_ret			
					|	stm_se			
					|	stm_enquanto	
					|	stm_para		
					;
stm_ret				:	'retorne' expr? ';'
					;

lvalue				:	T_IDENTIFICADOR ('[' expr ']')*;
					
stm_attr			:	lvalue ':=' expr ';'
					;
stm_se				:	'se' expr 'entao' stm_list+ ('senao' stm_list+)? 'fim_se'
					;
stm_enquanto		:	'enquanto' expr 'faca' stm_list+ 'fim_enquanto'
					;
stm_para			:	'para' lvalue 'de' expr 'ate' expr passo? 'faca' stm_list+ 'fim_para'
					;
passo				:	'passo' ('+'|'-')? T_INT_LIT
					;
expr				:	expr op=('ou'|'||') expr                   #ExprOr
					|	expr op=('e'|'&&') expr            #ExprAnd
					|	expr ('|') expr                   #ExprBinaryOr
					|	expr ('^') expr                   #ExprPow
					|	expr ('&') expr                   #ExprBinaryAnd
					|	expr op=('='|'<>') expr            #ExprAtrrib
					|	expr op=('>'|'>='|'<'|'<=') expr   #ExprComp
					|	expr op=('+' | '-') expr           #ExprAddSub
					|	expr op=('/'|'*'|'%') expr         #ExprMultDiv
					|	op=('+'|'-'|'~'|'nao')? termo      #ExprTermo
					;
termo				:	fcall
					|	lvalue
					|	literal
					|	'(' expr ')'
					;
					
fcall				:	T_IDENTIFICADOR '(' fargs? ')';



fargs				:	expr (',' expr)*;

literal				:	T_STRING_LIT
					|	T_INT_LIT
					|	T_REAL_LIT
					|	T_CARAC_LIT
					|	T_KW_VERDADEIRO
					|	T_KW_FALSO
				;

func_decls			:	'funcao' T_IDENTIFICADOR '(' fparams? ')' (':' tp_primitivo)? fvar_decl stm_block
					;
fvar_decl			:	(var_decl ';')*
					;
fparams				:	fparam (',' fparam)*
					;
fparam 				:	T_IDENTIFICADOR ':' (tp_primitivo | tp_matriz)
					;

fragment CR				:	'\r';
fragment LF				:	'\n';

fragment T_OCTAL_LIT	:	'0'('c'|'C')[0-8]+;
fragment T_HEX_LIT		:	'0'('x'|'X')[0-9'a'-'f''A'-'F']+;
fragment T_BIN_LIT		:	'0'('b'|'B')[01]+;
fragment T_DEC_LIT		:	[0-9]+;
T_REAL_LIT		:	T_DEC_LIT+'.'T_DEC_LIT+;
T_INT_LIT				:	T_OCTAL_LIT|T_HEX_LIT|T_BIN_LIT|T_DEC_LIT;

T_KW_VERDADEIRO         :   'verdadeiro';
T_KW_FALSO              :   'falso';

T_CARAC_LIT				:	'\''(~('\''|'\\')|'\\'.)?'\'';
T_STRING_LIT			:	'"'(~('"'|'\\'| '\r' | '\n' )|'\\'.)*'"';

SL_COMMENT				:	'//' .*? (LF|EOF) -> skip;
ML_COMMENT				:	'/*'.*?'*/' -> skip;      

T_IDENTIFICADOR			:	[a-zA-Z'_'][a-zA-Z0-9'_']*;

WS     : [' '\t\r\n]+ -> skip ;