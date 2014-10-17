package compiler.IR;

public abstract class MJBinaryOp extends MJExpression {

	protected MJExpression lhs;
	protected MJExpression rhs;

	public MJBinaryOp(MJExpression lhs, MJExpression rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public void setLeftOperand(MJExpression op) {
		this.lhs = op;
	}

	public void setRightOperand(MJExpression op) {
		this.rhs = op;
	}

	public MJBinaryOp() {
	};

	
}
