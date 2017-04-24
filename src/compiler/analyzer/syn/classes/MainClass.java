package compiler.analyzer.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.SyntaxEngine;
import compiler.analyzer.syn.visitor.CodePart;
import compiler.analyzer.syn.visitor.Visitor;

public class MainClass implements CodePart {
	public Identifier className;
	public Identifier arg;
	public Statement statement;
	
	public MainClass(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("class"))
			SyntaxEngine.error(leximesIndex);
		
		className = new Identifier(leximes, leximesIndex);
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("{"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("public"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("static"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("void"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("main"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("("))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("String"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("["))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("]"))
			SyntaxEngine.error(leximesIndex);
		
		arg = new Identifier(leximes, leximesIndex);
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals(")"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("{"))
			SyntaxEngine.error(leximesIndex);
		
		statement = new Statement() // this is just interface, need to implement the children
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("}"))
			SyntaxEngine.error(leximesIndex);
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals("}"))
			SyntaxEngine.error(leximesIndex);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
