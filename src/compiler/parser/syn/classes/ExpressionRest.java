package compiler.parser.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public abstract class ExpressionRest implements SyntaxRule {
	public Expression expression;
	public DotRest dotRest;
	public String op; // Terminal

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	public static ExpressionRest getType(ArrayList<Lexime> leximes, MutableInt leximesIndex){

		ExpressionRest expressionRest =null;
		
		if (leximes.get(leximesIndex.getValue()).value.equals("&&")){
			expressionRest =new TypeAnd(leximes, leximesIndex);
		}
			
		else if(leximes.get(leximesIndex.getValue()).value.equals("<")){
			expressionRest =new TypeLessThan(leximes, leximesIndex);
		}
			
		else if( leximes.get(leximesIndex.getValue()).value.equals("+"))	{
			expressionRest =new TypePlus(leximes, leximesIndex);
		}
		else if(leximes.get(leximesIndex.getValue()).value.equals("-"))	{
			expressionRest =new TypeMinus(leximes, leximesIndex);
		}
		else if(leximes.get(leximesIndex.getValue()).value.equals("*"))	{
			expressionRest =new TypeMultiply(leximes, leximesIndex);
		}
		
		else if(leximes.get(leximesIndex.getValue()).value.equals("["))	{
			expressionRest =new  TypeOpeningBracket(leximes, leximesIndex);
		}
				

		else if(leximes.get(leximesIndex.getValue()).value.equals(".")){
			expressionRest =new TypeDot(leximes, leximesIndex);
		}
		else{
			SyntaxEngine.error(leximesIndex);
		}
		return expressionRest;
		
	}
		
							
			/*	
		
			op = leximes.get(leximesIndex.getValue()).value; //Save the type of operation (for printing later)
			// abdo's part aka expression class
			expression = new Expression(leximes, leximesIndex);

	
	*/

	
}