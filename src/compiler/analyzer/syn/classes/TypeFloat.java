package compiler.analyzer.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;

public class TypeFloat extends Type {
	public Bracket bracket;
	
	public TypeFloat(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		leximesIndex.increment();
		bracket = new Bracket(leximes, leximesIndex);
	}

}