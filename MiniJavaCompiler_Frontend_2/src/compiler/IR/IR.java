package compiler.IR;

import java.io.Serializable;

public class IR implements Serializable {

	private MJProgram program;
	
	public IR() {
	}

	public IR(MJProgram program) {
		this.program = program;
	}

	public MJProgram getProgram() {
		return program;
	}

}
