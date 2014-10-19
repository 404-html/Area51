package compiler.IR;

import compiler.PrettyPrinter;

public class MJNew extends MJExpression {

	protected MJIdentifier id;
	public MJNew(MJIdentifier visitId) {
		this.id = visitId;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("new ");
		this.id.prettyPrint(prepri);
		prepri.println("()");
	}
}
