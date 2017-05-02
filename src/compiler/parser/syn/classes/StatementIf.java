package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;

public class StatementIf extends Statement {
	public Expression expression;
	public Statement statement;
	public IfRest ifRest;

	public StatementIf(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (!leximes.get(leximesIndex.getAndIncrement()).value.equals("if"))
			SyntaxEngine.error(leximesIndex);

		if (!leximes.get(leximesIndex.getAndIncrement()).value.equals("("))
			SyntaxEngine.error(leximesIndex);

		expression = Expression.getExpression(leximes, leximesIndex);

		if (!leximes.get(leximesIndex.getAndIncrement()).value.equals(")"))
			SyntaxEngine.error(leximesIndex);

		statement = Statement.getStatementType(leximes, leximesIndex);
		
		if(leximes.get(leximesIndex.getValue()).value.equals("else"))
			ifRest = new IfRest(leximes, leximesIndex);
		else
			ifRest = null;
	}
}
