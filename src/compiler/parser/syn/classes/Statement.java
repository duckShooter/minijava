package compiler.parser.syn.classes;


import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public abstract class Statement implements SyntaxRule {
	public ArrayList<Statement> arr;
	public Statement stmt;
	public IfRest ifrest;
	public Expression expression;
	public Identifier identifier;
	public StatementRest stmtRest;

	public Statement(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if (leximes.get(leximesIndex.getValue()).value.equals("{")) {
			leximesIndex.increment();
			while (!leximes.get(leximesIndex.getValue()).value.equals("}"))
				leximesIndex.increment();
			
			if (!leximes.get(leximesIndex.getValue()).value.equals("}"))
				SyntaxEngine.error(leximesIndex);
			
		} else if (leximes.get(leximesIndex.getValue()).value.equals("if")) {
			leximesIndex.increment();
			if (leximes.get(leximesIndex.getValue()).value.equals("(")) {
				leximesIndex.increment();
				leximesIndex.increment();
				ifrest = new IfRest(leximes, leximesIndex);

			} else
				SyntaxEngine.error(leximesIndex);
			
		} else if (leximes.get(leximesIndex.getValue()).value.equals("while")) {
			leximesIndex.increment();
			if (leximes.get(leximesIndex.getValue()).value.equals("(")) {
				leximesIndex.increment();
				leximesIndex.increment();
				if (leximes.get(leximesIndex.getValue()).value.equals(")"))
					leximesIndex.increment();
				else 
					SyntaxEngine.error(leximesIndex);
			} else
				SyntaxEngine.error(leximesIndex);
			
		} else if (leximes.get(leximesIndex.getValue()).value
				.equals("System.out.println")) {
			leximesIndex.increment();
			if (leximes.get(leximesIndex.getValue()).value.equals("(")) {
				leximesIndex.increment();
				leximesIndex.increment();
				if (leximes.get(leximesIndex.getValue()).value.equals(")"))
					leximesIndex.increment();
				else
					SyntaxEngine.error(leximesIndex);
				if (!leximes.get(leximesIndex.getValue()).value.equals(";")) 
					SyntaxEngine.error(leximesIndex);
			}
		} else {
			identifier = new Identifier(leximes, leximesIndex);
			leximesIndex.increment();
			stmtRest = new StatementRest(leximes, leximesIndex);
		}
	}
	
	public void accept(Visitor v){
		v.visit(this);
	}
}
