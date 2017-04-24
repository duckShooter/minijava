package compiler.analyzer.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.SyntaxEngine;

public class ClassDeclaration {
	public Identifier className;
	public Identifier extendedClassName;
	public ArrayList<VarDeclaration> varDeclarations;
	public ArrayList<MethodDeclaration> methodDeclarations;
	
	public ClassDeclaration(ArrayList<Token> tokens, MutableInt tokensIndex) {
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("class"))
			SyntaxEngine.error(tokensIndex);
		
		className = new Identifier(tokens, tokensIndex);
		
		if(tokens.get(tokensIndex.getValue()).regex.equals("extends")){
			tokensIndex.increment();
			extendedClassName = new Identifier(tokens, tokensIndex);
		}
		
		if(!tokens.get(tokensIndex.getAndIncrement()).regex.equals("{"))
			SyntaxEngine.error(tokensIndex);
			
		varDeclarations = new ArrayList<>();
		while(!tokens.get(tokensIndex.getValue()).regex.equals("public") && !tokens.get(tokensIndex.getValue()).equals("private") && 
				tokens.get(tokensIndex.getValue()).regex.equals("}"))
			varDeclarations.add(new VarDeclaration(tokens, tokensIndex));
		
		methodDeclarations = new ArrayList<>();
		while(!tokens.get(tokensIndex.getValue()).regex.equals("}"))
			methodDeclarations.add(new MethodDeclaration(tokens, tokensIndex));
	}
}
