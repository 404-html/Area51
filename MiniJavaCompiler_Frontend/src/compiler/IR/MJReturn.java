package compiler.IR;

import compiler.PrettyPrinter;

public class MJReturn extends MJStatement {

	private MJExpression retExp;

	public MJReturn() {
		this.retExp = new MJNoExpression();
	}
	
	public MJReturn(MJExpression retExp) {
		this.retExp = retExp;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("return");

		if (!(this.retExp instanceof MJNoExpression)) {
			prepri.print(" ");
			this.retExp.prettyPrint(prepri);
		}

		prepri.println(";");
	}
}
