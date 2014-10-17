package compiler.IR;

import compiler.PrettyPrinter;
import compiler.Frontend.MiniJavaParser.Level5Context;

public class MJNegate extends MJUnaryOp {

	public MJNegate(MJExpression arg) {
		super(arg);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("!");
		this.arg.prettyPrint(prepri);
		
	}
}
