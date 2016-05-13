ANTLR = -Xmx500M -cp "/usr/local/lib/antlr-4.5-complete.jar:$CLASSPATH" org.antlr.v4.Tool

all: teste

teste: compile
	java GeradorArvore Entrada.txt saida.dot
	dot -Tpdf saida.dot -o tree.pdf

compile:
	java $(ANTLR) -visitor GPortugol.g4
	javac *.java

clean:
	rm *.tokens
	rm GPortugol*.java
	rm *.class
	rm saida.dot
	rm tree.pdf
