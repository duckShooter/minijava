package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.TokenName;

public class ExpressionRestNewIdentifier extends ExpressionRestNew {
	public Identifier identifier;
	
	public ExpressionRestNewIdentifier(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(!leximes.get(leximesIndex.getValue()).type.equals(TokenName.ID))
			SyntaxEngine.error(leximesIndex);
		
		identifier = new Identifier(leximes, leximesIndex);
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("("))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals(")"))
			SyntaxEngine.error(leximesIndex);
	}
}
