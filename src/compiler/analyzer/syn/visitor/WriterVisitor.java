package compiler.analyzer.syn.visitor;

import compiler.analyzer.syn.classes.Bracket;
import compiler.analyzer.syn.classes.ClassDeclaration;
import compiler.analyzer.syn.classes.Expression;
import compiler.analyzer.syn.classes.Goal;
import compiler.analyzer.syn.classes.Identifier;
import compiler.analyzer.syn.classes.MainClass;
import compiler.analyzer.syn.classes.MethodDeclaration;
import compiler.analyzer.syn.classes.Statement;
import compiler.analyzer.syn.classes.Type;
import compiler.analyzer.syn.classes.VarDeclaration;

public class WriterVisitor implements Visitor {

	@Override
	public void visit(Bracket bracket) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ClassDeclaration classDeclaration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Goal goal) {
		goal.mainClass.accept(this);
		for(ClassDeclaration d : goal.classDeclarations)
			d.accept(this);
	}

	@Override
	public void visit(Identifier identifier) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(MainClass mainClass) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(MethodDeclaration methodDeclaration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Statement statement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Type type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VarDeclaration varDeclaration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Expression expression) {
		// TODO Auto-generated method stub
		
	}

}
