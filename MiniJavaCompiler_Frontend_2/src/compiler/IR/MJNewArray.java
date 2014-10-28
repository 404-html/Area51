package compiler.IR;

public class MJNewArray extends MJNew {

	private MJExpression size;

	public MJNewArray(MJExpression size) {
		this.size = size;
	}

	public MJExpression getSize() {
		return this.size;
	}
}
