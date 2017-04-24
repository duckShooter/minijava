package compiler.analyzer.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.SyntaxEngine;

public class VarDeclaration {
	public Type type;
	public Identifier identifier;
	
	public VarDeclaration(ArrayList<Token> tokens, MutableInt tokensIndex) {
		if(tokens.get(tokensIndex.getValue()).regex.equals("int"))
			type = new TypeInt(tokens, tokensIndex);
		else if(tokens.get(tokensIndex.getValue()).regex.equals("boolean"))
			type = new TypeBoolean(tokens, tokensIndex);
		else if(tokens.get(tokensIndex.getValue()).regex.equals("char"))
			type = new TypeChar(tokens, tokensIndex);
		else if(tokens.get(tokensIndex.getValue()).regex.equals("String"))
			type = new TypeString(tokens, tokensIndex);
		else if(tokens.get(tokensIndex.getValue()).regex.equals("float"))
			type = new TypeFloat(tokens, tokensIndex);
		else
			SyntaxEngine.error(tokensIndex);
 
		identifier = new Identifier(tokens, tokensIndex);
		
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals(";"))
			SyntaxEngine.error(tokensIndex);
	}
}
