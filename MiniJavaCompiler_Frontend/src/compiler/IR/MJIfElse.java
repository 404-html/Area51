package compiler.IR;

import java.util.ArrayList;

import compiler.PrettyPrinter;

public class MJIfElse extends MJIf {
	protected ArrayList<MJBlock> elseBlock;

	public MJIfElse(MJExpression expr, MJBlock ifBlock, ArrayList<MJBlock> elseBlock){
		super(expr, ifBlock);
		this.elseBlock = elseBlock;
	}
	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("if (");
		this.expr.prettyPrint(prepri);
		prepri.print(")");
		this.ifBlock.prettyPrint(prepri);
		for(MJBlock b : elseBlock){
			prepri.print("else");
			b.prettyPrint(prepri);
		}
	}

}
