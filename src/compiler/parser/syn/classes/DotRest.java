package compiler.parser.syn.classes;


import java.util.ArrayList;
import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public class DotRest  implements SyntaxRule{
	Expression expression;
	Identifier identifier;

	DotRest(ArrayList<Lexime> leximes, MutableInt leximesIndex){
		if (leximes.get(leximesIndex.getValue()).value.equals("length")){
			leximesIndex.increment();
			
		}
		else{
			identifier= new Identifier(leximes, leximesIndex);
			if (leximes.get(leximesIndex.getValue()).value.equals("(")){
				leximesIndex.increment();
				if (!leximes.get(leximesIndex.getValue()).value.equals(")")){
					expression=new Expression(leximes, leximesIndex);
					while(leximes.get(leximesIndex.getValue()).value.equals(",")){
						expression=new Expression(leximes, leximesIndex);
					}
					if(leximes.get(leximesIndex.getValue()).value.equals(")")){
						leximesIndex.increment();
					}
					else{
						SyntaxEngine.error(leximesIndex);
					}
				}
				else if(leximes.get(leximesIndex.getValue()).value.equals(")")){
					leximesIndex.increment();
					
				}
				else{
					SyntaxEngine.error(leximesIndex);
				}
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
