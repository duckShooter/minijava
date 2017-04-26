package compiler.parser.syn.classes;

import compiler.parser.syn.visitor.CodePart;
import compiler.parser.syn.visitor.Visitor;

public abstract class Statement implements CodePart {
	public void accept(Visitor v){
		v.visit(this);
	}
}
