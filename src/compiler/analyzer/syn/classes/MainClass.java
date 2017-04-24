package compiler.analyzer.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.SyntaxEngine;

public class MainClass {
	public Identifier className;
	public Identifier arg;
	public Statement statement;
	
	public MainClass(ArrayList<Token> tokens, MutableInt tokensIndex) {
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("class"))
			SyntaxEngine.error(tokensIndex);
		
		className = new Identifier(tokens, tokensIndex);
		
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("{"))
			SyntaxEngine.error(tokensIndex);
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("public"))
			SyntaxEngine.error(tokensIndex);
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("static"))
			SyntaxEngine.error(tokensIndex);
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("void"))
			SyntaxEngine.error(tokensIndex);
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("main"))
			SyntaxEngine.error(tokensIndex);
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("("))
			SyntaxEngine.error(tokensIndex);
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("String"))
			SyntaxEngine.error(tokensIndex);
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("["))
			SyntaxEngine.error(tokensIndex);
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("]"))
			SyntaxEngine.error(tokensIndex);
		
		arg = new Identifier(tokens, tokensIndex);
		
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals(")"))
			SyntaxEngine.error(tokensIndex);
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("{"))
			SyntaxEngine.error(tokensIndex);
		
		statement = new Statement() // this is just interface, need to implement the children
		
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("}"))
			SyntaxEngine.error(tokensIndex);
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("}"))
			SyntaxEngine.error(tokensIndex);
	}
}
