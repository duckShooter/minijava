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
import static java.lang.System.out;

public class PrettyPrintVisitor implements Visitor { //Over 9000 method calls

	@Override
	public void visit(Goal goal) {
		goal.mainClass.accept(this);
		for(ClassDeclaration d : goal.classDeclarations)
			d.accept(this);
	}

	@Override
	public void visit(MainClass mainClass) {
		out.print("class ");
		mainClass.className.accept(this);
		out.println(" {"); //Main class block opening
		out.print("	public static void main(String[] "); //TABx1
		mainClass.arg.accept(this);
		out.println(") {"); //Main method block opening
		out.print("		"); //TABx2
		mainClass.statement.accept(this);
		out.println("	}"); //Main method block closing
		out.println("}"); //Main class block closing 
	}

	@Override
	public void visit(ClassDeclaration classDeclaration) {
		out.print("class ");
		classDeclaration.className.accept(this);
		if(classDeclaration.extendedClassName != null) {
			out.print(" extends ");
			classDeclaration.extendedClassName.accept(this);
		}
		out.println(" {");
		for(VarDeclaration v : classDeclaration.varDeclarations) {
			out.print("	");
			v.accept(this);
			out.print(";\n"); //Requires Review
		}
		
		for(MethodDeclaration m : classDeclaration.methodDeclarations) {
			out.print("	");
			m.accept(this);
			out.println();
		}
		
		out.println("}");
	}

	@Override
	public void visit(VarDeclaration varDeclaration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(MethodDeclaration methodDeclaration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Type type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Bracket bracket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Identifier identifier) {
		out.print(identifier.identifier);
		
	}

	@Override
	public void visit(Statement statement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Expression expression) {
		// TODO Auto-generated method stub
		
	}
}
