package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public abstract class Expression implements SyntaxRule {

	//public ExpressionRestNew expressionrestnew; 
	public Integer x;
	public Expression exp;

	// public ExpressionBar expressionbar;
	public static Boolean IsInteger(String s) {
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) < '0' || s.charAt(i) > '9')
				return false;
		return true;
	}
	
	public Expression(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (leximes.get(leximesIndex.getValue()).value.equals("true"))
			leximesIndex.increment();
		else if (leximes.get(leximesIndex.getValue()).value.equals("false"))
			leximesIndex.increment();
		else if (leximes.get(leximesIndex.getValue()).value.equals("this"))
			leximesIndex.increment();
		else if (leximes.get(leximesIndex.getValue()).value.equals("new")) {
			leximesIndex.increment();
			// expressionrestnew = new ExpressionRestNew(leximes,leximesIndex);
			leximesIndex.increment();

		} else if (IsInteger(leximes.get(leximesIndex.getValue()).value)) {
			x = Integer.parseInt(leximes.get(leximesIndex.getValue()).value);
			leximesIndex.increment();
		} else if (leximes.get(leximesIndex.getValue()).value.equals("!")) {
			leximesIndex.increment();
			// exp = new Expression(leximes,leximesIndex);
			// TODO : handle this shit

		} else if (leximes.get(leximesIndex.getValue()).value.equals("(")) {
			leximesIndex.increment();
			// exp = new Expression(leximes,leximesIndex);
			// TODO : handle this shit
			leximesIndex.increment();
			if (!leximes.get(leximesIndex.getValue()).value.equals(")")) {
				SyntaxEngine.error(leximesIndex);
			}
		}
		// expressionbar = new ExpressionBar(leximes,leximesIndex);
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
