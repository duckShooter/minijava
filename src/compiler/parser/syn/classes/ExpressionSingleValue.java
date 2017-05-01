package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.TokenName;

public class ExpressionSingleValue extends Expression {
	private enum Value{
		THIS, TRUE, FALSE, INTERGRAL_VALUE, IDENTIFIER
	}
	public ExpressionBar expressionBar;
	public Identifier identifier;
	public String intValue;
	public Value value;
	
    public ExpressionSingleValue(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(leximes.get(leximesIndex.getValue()).type.equals(TokenName.THIS))
			value = Value.THIS;
		else if(leximes.get(leximesIndex.getValue()).type.equals(TokenName.TRUE))
			value = Value.TRUE;
		else if(leximes.get(leximesIndex.getValue()).type.equals(TokenName.FALSE))
			value = Value.FALSE;
		else if(leximes.get(leximesIndex.getValue()).type.equals(TokenName.INTEGRAL_LITERAL))
			intValue = leximes.get(leximesIndex.getValue()).value;
		else if(leximes.get(leximesIndex.getValue()).type.equals(TokenName.ID))
			identifier = new Identifier(leximes, leximesIndex);
		else
			SyntaxEngine.error(leximesIndex); //useless as it's already checked, but keep it anyway
		
		//only increment in case of not identifier, as the identifier constructor will handle it
		if(value != Value.IDENTIFIER)
			leximesIndex.increment();
		
		//checking if expression bar is lambda or not, null in case it's lambda
		switch(leximes.get(leximesIndex.getValue()).value){
		case "&&":
		case "<" :
		case "+" :
		case "-" :
		case "*" :
		case "[" :
		case "." :
			expressionBar = new ExpressionBar(leximes, leximesIndex);
			break;
		default :
			expressionBar = null;
		}
	}
}
