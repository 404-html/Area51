package compiler.IR;

public class MJReturn extends MJStatement {

	private MJExpression argument;

	public MJReturn() {
		this.argument = new MJNoExpression();
	}
	
	public MJReturn(MJExpression retExp) {
		this.argument = retExp;
	}

	public MJExpression getArgument() {
		return this.argument;
	}
}
