package compiler.IR;

import compiler.PrettyPrinter;

public class MJEqual extends MJBinaryOp {

	public MJEqual() {}
	
	public MJEqual(MJExpression a, MJExpression b) {
		super(a, b);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" == ");
		this.rhs.prettyPrint(prepri);
	}
}
