grammar GPortugol;

algoritmo			:	declaracao_algoritmo (var_decl_block)? stm_block (func_decls)* EOF
					;

declaracao_algoritmo:	T_ALGORITMO T_IDENTIFICADOR ';'
					;

var_decl_block		:	T_VARIAVEIS (var_decl ';')+ 'fim_variaveis'
					;

var_decl			:	T_IDENTIFICADOR (',' T_IDENTIFICADOR)* ':' (tp_primitivo | tp_matriz)
					;

tp_primitivo		:	T_INTEIRO
					| 	T_REAL
					|	T_CARACTERE
					|	T_LITERAL
					|	T_LOGICO
					;

tp_matriz			:	'matriz' ('[' T_INT_LIT ']')+ 'de' tp_prim_pl
					;

tp_prim_pl			:	'inteiros'
					|	'reais'
					|	'caracteres'
					|	'literais'
					|	'logicos'
					;

stm_block			:	T_INICIO (stm_list)* 'fim'
					;

stm_list			:	stm_attr		#default_stm_list
					|	fcall ';'		#semicolon_stm_list
					|	stm_ret			#default_stm_list
					|	stm_se			#default_stm_list
					|	stm_enquanto	#default_stm_list
					|	stm_para		#default_stm_list
					;
stm_ret				:	T_RETORNE expr? ';'
					;

lvalue				:	T_IDENTIFICADOR ('[' expr ']')*;
					
stm_attr			:	lvalue T_ATTR expr ';'
					;

true_block : stm_list+ ;

false_block : stm_list+ ;

stm_se				:	T_SE expr 'entao' true_block ('senao' false_block)? 'fim_se'
					;

stm_enquanto		:	T_ENQUANTO expr 'faca' stm_list+ 'fim_enquanto'
					;

stm_para			:	T_PARA lvalue 'de' expr 'ate' expr passo? 'faca' stm_list+ 'fim_para'
					;

passo				:	T_PASSO op=('+'|'-')? T_INT_LIT
					;

expr				:	expr op=(T_OU) expr											#ExprOr
					|	expr op=(T_E) expr											#ExprAnd
					|	expr ('|') expr												#ExprBinaryOr
					|	expr ('^') expr												#ExprPow
					|	expr ('&') expr												#ExprBinaryAnd
					|	expr op=(T_IGUAL|T_DIFERENTE) expr							#ExprAtrrib
					|	expr op=(T_MAIOR|T_MAIORIGUAL|T_MENOR|T_MENORIGUAL) expr	#ExprComp
					|	expr op=(T_MAIS | T_MENOS) expr								#ExprAddSub
					|	expr op=(T_DIV|T_MULT|T_MOD) expr							#ExprMultDiv
					|	op=('+'|'-'|T_NAO)? termo									#ExprTermo
					;

termo				:	fcall			#default_termo
					|	lvalue			#default_termo
					|	literal			#default_termo
					|	'(' expr ')'	#parentesis_termo
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

func_decls			:	T_FUNCAO T_IDENTIFICADOR '(' fparams? ')' (':' tp_primitivo)? fvar_decl stm_block
					;

fvar_decl			:	(var_decl ';')*
					;

fparams				:	fparam (',' fparam)*
					;

fparam 				:	T_IDENTIFICADOR ':' (tp_primitivo | tp_matriz)
					;



fragment CR				:	'\r';
fragment LF				:	'\n';

fragment T_OCTAL_LIT	:	'0'('c'|'C')[0-7]+;
fragment T_HEX_LIT		:	'0'('x'|'X')[0-9a-fA-F]+;
fragment T_BIN_LIT		:	'0'('b'|'B')[01]+;
fragment T_DEC_LIT		:	[0-9]+;

T_REAL_LIT				:	T_DEC_LIT+'.'T_DEC_LIT+;
T_INT_LIT				:	T_OCTAL_LIT|T_HEX_LIT|T_BIN_LIT|T_DEC_LIT;

T_KW_VERDADEIRO         :   'verdadeiro';
T_KW_FALSO              :   'falso';

T_CARAC_LIT				:	'\''(~['\'''\\']|'\\'.)?'\'';
T_STRING_LIT			:	'"'(~('"'|'\\'|'\r'|'\n')|'\\'.)*'"';

SL_COMMENT				:	'//' .*? (LF|EOF) -> skip;
ML_COMMENT				:	'/*'.*?'*/' -> skip;      

T_IDENTIFICADOR			:	[a-zA-Z'_'][a-zA-Z0-9'_']*;

T_ALGORITMO				: 'algoritmo';
T_VARIAVEIS				: 'variaveis';
T_INICIO				: 'inicio';
T_RETORNE				: 'retorne'
T_SE					: 'se';
T_ENQUANTO				: 'enquanto';
T_PARA					: 'para';
T_PASSO					: 'passo';
T_FUNCAO				: 'funcao';

T_INTEIRO				: 'inteiro';
T_REAL					: 'real';
T_CARACTERE				: 'caractere';
T_LITERAL				: 'literal';
T_LOGICO				: 'logico';

T_ATTR					: ':=';

T_IGUAL					: '=';
T_DIFERENTE				: '<>';
T_MAIOR					: '>';
T_MENOR					: '<';
T_MAIORIGUAL			: '>=';
T_MENORIGUAL			: '<=';
T_MAIS					: '+';
T_MENOS					: '-';
T_MULT					: '*';
T_DIV					: '/';
T_MOD				: '%';
T_NAO					: ('~'|'nao');

T_OU					: ('ou'|'||');
T_E						: ('e'|'&&');



WS     : [' ''\t''\r''\n'] -> skip ;

