package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.TokenName;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public abstract class Expression implements SyntaxRule {

	public static Expression getExpression(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		Expression expression = null;
		String tokenType = leximes.get(leximesIndex.getValue()).type,
				tokenValue = leximes.get(leximesIndex.getValue()).value;

		if (tokenType.equals(TokenName.THIS) || tokenType.equals(TokenName.TRUE) || tokenType.equals(TokenName.FALSE)
				|| tokenType.equals(TokenName.INTEGRAL_LITERAL) || tokenType.equals(TokenName.ID))
			expression = new ExpressionSingleValue(leximes, leximesIndex);
		else if (tokenValue.equals("!") || tokenValue.equals("new") || tokenValue.equals("("))
			expression = new ExpressionComplexValue(leximes, leximesIndex);
		else
			SyntaxEngine.error(leximesIndex);

		return expression;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
