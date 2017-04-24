package compiler.analyzer.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.visitor.CodePart;
import compiler.analyzer.syn.visitor.Visitor;

public class Goal implements CodePart {
	private MutableInt leximesIndex;
	public MainClass mainClass;
	public ArrayList<ClassDeclaration> classDeclarations;
	
	public Goal(ArrayList<Lexime> leximes) {
		mainClass = new MainClass(leximes, leximesIndex);
		
		classDeclarations = new ArrayList<>();
		while(leximesIndex.getValue() <= leximes.size()){
			classDeclarations.add(new ClassDeclaration(leximes, leximesIndex));
		}
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
