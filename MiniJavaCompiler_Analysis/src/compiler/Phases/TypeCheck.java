package compiler.Phases;

import compiler.IR.*;
import compiler.IR.support.IRElementVisitor;
import compiler.IR.support.MJClassTable;
import compiler.Exceptions.ClassAlreadyDeclared;
import compiler.Exceptions.ClassErrorField;
import compiler.Exceptions.ClassErrorMethod;
import compiler.Exceptions.ClassNotFound;
import compiler.Exceptions.InheritanceError;
import compiler.Exceptions.MethodNotFound;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableAlreadyDeclared;
import compiler.Exceptions.VariableNotFound;
import compiler.Exceptions.VisitorException;

import java.io.PrintStream;
import java.util.LinkedList;


public class TypeCheck extends IRElementVisitor<MJType> {

	public static void check(IR ir) throws TypeCheckerException {

		try {
			new TypeCheck().visitProgram(ir.getProgram());
		} catch (VisitorException e) {
			throw new TypeCheckerException(e.getMessage());
		}

	}

	// check whether one type can be assigned to the other

	public static boolean isAssignable(MJType src, MJType dest) throws TypeCheckerException {

		// if they have the same type, this should be easy

		if (src.isSame(dest)) {

			// if they are class types

			if (src.isClass()) {

				// if they are the same class, return true

				if (src.getName().equals(dest.getName())) {
					return true;
				}

				// may be dest is a super class of src...

				// but only if we are not yet at the root

				if (src.getName().equals("Object")) {
					return false;
				}

				if (src.getDecl()==null) {
					try {
						src.setDecl(IR.classes.lookup(src.getName()));
					} catch (ClassNotFound exc) {
						throw new TypeCheckerException("Class "+src.getName()+" not defined.");
					}

				}

				return isAssignable(src.getDecl().getSuperClass(), dest);

			}

			// if they are array types

			if (src.isArray()) {
				if (dest.isClass() && dest.getName().equals("Object")){
					return true;
				}
				return isAssignable(src.getBaseType(), dest.getBaseType());
			}

			// otherwise they must be basic types
			return true;
		}

		return false;
	}

	@Override
	public MJType visitProgram(MJProgram e) throws VisitorException {
		// we need a super class in our class table - Object

		MJClass oc = new MJClass("Object", null,
				new LinkedList<MJVariable>(), new LinkedList<MJMethod>());
		oc.setAsTop();
		e.getClasses().addLast(oc);

		LinkedList<MJVariable> varlist = new LinkedList<MJVariable>();
		MJVariable length = new MJVariable(MJType.getIntType(), "length");
		MJVariable text = new MJVariable(MJType.getClassType("String"), "text");
		varlist.add(text);
		varlist.addLast(length);

		oc = new MJClass("String", "Object", varlist, new LinkedList<MJMethod>());

		e.getClasses().addLast(oc);

		// and add its methods - this is actually empty

		// now iterate over all classes in the program and add them to the
		// classtable

		for (MJClass c : e.getClasses()) {
			try {
				IR.classes.add(c);
			} catch (ClassAlreadyDeclared e1) {
				throw new TypeCheckerException("Class " + e1.getMessage()
						+ " already declared.");
			} catch (ClassErrorField e1) {
				throw new TypeCheckerException("Class " + c.getName()
						+ " has two fields with name " + e1.getMessage());
			}
		}

		// and add all methods as well

		for (MJClass c : e.getClasses()) {
			try {
				IR.classes.addMethods(c);
			} catch (ClassErrorMethod e1) {
				throw new TypeCheckerException("Class " + e1.getMessage()
						+ " already declared.");
			} catch (ClassNotFound e1) {
				throw new TypeCheckerException("Class " + e1.getMessage()
						+ " not found.");
			} catch (InheritanceError e1) {
				throw new TypeCheckerException("Class " + c.getName()
						+ " overwrites a method.");
			}
		}

		// finally we can typecheck the individual classes

		MJClass mainClass = e.getClasses().getFirst();

		if (mainClass.getFieldList().size()>0) {
			throw new TypeCheckerException("Main class may not have fields");
		}

		if (mainClass.getMethodList().size()!=1) {
			throw new TypeCheckerException("Main class may only have one method.");
		}

		MJMethod method = mainClass.getMethodList().getFirst();

		if (!(method.isPublic() && method.isStatic()) ) {
			throw new TypeCheckerException("Main method must be public static.");
		}

		if (!method.getReturnType().isVoid()) {
			throw new TypeCheckerException("Main method must have return type void.");			
		}

		if (!method.getName().equals("main")) {
			throw new TypeCheckerException("Main method must have name 'main'.");
		}

		if (!(method.getParameters().size()==1)) {
			throw new TypeCheckerException("Main method must take one argument.");
		}

		MJVariable parameter = method.getParameters().getFirst();

		if (!(parameter.getType().isArray() && 
				parameter.getType().getBaseType().isClass() && 
				parameter.getType().getBaseType().getName().equals("String"))) {
			throw new TypeCheckerException("Main method argument must have type 'String[]'");
		}

		for (MJClass c : e.getClasses()) {
			visitClass(c);
		}

		return MJType.getVoidType();
	}

	@Override
	public MJType visitClass(MJClass e) throws VisitorException {
		// remember the current class

		// typecheck super class

		if (!e.isTop()) {
			try {
				visitClass(IR.classes.lookup(e.getSuperClass().getName()));
			} catch (ClassNotFound exc) {
				throw new TypeCheckerException("Class "+e.getSuperClass().getName()+" not found!");
			}
		}

		IR.currentClass = e;

		// check fields

		for (MJVariable f : e.getFieldList()) {
			f.setIsField();
			visitVariable(f);
		}

		// and check methods

		for (MJMethod m : e.getMethodList()) {

			visitMethod(m);
		}

		return MJType.getVoidType();	}

	@Override
	public MJType visitVariable(MJVariable e) throws VisitorException {
		// we only need to typecheck the type of the variable
		// adding to the scope stack ensures that the name is unique

		visitType(e.getType());

		return MJType.getVoidType();
	}

	@Override
	public MJType visitType(MJType e) throws VisitorException {

		if (e.isBoolean()) return MJType.getBooleanType();
		if (e.isInt()) return MJType.getIntType();
		if (e.isVoid()) return MJType.getVoidType();
		if (e.isClass()) {
			try {
				//Look up Class definition in Class Table
				e.setDecl(IR.classes.lookup(e.getName()));
			} catch (ClassNotFound exc) {
				//Throw exception if Class not defined
				throw new TypeCheckerException("Class "+e.getName()+" not defined.");
			}
			return e;
		}
		if (e.isArray()) {
			visitType(e.getBaseType());
			return e;
		}
		throw new TypeCheckerException("Unknown type "+e.getName()+".");
	}

	@Override
	public MJType visitMethod(MJMethod e) throws VisitorException {
		// remember which method we are in
		IR.currentMethod = e;

		// typecheck the return type
		visitType(e.getReturnType());

		// we need a new scope for the parameters
		IR.stack.enterScope();

		for (MJVariable par : e.getParameters()) {

			// each parameter is type checked
			visitVariable(par);

			// and added to the scope
			try {
				IR.stack.add(par);
			} catch (VariableAlreadyDeclared exc) {
				throw new TypeCheckerException("Method "+e.getName()+" has duplicate parameter "+par.getName());
			}
		}

		// now we typecheck the body
		//Implicit check of return type vs. return declaration - return statements in block
		//are checked against return type of IR.currentMethod
		visitStatement(e.getBody());

		// and leave the scope
		IR.stack.leaveScope();

		return MJType.getVoidType();
	}

	@Override
	public MJType visitStatement(MJBlock e) throws VisitorException {
		// enter a new scope
		IR.stack.enterScope();

		// add all variables
		for (MJVariable v : e.getVariables()) {
			try {
				visitVariable(v);
				IR.stack.add(v);
			} catch (VariableAlreadyDeclared exc) {
				throw new TypeCheckerException("Variable "+v.getName()+" already declared.");
			}
		}

		// typecheck all statements
		for (MJStatement s : e.getStatements()) {
			visitStatement(s);
		}

		// leave the scope
		IR.stack.leaveScope();

		return MJType.getVoidType();
	}

	@Override
	public MJType visitStatement(MJIf e) throws VisitorException {
		//TODO Test
		//Visiting Condition expression - must evaluate to boolean
		if (!visitExpression(e.getCondition()).isBoolean()){
			throw new TypeCheckerException("If condition does not evaluate to Boolean");
		}
		//Visiting if block
		if (e.getIfBlock() != null){
			visitStatement(e.getIfBlock());
		};

		return MJType.getVoidType();
	}

	@Override
	public MJType visitStatement(MJIfElse e) throws VisitorException {
		//TODO Test
		//Checking if condition and block by typecasting
		visitStatement((MJIf)e);
		//Visiting Else block
		if(e.getElseBlock()!=null){
			visitStatement(e.getElseBlock());
		}

		return MJType.getVoidType();
	}

	@Override
	public MJType visitStatement(MJWhile e) throws VisitorException {
		//TODO Test
		//Check condition (condition not null)
		if(!visitExpression(e.getCondition()).isBoolean()){
			throw new TypeCheckerException("While condition does not evaluate to Boolean");
		}
		//Check Block contents
		if(e.getBlock()!=null){
			visitStatement(e.getBlock());
		}
		return MJType.getVoidType();
	}

	@Override
	public MJType visitStatement(MJAssign e) throws VisitorException {
		// typecheck the rhs and lhs
		MJType rhsType = visitExpression(e.getRhs());
		MJType lhsType = visitExpression(e.getLhs());

		// check that rhs is assignable to lhs
		if (!isAssignable(rhsType, lhsType)) {
			throw new TypeCheckerException("Types in assignment are not assignable ("+lhsType+","+rhsType+")");
		}

		return MJType.getVoidType();
	}

	@Override
	public MJType visitStatement(MJPrint e) throws VisitorException {
		//Null check unnecessary since grammar demands an expresssion - but for safety ;)
		if (e.getParameter()!=null){	
			visitExpression(e.getParameter());
		}
		return MJType.getVoidType();
	}

	@Override
	public MJType visitStatement(MJPrintln e) throws VisitorException {
		// typecheck the parameter - and done. (no null check here ;))
		visitExpression(e.getParameter());

		return MJType.getVoidType();
	}

	@Override
	public MJType visitStatement(MJMethodCallStmt e) throws VisitorException {
		visitExpression(e.getMethodCallExpr());

		return MJType.getVoidType();
	}

	@Override
	public MJType visitStatement(MJReturn e) throws VisitorException {

		// typecheck the parameter - and done.
		MJType returnType = visitExpression(e.getArgument());

		if (!isAssignable(returnType, IR.currentMethod.getReturnType())) {
			throw new TypeCheckerException("Type of return does not match function's return type.");
		}

		return MJType.getVoidType();
	}

	@Override
	public MJType visitExpression(MJAnd e) throws VisitorException {

		MJType ltype = visitExpression(e.getLhs());
		MJType rtype = visitExpression(e.getRhs());

		if (!ltype.isSame(rtype)) { 
			throw new TypeCheckerException("Arguments to && must be of same type");
		}

		if(!ltype.isBoolean()) {
			throw new TypeCheckerException("Arguments to && must be of type boolean");
		}
		e.setType(ltype);
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJEqual e) throws VisitorException {

		MJType ltype = visitExpression(e.getLhs());
		MJType rtype = visitExpression(e.getRhs());

		if (!ltype.isSame(rtype)) { 
			throw new TypeCheckerException("Arguments to == must be of same type");
		}

		e.setType(MJType.getBooleanType());
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJLess e) throws VisitorException {
		MJType ltype = visitExpression(e.getLhs());
		MJType rType = visitExpression(e.getRhs());
		if (!ltype.isSame(rType)){
			throw new TypeCheckerException("Arguments of < must match!");
		}
		e.setType(MJType.getBooleanType());
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJPlus e) throws VisitorException {
		//TODO intense testing - DANGER	
		MJType ltype = visitExpression(e.getLhs());
		MJType rtype = visitExpression(e.getRhs());
		//Matching types 
		if(!ltype.isSame(rtype)){
			throw new TypeCheckerException("Operands of + must match");
		}  
		//Matching integer or String class
		if (!ltype.isInt() &&
				!(ltype.isClass() && ltype.isSame(MJType.getClassType("String")))){
			throw new TypeCheckerException("+ only allowed for String and integer types");
		}
		//Return type of operands
		e.setType(ltype);	
		return e.getType();
	}


	@Override
	public MJType visitExpression(MJMinus e) throws VisitorException {

		// get types from child elements

		MJType leftType = visitExpression(e.getLhs());
		MJType rightType = visitExpression(e.getRhs());

		// check whether they are correct

		if (!leftType.isSame(rightType)) {
			throw new TypeCheckerException("Arguments of - must have same type.");
		}

		if (!leftType.isInt()) {
			throw new TypeCheckerException("Arguments of - must be int.");
		}

		// safe type and return 

		e.setType(leftType);
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJMult e) throws VisitorException {
		// get types from child elements

		MJType leftType = visitExpression(e.getLhs());
		MJType rightType = visitExpression(e.getRhs());

		// check whether they are correct

		if (!leftType.isSame(rightType)) {
			throw new TypeCheckerException("Arguments of * must have same type.");
		}

		if (!leftType.isInt()) {
			throw new TypeCheckerException("Arguments of * must be int.");
		}

		// safe type and return 

		e.setType(leftType);
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJUnaryMinus e) throws VisitorException {

		MJType type = visitExpression(e.getArgument());

		if(!type.isInt()) {
			throw new TypeCheckerException("Arguments to - must be of type int");
		}

		e.setType(type);
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJNegate e) throws VisitorException {
		MJType type = visitExpression(e.getArgument());

		if (!type.isBoolean()){
			throw new TypeCheckerException("Argument of ! must be boolean");
		}

		e.setType(type);
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJNew e) throws VisitorException {
		//Looking up if class has been declared
		try{
			IR.classes.lookup(e.getClassName());
		} catch(ClassNotFound e1){
			throw new TypeCheckerException("Class not found" + e1.getMessage());
		}
		//Setting type to relevant class type
		e.setType(MJType.getClassType(e.getClassName()));
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJNewArray e) throws VisitorException {
		MJType type = visitExpression(e.getSize());
		// Checking Size Expression
		if (!type.isInt()){
			throw new TypeCheckerException("Array size must evaluate to int");
		}
		//Setting type to ArrayType of IntType
		e.setType(MJType.getArrayType(MJType.getIntType()));
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJMethodCallExpr e) throws VisitorException {
		//Getting and visiting args for method
		LinkedList<MJExpression> args = e.getArguments();
		for (MJExpression mjExpression : args) {
			visitExpression(mjExpression);
		}
		//Getting Identifier for object - if null -> own method
		MJIdentifier objectid = e.getObject();
		//Class for method - set to CurrentClass until further
		MJClass oclass = IR.currentClass; 
		//MJ method var
		MJMethod mmethod;
		//Checking if there is a parent object 
		if (objectid !=null){
			//If parent object -> visit parent...
			MJType otype =  visitExpression(e.getObject());
			try {
				//...and get class from class table
				oclass = IR.classes.lookup(otype.getName());
			} catch (ClassNotFound e2) {
				throw new TypeCheckerException("Object not found for methodcall");
			}
		}	
		//Get method from class table 
		try {
			mmethod = IR.classes.lookupMethod(oclass, e.getMethodName(), args);
			//setting target (future)
			e.setTarget(mmethod);
		} catch (ClassErrorMethod | MethodNotFound e1) {
			throw new TypeCheckerException("Method not found for Class");
		}
		//set type to return type.		
		e.setType(mmethod.getReturnType());
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJParentheses e) throws VisitorException {
		e.setType(visitExpression(e.getArgument()));
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJBoolean e) throws VisitorException {
		e.setType(MJType.getBooleanType());
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJInteger e) throws VisitorException {
		e.setType(MJType.getIntType());
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJString e) throws VisitorException {
		e.setType(MJType.getClassType("String"));
		return e.getType();

	}

	@Override
	public MJType visitExpression(MJIdentifier e) throws VisitorException {
		// find the declaration for the identifier on the stack
		MJVariable var;
		String name = e.getName();

		if (name.equals("this") && IR.currentMethod.isStatic()) {
			throw new TypeCheckerException("this encountered in static method.");
		}

		try {
			var = IR.find(name);
		} catch (VariableNotFound exc) {
			throw new TypeCheckerException("Unknown identifier " + name);
		}

		// remember the declaration

		e.setDeclaration(var);
		e.setType(var.getType());
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJArray e) throws VisitorException {

		// typecheck the identifier
		MJType idtype = visitExpression(e.getArray());

		// which must have array type
		if (!idtype.isArray()) {
			throw new TypeCheckerException(e.getName()+" must have array type");
		}

		// typecheck the index
		MJType idxtype = visitExpression(e.getIndex());

		// which must have type integer
		if (!idxtype.isInt()) {
			throw new TypeCheckerException("Index for "+e.getName()+" must have type int");			
		}

		// type of the element is that of the base type
		e.setType(idtype.getBaseType());		
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJSelector e) throws VisitorException {

		// a selector has the form object.field

		// first type check the object
		// this sets also the object.decl 

		MJType idtype = visitExpression(e.getObject());

		// the object must have class type

		if (!idtype.isClass()) {
			throw new TypeCheckerException("Type of an object in a selector must be a class type.");
		}

		// now get the class declaration of object

		MJClass classDecl;
		try {
			classDecl = IR.classes.lookup(idtype.getName());
		} catch (ClassNotFound exc) {
			throw new TypeCheckerException("No class declaration for object's type found.");
		}

		// now we can finally search for the field in the declaration

		MJVariable fieldDecl;
		try {
			fieldDecl = IR.classes.lookupField(classDecl, e.getField().getName());
		} catch (ClassErrorField exc) {
			throw new TypeCheckerException("Class "+ classDecl.getName() + 
					" has no field "+ e.getField().getName() + ".");
		}

		// and from the field declaration we get the type...

		e.setDeclaration(fieldDecl);
		e.setType(fieldDecl.getType());
		return e.getType();
	}

	@Override
	public MJType visitExpression(MJNoExpression e) throws VisitorException {
		e.setType(MJType.getVoidType());
		return e.getType();
	}


}
