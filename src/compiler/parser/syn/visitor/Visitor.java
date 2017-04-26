package compiler.parser.syn.visitor;

import compiler.parser.syn.classes.*;

public interface Visitor {
	public void visit(Goal goal);
	public void visit(MainClass mainClass);
	public void visit(ClassDeclaration classDeclaration);
	public void visit(VarDeclaration varDeclaration);
	public void visit(MethodDeclaration methodDeclaration);
	public void visit(Type type);
	public void visit(Bracket bracket);
	public void visit(Identifier identifier);
	public void visit(Statement statement);
	public void visit(Expression expression);
}
