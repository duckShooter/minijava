package compiler.parser.syn.visitor;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

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
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode recentNode; //Depth pointer
	
	@Override
	public void visit(Goal goal) {
		root = new DefaultMutableTreeNode(Goal.class.getName());
		recentNode = root;
		goal.mainClass.accept(this);
		for(ClassDeclaration d : goal.classDeclarations)
			d.accept(this);
	}

	@Override
	public void visit(MainClass mainClass) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(MainClass.class.getName());
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
		mainClass.statement.accept(this);
		tabber.tabOneLess();
		
		node.add(new DefaultMutableTreeNode("}"));
		node.add(new DefaultMutableTreeNode("}"));
		out.println(tabber.tab + "}"); //Main method block closing
		out.println("}"); //Main class block closing
		
		recentNode = (DefaultMutableTreeNode)node.getParent(); //Backtrack to daddy
	}

	@Override
	public void visit(ClassDeclaration classDeclaration) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(ClassDeclaration.class.getName());
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
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(VarDeclaration.class.getName());
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
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(MethodDeclaration.class.getName());
		recentNode.add(node);
		recentNode = node;
		
		node.add(new DefaultMutableTreeNode(methodDeclaration.visibility));
		out.print(methodDeclaration.visibility + " ");
		
		methodDeclaration.methodType.accept(this);
		out.print(" ");
		methodDeclaration.methodName.accept(this);
		
		node.add(new DefaultMutableTreeNode("("));
		out.print("(");
		if(methodDeclaration.methodArgs != null && !methodDeclaration.methodArgs.isEmpty()) {
			methodDeclaration.methodArgs.get(0).getKey().accept(this); //Type
			methodDeclaration.methodArgs.get(0).getValue().accept(this); //Identifier
			for(int i=1; i<methodDeclaration.methodArgs.size(); i++) { //Escape first argument
				node.add(new DefaultMutableTreeNode(",")); 
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
		node.add(new DefaultMutableTreeNode("}"));
		out.println("\n}"); //Method block closing
		
		recentNode = (DefaultMutableTreeNode)node.getParent();
	}

	@Override
	public void visit(Type type) { //I thought visitor DP was supposed to eliminate the 'instanceof' check!
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(Type.class.getName());
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
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(Identifier.class.getName());
		recentNode.add(node); 
		node.add(new DefaultMutableTreeNode(identifier.identifier));
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
	
	public JTree createVisualParseTree() {
		return new JTree(root);
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
