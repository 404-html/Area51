package compiler.IR;

import compiler.PrettyPrinter;
import compiler.Frontend.MiniJavaParser.Level5Context;

public class MJNegate extends MJUnaryOp {

	public MJNegate(MJExpression argument) {
		super(argument);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("!");
		this.arg.prettyPrint(prepri);
	}
}
