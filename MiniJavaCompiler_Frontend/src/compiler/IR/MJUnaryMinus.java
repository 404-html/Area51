package compiler.IR;

import compiler.PrettyPrinter;

public class MJUnaryMinus extends MJUnaryOp {

	public MJUnaryMinus() {
		
	}
	
	public MJUnaryMinus(MJExpression l) {
		super(l);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("-");
		this.arg.prettyPrint(prepri);
	}
}
