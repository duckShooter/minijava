package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;

public class ExpressionRestNewInt extends ExpressionRestNew {
	public Expression expression;
	public ExpressionRestNewInt(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("int"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("["))
			SyntaxEngine.error(leximesIndex);
		
		expression = Expression.getExpression(leximes, leximesIndex);
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("]"))
			SyntaxEngine.error(leximesIndex);
	}

}
