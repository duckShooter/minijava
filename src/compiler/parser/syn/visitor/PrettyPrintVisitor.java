package compiler.parser.syn.visitor;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import compiler.parser.syn.classes.ClassDeclaration;
import compiler.parser.syn.classes.DotRest;
import compiler.parser.syn.classes.Expression;
import compiler.parser.syn.classes.ExpressionBar;
import compiler.parser.syn.classes.ExpressionComplexValue;
import compiler.parser.syn.classes.ExpressionRest;
import compiler.parser.syn.classes.ExpressionRestNew;
import compiler.parser.syn.classes.ExpressionRestNewIdentifier;
import compiler.parser.syn.classes.ExpressionRestNewInt;
import compiler.parser.syn.classes.ExpressionSingleValue;
import compiler.parser.syn.classes.Goal;
import compiler.parser.syn.classes.Identifier;
import compiler.parser.syn.classes.IfRest;
import compiler.parser.syn.classes.MainClass;
import compiler.parser.syn.classes.MethodDeclaration;
import compiler.parser.syn.classes.Statement;
import compiler.parser.syn.classes.StatementBrackets;
import compiler.parser.syn.classes.StatementIdentifier;
import compiler.parser.syn.classes.StatementIf;
import compiler.parser.syn.classes.StatementPrint;
import compiler.parser.syn.classes.StatementRest;
import compiler.parser.syn.classes.StatementRestBracket;
import compiler.parser.syn.classes.StatementRestEqual;
import compiler.parser.syn.classes.StatementWhile;
import compiler.parser.syn.classes.Type;
import compiler.parser.syn.classes.TypeBoolean;
import compiler.parser.syn.classes.TypeChar;
import compiler.parser.syn.classes.TypeFloat;
import compiler.parser.syn.classes.TypeInt;
import compiler.parser.syn.classes.VarDeclaration;
import static java.lang.System.out;

/**
 * 
 * @author Giovanni
 * @version 2.1
 *
 */

public class PrettyPrintVisitor implements Visitor { //Over 9000 method calls
	private TabTracker tabber = new TabTracker();
	private DefaultMutableTreeNode root; //Seed
	private DefaultMutableTreeNode recentNode; //Depth pointer
	
	@Override
	public void visit(Goal goal) {
		root = new DefaultMutableTreeNode(Goal.class.getSimpleName());
		recentNode = root;
		goal.mainClass.accept(this);
		for(ClassDeclaration d : goal.classDeclarations)
			d.accept(this);
	}

	@Override
	public void visit(MainClass mainClass) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(MainClass.class.getSimpleName());
		recentNode.add(node); //Add to previous node
		recentNode = node; //Update last node
		
		node.add(new DefaultMutableTreeNode("class")); //You're going to see a LOT of these!
		out.print("class ");
		mainClass.className.accept(this);
		node.add(new DefaultMutableTreeNode("{"));
		out.println(" {"); //Main class block opening
		
		node.add(new DefaultMutableTreeNode("public"));
		node.add(new DefaultMutableTreeNode("static"));
		node.add(new DefaultMutableTreeNode("void"));
		node.add(new DefaultMutableTreeNode("main"));
		node.add(new DefaultMutableTreeNode("("));
		node.add(new DefaultMutableTreeNode("String"));
		node.add(new DefaultMutableTreeNode("[]"));
		out.print(tabber.tab + "public static void main(String[] ");
		
		mainClass.arg.accept(this);
		
		node.add(new DefaultMutableTreeNode(")"));
		node.add(new DefaultMutableTreeNode("{"));
		out.println(") {"); //Main method block opening
		
		tabber.tabOneMore();
		out.print(tabber.tab);
		mainClass.statement.accept(this);
		tabber.tabOneLess();
		
		node.add(new DefaultMutableTreeNode("}"));
		node.add(new DefaultMutableTreeNode("}"));
		out.println("\n" + tabber.tab + "}"); //Main method block closing
		out.println("}"); //Main class block closing
		
		recentNode = (DefaultMutableTreeNode)node.getParent(); //Backtrack to daddy
	}

	@Override
	public void visit(ClassDeclaration classDeclaration) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(ClassDeclaration.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		
		node.add(new DefaultMutableTreeNode("class"));
		out.print("class ");
		classDeclaration.className.accept(this);
		if(classDeclaration.extendedClassName != null) {
			node.add(new DefaultMutableTreeNode("extends"));
			out.print(" extends ");
			classDeclaration.extendedClassName.accept(this);
		}
		
		node.add(new DefaultMutableTreeNode("{"));
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
		
		node.add(new DefaultMutableTreeNode("}"));
		out.println("}"); //Class block closing
		recentNode = (DefaultMutableTreeNode)node.getParent();
	}

	@Override
	public void visit(VarDeclaration varDeclaration) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(VarDeclaration.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		
		varDeclaration.type.accept(this);
		out.print(" ");
		varDeclaration.identifier.accept(this);
		node.add(new DefaultMutableTreeNode(";"));
		out.print(";");
		
		recentNode = (DefaultMutableTreeNode)node.getParent();
	}

	@Override
	public void visit(MethodDeclaration methodDeclaration) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(MethodDeclaration.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		
		node.add(new DefaultMutableTreeNode(methodDeclaration.visibility.toString().toLowerCase()));
		out.print(methodDeclaration.visibility.toString().toLowerCase() + " ");
		
		methodDeclaration.methodType.accept(this);
		out.print(" ");
		methodDeclaration.methodName.accept(this);
		
		node.add(new DefaultMutableTreeNode("("));
		out.print("(");
		if(methodDeclaration.methodArgs != null && !methodDeclaration.methodArgs.isEmpty()) {
			methodDeclaration.methodArgs.get(0).getKey().accept(this); //Type
			out.print(" ");
			methodDeclaration.methodArgs.get(0).getValue().accept(this); //Identifier
			for(int i=1; i<methodDeclaration.methodArgs.size(); i++) { //Escape first argument
				node.add(new DefaultMutableTreeNode(",")); 
				out.print(", ");
				methodDeclaration.methodArgs.get(i).getKey().accept(this);
				out.print(" ");
				methodDeclaration.methodArgs.get(i).getValue().accept(this);
			}
		}
		node.add(new DefaultMutableTreeNode(")"));
		node.add(new DefaultMutableTreeNode("{")); 
		out.println(") {"); //Method block opening
		
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
		
		node.add(new DefaultMutableTreeNode("return"));
		out.print(tabber.tab + "return ");
		methodDeclaration.returnExpression.accept(this);
		node.add(new DefaultMutableTreeNode(";"));
		out.print(";");
		tabber.tabOneLess();
		node.add(new DefaultMutableTreeNode("}"));
		out.println("\n" + tabber.tab + "}"); //Method block closing
		
		recentNode = (DefaultMutableTreeNode)node.getParent();
	}

	@Override
	public void visit(Type type) { //I thought visitor DP was supposed to eliminate the 'instanceof' check!
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(Type.class.getSimpleName());
		recentNode.add(node);
		//No need to refer to this node as a recent node since we only have terminals here (no more children to visit)
		
		String myCoolType;
		if(type instanceof TypeBoolean)
			myCoolType = "boolean";
		else if(type instanceof TypeInt)
			myCoolType = "int";
		else if(type instanceof TypeChar)
			myCoolType = "char";
		else if(type instanceof TypeFloat)
			myCoolType = "float";
		else
			myCoolType = "String";
		
		node.add(new DefaultMutableTreeNode(myCoolType));
		out.print(myCoolType);
		
		if(type.bracket.isAvailable) //NO! i'm not visiting Bracket, I don't have time
			out.print("[]");
		//We didn't change the recent node reference, so we're not backtracking!
	}

	@Override
	public void visit(Identifier identifier) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(Identifier.class.getSimpleName());
		recentNode.add(node); 
		node.add(new DefaultMutableTreeNode(identifier.identifier));
		out.print(identifier.identifier);
		
	}

	@Override
	public void visit(Statement statement) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(Statement.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		
		if(statement instanceof StatementBrackets) {
			node.add(new DefaultMutableTreeNode("{"));
			out.print("{\n"); //Statement block opening
			tabber.tabOneMore();
			for(Statement  s : ((StatementBrackets) statement).statements) {
				out.print(tabber.tab);
				s.accept(this);
				out.println();
			}
			tabber.tabOneLess();
			node.add(new DefaultMutableTreeNode("}"));
			out.println("}"); //Statement block closing
			
		} else if(statement instanceof StatementIf) {
			node.add(new DefaultMutableTreeNode("if"));
			node.add(new DefaultMutableTreeNode("("));
			out.print("if (");
			((StatementIf) statement).expression.accept(this);
			node.add(new DefaultMutableTreeNode(")"));
			out.println(") ");
			((StatementIf) statement).statement.accept(this);
			if(((StatementIf) statement).ifRest != null)
				((StatementIf) statement).ifRest.accept(this);
		} else if(statement instanceof StatementWhile) {
			node.add(new DefaultMutableTreeNode("while"));
			node.add(new DefaultMutableTreeNode("("));
			out.print("while (");
			((StatementWhile) statement).expression.accept(this);
			node.add(new DefaultMutableTreeNode(")"));
			out.print(") ");
			((StatementWhile) statement).statement.accept(this);
			
		} else if(statement instanceof StatementPrint) {
			node.add(new DefaultMutableTreeNode("System.out.println"));
			node.add(new DefaultMutableTreeNode("("));
			out.print("System.out.println(");
			((StatementPrint) statement).expression.accept(this);
			node.add(new DefaultMutableTreeNode(")"));
			node.add(new DefaultMutableTreeNode(";"));
			out.print(");");
		} else {
			((StatementIdentifier) statement).identifier.accept(this);
			((StatementIdentifier) statement).statementRest.accept(this);
			node.add(new DefaultMutableTreeNode(";"));
			out.print(";");
		}
		
		recentNode = (DefaultMutableTreeNode)node.getParent();
	}

	@Override
	public void visit(Expression expression) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(Expression.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		
		if(expression instanceof ExpressionSingleValue) {
			if(((ExpressionSingleValue) expression).value != null) {
				node.add(new DefaultMutableTreeNode(((ExpressionSingleValue) expression).value.toString().toLowerCase()));
				out.print(((ExpressionSingleValue) expression).value.toString().toLowerCase());				
			}
			
			else if(((ExpressionSingleValue) expression).intValue != null) {
				node.add(new DefaultMutableTreeNode(((ExpressionSingleValue) expression).intValue));
				out.print(((ExpressionSingleValue) expression).intValue);

			} else if(((ExpressionSingleValue) expression).identifier != null)
				((ExpressionSingleValue) expression).identifier.accept(this);
			
			if(((ExpressionSingleValue) expression).expressionBar != null)
				((ExpressionSingleValue) expression).expressionBar.accept(this);
			
		} else if(expression instanceof ExpressionComplexValue) {
			switch(((ExpressionComplexValue) expression).value) {
			case NEW:
				node.add(new DefaultMutableTreeNode("new"));
				out.print("new ");
				((ExpressionComplexValue) expression).expressionRestNew.accept(this);
				break;
			case NOT:
				node.add(new DefaultMutableTreeNode("!"));
				out.print("!");
				((ExpressionComplexValue) expression).expression.accept(this);
				break;
			case BRACKETS:
				node.add(new DefaultMutableTreeNode("("));
				out.print("(");
				((ExpressionComplexValue) expression).expression.accept(this);
				node.add(new DefaultMutableTreeNode(")"));
				out.print(")");
			}
			
			if(((ExpressionComplexValue) expression).expressionBar != null)
				((ExpressionComplexValue) expression).expressionBar.accept(this);
		}
		recentNode = (DefaultMutableTreeNode)node.getParent();
	}
	

	@Override
	public void visit(ExpressionBar expressionBar) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(ExpressionBar.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		
		expressionBar.expressionRest.accept(this);
		if(expressionBar.expressionBar != null)
			expressionBar.expressionBar.accept(this);
		
		recentNode = (DefaultMutableTreeNode)node.getParent();
	}

	@Override
	public void visit(ExpressionRest expressionRest) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(ExpressionRest.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		
		if(expressionRest.op != null) {
			node.add(new DefaultMutableTreeNode(expressionRest.op));
			out.print(" " + expressionRest.op + " ");
			expressionRest.expression.accept(this);	
			
		} else if(expressionRest.dotRest != null) {
			node.add(new DefaultMutableTreeNode("."));
			out.print(".");
			expressionRest.dotRest.accept(this);
		} else {
			node.add(new DefaultMutableTreeNode("["));
			out.print("[");
			expressionRest.expression.accept(this);
			node.add(new DefaultMutableTreeNode("]"));
			out.print("]");
		}
		recentNode = (DefaultMutableTreeNode)node.getParent();
	}

	@Override
	public void visit(DotRest dotRest) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(DotRest.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		
		if(dotRest.lengthVariable) {
			node.add(new DefaultMutableTreeNode("length"));
			out.print("length");
		}
		else {
			dotRest.identifier.accept(this);
			if(dotRest.expressions != null && !dotRest.expressions.isEmpty()) {
				node.add(new DefaultMutableTreeNode("("));
				out.print("(");
				dotRest.expressions.get(0).accept(this);
				for(Expression e : dotRest.expressions) {
					node.add(new DefaultMutableTreeNode(","));
					out.print(", ");
					e.accept(this);
				}
				node.add(new DefaultMutableTreeNode(")"));
				out.print(")");
			}
		}
		recentNode = (DefaultMutableTreeNode)node.getParent();
	}
	
	@Override
	public void visit(IfRest ifRest) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(IfRest.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		node.add(new DefaultMutableTreeNode("else"));
		out.println("else ");
		ifRest.stmt.accept(this);
		recentNode = (DefaultMutableTreeNode)node.getParent();
	};
	
	@Override
	public void visit(StatementRest statementRest) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(StatementRest.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		
		if(statementRest instanceof StatementRestEqual) {
			node.add(new DefaultMutableTreeNode("="));
			out.print(" = ");
			((StatementRestEqual) statementRest).expression.accept(this);
			node.add(new DefaultMutableTreeNode(";"));
			out.print(";");
		} else {
			node.add(new DefaultMutableTreeNode("["));
			out.print("[");
			((StatementRestBracket) statementRest).firstExp.accept(this);
			node.add(new DefaultMutableTreeNode("]"));
			node.add(new DefaultMutableTreeNode("="));
			out.print("] = ");
			((StatementRestBracket) statementRest).secondExp.accept(this);
			node.add(new DefaultMutableTreeNode(";"));
			out.print(";");
		}
		
		recentNode = (DefaultMutableTreeNode)node.getParent();
	};

	@Override
	public void visit(ExpressionRestNew expressionRestNew) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(ExpressionRestNew.class.getSimpleName());
		recentNode.add(node);
		recentNode = node;
		
		if(expressionRestNew instanceof ExpressionRestNewInt) {
			node.add(new DefaultMutableTreeNode("int"));
			node.add(new DefaultMutableTreeNode("["));
			out.print("int [");
			((ExpressionRestNewInt) expressionRestNew).expression.accept(this);
			node.add(new DefaultMutableTreeNode("]"));
			out.print("]");
		} else {
			((ExpressionRestNewIdentifier) expressionRestNew).identifier.accept(this);
			node.add(new DefaultMutableTreeNode("("));
			node.add(new DefaultMutableTreeNode(")"));
			out.print("()");
		}
		recentNode = (DefaultMutableTreeNode)node.getParent();
	}
	
	public JTree createVisualParseTree() {
		return new JTree(root);
	}
}

//============================================================
//Don't bother! it's just a helper class it has no other purpose in life
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
