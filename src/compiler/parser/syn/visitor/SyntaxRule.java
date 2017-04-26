package compiler.parser.syn.visitor;

public interface SyntaxRule { //The Element Hierarchy (CFG Rules)
	public void accept(Visitor v);
}