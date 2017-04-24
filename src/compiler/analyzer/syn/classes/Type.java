package compiler.analyzer.syn.classes;

import java.util.ArrayList;
import compiler.analyzer.lex.Token;
import compiler.analyzer.syn.MutableInt;
import compiler.analyzer.syn.visitor.CodePart;
import compiler.analyzer.syn.visitor.Visitor;

public abstract class Type implements CodePart {
	public void accept(Visitor v){
		v.visit(this);
	}
}
