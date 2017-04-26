package compiler.parser.syn;

import java.util.ArrayList;

import compiler.analyzer.lex.Token;

public class SyntaxEngine {
	private static ArrayList<Token> tokens;
	public static void main(String[] args) {
		readTokens();
		
	}
	private static void analyzeSyntax(){
		
	}
	private static void readTokens() {
		// TODO Auto-generated method stub
		
	}
	public static void error(MutableInt index){
		System.err.println("Syntax error within token " + tokens.get(index.getValue() - 1).regex + ", at index" + (index.getValue() + 1));
		System.exit(1);
	}

}
