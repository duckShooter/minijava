package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;

public class TypeBoolean extends Type {
	
	public TypeBoolean(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		leximesIndex.increment();
		bracket = new Bracket(leximes, leximesIndex);
	}

}
