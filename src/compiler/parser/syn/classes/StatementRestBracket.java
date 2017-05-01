package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;

public class StatementRestBracket extends StatementRest {
	public Expression firstExp, secondExp;
	
	public StatementRestBracket(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("["))
			SyntaxEngine.error(leximesIndex);
		
		firstExp = Expression.getExpression(leximes, leximesIndex);
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("]"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("="))
			SyntaxEngine.error(leximesIndex);
		
		secondExp = Expression.getExpression(leximes, leximesIndex);
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals(";"))
			SyntaxEngine.error(leximesIndex);
	}
}
