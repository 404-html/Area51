package compiler.IR;

import java.util.LinkedList;

import compiler.PrettyPrinter;

public class MJMethod extends IR {

	private MJType returnType;
	private String name;
	private LinkedList<MJVariable> parameters;
	private MJBlock body;
	private boolean isStatic;
	private boolean isPublic;

	public MJMethod(MJType returnType, String name, LinkedList<MJVariable> parList,
			MJBlock b, boolean isStatic, boolean isPublic) {
		this.returnType = returnType;
		this.name = name;
		this.parameters = parList;
		this.body = b;
		this.isStatic = isStatic;
		this.isPublic = isPublic;
	}

	public String getName() {
		return name;
	}

	public LinkedList<MJVariable> getParameters() {
		return parameters;
	}

	public MJType getReturnType() {
		return returnType;
	}

	public MJBlock getBody() {
		return body;
	}

	public boolean isStatic() {
		return this.isStatic;
	}

	public boolean isPublic() {
		return this.isPublic;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		if (this.isPublic()) {
			prepri.print("public ");
		}
		if (this.isStatic()) {
			prepri.print("static ");
		}

		this.returnType.prettyPrint(prepri);
		prepri.print(" ");
		
		prepri.print(this.name + "(");
		boolean first = true;
		for (MJVariable v : this.parameters) {
			if (!first) {
				prepri.print(", ");
			} else {
				first = false;
			}
			v.prettyPrint(prepri);
		}
		prepri.println(")");
		body.prettyPrint(prepri);
		prepri.println("");
	}
}
