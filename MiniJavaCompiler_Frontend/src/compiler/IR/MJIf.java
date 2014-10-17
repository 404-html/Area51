package compiler.IR;

import compiler.PrettyPrinter;

public class MJIf extends MJStatement {
	protected MJExpression expr;
	protected MJBlock ifBlock;

	public MJIf(MJExpression expr, MJBlock ifBlock) {
	this.expr = expr;
	this.ifBlock = ifBlock;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("if (");
		expr.prettyPrint(prepri);
		prepri.print(")");
		ifBlock.prettyPrint(prepri);
	}

}
