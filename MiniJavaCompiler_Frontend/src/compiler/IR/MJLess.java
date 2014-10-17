package compiler.IR;

import java.util.ArrayList;

import compiler.PrettyPrinter;

public class MJLess extends MJBinaryOp {

	protected MJExpression head;
	protected ArrayList<MJExpression> tail;
	
	public MJLess(MJExpression head, ArrayList<MJExpression> tail){
	this.head = head;
	this.tail = tail;
	}
	public void prettyPrint(PrettyPrinter prepri) {
		head.prettyPrint(prepri);
		for(MJExpression expr : tail){
			prepri.print("<");
			expr.prettyPrint(prepri);
		}
	}
}
