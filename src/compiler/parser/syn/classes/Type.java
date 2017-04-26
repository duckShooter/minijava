package compiler.parser.syn.classes;

import java.util.ArrayList;

import compiler.analyzer.lex.Lexime;
import compiler.analyzer.lex.Token;
import compiler.parser.syn.MutableInt;
import compiler.parser.syn.SyntaxEngine;
import compiler.parser.syn.visitor.CodePart;
import compiler.parser.syn.visitor.Visitor;

public abstract class Type implements CodePart {
	public void accept(Visitor v){
		v.visit(this);
	}
	public static Type getType(ArrayList<Lexime> leximes, MutableInt leximesIndex){
		Type type = null;
		if(leximes.get(leximesIndex.getValue()).value.equals("int"))
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
			SyntaxEngine.error(leximesIndex);
		return type;
	}
}
