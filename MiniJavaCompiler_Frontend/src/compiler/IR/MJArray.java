	package compiler.IR;

import compiler.PrettyPrinter;

public class MJArray extends MJIdentifier {

	private MJIdentifier array;
	private MJExpression index;

	public MJArray() {}
	
	public MJArray(MJIdentifier array, MJExpression idx) {
		this.array = array;
		this.index = idx;
	}

	public MJIdentifier getArray() {
		return array;
	}

	public MJExpression getIndex() {
		return index;
	}
	
	public String getName(){
		return array.getName() + "[" + index.toString() + "]";
		
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.array.prettyPrint(prepri);
		prepri.print("[");
		this.index.prettyPrint(prepri);
		prepri.print("]");
	}
}
