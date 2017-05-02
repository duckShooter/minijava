package compiler.parser.syn.visitor;

import compiler.parser.syn.classes.*;

public interface Visitor {
	public void visit(Goal goal);
	public void visit(MainClass mainClass);
	public void visit(ClassDeclaration classDeclaration);
	public void visit(VarDeclaration varDeclaration);
	public void visit(MethodDeclaration methodDeclaration);
	public void visit(Type type);
	public void visit(Identifier identifier);
	public void visit(Statement statement);
	public void visit(Expression expression);
	public void visit(ExpressionBar expressionBar);
	public void visit(ExpressionRest expressionRest);
	public void visit(DotRest dotRest);
	public void visit(IfRest ifRest);
	public void visit(StatementRest statementRest);
	public void visit(ExpressionRestNew expressionRestNew);


}
