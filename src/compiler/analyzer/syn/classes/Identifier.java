package compiler.analyzer.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.visitor.CodePart;
import compiler.analyzer.syn.visitor.Visitor;

public class Identifier implements CodePart {
	public String identifier;
	public Identifier(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		identifier = leximes.get(leximesIndex.getAndIncrement()).value;
	}
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
