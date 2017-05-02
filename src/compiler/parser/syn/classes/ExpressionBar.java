package compiler.parser.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public class ExpressionBar implements SyntaxRule {
	public ExpressionBar expressionBar;
	public ExpressionRest expressionRest;

	ExpressionBar(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		expressionRest = ExpressionRest.getType(leximes, leximesIndex); //FIX
		
		switch(leximes.get(leximesIndex.getValue()).value){
		case "&&":
		case "<" :
		case "+" :
		case "-" :
		case "*" :
		case "[" :
		case "." :
			expressionBar = new ExpressionBar(leximes, leximesIndex);
			break;
		default :
			expressionBar = null;
		}
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
