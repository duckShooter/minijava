package compiler.parser.syn.visitor;

import compiler.parser.syn.classes.Bracket;
import compiler.parser.syn.classes.ClassDeclaration;
import compiler.parser.syn.classes.DotRest;
import compiler.parser.syn.classes.Expression;
import compiler.parser.syn.classes.ExpressionBar;
import compiler.parser.syn.classes.ExpressionRest;
import compiler.parser.syn.classes.ExpressionRestNew;
import compiler.parser.syn.classes.Goal;
import compiler.parser.syn.classes.Identifier;
import compiler.parser.syn.classes.MainClass;
import compiler.parser.syn.classes.MethodDeclaration;
import compiler.parser.syn.classes.Statement;
import compiler.parser.syn.classes.Type;
import compiler.parser.syn.classes.TypeBoolean;
import compiler.parser.syn.classes.TypeChar;
import compiler.parser.syn.classes.TypeFloat;
import compiler.parser.syn.classes.TypeInt;
import compiler.parser.syn.classes.TypeString;
import compiler.parser.syn.classes.VarDeclaration;
import static java.lang.System.out;

public class PrettyPrintVisitor implements Visitor { //Over 9000 method calls
	private TabTracker tabber = new TabTracker();
	
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
		out.print(tabber.tab + "public static void main(String[] "); //TABx1
		mainClass.arg.accept(this);
		out.println(") {"); //Main method block opening
		tabber.tabOneMore();
		//out.print("		"); //TABx2
		mainClass.statement.accept(this);
		tabber.tabOneLess();
		out.println(tabber.tab + "}"); //Main method block closing
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
		out.println(" {"); //Class block opening
		for(VarDeclaration v : classDeclaration.varDeclarations) {
			out.print(tabber.tab);
			v.accept(this);
			out.println();
		}
		
		for(MethodDeclaration m : classDeclaration.methodDeclarations) {
			out.print(tabber.tab);
			m.accept(this);
			out.println();
		}
		
		out.println("}"); //Class block closing
	}

	@Override
	public void visit(VarDeclaration varDeclaration) {
		varDeclaration.type.accept(this);
		out.print(" ");
		varDeclaration.identifier.accept(this);
		out.print(";");
	}

	@Override
	public void visit(MethodDeclaration methodDeclaration) {
		out.print(methodDeclaration.visibility + " ");
		methodDeclaration.methodType.accept(this);
		out.print(" ");
		methodDeclaration.methodName.accept(this);
		out.print("(");
		if(methodDeclaration.methodArgs != null && !methodDeclaration.methodArgs.isEmpty()) {
			methodDeclaration.methodArgs.get(0).getKey().accept(this);
			methodDeclaration.methodArgs.get(0).getValue().accept(this);
			for(int i=1; i<methodDeclaration.methodArgs.size(); i++) {
				out.print(", ");
				methodDeclaration.methodArgs.get(i).getKey().accept(this);
				methodDeclaration.methodArgs.get(i).getValue().accept(this);
			}
		}
		out.print(") {"); //Method block opening
		
		tabber.tabOneMore();
		if(methodDeclaration.variables != null) { //Unnecessary check
			for(VarDeclaration v : methodDeclaration.variables) {
				out.print(tabber.tab);
				v.accept(this);
				out.println(); //We will always end up with extra line. Not a big deal! 
			}
		}
		
		if(methodDeclaration.statements != null) {
			for(Statement s : methodDeclaration.statements) {
				out.print(tabber.tab);
				s.accept(this);
				out.println();
			}
		}
		
		out.print(tabber.tab + "return");
		methodDeclaration.returnExpression.accept(this);
		tabber.tabOneLess();
		out.println("\n}"); //Method block closing
	}

	@Override
	public void visit(Type type) { //I thought visitor DP was supposed to eliminate the 'instanceof' check!
		if(type instanceof TypeBoolean)
			out.print("boolean");
		else if(type instanceof TypeInt)
			out.print("int");
		else if(type instanceof TypeChar)
			out.print("char");
		else if(type instanceof TypeFloat)
			out.print("float");
		else
			out.print("String");
		
		if(type.bracket.isAvailable) //NO! i'm not visiting Bracket, I don't have time
			out.print("[]");
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
	

	@Override
	public void visit(ExpressionBar expressionBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ExpressionRest expressionRest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DotRest dotRest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ExpressionRestNew expressionRestNew) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		String tab="";
		for(int i=0; i<3; i++)
			tab += "\t";
		out.println("			sdf");
		out.println(tab.substring(0, tab.length()-1) + "sdf");
	}
}

class TabTracker { //Yes, It's true what your eyes see! this is a class for tabs .. deal with it!
	String tab = "\t";
	
	//These methods took me years of continuous development .. show some respect!
	public void tabOneLess() {
		tab = tab.substring(0, tab.length()-1);
	}
	
	public void tabOneMore() {
		tab += "\t";
	}
	
}
