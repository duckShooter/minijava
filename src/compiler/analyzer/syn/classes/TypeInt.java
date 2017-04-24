package compiler.analyzer.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;

public class TypeInt extends Type {
	public Bracket bracket;
	
	public TypeInt(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		bracket = new Bracket(leximes, leximesIndex);
	}
}
