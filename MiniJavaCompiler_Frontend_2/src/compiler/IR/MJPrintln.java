package compiler.IR;

public class MJPrintln extends MJStatement {

	private MJExpression parameter;

	public MJPrintln() {
		
	}
	
	public MJPrintln(MJExpression parameter) {
		this.parameter = parameter;
	}

	public MJExpression getParameter() {
		return parameter;
	}

}
