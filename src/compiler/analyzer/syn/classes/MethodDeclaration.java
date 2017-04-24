package compiler.analyzer.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.SyntaxEngine;
import compiler.analyzer.syn.visitor.CodePart;
import compiler.analyzer.syn.visitor.Visitor;
import javafx.util.Pair;

public class MethodDeclaration implements CodePart {
	private enum Visibility{
		PUBLIC, PRIVATE
	}
	public Type methodType;
	public Visibility visibility;
	public Identifier methodName;
	public ArrayList<Pair<Type, Identifier>> methodArgs;
	public ArrayList<VarDeclaration> variables;
	public ArrayList<Statement> statements;
	public Expression returnExpression;

	public MethodDeclaration(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(leximes.get(leximesIndex.getValue()).value.equals("public"))
			visibility = Visibility.PUBLIC;
		else if(leximes.get(leximesIndex.getValue()).value.equals("private"))
			visibility = Visibility.PRIVATE;
		else
			SyntaxEngine.error(leximesIndex);
		leximesIndex.increment();
		
		methodType = Type.getType(leximes, leximesIndex);
		
		methodName = new Identifier(leximes, leximesIndex);
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("("))
			SyntaxEngine.error(leximesIndex);
		
		methodArgs = new ArrayList<>();
		if(!leximes.get(leximesIndex.getValue()).value.equals(")")){
			Type type = Type.getType(leximes, leximesIndex);
			methodArgs.add(new Pair<Type, Identifier>(type, new Identifier(leximes, leximesIndex)));
			
			while(!leximes.get(leximesIndex.getValue()).value.equals(")")){
				if(!leximes.get(leximesIndex.getAndIncrement()).value.equals(","))
					SyntaxEngine.error(leximesIndex);
				
				type = Type.getType(leximes, leximesIndex);
				methodArgs.add(new Pair<Type, Identifier>(type, new Identifier(leximes, leximesIndex)));
			}
		}
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals(")"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("{"))
			SyntaxEngine.error(leximesIndex);
		
		variables = new ArrayList<>();
		String nextValue = leximes.get(leximesIndex.getValue()).value;
		while(nextValue.equals("int") || nextValue.equals("float") || nextValue.equals("chat") || nextValue.equals("boolean") || 
				nextValue.equals("double")){
			variables.add(new VarDeclaration(leximes, leximesIndex));
			nextValue = leximes.get(leximesIndex.getValue()).value;
		}
		
		while(!leximes.get(leximesIndex.getValue()).value.equals("return")){
			//get the statement type
		}
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("return"))
			SyntaxEngine.error(leximesIndex);
		
		//expression, should be an interface
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals(";"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("}"))
			SyntaxEngine.error(leximesIndex);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}