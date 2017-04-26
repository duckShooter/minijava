package compiler.parser.syn.classes;

import compiler.parser.syn.visitor.CodePart;
import compiler.parser.syn.visitor.Visitor;

public abstract class Expression implements CodePart {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
