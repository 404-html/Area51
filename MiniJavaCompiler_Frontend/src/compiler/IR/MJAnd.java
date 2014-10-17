package compiler.IR;

import compiler.PrettyPrinter;

public class MJAnd extends MJBinaryOp {

	public MJAnd() {}
	
	public MJAnd(MJExpression a, MJExpression b) {
		super(a, b);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" && ");
		this.rhs.prettyPrint(prepri);
	}
}
