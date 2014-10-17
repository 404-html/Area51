package compiler.IR;

import java.util.LinkedList;

import compiler.PrettyPrinter;

public class MJProgram extends IR {

	private LinkedList<MJClass> classes;

	public MJProgram() {
		
	}
	
	public MJProgram(LinkedList<MJClass> cdl) {
		this.classes = cdl;
	}

	public LinkedList<MJClass> getClasses() {
		return classes;
	}

	public void prettyPrint(PrettyPrinter prettyPrinter) {
		for (MJClass c : classes) {
			c.prettyPrint(prettyPrinter);
		}
	}
}
