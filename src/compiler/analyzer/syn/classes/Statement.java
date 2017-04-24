package compiler.analyzer.syn.classes;

import compiler.analyzer.syn.visitor.CodePart;
import compiler.analyzer.syn.visitor.Visitor;

public abstract class Statement implements CodePart {
	public void accept(Visitor v){
		v.visit(this);
	}
}
