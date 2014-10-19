package compiler.IR;

import compiler.PrettyPrinter;

public class MJAssign extends MJStatement {

	private MJExpression rhs;
	private MJExpression index;
	private MJIdentifier lhs;
	
	public MJAssign(MJIdentifier id, MJExpression index, MJExpression rhs) {
		this.lhs = id;
		this.index = index;
		this.rhs = rhs;
	}
	
	public MJAssign(MJIdentifier lhs, MJExpression rhs) {
		this(lhs, null, rhs);
	}

	public MJIdentifier getLhs() {
		return this.lhs;
	}

	public MJExpression getRhs() {
		return this.rhs;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		if(index != null){
			prepri.print("[");
			index.prettyPrint(prepri);
			prepri.print("]");
		}
		prepri.print(" = ");
		this.rhs.prettyPrint(prepri);
		prepri.println(";");
	}
}
