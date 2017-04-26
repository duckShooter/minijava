package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public class Bracket implements SyntaxRule {
	public boolean isAvailable;
	
	public Bracket(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(leximes.get(leximesIndex.getValue()).value.equals("[") && leximes.get(leximesIndex.getValue() + 1).value.equals("]")){
			leximesIndex.increment();
			leximesIndex.increment();
			isAvailable = true;
		} else
			isAvailable = false;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
