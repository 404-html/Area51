package compiler.IR;

import compiler.PrettyPrinter;

public class MJPrint extends MJStatement {

	private MJExpression parameter;

	public MJPrint() {
		
	}
	
	public MJPrint(MJExpression parameter) {
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
