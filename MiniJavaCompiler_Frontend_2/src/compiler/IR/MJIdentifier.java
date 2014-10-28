package compiler.IR;

public class MJIdentifier extends MJExpression {

	private String name;

	public MJIdentifier() {
	}

	public MJIdentifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
