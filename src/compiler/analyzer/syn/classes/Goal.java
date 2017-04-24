package compiler.analyzer.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;

public class Goal {
	private MutableInt tokensIndex;
	public MainClass mainClass;
	public ArrayList<ClassDeclaration> classDeclarations;
	
	public Goal(ArrayList<Token> tokens) {
		mainClass = new MainClass(tokens, tokensIndex);
		
		classDeclarations = new ArrayList<>();
		while(tokensIndex.getValue() <= tokens.size()){
			classDeclarations.add(new ClassDeclaration(tokens, tokensIndex));
		}
	}
}
