package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public class IfRest implements SyntaxRule {

	Statement stmt;
	public IfRest (ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("else"))
			SyntaxEngine.error(leximesIndex);
		// stmt = new Statement(leximes,leximesIndex);
		
		
	}
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub

	}
}