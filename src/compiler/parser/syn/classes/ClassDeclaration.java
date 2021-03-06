package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public class ClassDeclaration implements SyntaxRule {
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
		while(!leximes.get(leximesIndex.getValue()).value.equals("public") && !leximes.get(leximesIndex.getValue()).value.equals("private") && 
				!leximes.get(leximesIndex.getValue()).value.equals("}"))
			varDeclarations.add(new VarDeclaration(leximes, leximesIndex));
		
		methodDeclarations = new ArrayList<>();
		while(!leximes.get(leximesIndex.getValue()).value.equals("}"))
			methodDeclarations.add(new MethodDeclaration(leximes, leximesIndex));
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("}"))
			SyntaxEngine.error(leximesIndex);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
