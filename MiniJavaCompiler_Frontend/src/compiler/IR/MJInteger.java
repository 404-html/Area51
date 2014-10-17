package compiler.IR;

import compiler.PrettyPrinter;

public class MJInteger extends MJExpression {

	private int value;

	public MJInteger(String string) {
		this.value = Integer.parseInt(string);
	}

	public MJInteger(int slot) {
		this.value = slot;
	}

	public int getValue() {
		return value;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(Integer.toString(this.value));
	}
}
