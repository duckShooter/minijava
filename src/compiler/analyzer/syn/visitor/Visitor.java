package compiler.analyzer.syn.visitor;

import compiler.analyzer.syn.classes.*;

public interface Visitor {
	public void visit(Bracket bracket);
	public void visit(ClassDeclaration classDeclaration);
	public void visit(Goal goal);
	public void visit(Identifier identifier);
	public void visit(MainClass mainClass);
	public void visit(MethodDeclaration methodDeclaration);
	public void visit(Statement statement);
	public void visit(Type type);
	public void visit(VarDeclaration varDeclaration);
	public void visit(Expression expression);
}
