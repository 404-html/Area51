package compiler.IR;

import compiler.PrettyPrinter;

public class MJParentheses extends MJExpression {

	private MJExpression exp;

	public MJParentheses() {
		
	}
	
	public MJParentheses(MJExpression e) {
		this.exp = e;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("( ");
		this.exp.prettyPrint(prepri);
		prepri.print(" )");
	}
}
