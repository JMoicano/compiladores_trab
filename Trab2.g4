grammar Trab2;

//p_reservada			('algoritmo'|'inicio'|'fim'|'fim_variaveis'|'variaveis'|'inteiro'|'real'|'caractere'|'literal'|'logico'|'inicio'|'se'|'senao'|'entao'|'fim_se'|'enquanto'|'faca'|'fim_enquanto'|'para'|'de'|'ate'|'fim_para'|'matriz'|'inteiros'|'reais'|'caracteres'|'literais'|'logicos'|'funcao'|'retorne'|'passo'|'logico')
//logico				('verdadeiro'|'falso')
//operador_aritmetico	(\+|-|\*|\/|\%|'++'|'--')
//operador_relacional	('>'|'>='|'<'|'<='|'='|'<>')
//operador_logico		e|ou|nao
//atribuicao			:=
//simbolo_especial	('('|')'|'['|']'|','|';'|':')
//comentario			('/*'.'*/')|('//'.'\n')
//espaco				' '

expr				:	expr ('ou'|'||') expr
					|	expr ('e'|'&&') expr
					|	expr '|' expr
					|	expr '^' expr
					|	expr '&' expr
					|	expr ('='|'<>') expr
					|	expr ('>'|'>='|'<'|'<=') expr
					|	expr ('+' | '-') expr
					|	expr ('/'|'*'|'%') expr
					|	('+'|'-'|'~'|'nao')? termo
					;
termo				:	fcall
					|	lvalue
					|	literal
					|	'(' expr ')'
					;
					
fcall				:	T_IDENTIFICADOR '(' fargs? ')';

lvalue				:	T_IDENTIFICADOR ('[' expr ']')*;

literal				:	T_STRING_LIT
					|	T_INT_LIT
					|	T_REAL_LIT
					|	T_CARAC_LIT
				//	|	T_KW_VERDADEIRO
				//	|	T_KW_FALSO
				;

fargs				:	expr (',' expr)*;

algoritmo			:	declaracao_algoritmo (var_decl_block)? stm_block (func_decls)* EOF
					;
declaracao_algoritmo:	'algoritmo' T_IDENTIFICADOR ';'
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
					|	fcall ';'
					|	stm_ret
					|	stm_se
					|	stm_enquanto
					|	stm_para
					;
stm_ret				:	'retorne' expr? ';'
					;

					
stm_attr			:	lvalue ':=' expr ';'
					;
stm_se				:	'se' expr 'entao' stm_list ('senao' stm_list)? 'fim_se'
					;
stm_enquanto		:	'enquanto' expr 'faca' stm_list 'fim_enquanto'
					;
stm_para			:	'para' lvalue 'de' expr 'ate' expr passo? 'faca' stm_list 'fim_para'
					;
passo				:	'passo' ('+'|'-')? T_INT_LIT
					;


					
func_decls			:	'funcao' T_IDENTIFICADOR '(' fparams? ')'// (':' tb_primitivo)?
fvar_decl
stm_block
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
fragment T_REAL_LIT		:	T_DEC_LIT+'.'T_DEC_LIT+;
T_INT_LIT				:	T_OCTAL_LIT|T_HEX_LIT|T_BIN_LIT|T_DEC_LIT;

T_CARAC_LIT				:	'\''(~('\''|'\\')|'\\'.)?'\'';
T_STRING_LIT			:	'"'(~('"'|'\\'| '\r' | '\n' )|'\\'.)*'"';

SL_COMMENT				:	'/'[~LF]*('\n')?;
ML_COMMENT				:	'/*' .*? '*/';

T_IDENTIFICADOR			:	['a'-'z''A'-'Z''_']['a'-'z''A'-'Z'0-9'_']*;
