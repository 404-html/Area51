package compiler.IR;


import compiler.PrettyPrinter;

public class MJString extends MJExpression {

	private String string;

	public MJString(String string) {
		this.string = string;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(this.string);
	}
}
