package compiler.analyzer.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.SyntaxEngine;
import compiler.analyzer.syn.visitor.CodePart;
import compiler.analyzer.syn.visitor.Visitor;

public class VarDeclaration implements CodePart {
	public Type type;
	public Identifier identifier;
	
	public VarDeclaration(ArrayList<Lexime> leximes, MutableInt leximesIndex) {
		/*if(leximes.get(leximesIndex.getValue()).value.equals("int"))
			type = new TypeInt(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("boolean"))
			type = new TypeBoolean(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("char"))
			type = new TypeChar(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("String"))
			type = new TypeString(leximes, leximesIndex);
		else if(leximes.get(leximesIndex.getValue()).value.equals("float"))
			type = new TypeFloat(leximes, leximesIndex);
		else
			SyntaxEngine.error(leximesIndex);*/
		type = Type.getType(leximes, leximesIndex);
 
		identifier = new Identifier(leximes, leximesIndex);
		
		if(!leximes.get(leximesIndex.getAndIncrement()).value.equals(";"))
			SyntaxEngine.error(leximesIndex);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}