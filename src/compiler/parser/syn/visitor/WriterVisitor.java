package compiler.parser.syn.visitor;

import compiler.parser.syn.classes.Bracket;
import compiler.parser.syn.classes.ClassDeclaration;
import compiler.parser.syn.classes.Expression;
import compiler.parser.syn.classes.Goal;
import compiler.parser.syn.classes.Identifier;
import compiler.parser.syn.classes.MainClass;
import compiler.parser.syn.classes.MethodDeclaration;
import compiler.parser.syn.classes.Statement;
import compiler.parser.syn.classes.Type;
import compiler.parser.syn.classes.VarDeclaration;

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
