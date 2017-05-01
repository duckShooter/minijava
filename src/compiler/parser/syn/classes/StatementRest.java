package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public abstract class  StatementRest implements SyntaxRule {

	public static StatementRest getStatementType(ArrayList<Lexime> leximes, MutableInt leximesIndex){
		if(leximes.get(leximesIndex.getValue()).value.equals("="))
			return new StatementRestEqual(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("["))
			return new StatementRestBracket(leximes, leximesIndex);
		else
			SyntaxEngine.error(leximesIndex);
		return null;
	}
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub

	}
}
