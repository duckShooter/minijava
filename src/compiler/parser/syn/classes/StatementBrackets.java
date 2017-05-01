package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;

public class StatementBrackets extends Statement {
	public ArrayList<Statement> statements;
	
	public StatementBrackets(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (leximes.get(leximesIndex.getAndIncrement()).value.equals("{")) {
			leximesIndex.increment();
			while (!leximes.get(leximesIndex.getValue()).value.equals("}")){
				
			}
			
			leximesIndex.increment();
			if (!leximes.get(leximesIndex.getValue()).value.equals("}"))
				SyntaxEngine.error(leximesIndex);
		} 
	}
}
