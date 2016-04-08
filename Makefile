all:
	flex Trab.l
	gcc lex.yy.c -lfl
	./a.out < Entrada.txt
