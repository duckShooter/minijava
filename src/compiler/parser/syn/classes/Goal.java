package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public class Goal implements SyntaxRule {
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
