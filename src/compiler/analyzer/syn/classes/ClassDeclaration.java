package compiler.analyzer.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.SyntaxEngine;
import compiler.analyzer.syn.visitor.CodePart;
import compiler.analyzer.syn.visitor.Visitor;

public class ClassDeclaration implements CodePart {
	public Identifier className;
	public Identifier extendedClassName;
	public ArrayList<VarDeclaration> varDeclarations;
	public ArrayList<MethodDeclaration> methodDeclarations;
	
	public ClassDeclaration(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("class"))
			SyntaxEngine.error(leximesIndex);
		
		className = new Identifier(leximes, leximesIndex);
		
		if(leximes.get(leximesIndex.getValue()).value.equals("extends")){
			leximesIndex.increment();
			extendedClassName = new Identifier(leximes, leximesIndex);
		}
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("{"))
			SyntaxEngine.error(leximesIndex);
			
		varDeclarations = new ArrayList<>();
		while(!leximes.get(leximesIndex.getValue()).value.equals("public") && !leximes.get(leximesIndex.getValue()).equals("private") && 
				!leximes.get(leximesIndex.getValue()).value.equals("}"))
			varDeclarations.add(new VarDeclaration(leximes, leximesIndex));
		
		methodDeclarations = new ArrayList<>();
		while(!leximes.get(leximesIndex.getValue()).value.equals("}"))
			methodDeclarations.add(new MethodDeclaration(leximes, leximesIndex));
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
