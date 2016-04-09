%{
  #include<stdio.h>
%}

digit			[0-9]
letter			[a-zA-Z]
p_reservada			"algoritmo"|"inicio"|"fim"
					|"fim_variaveis"|"variáveis"|"inteiro"
					|"real"|"caractere"|"literal"
					|"lógico"|"início"|"ou"
					|"e"|"não"|"se"
					|"senão"|"então"|"fim_se"
					|"enquanto"|"faça"|"fim_enquanto"
					|"para"|"de"|"até"
					|"fim_para"|"matriz"|"inteiros"
					|"reais"|"caracteres"|"literais"
					|"lógicos"|"função"|"retorne"
					|"passo"|"logico"
logico				"verdadeiro"|"falso"
operador_aritmetico	["+""-""*""/""%""++""--"]
operador_relacional	[>">="<"<="="<>"]
operador_logico		e|ou|nao
atribuicao			:=
simbolo_especial	["("")""[""]"","";"":"]
comentario			("/*"."*/")|("//"."\n")
espaco				" "
fim_linha			"\n"

%%
{p_reservada}			{ printf("%s -> PALAVRA_RESERVADA\n",yytext); }
//TODO: Olhar regras de identificadores no manual da linguagem
{digit}+				{ printf("%s -> INTEIRO\n",yytext); }
{digit}+("."){digit}+	{ printf("%s -> REAL\n",yytext); }
\'{letter}{1}\'			{ printf("%s -> CARACTERE\n",yytext); }
\"{letter}{2,}\"		{ printf("%s -> LITERAL\n",yytext); }
{logico}				{ printf("%s -> LOGICO\n",yytext); }
{operador_aritmetico}	{ printf("%s -> OPERADOR_ARITMETICO\n",yytext); }
{operador_relacional}	{ printf("%s -> OPERADOR_RELACIONAL\n",yytext); }
{operador_logico}		{ printf("%s -> OPERADOR_LOGICO\n",yytext); }
{atribuicao}			{ printf("%s -> ATRIBUICAO\n",yytext); }
{simbolo_especial}		{ printf("%s -> SIMBOLO_ESPECIAL\n",yytext); }
comentario				;
espaco					;
fim_linha				;
.						{ printf("%s -> DESCONHECIDO\n",yytext); }


. {}

%%
int main() {
  yylex();
  return 0;
}
