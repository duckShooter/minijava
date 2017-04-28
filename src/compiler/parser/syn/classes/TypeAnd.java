package compiler.parser.syn.classes;
import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
public class TypeAnd extends ExpressionRest {
	public TypeAnd(ArrayList<Lexime> leximes, MutableInt leximesIndex){
		op = leximes.get(leximesIndex.getValue()).value;
		leximesIndex.increment();
		expression = new Expression(leximes, leximesIndex);
		
	}
}
