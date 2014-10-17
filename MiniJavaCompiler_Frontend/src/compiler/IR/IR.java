package compiler.IR;

import compiler.PrettyPrinter;

public class IR {

	private MJProgram program;
	
	public IR() {
	}

	public IR(MJProgram program) {
		this.program = program;
	}

	public MJProgram getProgram() {
		return program;
	}

	public final void prettyPrint() {
		this.program.prettyPrint(new PrettyPrinter());
	}
}
