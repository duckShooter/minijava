package compiler.parser.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public abstract class ExpressionRest implements SyntaxRule {
	public Expression expression;
	public DotRest dotRest;
	public String op; // Terminal

	public static ExpressionRest getType(ArrayList<Lexime> leximes, MutableInt leximesIndex){

		ExpressionRest expressionRest =null;
		
		if (leximes.get(leximesIndex.getValue()).value.equals("&&"))
			expressionRest =new OpAnd(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("<"))
			expressionRest =new OpLessThan(leximes, leximesIndex);
		else if( leximes.get(leximesIndex.getValue()).value.equals("+"))
			expressionRest =new OpPlus(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("-"))
			expressionRest =new OpMinus(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("*"))
			expressionRest =new OpMultiply(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("["))
			expressionRest =new  OpOpeningBracket(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("."))
			expressionRest =new OpDot(leximes, leximesIndex);
		else
			SyntaxEngine.error(leximesIndex);
		return expressionRest;
		
	}
		
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}