package compiler.parser.syn.classes;


import java.util.ArrayList;
import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;
public class ExpressionRest implements SyntaxRule {
	Expression expression;
	DotRest dotRest;

	ExpressionRest(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (leximes.get(leximesIndex.getValue()).value.equals("&&")
				|| leximes.get(leximesIndex.getValue()).value.equals("<")
				|| leximes.get(leximesIndex.getValue()).value.equals("+")
				|| leximes.get(leximesIndex.getValue()).value.equals("-")
				|| leximes.get(leximesIndex.getValue()).value.equals("*")) {
			leximesIndex.increment();
			// abdo's part aka expression class
			expression = new Expression(leximes, leximesIndex);

		} else if (leximes.get(leximesIndex.getValue()).value.equals("[")) {
			leximesIndex.increment();
			expression = new Expression(leximes, leximesIndex);
			if (leximes.get(leximesIndex.getValue()).value.equals("]")) {
				leximesIndex.increment();
			} else {
				SyntaxEngine.error(leximesIndex);
			}

		} else if (leximes.get(leximesIndex.getValue()).value.equals(".")) {
			leximesIndex.increment();
			dotRest= new DotRest(leximes, leximesIndex);

		} else {
			SyntaxEngine.error(leximesIndex);
		}
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}