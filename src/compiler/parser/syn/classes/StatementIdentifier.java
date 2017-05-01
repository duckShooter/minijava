package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.parser.syn.MutableInt;

public class StatementIdentifier extends Statement {
	public Identifier identifier;
	public StatementRest statementRest;
	public StatementIdentifier(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		identifier = new Identifier(leximes, leximesIndex);
		statementRest = StatementRest.getStatementType(leximes, leximesIndex);
	}

}
