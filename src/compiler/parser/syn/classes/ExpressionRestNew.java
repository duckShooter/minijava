package compiler.parser.syn.classes;


import java.util.ArrayList;
import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;
public class ExpressionRestNew implements SyntaxRule {
	Expression expression;
	Identifier identifier;

	ExpressionRestNew(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (leximes.get(leximesIndex.getValue()).value.equals("int")) {
			leximesIndex.increment();
			if (leximes.get(leximesIndex.getValue()).value.equals("[")) {
				leximesIndex.increment();
				expression = new Expression(leximes, leximesIndex);
				if (leximes.get(leximesIndex.getValue()).value.equals("]")) {
					leximesIndex.increment();
				} else {
					SyntaxEngine.error(leximesIndex);
				}
			} else {
				SyntaxEngine.error(leximesIndex);
			}
		} else

		{
			identifier= new Identifier(leximes, leximesIndex);
			if (leximes.get(leximesIndex.getValue()).value.equals("(")&&leximes.get(leximesIndex.getValue()+1).value.equals(")")){
				leximesIndex.increment();
				leximesIndex.increment();
				
			}
			else{
				SyntaxEngine.error(leximesIndex);
			}
		}
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
