package compiler.parser.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public class ExpressionRest implements SyntaxRule {
	public Expression expression;
	public DotRest dotRest;
	public String op; //Terminal

	ExpressionRest(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (leximes.get(leximesIndex.getValue()).value.equals("&&")
				|| leximes.get(leximesIndex.getValue()).value.equals("<")
				|| leximes.get(leximesIndex.getValue()).value.equals("+")
				|| leximes.get(leximesIndex.getValue()).value.equals("-")
				|| leximes.get(leximesIndex.getValue()).value.equals("*")) {
			op = leximes.get(leximesIndex.getAndIncrement()).value; //Save the type of operation (for printing later)
			expression = Expression.getExpression(leximes, leximesIndex);

		} else if (leximes.get(leximesIndex.getValue()).value.equals("[")) {
			leximesIndex.increment();
			expression = Expression.getExpression(leximes, leximesIndex);
			if (leximes.get(leximesIndex.getValue()).value.equals("]"))
				leximesIndex.increment();				
			else
				SyntaxEngine.error(leximesIndex);

		} else if (leximes.get(leximesIndex.getValue()).value.equals(".")) {
			leximesIndex.increment();
			dotRest= new DotRest(leximes, leximesIndex);
		} else 
			SyntaxEngine.error(leximesIndex);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
}
}