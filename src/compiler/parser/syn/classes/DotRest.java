package compiler.parser.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.TokenName;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public class DotRest implements SyntaxRule {
	public boolean lengthVariable = false;
	public ArrayList<Expression> expressions;
	public Identifier identifier;

	public DotRest(ArrayList<Lexime> leximes, MutableInt leximesIndex) {

		if (leximes.get(leximesIndex.getValue()).value.equals("length")) {
			lengthVariable = true;
			return;
		} else {
			if (!leximes.get(leximesIndex.getValue()).type.equals(TokenName.ID))
				SyntaxEngine.error(leximesIndex);
			
			identifier = new Identifier(leximes, leximesIndex);

			if (!leximes.get(leximesIndex.getAndIncrement()).value.equals("("))
				SyntaxEngine.error(leximesIndex);

			expressions = new ArrayList<>();
			if (!leximes.get(leximesIndex.getValue()).value.equals(")")) {
				expressions.add(Expression.getExpression(leximes, leximesIndex));

				while (leximes.get(leximesIndex.getValue()).value.equals(","))
					expressions.add(Expression.getExpression(leximes, leximesIndex));

				if (!leximes.get(leximesIndex.getAndIncrement()).value.equals(")"))
					SyntaxEngine.error(leximesIndex);
			}

		}
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
