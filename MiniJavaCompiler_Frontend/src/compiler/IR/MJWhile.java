package compiler.IR;

import compiler.PrettyPrinter;

public class MJWhile extends MJStatement {

	private MJExpression expr;
	private MJStatement stat;
	
	public MJWhile(MJExpression expr, MJStatement stat){ 
		this.expr = expr;
		this.stat = stat;
	}
	
	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("while (");
		this.expr.prettyPrint(prepri);
		prepri.print(")");
		this.stat.prettyPrint(prepri);
		prepri.println(";");
	}
}
