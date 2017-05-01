package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.TokenName;

public class StatementPrint extends Statement {
	public Expression expression;

	public StatementPrint(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (!leximes.get(leximesIndex.getAndIncrement()).type.equals(TokenName.SYSTEM_OUT_PRINTLN))
			SyntaxEngine.error(leximesIndex);

		if (!leximes.get(leximesIndex.getAndIncrement()).value.equals("("))
			SyntaxEngine.error(leximesIndex);

		expression = Expression.getExpression(leximes, leximesIndex);
		
		if (!leximes.get(leximesIndex.getAndIncrement()).value.equals(")"))
			SyntaxEngine.error(leximesIndex);

		if (!leximes.get(leximesIndex.getAndIncrement()).value.equals(";"))
			SyntaxEngine.error(leximesIndex);

	}
}
