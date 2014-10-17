package compiler.IR;

import java.util.ArrayList;

import compiler.PrettyPrinter;

public class MJIfElse extends MJIf {
	protected MJBlock elseBlock;

	public MJIfElse(MJExpression expr, MJBlock ifBlock, MJBlock elseBlock){
		super(expr, ifBlock);
		this.elseBlock = elseBlock;
	}
	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("if (");
		this.expr.prettyPrint(prepri);
		prepri.print(")");
		this.ifBlock.prettyPrint(prepri);

		prepri.print("else");
		elseBlock.prettyPrint(prepri);
	}

}
