package compiler.IR;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.MenuKeyEvent;

import compiler.Frontend.*;
import compiler.Frontend.MiniJavaParser.ArraytypeContext;
import compiler.Frontend.MiniJavaParser.ExpressionArrayAccessContext;
import compiler.Frontend.MiniJavaParser.ExpressionContext;
import compiler.Frontend.MiniJavaParser.ExpressionIdentifierContext;
import compiler.Frontend.MiniJavaParser.ExpressionMethodCallContext;
import compiler.Frontend.MiniJavaParser.ExpressionNegationContext;
import compiler.Frontend.MiniJavaParser.ExpressionNewArrayContext;
import compiler.Frontend.MiniJavaParser.ExpressionNewObjectContext;
import compiler.Frontend.MiniJavaParser.SimpleTypeContext;
import compiler.Frontend.MiniJavaParser.StatementArrayAssignContext;
import compiler.Frontend.MiniJavaParser.StatementIfContext;
import compiler.Frontend.MiniJavaParser.StatementMethodCallContext;
import compiler.Frontend.MiniJavaParser.StatementPrintContext;
import compiler.Frontend.MiniJavaParser.*;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import compiler.Frontend.*;

//public class IRbuilder extends MiniJavaBaseVisitor<IR> {
public class IRbuilder extends AbstractParseTreeVisitor<IR> implements MiniJavaVisitor<IR>{
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

		boolean isPublic = true;
		boolean isStatic = true;

		MJType returnType = MJType.getVoidType();
		String methodName = "main";

		MJType parameterType = MJType.getArrayType("String");
		String parameterName = ctx.parameterName.getText();
		MJVariable parameter = new MJVariable(parameterType, parameterName);
		LinkedList<MJVariable> parameterList = new LinkedList<MJVariable>();
		parameterList.add(parameter);

		MJBlock body = visitBlock(ctx.block());

		MJMethod method = new MJMethod(returnType, methodName, parameterList, body, isPublic, isStatic);

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
			MJVariable v = visitVarDeclaration(c);			
			variableDeclarations.add(v);
		}

		LinkedList<MJStatement> statements = new LinkedList<MJStatement>();
		for (MiniJavaParser.StatementContext c : ctx.statement()) {
			statements.add(visitStatement(c));
		}
		return new MJBlock(variableDeclarations, statements);
	}

	//	varDeclaration
	//	  : variable  ';'
	//	  ;

	public MJVariable visitVarDeclaration(MiniJavaParser.VarDeclarationContext ctx) { 
		return visitVariable(ctx.variable());
		
		// return (MJVariable)visitChildren(ctx); 
		}

	//	variable : type variableName=IDENT
	//			  ;

	public MJVariable visitVariable(MiniJavaParser.VariableContext ctx){

		MJType variableType = visitType(ctx.type());
		String variableName = ctx.IDENT().getText();

		return new MJVariable(variableType, variableName);
	}

	//	type
	//	  : typeBoolean
	//	  | typeInt
	//	  | typeClass
	//	  ;
	//
	//	typeBoolean : 'boolean' ;
	//	typeInt     : 'int' ;
	//	typeClass   : className=IDENT;

	public MJType visitType(MiniJavaParser.TypeContext ctx) { return (MJType)visitChildren(ctx); }

	public MJType visitTypeBoolean(MiniJavaParser.TypeBooleanContext ctx) {
		return MJType.getBooleanType();
	}	

	public MJType visitTypeInt(MiniJavaParser.TypeIntContext ctx) {
		return MJType.getIntType();
	}

	public MJType visitTypeClass(MiniJavaParser.TypeClassContext ctx) {
		return MJType.getClassType(ctx.className.getText());
	}

	public MJType visitVoidType(MiniJavaParser.TypeVoidContext ctx) {
		return MJType.getVoidType();
	}

	//TODO NOT TESTED
	public MJType visitSimpleType(MiniJavaParser.SimpleTypeContext ctx) {
		String text = ctx.getText();
		if(text == "boolean") {
			return MJType.getBooleanType();
		} else if(text == "int"){
			return MJType.getIntType();
		} else{
			return MJType.getClassType(text);
		}
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
		List<org.antlr.v4.runtime.tree.ParseTree>x = ctx.children;

		return (MJType)visitChildren(ctx); 
	}

	public MJType visitTypeVoid(MiniJavaParser.TypeVoidContext ctx) {
		return MJType.getVoidType();
	}

	//	statement
	//	  : statementAssign
	//	  | statementPrintln
	//	  ;
	//	  
	//	statementAssign  : lhs=id '=' rhs=expression ';' ;
	//	statementPrintln : 'System.out.println' '(' argument=expression ')' ';' ;
	//	statementReturn  : 'return' ('(' argument=expression ')')? ';' ;

	public MJStatement visitStatement(MiniJavaParser.StatementContext ctx) { return (MJStatement)visitChildren(ctx); }	

	public MJStatement visitStatementAssign(MiniJavaParser.StatementAssignContext ctx) {

		MJIdentifier leftHandSide = (MJIdentifier)visitId(ctx.lhs);
		MJExpression rightHandSide = (MJExpression)visitExpression(ctx.rhs);

		return new MJAssign(leftHandSide, rightHandSide);
	}

	public MJStatement visitStatementPrintln(MiniJavaParser.StatementPrintlnContext ctx) {

		MJExpression argument = (MJExpression)visitExpression(ctx.argument);

		return new MJPrintln(argument);
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
	//	  : head=level3 
	//	  ;

	public MJExpression visitLevel2(MiniJavaParser.Level2Context ctx) {

		MJExpression root = visitLevel3(ctx.head);
		ArrayList<MJExpression> tail = new ArrayList<MJExpression>();
		for(Level3Context expr : ctx.tail){
			tail.add(visitLevel3(expr));
		}
		if(tail.isEmpty()){
			return root;
		} else{
			return new MJLess(root, tail);
		}
	}

	//	level3
	//	  : head=level4 ( '+' tail+=level4 )*
	//	  ;

	public MJExpression visitLevel3(MiniJavaParser.Level3Context ctx) {

		MJExpression root = visitLevel4(ctx.head);
		MJExpression newRoot = null;
		List<Token> binOp = ctx.binOp; 
		for (int i = 0; i < ctx.tail.size(); i++) {
			if(binOp.get(i).getText().equals("+")){
				newRoot = new MJPlus(root, visitLevel4(ctx.tail.get(i)));
			}
			else if( ctx.binOp.get(i).getText().equals("-")){
				newRoot = new MJMinus(root, visitLevel4(ctx.tail.get(i)));
			
			}
			root = newRoot;
		}


		return root;
	}

	//	level4
	//	  : head=level5
	//	  ;

	public MJExpression visitLevel4(MiniJavaParser.Level4Context ctx) {

		MJExpression root = visitLevel5(ctx.head);
		for (MiniJavaParser.Level5Context c : ctx.tail){
			MJExpression newRooooot = new MJMult(root, visitLevel5(c));
			root = newRooooot;
		}

		return root;
	}

	//	level5
	//	  : expressionUnaryMinus
	//	  | expressionIdentifier
	//	  | expressionParentheses
	//	  | expressionConstantTrue
	//	  | expressionConstantFalse
	//	  | expressionConstantInteger
	//	  | expressionConstantString
	//	  ;
	//	  
	//	expressionUnaryMinus      : '-' argument=level5 ;
	//	expressionIdentifier      : identifier ;
	//	expressionParentheses     : '(' argument=expression ')' ;
	//	expressionConstantTrue    : 'true' ;
	//	expressionConstantFalse   : 'false' ;
	//	expressionConstantInteger : value=INT ;
	//	expressionConstantString  : value=STRING ;

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



	@Override
	public MJWhile visitStatementWhile(StatementWhileContext ctx) {
		MJStatement stat = visitStatement(ctx.statement());
		MJExpression expr = visitExpression(ctx.expression());

		return new MJWhile(expr, stat);
	}

	@Override
	public MJIdentifier visitExpressionIdentifier(ExpressionIdentifierContext ctx) {
		return visitId(ctx.id());
	}

	@Override
	public MJMethodCallExpr visitExpressionMethodCall(ExpressionMethodCallContext ctx) {
		ArrayList<MJExpression> tail = new ArrayList<MJExpression>();
		MJIdentifier id = visitIdentifier(ctx.identifier());
		MJExpression arg = visitExpression(ctx.argument);
		for(ExpressionContext e : ctx.tail){
			tail.add(visitExpression(e));
		}
		return new MJMethodCallExpr(id, arg, tail);
	}

	@Override
	public MJMethodCallStmt visitStatementMethodCall(StatementMethodCallContext ctx) {
		MJIdentifier id = new MJIdentifier(ctx.methodId.getText());
		MJExpression head = visitExpression(ctx.head);
		ArrayList<MJExpression> tail = new ArrayList<>();
		for (ExpressionContext ec : ctx.tail) {
			tail.add(visitExpression(ec));
		}
		return new MJMethodCallStmt(id, head, tail);
	}

	@Override
	public MJNew visitExpressionNewObject(ExpressionNewObjectContext ctx) {
		return new MJNew(visitId(ctx.id()));
	}

	@Override
	public IR visitStatementPrint(StatementPrintContext ctx) {
		return new MJPrint(visitExpression(ctx.argument));
	}

	@Override
	public MJType visitArraytype(ArraytypeContext ctx) {
		MJType type = visitSimpleType(ctx.arrayType);
		return MJType.getArrayType(type);

	}

	@Override
	public MJIf visitStatementIf(StatementIfContext ctx) {
		MJExpression expr = visitExpression(ctx.expression());
		MJBlock ifBlock = visitBlock(ctx.block(0));
		MJBlock elseBlock = null;
		if (ctx.block().size()>=2){
			elseBlock = visitBlock(ctx.block(1));
		}
		if (elseBlock == null){
			return new MJIf(expr, ifBlock);
		}
		else return new MJIfElse(expr, ifBlock, elseBlock);
	}

	@Override
	public MJArray visitExpressionArrayAccess(ExpressionArrayAccessContext ctx) {
		MJExpression expr = visitExpression(ctx.argument);
		MJIdentifier id = visitId(ctx.id());
		return new MJArray(id, expr);
	}

	//TODO NOT TESTED not sure if .getName() gets correct string
	@Override
	public MJAssign visitStatementArrayAssign(StatementArrayAssignContext ctx) {
		MJIdentifier id = visitId(ctx.lhs);
		MJExpression index = visitExpression(ctx.size);
		MJExpression val = visitExpression(ctx.rhs);
		MJIdentifier lhs = new MJIdentifier(new MJArray(id, index).getName());

		return new MJAssign(lhs, val);
	}

	@Override
	public MJNegate visitExpressionNegation(ExpressionNegationContext ctx) {
		return new MJNegate(visitLevel5(ctx.argument));
	}

	@Override
	public IR visitExpressionNewArray(ExpressionNewArrayContext ctx) {
		//TODO alternativt kan vi have en MJIdentifier som ekstra argument 
		return new MJNewArray(visitExpression(ctx.argument));	
	}

}
