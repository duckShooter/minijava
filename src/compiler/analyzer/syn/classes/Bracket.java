package compiler.analyzer.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.visitor.CodePart;
import compiler.analyzer.syn.visitor.Visitor;

public class Bracket implements CodePart {
	public boolean isAvailable;
	
	public Bracket(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(leximes.get(leximesIndex.getValue()).equals("[") && leximes.get(leximesIndex.getValue() + 1).equals("]")){
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
