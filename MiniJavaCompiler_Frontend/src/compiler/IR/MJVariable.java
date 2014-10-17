package compiler.IR;

import compiler.PrettyPrinter;

public class MJVariable extends IR {

	private MJType type;
	private String name;
	
	public MJVariable(MJType t, String name) {
		this.type = t;
		this.name = name;
	}

	public MJType getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(this.type + " " + this.name);
	}
}
