package compiler.IR;

import java.util.LinkedList;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import compiler.Frontend.*;
import compiler.Frontend.MiniJavaParser.ExpressionArrayAccessContext;
import compiler.Frontend.MiniJavaParser.ExpressionIdentContext;
import compiler.Frontend.MiniJavaParser.ExpressionIdentifierContext;
import compiler.Frontend.MiniJavaParser.ExpressionIntArrayContext;
import compiler.Frontend.MiniJavaParser.ExpressionNegationContext;
import compiler.Frontend.MiniJavaParser.ExpressionNewArrayContext;
import compiler.Frontend.MiniJavaParser.ExpressionNewObjectContext;
import compiler.Frontend.MiniJavaParser.StatementAssignArrayContext;
import compiler.Frontend.MiniJavaParser.StatementIfContext;
import compiler.Frontend.MiniJavaParser.StatementMethodContext;
import compiler.Frontend.MiniJavaParser.StatementPrintContext;
import compiler.Frontend.MiniJavaParser.StatementWhileContext;
import compiler.Frontend.MiniJavaParser.TypeArrayContext;
import compiler.Frontend.MiniJavaParser.TypeBasicContext;

public class IRbuilder extends AbstractParseTreeVisitor<IR> implements MiniJavaVisitor<IR> {
		
//	program
//	  : mainClass ( classDeclaration )*
//	  ;

	 
	public MJProgram visitProgram(MiniJavaParser.ProgramContext ctx) {
		
		LinkedList<MJClass> classDeclarations = new LinkedList<MJClass>();

		classDeclarations.add(visitMainClass(ctx.mainClass()));
		
		for (MiniJavaParser.ClassDeclarationContext c : ctx.classDeclaration()) {
			classDeclarations.add(visitClassDeclaration(c));
		}
		
		return new MJProgram(classDeclarations);
	}

//	classDeclaration
//	  : 'class' className=IDENT ( 'extends' superClassName=IDENT )? 
//	    '{' ( varDeclaration 
//	        )* 
//	        ( methodDeclaration 
//	        )*
//	    '}'
//	  ;
	
	public MJClass visitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
		
		String className = ctx.className.getText();
		String superClassName="Object";
		
		if (ctx.superClassName!=null) {
			superClassName = ctx.superClassName.getText();
		}
		
		LinkedList<MJVariable> variableDeclarations = new LinkedList<MJVariable>();
		for (MiniJavaParser.VarDeclarationContext c : ctx.varDeclaration()) {
			variableDeclarations.add(visitVarDeclaration(c));
		}
		
		LinkedList<MJMethod> statements = new LinkedList<MJMethod>();
		for (MiniJavaParser.MethodDeclarationContext c : ctx.methodDeclaration()) {
			statements.add(visitMethodDeclaration(c));
		}
		
		return new MJClass(className, superClassName, variableDeclarations, statements);
	}
	
//	mainClass
//	  : 'class' className=IDENT 
//	    '{' 
//	      'public' 'static' 'void' 'main' '(' 'String' '[' ']' parameterName=IDENT ')' 
//	      block
//	    '}'
//	  ;
	  
	public MJClass visitMainClass(MiniJavaParser.MainClassContext ctx) {
		
		String className = ctx.className.getText();

		MJMethod method = visitMethodDeclaration(ctx.method);
	
		return new MJClass(className, method);
	}

//	block
//	  : '{' ( varDeclaration )* 
//	        ( statement )*
//	    '}'
//	  ;
	    
	public MJBlock visitBlock(MiniJavaParser.BlockContext ctx) {
		
		LinkedList<MJVariable> variableDeclarations = new LinkedList<MJVariable>();
		for (MiniJavaParser.VarDeclarationContext c : ctx.varDeclaration()) {
			MJVariable var = visitVarDeclaration(c);
			variableDeclarations.add(var);
		}
		
		LinkedList<MJStatement> statements = new LinkedList<MJStatement>();
		for (MiniJavaParser.StatementContext c : ctx.statement()) {
			statements.add(visitStatement(c));
		}
		
		return new MJBlock(variableDeclarations, statements);
	}
	
//	varDeclaration
//	  : var=variable ';'
//	  ;

	public MJVariable visitVarDeclaration(MiniJavaParser.VarDeclarationContext ctx) {  
			MJVariable x = visitVariable(ctx.var); 
			return x;
	}

//	variable : type variableName=IDENT;
	
	public MJVariable visitVariable(MiniJavaParser.VariableContext ctx){
		
		MJType variableType = visitType(ctx.type());
		String variableName = ctx.IDENT().getText();
		
		MJVariable x = new MJVariable(variableType, variableName);
		
		return x;
	}

//	type 
//	   : typeBasic
//	   | typeArray
//	   ;
//
//	typeBasic
//	  : typeBoolean
//	  | typeInt
//	  | typeClass
//	  ;
//
//	typeBoolean : 'boolean' ;
//	typeInt     : 'int' ;
//	typeClass   : className=IDENT;

	public MJType visitType(MiniJavaParser.TypeContext ctx) { return (MJType)visitChildren(ctx); }
	
	public MJType visitTypeBasic(TypeBasicContext ctx) {
		return (MJType)visitChildren(ctx);
	}

	public MJType visitTypeBoolean(MiniJavaParser.TypeBooleanContext ctx) {
		return MJType.getBooleanType();
	}	
	
	public MJType visitTypeInt(MiniJavaParser.TypeIntContext ctx) {
		return MJType.getIntType();
	}
	
	public MJType visitTypeClass(MiniJavaParser.TypeClassContext ctx) {
		return MJType.getClassType(ctx.className.getText());
	}
	
	public IR visitTypeArray(TypeArrayContext ctx) {
		return MJType.getArrayType( (MJType)visitTypeBasic(ctx.typeBasic()));
	}

//	methodDeclaration
//	  : ( isPublic='public'  )?
//	    ( isStatic='static'  )? 
//	    procType methodName=IDENT 
//	    '(' 
//	      ( variable ( ',' variable )* )? 
//	    ')' 
//	    '{' ( varDeclaration )* 
//	        ( statement )*
//	        statementReturn
//	    '}'
//	  ;
	  
	public MJMethod visitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
		
		boolean isPublic = (ctx.isPublic != null);
		boolean isStatic = (ctx.isStatic != null);
		
		MJType returnType = visitProcType(ctx.procType());
		
		String methodName = ctx.methodName.getText();
		
		LinkedList<MJVariable> parameterList = new LinkedList<MJVariable>();
		for (MiniJavaParser.VariableContext c : ctx.variable()) {
			parameterList.add(visitVariable(c));
		}
		
		LinkedList<MJVariable> variableDeclarations = new LinkedList<MJVariable>();
		for (MiniJavaParser.VarDeclarationContext c : ctx.varDeclaration()) {
			variableDeclarations.add(visitVarDeclaration(c));
		}
		
		LinkedList<MJStatement> statements = new LinkedList<MJStatement>();
		for (MiniJavaParser.StatementContext c : ctx.statement()) {
			statements.add(visitStatement(c));
		}
		
		statements.add(visitStatementReturn(ctx.statementReturn()));
		
		MJBlock body = new MJBlock(variableDeclarations, statements);
		
		MJMethod method = new MJMethod(returnType, methodName, parameterList, body, isStatic, isPublic);
		
		return method;
	}
	
//	procType
//	  : type  
//	  | voidtype 
//	  ;
//
//	voidtype: 'void' ;
	  
	public MJType visitProcType(MiniJavaParser.ProcTypeContext ctx) { 
		return (MJType)visitChildren(ctx); 
	}
	
	public MJType visitTypeVoid(MiniJavaParser.TypeVoidContext ctx) {
		return MJType.getVoidType();
	}

//	statement
//	  : block
//	  | statementIf
//	  | statementWhile
//	  | statementAssign
//	  | statementAssignArray
//	  | statementPrintln
//	  | statementPrint
//	  | statementMethod
//	  ;
//
//	statementIf : 'if' '(' condition=expression ')' ifBlock=block ('else' elseBlock=block )? ;
//	statementWhile : 'while' '(' condition=expression ')' whileBlock=statement ;
//	statementAssign  : lhs=id '=' rhs=expression ';' ;
//	statementAssignArray : array=identifier '[' element=expression ']' '=' value=expression ';';
//	statementPrint : 'System.out.print' '(' argument=expression ')' ';' ;
//	statementPrintln : 'System.out.println' '(' argument=expression ')' ';' ;
//	statementMethod : (object=identifier '.')? method=IDENT '(' (arguments+=expression (',' arguments+=expression)? )? ')' ';';
//
//	statementReturn  : 'return' ('(' argument=expression ')' )? ';' ;
	
	public MJStatement visitStatement(MiniJavaParser.StatementContext ctx) { return (MJStatement)visitChildren(ctx); }	
	
	public MJStatement visitStatementIf(StatementIfContext ctx) {
		MJExpression condition = (MJExpression)visitExpression(ctx.condition);
		MJBlock ifBlock = (MJBlock)visitBlock(ctx.ifBlock);
		MJBlock elseBlock = null;
		
		if (ctx.elseBlock == null) {
			return new MJIf(condition, ifBlock);
		} else {
			elseBlock = (MJBlock)visitBlock(ctx.elseBlock);
			return new MJIfElse(condition, ifBlock, elseBlock);
		}		
	}

	public MJWhile visitStatementWhile(StatementWhileContext ctx) {
		MJExpression condition = (MJExpression)visitExpression(ctx.condition);
		MJBlock block = (MJBlock)visitStatement(ctx.whileBlock);
		
		return new MJWhile(condition, block);
	}

	public MJStatement visitStatementAssign(MiniJavaParser.StatementAssignContext ctx) {
		
		MJIdentifier leftHandSide = (MJIdentifier)visitId(ctx.lhs);
		MJExpression rightHandSide = (MJExpression)visitExpression(ctx.rhs);
		
		return new MJAssign(leftHandSide, rightHandSide);
	}
	
	public MJStatement visitStatementAssignArray(StatementAssignArrayContext ctx) {
		MJIdentifier array = (MJIdentifier)visitIdentifier(ctx.array);
		MJExpression element = (MJExpression)visitExpression(ctx.element);
		MJExpression rhs = (MJExpression)visitExpression(ctx.value);
		
		MJArray lhs = new MJArray(array, element);
		return new MJAssign(lhs, rhs);
	}

	public MJStatement visitStatementPrint(StatementPrintContext ctx) {
		MJExpression argument = (MJExpression)visitExpression(ctx.argument);
		
		return new MJPrint(argument);
	}

	public MJStatement visitStatementPrintln(MiniJavaParser.StatementPrintlnContext ctx) {
		
		MJExpression argument = (MJExpression)visitExpression(ctx.argument);
		
		return new MJPrintln(argument);
	}
	
	public MJStatement visitStatementMethod(StatementMethodContext ctx) {
		MJIdentifier object = visitIdentifier(ctx.object);
		String methodName = ctx.method.getText();
		LinkedList<MJExpression> arguments = new LinkedList<MJExpression>();
		
		for (MiniJavaParser.ExpressionContext argCtx : ctx.arguments ) {
			arguments.add( visitExpression(argCtx));
		}	
				
		return new MJMethodCallStmt(object, methodName, arguments);

	}

	public MJStatement visitStatementReturn(MiniJavaParser.StatementReturnContext ctx) {
		
		MJExpression argument = new MJNoExpression();
		
		if (ctx.argument!=null) {
			argument = visitExpression(ctx.argument);
		}
		
		return new MJReturn(argument);
	}

//	expression
//	  : head=level1 ( '&&' tail+=level1 )*
//	  ;
	
	public MJExpression visitExpression(MiniJavaParser.ExpressionContext ctx) {
		
		MJExpression root = visitLevel1(ctx.head);
		
		for (MiniJavaParser.Level1Context c : ctx.tail) {
			MJExpression newRoot = new MJAnd(root, visitLevel1(c));
			root = newRoot;
		}
		
		return root;
	}
	
//	level1
//	  : head=level2 ( '==' tail+=level2 )*
//	  ;

	public MJExpression visitLevel1(MiniJavaParser.Level1Context ctx) {
		
		MJExpression root = visitLevel2(ctx.head);
		
		for (MiniJavaParser.Level2Context c : ctx.tail) {
			MJExpression newRoot = new MJEqual(root, visitLevel2(c));
			root = newRoot;
		}
		
		return root;
	}

//	level2
//	  : head=level3 ( '<' tail+=level3 )* 
//	  ;

	public MJExpression visitLevel2(MiniJavaParser.Level2Context ctx) {
		
		MJExpression root = visitLevel3(ctx.head);
		
		for (MiniJavaParser.Level3Context c : ctx.tail) {
			MJExpression newRoot = new MJLess(root, visitLevel3(c));
			root = newRoot;
		}
		
		return root;
	}

//	level3
//	  : head=level4 ( ('+'|'-' tail+=level4 )*
//	  ;

	public MJExpression visitLevel3(MiniJavaParser.Level3Context ctx) {
		
		MJExpression root = visitLevel4(ctx.head);
		
		for (MiniJavaParser.Level4Context c : ctx.tail) {
			String operator = ctx.operator.getText();
			
			MJExpression newRoot;
			
			if (operator.equals("+")) {
				newRoot = new MJPlus(root, visitLevel4(c));
			} else {
				newRoot = new MJMinus(root, visitLevel4(c));
			}
			
			root = newRoot;
		}
		
		return root;
	}

//	level4
//    : head=level5 ( '*' tail+=level5 )*
//	  ;

	public MJExpression visitLevel4(MiniJavaParser.Level4Context ctx) {

		MJExpression root = visitLevel5(ctx.head);
		
		for (MiniJavaParser.Level5Context c : ctx.tail) {
			MJExpression newRoot = new MJMult(root, visitLevel5(c));
			root = newRoot;
		}
		
		return root;
	}

//	level5
//	  : expressionUnaryMinus
//	  | expressionNegation
//	  | expressionNewObject
//	  | expressionNewArray
//	  | expressionIdentifier
//	  | expressionArrayAccess
//	  | expressionMethodCall
//	  | expressionParentheses
//	  | expressionConstantTrue
//	  | expressionConstantFalse
//	  | expressionConstantInteger
//	  | expressionConstantString
//	  ; 
//	expressionNewObject : 'new' expressionIdent ;
//	expressionNewArray  : 'new' expressionIntArray ;
//	        
//
//	expressionUnaryMinus      : '-' argument=level5 ;
//	expressionNegation        : '!' argument=level5 ;
//	expressionIntArray        : 'int' '[' size=expression ']' ;
//	expressionIdent           : classname=IDENT '(' ')' ;
//	expressionIdentifier      : ident=id ;
//	expressionArrayAccess     : ident=identifier '[' element=expression ']' ;
//	expressionParentheses     : '(' argument=expression ')' ;
//	expressionConstantTrue    : 'true' ;
//	expressionConstantFalse   : 'false' ;
//	expressionConstantInteger : value=INT ;
//	expressionConstantString  : value=STRING ;
//	expressionMethodCall      : (object=identifier '.')? method=IDENT '(' (arguments+=expression (',' arguments+=expression)? )? ')' ;

	public MJExpression visitLevel5(MiniJavaParser.Level5Context ctx) { return (MJExpression)visitChildren(ctx); }

	public MJExpression visitExpressionUnaryMinus(MiniJavaParser.ExpressionUnaryMinusContext ctx) {
		return new MJUnaryMinus(visitLevel5(ctx.argument));
	}

	public MJExpression visitExpressionParentheses(MiniJavaParser.ExpressionParenthesesContext ctx) {
		return new MJParentheses(visitExpression(ctx.argument));
	}

	public MJExpression visitExpressionConstantTrue(MiniJavaParser.ExpressionConstantTrueContext ctx) {
		return MJBoolean.True;
	}

	public MJExpression visitExpressionConstantFalse(MiniJavaParser.ExpressionConstantFalseContext ctx) {
		return MJBoolean.False;
	}
	
	public MJExpression visitExpressionConstantInteger(MiniJavaParser.ExpressionConstantIntegerContext ctx) {
		return new MJInteger(ctx.value.getText());
	}
	
	public MJExpression visitExpressionConstantString(MiniJavaParser.ExpressionConstantStringContext ctx) {
		return new MJString(ctx.value.getText());
	}
	
//	identifier
//	  : id ( '.' selectors+=IDENT )*
//	  ;
//
//	id
//	  : idThis
//	  | idIDENT
//	  ;
//
//	idThis  : 'this' ;
//	idIDENT : name=IDENT ;

	public MJIdentifier visitIdentifier(MiniJavaParser.IdentifierContext ctx) { 
		MJIdentifier id = visitId(ctx.id()); 
		
		for (Token t : ctx.selectors) {
			MJIdentifier selector = new MJIdentifier(t.getText());
			MJSelector s = new MJSelector(id, selector);
			id = s;
		}
		
		return id;
	}
	
	public MJIdentifier visitId(MiniJavaParser.IdContext ctx) { return (MJIdentifier)visitChildren(ctx); }
	
	public MJIdentifier visitIdThis(MiniJavaParser.IdThisContext ctx) { return new MJIdentifier("this"); }
	
	public MJIdentifier visitIdIDENT(MiniJavaParser.IdIDENTContext ctx) { return new MJIdentifier(ctx.name.getText()); }

	public MJIdentifier visitExpressionIdentifier(ExpressionIdentifierContext ctx) {
		return (MJIdentifier)visitChildren(ctx);
	}

	public MJMethodCallExpr visitExpressionMethodCall(MiniJavaParser.ExpressionMethodCallContext ctx) {
		MJIdentifier object = visitIdentifier(ctx.object);
		String methodName = ctx.method.getText();
		LinkedList<MJExpression> arguments = new LinkedList<MJExpression>();
		
		for (MiniJavaParser.ExpressionContext argCtx : ctx.arguments ) {
			arguments.add( visitExpression(argCtx));
		}
		
		return new MJMethodCallExpr(object, methodName, arguments);
	}

	public MJNewArray visitExpressionIntArray(ExpressionIntArrayContext ctx) {

		MJExpression size = (MJExpression)visitExpression(ctx.size);
		
		return new MJNewArray(size);
	}

	public MJExpression visitExpressionArrayAccess(ExpressionArrayAccessContext ctx) {
		MJIdentifier id = (MJIdentifier)visitIdentifier(ctx.ident);
		MJExpression index = (MJExpression)visitExpression(ctx.element);
		
		return new MJArray(id, index);
	}

	public MJExpression visitExpressionNegation(ExpressionNegationContext ctx) {
		MJExpression argument = (MJExpression)visitLevel5(ctx.argument);
		
		return new MJNegate(argument);
	}

	public MJIdentifier visitExpressionIdent(ExpressionIdentContext ctx) {
		return (MJIdentifier)visitChildren(ctx);
	}

	public IR visitExpressionNewObject(ExpressionNewObjectContext ctx) {
		String className = ctx.expressionIdent().classname.getText();
		
		return new MJNew(className);
	}

	public IR visitExpressionNewArray(ExpressionNewArrayContext ctx) {
		MJExpression expr = (MJExpression)visitExpressionIntArray(ctx.expressionIntArray());
		return expr;
	}

}
