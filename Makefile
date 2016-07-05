LIB = /usr/local/lib/
ANTLR = -Xmx500M -cp $(LIB)antlr-4.5.3-complete.jar:$CLASSPATH org.antlr.v4.Tool

all: compile

compile: precompile
	javac *.java

precompile:
	java $(ANTLR) -visitor GPortugol.g4

clean:
	rm *.tokens
	rm GPortugol*.java
	rm *.class
	rm *.dot
	rm *.pdf
