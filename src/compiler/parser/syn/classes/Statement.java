package compiler.parser.syn.classes;


import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.TokenName;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public abstract class Statement implements SyntaxRule {

	public static Statement getStatementType(ArrayList<Lexime> leximes, MutableInt leximesIndex){
		if(leximes.get(leximesIndex.getValue()).value.equals("{"))
			return new StatementBrackets(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("if"))
			return new StatementIf(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("while"))
			return new StatementWhile(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("System.out.println"))
			return new StatementPrint(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).type.equals(TokenName.ID))
			return new StatementIdentifier(leximes, leximesIndex);
		else
			SyntaxEngine.error(leximesIndex);
		return null;
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}
}
