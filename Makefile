all: compile
	
	
compile:
	antlr4 -visitor Trab2.g4
	javac *.java

clean:
	rm *.tokens
	rm GPortugol*.java
	rm *.class
