package compiler.parser.syn.classes;


import java.util.ArrayList;
import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;
public class ExpressionBar implements SyntaxRule {
	public ExpressionBar expressionBar;
	public ExpressionRest expressionRest;
	ExpressionBar(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (leximes.get(leximesIndex.getValue()).value.equals("&&")
				|| leximes.get(leximesIndex.getValue()).value.equals("<")
				|| leximes.get(leximesIndex.getValue()).value.equals("+")
				|| leximes.get(leximesIndex.getValue()).value.equals("-")
				|| leximes.get(leximesIndex.getValue()).value.equals("*")|| leximes.get(leximesIndex.getValue()).value.equals("[")|| leximes.get(leximesIndex.getValue()).value.equals(".")){
			expressionBar= new ExpressionBar( leximes,leximesIndex);
			expressionRest= new ExpressionRest( leximes,leximesIndex);
		}
		else{
			leximesIndex.increment();
		}
		
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
