%{
  #include<stdio.h>
%}

T_INT_LIT			({T_OCTAL_LIT}|{T_HEX_LIT}|{T_BIN_LIT}|{T_DEC_LIT})
T_DEC_LIT			[0-9]+
T_OCTAL_LIT			0(c|C)[0-8]+
T_HEX_LIT			0(x|X)[0-9a-fA-F]+
T_BIN_LIT			0(b|B)[01]+
T_REAL_LIT			{T_DEC_LIT}+\.{T_DEC_LIT}+

T_CARAC_LIT			\'(\\.|[^(\'|\\)])?\'
T_STRING_LIT		\"([^(\"|\\|"\n")]|\\.)*\"

SL_COMMENT			"//"[^"\n"]*("\n")?
ML_COMMENT			(\/\*)([^(\*\/)])*(\*\/)
COMMENT				{SL_COMMENT}|{ML_COMMENT}

T_IDENTIFICADOR		[a-zA-Z_][a-zA-Z0-9_]*

p_reservada			("algoritmo"|"inicio"|"fim"|"fim_variaveis"|"variaveis"|"inteiro"|"real"|"caractere"|"literal"|"logico"|"inicio"|"se"|"senao"|"entao"|"fim_se"|"enquanto"|"faca"|"fim_enquanto"|"para"|"de"|"ate"|"fim_para"|"matriz"|"inteiros"|"reais"|"caracteres"|"literais"|"logicos"|"funcao"|"retorne"|"passo"|"logico")
logico				("verdadeiro"|"falso")
operador_aritmetico	(\+|-|\*|\/|\%|"++"|"--")
operador_relacional	[>">="<"<="="<>"]
operador_logico		e|ou|nao
atribuicao			:=
simbolo_especial	("("|")"|"["|"]"|","|";"|":")
comentario			("/*"."*/")|("//"."\n")
espaco				" "

%%

{T_INT_LIT}				{ printf("%s -> INTEIRO\n",yytext); }
T_HEX_LIT				{ printf("PEITOBUNDA\n"); }
{p_reservada}			{ printf("%s -> PALAVRA_RESERVADA\n",yytext); }
{T_REAL_LIT}			{ printf("%s -> REAL\n",yytext); }
{T_CARAC_LIT}			{ printf("%s -> CARACTERE\n",yytext); }
{T_STRING_LIT}			{ printf("%s -> LITERAL\n",yytext); }
{simbolo_especial}		{ printf("%s -> SIMBOLO_ESPECIAL\n",yytext); }
SL_COMMENT				{ printf("COMMENT\n"); }
"//"[^"\n"]*("\n")?		{ printf("LF\n"); }
%{
/*
[a-zA-Z_][a-zA-Z0-9_]*	{ printf("%s -> IDENTIFICADOR\n",yytext); }

{logico}				
\"						{ printf("Achei\n"); }
{operador_aritmetico}	{ printf("%s -> OPERADOR_ARITMETICO\n",yytext); }
{operador_relacional}	{ printf("%s -> OPERADOR_RELACIONAL\n",yytext); }
{operador_logico}		{ printf("%s -> OPERADOR_LOGICO\n",yytext); }
{atribuicao}			{ printf("%s -> ATRIBUICAO\n",yytext); }
comentario				;
espaco					;
fim_linha				;
;						{ printf("PV\n"); }
*/%}
.						;
%{
	/*{ printf("%s -> DESCONHECIDO\n",yytext); }*/
%}

%%
int main() {
  yylex();
  return 0;
}
