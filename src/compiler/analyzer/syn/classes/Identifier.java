package compiler.analyzer.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;

public class Identifier {
	public String identifier;
	public Identifier(ArrayList<Token> tokens, MutableInt tokensIndex) {
		identifier = tokens.get(tokensIndex.getAndIncrement()).regex;
	}
}
