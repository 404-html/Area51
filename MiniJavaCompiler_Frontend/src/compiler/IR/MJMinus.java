package compiler.IR;

import compiler.PrettyPrinter;

public class MJMinus extends MJBinaryOp {

	
	public MJMinus(MJExpression lhs, MJExpression rhs){
		super(lhs, rhs);
	}
	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" - ");
		this.rhs.prettyPrint(prepri);
	
	}
}
