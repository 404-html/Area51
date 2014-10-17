package compiler.IR;
//mh
import java.util.ArrayList;
import java.util.LinkedList;

import compiler.PrettyPrinter;

public class MJMethodCallStmt extends MJStatement {
	MJIdentifier methodId;
	MJExpression head;
	ArrayList<MJExpression> tail;
	
	public MJMethodCallStmt(MJIdentifier methodId, MJExpression head, ArrayList<MJExpression> tail){
		this.methodId = methodId;
		this.head = head;
		this.tail = tail;
	}
	
	public void prettyPrint(PrettyPrinter prepri) {
		this.methodId.prettyPrint(prepri);
		prepri.print("(");
		this.head.prettyPrint(prepri);
		if(tail != null){
			for(MJExpression methodCall : tail){
				prepri.print(",");
				methodCall.prettyPrint(prepri);
			}
		}
		prepri.print(");");
		
		
	}
}
