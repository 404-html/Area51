package compiler.IR;

import compiler.PrettyPrinter;

public class MJIdentifier extends MJExpression {

	private String name;

	protected MJIdentifier() {
	}

	public MJIdentifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(this.name);
	}
}
