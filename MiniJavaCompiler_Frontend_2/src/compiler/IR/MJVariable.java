package compiler.IR;

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
	
}
