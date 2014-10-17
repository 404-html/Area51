package compiler.IR;
//done by mh
import compiler.PrettyPrinter;

public class MJNewArray extends MJNew {

	private MJExpression expr;
	
	public MJNewArray(MJExpression expr){
		super(new MJIdentifier("int"));
		this.expr	= expr;
	}
	
	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("new int[");
		this.expr.prettyPrint(prepri);
		prepri.print("]");
	}
}
