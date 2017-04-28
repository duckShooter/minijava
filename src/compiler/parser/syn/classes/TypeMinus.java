package compiler.parser.syn.classes;
import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
public class TypeMinus extends ExpressionRest {
public TypeMinus(ArrayList<Lexime> leximes, MutableInt leximesIndex){
	op = leximes.get(leximesIndex.getValue()).value;
	leximesIndex.increment();
	expression = new Expression(leximes, leximesIndex);
	}
}
