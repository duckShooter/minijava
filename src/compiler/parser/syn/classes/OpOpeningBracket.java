package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;

public class OpOpeningBracket extends ExpressionRest {
	public OpOpeningBracket(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		op = leximes.get(leximesIndex.getValue()).value;
		leximesIndex.increment();
		expression = Expression.getExpression(leximes, leximesIndex);
		if (leximes.get(leximesIndex.getValue()).value.equals("]")) {
			leximesIndex.increment();
		} else {
			SyntaxEngine.error(leximesIndex);
		}
	}
}
