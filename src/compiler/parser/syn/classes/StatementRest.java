package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public class StatementRest implements SyntaxRule {

	public Expression exp1, exp2;

	public StatementRest(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (leximes.get(leximesIndex.getValue()).value.equals("=")) {
			leximesIndex.increment();
			leximesIndex.increment();
			
			if (!leximes.get(leximesIndex.getValue()).value.equals(";")) 
				SyntaxEngine.error(leximesIndex);

		} else if (leximes.get(leximesIndex.getValue()).value.equals("[")) {
			leximesIndex.increment();
			leximesIndex.increment();
			
			if (!leximes.get(leximesIndex.getValue()).value.equals("]"))
				SyntaxEngine.error(leximesIndex);
			
			leximesIndex.increment();
			if (!leximes.get(leximesIndex.getValue()).value.equals("="))
				SyntaxEngine.error(leximesIndex);
			
			leximesIndex.increment();
			if (!leximes.get(leximesIndex.getValue()).value.equals(";"))
				SyntaxEngine.error(leximesIndex);
		} else
			SyntaxEngine.error(leximesIndex);
	}
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub

	}
}
