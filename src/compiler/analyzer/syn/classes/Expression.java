package compiler.analyzer.syn.classes;

import compiler.analyzer.syn.visitor.CodePart;
import compiler.analyzer.syn.visitor.Visitor;

public abstract class Expression implements CodePart {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
