package compiler.Phases;

import compiler.Exceptions.*;
import compiler.IR.*;
import compiler.Compiler;

public class Analysis {

	public static void analyse(IR ir) throws TypeCheckerException {
		
		System.out.println("Rewriting 1... (add this arguments)");
		AddThisArgument.rewrite(ir);
		System.out.println("done.");

		if (Compiler.isDebug()) {
			PrettyPrint.print(ir);
		}
		
		TypeCheck.check(ir);
		
		System.out.println("Rewriting 2... (add this to selectors)");
		AddThisToSelectors.rewrite(ir);
		System.out.println("done.");

		if (Compiler.isDebug()) {
			PrettyPrint.print(ir);
		}
		
		VariableInit.check(ir);
	}
}
