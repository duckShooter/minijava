package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;

public class TypeChar extends Type {
	
	public TypeChar(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		leximesIndex.increment();
		bracket = new Bracket(leximes, leximesIndex);
	}
}
