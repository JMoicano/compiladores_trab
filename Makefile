ANTLR = -Xmx500M -cp "/usr/local/lib/antlr-4.5-complete.jar:$CLASSPATH" org.antlr.v4.Tool

all: compile

compile:
	java $(ANTLR) -visitor GPortugol.g4
	javac *.java

clean:
	rm *.tokens
	rm GPortugol*.java
	rm *.class
