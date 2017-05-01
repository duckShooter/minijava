package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.visitor.SyntaxRule;
import compiler.parser.syn.visitor.Visitor;

public class Identifier implements SyntaxRule {
	public String identifier;
	public Identifier(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		identifier = leximes.get(leximesIndex.getAndIncrement()).value;
	}
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
