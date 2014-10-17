package compiler.IR;

import compiler.PrettyPrinter;

public class MJPrintln extends MJStatement {

	private MJExpression parameter;

	public MJPrintln() {
		
	}
	
	public MJPrintln(MJExpression parameter) {
		this.parameter = parameter;
	}

	public MJExpression getParameter() {
		return parameter;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("System.out.println(");
		this.parameter.prettyPrint(prepri);
		prepri.println(");");
	}
}
