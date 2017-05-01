package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.TokenName;

public class ExpressionComplexValue extends Expression {
	private enum Value {
		NEW, NOT, BRACKETS
	}

	public ExpressionBar expressionBar;
	public ExpressionRestNew expressionRestNew;
	public Expression expression;
	public Value value;

	public ExpressionComplexValue(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (leximes.get(leximesIndex.getValue()).value.equals("new"))
			value = Value.NEW;
		else if (leximes.get(leximesIndex.getValue()).value.equals("!"))
			value = Value.NOT;
		else if (leximes.get(leximesIndex.getValue()).value.equals("("))
			value = Value.BRACKETS;
		else
			SyntaxEngine.error(leximesIndex);
		leximesIndex.increment();

		switch (value) {
		case NEW:
			if (leximes.get(leximesIndex.getValue()).value.equals("int"))
				expressionRestNew = new ExpressionRestNewInt(leximes, leximesIndex);
			else if (leximes.get(leximesIndex.getValue()).type.equals(TokenName.ID))
				expressionRestNew = new ExpressionRestNewIdentifier(leximes, leximesIndex);
			else
				SyntaxEngine.error(leximesIndex);
			break;

		case NOT:
			expression = Expression.getExpression(leximes, leximesIndex);
			break;

		case BRACKETS:
			expression = Expression.getExpression(leximes, leximesIndex);
			if (!leximes.get(leximesIndex.getAndIncrement()).value.equals(")"))
				expressionRestNew = new ExpressionRestNewInt(leximes, leximesIndex);
		}
		
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
}
