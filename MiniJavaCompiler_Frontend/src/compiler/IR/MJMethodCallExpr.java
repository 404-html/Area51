package compiler.IR;
//mh
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import compiler.PrettyPrinter;

public class MJMethodCallExpr extends MJExpression {
	private MJIdentifier id;
	private MJExpression argument;
	private ArrayList<MJExpression> tail;
	
	public MJMethodCallExpr(MJIdentifier id, MJExpression argument, ArrayList<MJExpression> tail){
		this.id = id;
		this.argument = argument;
		this.tail = tail;
	}
	
	public void prettyPrint(PrettyPrinter prepri) {
		this.id.prettyPrint(prepri);
		prepri.print("(");
		this.argument.prettyPrint(prepri);
		if(tail != null){
			for(MJExpression methodCall : tail){
				prepri.print(",");
				methodCall.prettyPrint(prepri);
			}
		}
		prepri.print(")");
	}
}
