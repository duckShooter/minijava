package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;

public class Bracket {
	public boolean isAvailable;
	
	public Bracket(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(leximes.get(leximesIndex.getValue()).value.equals("[") && leximes.get(leximesIndex.getValue() + 1).value.equals("]")){
			leximesIndex.increment();
			leximesIndex.increment();
			isAvailable = true;
		} else
			isAvailable = false;
	}
}
