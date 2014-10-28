// Generated from /Users/probst/Desktop/MiniJava.g4 by ANTLR 4.1
package compiler.Frontend;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniJavaParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__33=1, T__32=2, T__31=3, T__30=4, T__29=5, T__28=6, T__27=7, T__26=8, 
		T__25=9, T__24=10, T__23=11, T__22=12, T__21=13, T__20=14, T__19=15, T__18=16, 
		T__17=17, T__16=18, T__15=19, T__14=20, T__13=21, T__12=22, T__11=23, 
		T__10=24, T__9=25, T__8=26, T__7=27, T__6=28, T__5=29, T__4=30, T__3=31, 
		T__2=32, T__1=33, T__0=34, IDENT=35, INT=36, STRING=37, COMMENT=38, WHITESPACE=39;
	public static final String[] tokenNames = {
		"<INVALID>", "']'", "'System.out.print'", "'public'", "','", "'while'", 
		"'['", "'-'", "'*'", "'('", "'if'", "'int'", "'<'", "'false'", "'void'", 
		"'{'", "'extends'", "'else'", "'}'", "'boolean'", "'true'", "'static'", 
		"'System.out.println'", "'.'", "')'", "'+'", "'return'", "'='", "';'", 
		"'&&'", "'this'", "'new'", "'=='", "'class'", "'!'", "IDENT", "INT", "STRING", 
		"COMMENT", "WHITESPACE"
	};
	public static final int
		RULE_program = 0, RULE_classDeclaration = 1, RULE_mainClass = 2, RULE_block = 3, 
		RULE_varDeclaration = 4, RULE_variable = 5, RULE_type = 6, RULE_typeBasic = 7, 
		RULE_typeArray = 8, RULE_typeBoolean = 9, RULE_typeInt = 10, RULE_typeClass = 11, 
		RULE_methodDeclaration = 12, RULE_procType = 13, RULE_typeVoid = 14, RULE_statement = 15, 
		RULE_statementIf = 16, RULE_statementWhile = 17, RULE_statementAssign = 18, 
		RULE_statementAssignArray = 19, RULE_statementPrint = 20, RULE_statementPrintln = 21, 
		RULE_statementMethod = 22, RULE_statementReturn = 23, RULE_expression = 24, 
		RULE_level1 = 25, RULE_level2 = 26, RULE_level3 = 27, RULE_level4 = 28, 
		RULE_level5 = 29, RULE_expressionNewObject = 30, RULE_expressionNewArray = 31, 
		RULE_expressionUnaryMinus = 32, RULE_expressionNegation = 33, RULE_expressionIntArray = 34, 
		RULE_expressionIdent = 35, RULE_expressionIdentifier = 36, RULE_expressionArrayAccess = 37, 
		RULE_expressionParentheses = 38, RULE_expressionConstantTrue = 39, RULE_expressionConstantFalse = 40, 
		RULE_expressionConstantInteger = 41, RULE_expressionConstantString = 42, 
		RULE_expressionMethodCall = 43, RULE_identifier = 44, RULE_id = 45, RULE_idThis = 46, 
		RULE_idIDENT = 47;
	public static final String[] ruleNames = {
		"program", "classDeclaration", "mainClass", "block", "varDeclaration", 
		"variable", "type", "typeBasic", "typeArray", "typeBoolean", "typeInt", 
		"typeClass", "methodDeclaration", "procType", "typeVoid", "statement", 
		"statementIf", "statementWhile", "statementAssign", "statementAssignArray", 
		"statementPrint", "statementPrintln", "statementMethod", "statementReturn", 
		"expression", "level1", "level2", "level3", "level4", "level5", "expressionNewObject", 
		"expressionNewArray", "expressionUnaryMinus", "expressionNegation", "expressionIntArray", 
		"expressionIdent", "expressionIdentifier", "expressionArrayAccess", "expressionParentheses", 
		"expressionConstantTrue", "expressionConstantFalse", "expressionConstantInteger", 
		"expressionConstantString", "expressionMethodCall", "identifier", "id", 
		"idThis", "idIDENT"
	};

	@Override
	public String getGrammarFileName() { return "MiniJava.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniJavaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public MainClassContext mainClass() {
			return getRuleContext(MainClassContext.class,0);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); mainClass();
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==33) {
				{
				{
				setState(97); classDeclaration();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public Token className;
		public Token superClassName;
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public List<TerminalNode> IDENT() { return getTokens(MiniJavaParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MiniJavaParser.IDENT, i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(33);
			setState(104); ((ClassDeclarationContext)_localctx).className = match(IDENT);
			setState(107);
			_la = _input.LA(1);
			if (_la==16) {
				{
				setState(105); match(16);
				setState(106); ((ClassDeclarationContext)_localctx).superClassName = match(IDENT);
				}
			}

			setState(109); match(15);
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(110); varDeclaration();
					}
					} 
				}
				setState(115);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 11) | (1L << 14) | (1L << 19) | (1L << 21) | (1L << IDENT))) != 0)) {
				{
				{
				setState(116); methodDeclaration();
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122); match(18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainClassContext extends ParserRuleContext {
		public Token className;
		public MethodDeclarationContext method;
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniJavaParser.IDENT, 0); }
		public MainClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterMainClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitMainClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitMainClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainClassContext mainClass() throws RecognitionException {
		MainClassContext _localctx = new MainClassContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_mainClass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); match(33);
			setState(125); ((MainClassContext)_localctx).className = match(IDENT);
			setState(126); match(15);
			setState(127); ((MainClassContext)_localctx).method = methodDeclaration();
			setState(128); match(18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(130); match(15);
			setState(134);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(131); varDeclaration();
					}
					} 
				}
				setState(136);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 5) | (1L << 10) | (1L << 15) | (1L << 22) | (1L << 30) | (1L << IDENT))) != 0)) {
				{
				{
				setState(137); statement();
				}
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(143); match(18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public VariableContext var;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145); ((VarDeclarationContext)_localctx).var = variable();
			setState(146); match(28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public Token variableName;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniJavaParser.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); type();
			setState(149); ((VariableContext)_localctx).variableName = match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeArrayContext typeArray() {
			return getRuleContext(TypeArrayContext.class,0);
		}
		public TypeBasicContext typeBasic() {
			return getRuleContext(TypeBasicContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		try {
			setState(153);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(151); typeBasic();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152); typeArray();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeBasicContext extends ParserRuleContext {
		public TypeBooleanContext typeBoolean() {
			return getRuleContext(TypeBooleanContext.class,0);
		}
		public TypeClassContext typeClass() {
			return getRuleContext(TypeClassContext.class,0);
		}
		public TypeIntContext typeInt() {
			return getRuleContext(TypeIntContext.class,0);
		}
		public TypeBasicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeBasic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterTypeBasic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitTypeBasic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitTypeBasic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeBasicContext typeBasic() throws RecognitionException {
		TypeBasicContext _localctx = new TypeBasicContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typeBasic);
		try {
			setState(158);
			switch (_input.LA(1)) {
			case 19:
				enterOuterAlt(_localctx, 1);
				{
				setState(155); typeBoolean();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 2);
				{
				setState(156); typeInt();
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(157); typeClass();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArrayContext extends ParserRuleContext {
		public TypeBasicContext typeBasic() {
			return getRuleContext(TypeBasicContext.class,0);
		}
		public TypeArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterTypeArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitTypeArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitTypeArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArrayContext typeArray() throws RecognitionException {
		TypeArrayContext _localctx = new TypeArrayContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); typeBasic();
			setState(161); match(6);
			setState(162); match(1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeBooleanContext extends ParserRuleContext {
		public TypeBooleanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeBoolean; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterTypeBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitTypeBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitTypeBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeBooleanContext typeBoolean() throws RecognitionException {
		TypeBooleanContext _localctx = new TypeBooleanContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeBoolean);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); match(19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeIntContext extends ParserRuleContext {
		public TypeIntContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeInt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterTypeInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitTypeInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitTypeInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeIntContext typeInt() throws RecognitionException {
		TypeIntContext _localctx = new TypeIntContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeInt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166); match(11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeClassContext extends ParserRuleContext {
		public Token className;
		public TerminalNode IDENT() { return getToken(MiniJavaParser.IDENT, 0); }
		public TypeClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterTypeClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitTypeClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitTypeClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeClassContext typeClass() throws RecognitionException {
		TypeClassContext _localctx = new TypeClassContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_typeClass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168); ((TypeClassContext)_localctx).className = match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public Token isPublic;
		public Token isStatic;
		public Token methodName;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public ProcTypeContext procType() {
			return getRuleContext(ProcTypeContext.class,0);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public StatementReturnContext statementReturn() {
			return getRuleContext(StatementReturnContext.class,0);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode IDENT() { return getToken(MiniJavaParser.IDENT, 0); }
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_methodDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(170); ((MethodDeclarationContext)_localctx).isPublic = match(3);
				}
			}

			setState(174);
			_la = _input.LA(1);
			if (_la==21) {
				{
				setState(173); ((MethodDeclarationContext)_localctx).isStatic = match(21);
				}
			}

			setState(176); procType();
			setState(177); ((MethodDeclarationContext)_localctx).methodName = match(IDENT);
			setState(178); match(9);
			setState(187);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 11) | (1L << 19) | (1L << IDENT))) != 0)) {
				{
				setState(179); variable();
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==4) {
					{
					{
					setState(180); match(4);
					setState(181); variable();
					}
					}
					setState(186);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(189); match(24);
			setState(190); match(15);
			setState(194);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(191); varDeclaration();
					}
					} 
				}
				setState(196);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 5) | (1L << 10) | (1L << 15) | (1L << 22) | (1L << 30) | (1L << IDENT))) != 0)) {
				{
				{
				setState(197); statement();
				}
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(203); statementReturn();
			setState(204); match(18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcTypeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeVoidContext typeVoid() {
			return getRuleContext(TypeVoidContext.class,0);
		}
		public ProcTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterProcType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitProcType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitProcType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcTypeContext procType() throws RecognitionException {
		ProcTypeContext _localctx = new ProcTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_procType);
		try {
			setState(208);
			switch (_input.LA(1)) {
			case 14:
				enterOuterAlt(_localctx, 1);
				{
				setState(206); typeVoid();
				}
				break;
			case 11:
			case 19:
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(207); type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeVoidContext extends ParserRuleContext {
		public TypeVoidContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeVoid; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterTypeVoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitTypeVoid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitTypeVoid(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeVoidContext typeVoid() throws RecognitionException {
		TypeVoidContext _localctx = new TypeVoidContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_typeVoid);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); match(14);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementPrintContext statementPrint() {
			return getRuleContext(StatementPrintContext.class,0);
		}
		public StatementMethodContext statementMethod() {
			return getRuleContext(StatementMethodContext.class,0);
		}
		public StatementPrintlnContext statementPrintln() {
			return getRuleContext(StatementPrintlnContext.class,0);
		}
		public StatementAssignArrayContext statementAssignArray() {
			return getRuleContext(StatementAssignArrayContext.class,0);
		}
		public StatementAssignContext statementAssign() {
			return getRuleContext(StatementAssignContext.class,0);
		}
		public StatementWhileContext statementWhile() {
			return getRuleContext(StatementWhileContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatementIfContext statementIf() {
			return getRuleContext(StatementIfContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statement);
		try {
			setState(220);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(213); statementIf();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(214); statementWhile();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(215); statementAssign();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(216); statementAssignArray();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(217); statementPrintln();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(218); statementPrint();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(219); statementMethod();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementIfContext extends ParserRuleContext {
		public ExpressionContext condition;
		public BlockContext ifBlock;
		public BlockContext elseBlock;
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public StatementIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatementIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatementIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatementIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementIfContext statementIf() throws RecognitionException {
		StatementIfContext _localctx = new StatementIfContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_statementIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222); match(10);
			setState(223); match(9);
			setState(224); ((StatementIfContext)_localctx).condition = expression();
			setState(225); match(24);
			setState(226); ((StatementIfContext)_localctx).ifBlock = block();
			setState(229);
			_la = _input.LA(1);
			if (_la==17) {
				{
				setState(227); match(17);
				setState(228); ((StatementIfContext)_localctx).elseBlock = block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementWhileContext extends ParserRuleContext {
		public ExpressionContext condition;
		public StatementContext whileBlock;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatementWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatementWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatementWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementWhileContext statementWhile() throws RecognitionException {
		StatementWhileContext _localctx = new StatementWhileContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_statementWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); match(5);
			setState(232); match(9);
			setState(233); ((StatementWhileContext)_localctx).condition = expression();
			setState(234); match(24);
			setState(235); ((StatementWhileContext)_localctx).whileBlock = statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementAssignContext extends ParserRuleContext {
		public IdContext lhs;
		public ExpressionContext rhs;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatementAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatementAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatementAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementAssignContext statementAssign() throws RecognitionException {
		StatementAssignContext _localctx = new StatementAssignContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_statementAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237); ((StatementAssignContext)_localctx).lhs = id();
			setState(238); match(27);
			setState(239); ((StatementAssignContext)_localctx).rhs = expression();
			setState(240); match(28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementAssignArrayContext extends ParserRuleContext {
		public IdentifierContext array;
		public ExpressionContext element;
		public ExpressionContext value;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public StatementAssignArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementAssignArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatementAssignArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatementAssignArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatementAssignArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementAssignArrayContext statementAssignArray() throws RecognitionException {
		StatementAssignArrayContext _localctx = new StatementAssignArrayContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_statementAssignArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242); ((StatementAssignArrayContext)_localctx).array = identifier();
			setState(243); match(6);
			setState(244); ((StatementAssignArrayContext)_localctx).element = expression();
			setState(245); match(1);
			setState(246); match(27);
			setState(247); ((StatementAssignArrayContext)_localctx).value = expression();
			setState(248); match(28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementPrintContext extends ParserRuleContext {
		public ExpressionContext argument;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementPrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementPrint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatementPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatementPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatementPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementPrintContext statementPrint() throws RecognitionException {
		StatementPrintContext _localctx = new StatementPrintContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_statementPrint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250); match(2);
			setState(251); match(9);
			setState(252); ((StatementPrintContext)_localctx).argument = expression();
			setState(253); match(24);
			setState(254); match(28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementPrintlnContext extends ParserRuleContext {
		public ExpressionContext argument;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementPrintlnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementPrintln; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatementPrintln(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatementPrintln(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatementPrintln(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementPrintlnContext statementPrintln() throws RecognitionException {
		StatementPrintlnContext _localctx = new StatementPrintlnContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_statementPrintln);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); match(22);
			setState(257); match(9);
			setState(258); ((StatementPrintlnContext)_localctx).argument = expression();
			setState(259); match(24);
			setState(260); match(28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementMethodContext extends ParserRuleContext {
		public IdentifierContext object;
		public Token method;
		public ExpressionContext expression;
		public List<ExpressionContext> arguments = new ArrayList<ExpressionContext>();
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode IDENT() { return getToken(MiniJavaParser.IDENT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public StatementMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementMethod; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatementMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatementMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatementMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementMethodContext statementMethod() throws RecognitionException {
		StatementMethodContext _localctx = new StatementMethodContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_statementMethod);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(262); ((StatementMethodContext)_localctx).object = identifier();
				setState(263); match(23);
				}
				break;
			}
			setState(267); ((StatementMethodContext)_localctx).method = match(IDENT);
			setState(268); match(9);
			setState(274);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 9) | (1L << 13) | (1L << 20) | (1L << 30) | (1L << 31) | (1L << 34) | (1L << IDENT) | (1L << INT) | (1L << STRING))) != 0)) {
				{
				setState(269); ((StatementMethodContext)_localctx).expression = expression();
				((StatementMethodContext)_localctx).arguments.add(((StatementMethodContext)_localctx).expression);
				setState(272);
				_la = _input.LA(1);
				if (_la==4) {
					{
					setState(270); match(4);
					setState(271); ((StatementMethodContext)_localctx).expression = expression();
					((StatementMethodContext)_localctx).arguments.add(((StatementMethodContext)_localctx).expression);
					}
				}

				}
			}

			setState(276); match(24);
			setState(277); match(28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementReturnContext extends ParserRuleContext {
		public ExpressionContext argument;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementReturn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterStatementReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitStatementReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitStatementReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementReturnContext statementReturn() throws RecognitionException {
		StatementReturnContext _localctx = new StatementReturnContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_statementReturn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279); match(26);
			setState(284);
			_la = _input.LA(1);
			if (_la==9) {
				{
				setState(280); match(9);
				setState(281); ((StatementReturnContext)_localctx).argument = expression();
				setState(282); match(24);
				}
			}

			setState(286); match(28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Level1Context head;
		public Level1Context level1;
		public List<Level1Context> tail = new ArrayList<Level1Context>();
		public List<Level1Context> level1() {
			return getRuleContexts(Level1Context.class);
		}
		public Level1Context level1(int i) {
			return getRuleContext(Level1Context.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288); ((ExpressionContext)_localctx).head = level1();
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==29) {
				{
				{
				setState(289); match(29);
				setState(290); ((ExpressionContext)_localctx).level1 = level1();
				((ExpressionContext)_localctx).tail.add(((ExpressionContext)_localctx).level1);
				}
				}
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Level1Context extends ParserRuleContext {
		public Level2Context head;
		public Level2Context level2;
		public List<Level2Context> tail = new ArrayList<Level2Context>();
		public Level2Context level2(int i) {
			return getRuleContext(Level2Context.class,i);
		}
		public List<Level2Context> level2() {
			return getRuleContexts(Level2Context.class);
		}
		public Level1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterLevel1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitLevel1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitLevel1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Level1Context level1() throws RecognitionException {
		Level1Context _localctx = new Level1Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_level1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296); ((Level1Context)_localctx).head = level2();
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==32) {
				{
				{
				setState(297); match(32);
				setState(298); ((Level1Context)_localctx).level2 = level2();
				((Level1Context)_localctx).tail.add(((Level1Context)_localctx).level2);
				}
				}
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Level2Context extends ParserRuleContext {
		public Level3Context head;
		public Level3Context level3;
		public List<Level3Context> tail = new ArrayList<Level3Context>();
		public Level3Context level3(int i) {
			return getRuleContext(Level3Context.class,i);
		}
		public List<Level3Context> level3() {
			return getRuleContexts(Level3Context.class);
		}
		public Level2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterLevel2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitLevel2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitLevel2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Level2Context level2() throws RecognitionException {
		Level2Context _localctx = new Level2Context(_ctx, getState());
		enterRule(_localctx, 52, RULE_level2);
		int _la;
		try {
			setState(310);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(304); ((Level2Context)_localctx).head = level3();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(305); ((Level2Context)_localctx).head = level3();
				setState(308);
				_la = _input.LA(1);
				if (_la==12) {
					{
					setState(306); match(12);
					setState(307); ((Level2Context)_localctx).level3 = level3();
					((Level2Context)_localctx).tail.add(((Level2Context)_localctx).level3);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Level3Context extends ParserRuleContext {
		public Level4Context head;
		public Token operator;
		public Level4Context level4;
		public List<Level4Context> tail = new ArrayList<Level4Context>();
		public Level4Context level4(int i) {
			return getRuleContext(Level4Context.class,i);
		}
		public List<Level4Context> level4() {
			return getRuleContexts(Level4Context.class);
		}
		public Level3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterLevel3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitLevel3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitLevel3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Level3Context level3() throws RecognitionException {
		Level3Context _localctx = new Level3Context(_ctx, getState());
		enterRule(_localctx, 54, RULE_level3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312); ((Level3Context)_localctx).head = level4();
			setState(317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==7 || _la==25) {
				{
				{
				setState(313);
				((Level3Context)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==7 || _la==25) ) {
					((Level3Context)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(314); ((Level3Context)_localctx).level4 = level4();
				((Level3Context)_localctx).tail.add(((Level3Context)_localctx).level4);
				}
				}
				setState(319);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Level4Context extends ParserRuleContext {
		public Level5Context head;
		public Level5Context level5;
		public List<Level5Context> tail = new ArrayList<Level5Context>();
		public Level5Context level5(int i) {
			return getRuleContext(Level5Context.class,i);
		}
		public List<Level5Context> level5() {
			return getRuleContexts(Level5Context.class);
		}
		public Level4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterLevel4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitLevel4(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitLevel4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Level4Context level4() throws RecognitionException {
		Level4Context _localctx = new Level4Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_level4);
		int _la;
		try {
			setState(326);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(320); ((Level4Context)_localctx).head = level5();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(321); ((Level4Context)_localctx).head = level5();
				setState(324);
				_la = _input.LA(1);
				if (_la==8) {
					{
					setState(322); match(8);
					setState(323); ((Level4Context)_localctx).level5 = level5();
					((Level4Context)_localctx).tail.add(((Level4Context)_localctx).level5);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Level5Context extends ParserRuleContext {
		public ExpressionConstantTrueContext expressionConstantTrue() {
			return getRuleContext(ExpressionConstantTrueContext.class,0);
		}
		public ExpressionConstantFalseContext expressionConstantFalse() {
			return getRuleContext(ExpressionConstantFalseContext.class,0);
		}
		public ExpressionNegationContext expressionNegation() {
			return getRuleContext(ExpressionNegationContext.class,0);
		}
		public ExpressionUnaryMinusContext expressionUnaryMinus() {
			return getRuleContext(ExpressionUnaryMinusContext.class,0);
		}
		public ExpressionMethodCallContext expressionMethodCall() {
			return getRuleContext(ExpressionMethodCallContext.class,0);
		}
		public ExpressionConstantStringContext expressionConstantString() {
			return getRuleContext(ExpressionConstantStringContext.class,0);
		}
		public ExpressionArrayAccessContext expressionArrayAccess() {
			return getRuleContext(ExpressionArrayAccessContext.class,0);
		}
		public ExpressionNewObjectContext expressionNewObject() {
			return getRuleContext(ExpressionNewObjectContext.class,0);
		}
		public ExpressionConstantIntegerContext expressionConstantInteger() {
			return getRuleContext(ExpressionConstantIntegerContext.class,0);
		}
		public ExpressionNewArrayContext expressionNewArray() {
			return getRuleContext(ExpressionNewArrayContext.class,0);
		}
		public ExpressionParenthesesContext expressionParentheses() {
			return getRuleContext(ExpressionParenthesesContext.class,0);
		}
		public ExpressionIdentifierContext expressionIdentifier() {
			return getRuleContext(ExpressionIdentifierContext.class,0);
		}
		public Level5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level5; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterLevel5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitLevel5(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitLevel5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Level5Context level5() throws RecognitionException {
		Level5Context _localctx = new Level5Context(_ctx, getState());
		enterRule(_localctx, 58, RULE_level5);
		try {
			setState(340);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(328); expressionUnaryMinus();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(329); expressionNegation();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(330); expressionNewObject();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(331); expressionNewArray();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(332); expressionIdentifier();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(333); expressionArrayAccess();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(334); expressionMethodCall();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(335); expressionParentheses();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(336); expressionConstantTrue();
				}
				break;

			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(337); expressionConstantFalse();
				}
				break;

			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(338); expressionConstantInteger();
				}
				break;

			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(339); expressionConstantString();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionNewObjectContext extends ParserRuleContext {
		public ExpressionIdentContext expressionIdent() {
			return getRuleContext(ExpressionIdentContext.class,0);
		}
		public ExpressionNewObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionNewObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionNewObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionNewObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionNewObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionNewObjectContext expressionNewObject() throws RecognitionException {
		ExpressionNewObjectContext _localctx = new ExpressionNewObjectContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expressionNewObject);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342); match(31);
			setState(343); expressionIdent();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionNewArrayContext extends ParserRuleContext {
		public ExpressionIntArrayContext expressionIntArray() {
			return getRuleContext(ExpressionIntArrayContext.class,0);
		}
		public ExpressionNewArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionNewArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionNewArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionNewArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionNewArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionNewArrayContext expressionNewArray() throws RecognitionException {
		ExpressionNewArrayContext _localctx = new ExpressionNewArrayContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_expressionNewArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); match(31);
			setState(346); expressionIntArray();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionUnaryMinusContext extends ParserRuleContext {
		public Level5Context argument;
		public Level5Context level5() {
			return getRuleContext(Level5Context.class,0);
		}
		public ExpressionUnaryMinusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionUnaryMinus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionUnaryMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionUnaryMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionUnaryMinus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionUnaryMinusContext expressionUnaryMinus() throws RecognitionException {
		ExpressionUnaryMinusContext _localctx = new ExpressionUnaryMinusContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expressionUnaryMinus);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348); match(7);
			setState(349); ((ExpressionUnaryMinusContext)_localctx).argument = level5();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionNegationContext extends ParserRuleContext {
		public Level5Context argument;
		public Level5Context level5() {
			return getRuleContext(Level5Context.class,0);
		}
		public ExpressionNegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionNegation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionNegation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionNegationContext expressionNegation() throws RecognitionException {
		ExpressionNegationContext _localctx = new ExpressionNegationContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_expressionNegation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351); match(34);
			setState(352); ((ExpressionNegationContext)_localctx).argument = level5();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionIntArrayContext extends ParserRuleContext {
		public ExpressionContext size;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionIntArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionIntArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionIntArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionIntArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionIntArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionIntArrayContext expressionIntArray() throws RecognitionException {
		ExpressionIntArrayContext _localctx = new ExpressionIntArrayContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_expressionIntArray);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354); match(11);
			setState(355); match(6);
			setState(356); ((ExpressionIntArrayContext)_localctx).size = expression();
			setState(357); match(1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionIdentContext extends ParserRuleContext {
		public Token classname;
		public TerminalNode IDENT() { return getToken(MiniJavaParser.IDENT, 0); }
		public ExpressionIdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionIdent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionIdentContext expressionIdent() throws RecognitionException {
		ExpressionIdentContext _localctx = new ExpressionIdentContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_expressionIdent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359); ((ExpressionIdentContext)_localctx).classname = match(IDENT);
			setState(360); match(9);
			setState(361); match(24);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionIdentifierContext extends ParserRuleContext {
		public IdContext ident;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExpressionIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionIdentifierContext expressionIdentifier() throws RecognitionException {
		ExpressionIdentifierContext _localctx = new ExpressionIdentifierContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_expressionIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363); ((ExpressionIdentifierContext)_localctx).ident = id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionArrayAccessContext extends ParserRuleContext {
		public IdentifierContext ident;
		public ExpressionContext element;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExpressionArrayAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionArrayAccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionArrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionArrayAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionArrayAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionArrayAccessContext expressionArrayAccess() throws RecognitionException {
		ExpressionArrayAccessContext _localctx = new ExpressionArrayAccessContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_expressionArrayAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365); ((ExpressionArrayAccessContext)_localctx).ident = identifier();
			setState(366); match(6);
			setState(367); ((ExpressionArrayAccessContext)_localctx).element = expression();
			setState(368); match(1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionParenthesesContext extends ParserRuleContext {
		public ExpressionContext argument;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionParenthesesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionParentheses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionParentheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionParentheses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionParenthesesContext expressionParentheses() throws RecognitionException {
		ExpressionParenthesesContext _localctx = new ExpressionParenthesesContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_expressionParentheses);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370); match(9);
			setState(371); ((ExpressionParenthesesContext)_localctx).argument = expression();
			setState(372); match(24);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionConstantTrueContext extends ParserRuleContext {
		public ExpressionConstantTrueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionConstantTrue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionConstantTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionConstantTrue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionConstantTrue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionConstantTrueContext expressionConstantTrue() throws RecognitionException {
		ExpressionConstantTrueContext _localctx = new ExpressionConstantTrueContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_expressionConstantTrue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374); match(20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionConstantFalseContext extends ParserRuleContext {
		public ExpressionConstantFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionConstantFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionConstantFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionConstantFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionConstantFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionConstantFalseContext expressionConstantFalse() throws RecognitionException {
		ExpressionConstantFalseContext _localctx = new ExpressionConstantFalseContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_expressionConstantFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376); match(13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionConstantIntegerContext extends ParserRuleContext {
		public Token value;
		public TerminalNode INT() { return getToken(MiniJavaParser.INT, 0); }
		public ExpressionConstantIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionConstantInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionConstantInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionConstantInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionConstantInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionConstantIntegerContext expressionConstantInteger() throws RecognitionException {
		ExpressionConstantIntegerContext _localctx = new ExpressionConstantIntegerContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_expressionConstantInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378); ((ExpressionConstantIntegerContext)_localctx).value = match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionConstantStringContext extends ParserRuleContext {
		public Token value;
		public TerminalNode STRING() { return getToken(MiniJavaParser.STRING, 0); }
		public ExpressionConstantStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionConstantString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionConstantString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionConstantString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionConstantString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionConstantStringContext expressionConstantString() throws RecognitionException {
		ExpressionConstantStringContext _localctx = new ExpressionConstantStringContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_expressionConstantString);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380); ((ExpressionConstantStringContext)_localctx).value = match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionMethodCallContext extends ParserRuleContext {
		public IdentifierContext object;
		public Token method;
		public ExpressionContext expression;
		public List<ExpressionContext> arguments = new ArrayList<ExpressionContext>();
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode IDENT() { return getToken(MiniJavaParser.IDENT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExpressionMethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionMethodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterExpressionMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitExpressionMethodCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitExpressionMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionMethodCallContext expressionMethodCall() throws RecognitionException {
		ExpressionMethodCallContext _localctx = new ExpressionMethodCallContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_expressionMethodCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(382); ((ExpressionMethodCallContext)_localctx).object = identifier();
				setState(383); match(23);
				}
				break;
			}
			setState(387); ((ExpressionMethodCallContext)_localctx).method = match(IDENT);
			setState(388); match(9);
			setState(394);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 9) | (1L << 13) | (1L << 20) | (1L << 30) | (1L << 31) | (1L << 34) | (1L << IDENT) | (1L << INT) | (1L << STRING))) != 0)) {
				{
				setState(389); ((ExpressionMethodCallContext)_localctx).expression = expression();
				((ExpressionMethodCallContext)_localctx).arguments.add(((ExpressionMethodCallContext)_localctx).expression);
				setState(392);
				_la = _input.LA(1);
				if (_la==4) {
					{
					setState(390); match(4);
					setState(391); ((ExpressionMethodCallContext)_localctx).expression = expression();
					((ExpressionMethodCallContext)_localctx).arguments.add(((ExpressionMethodCallContext)_localctx).expression);
					}
				}

				}
			}

			setState(396); match(24);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public Token IDENT;
		public List<Token> selectors = new ArrayList<Token>();
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<TerminalNode> IDENT() { return getTokens(MiniJavaParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MiniJavaParser.IDENT, i);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_identifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(398); id();
			setState(403);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(399); match(23);
					setState(400); ((IdentifierContext)_localctx).IDENT = match(IDENT);
					((IdentifierContext)_localctx).selectors.add(((IdentifierContext)_localctx).IDENT);
					}
					} 
				}
				setState(405);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public IdThisContext idThis() {
			return getRuleContext(IdThisContext.class,0);
		}
		public IdIDENTContext idIDENT() {
			return getRuleContext(IdIDENTContext.class,0);
		}
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_id);
		try {
			setState(408);
			switch (_input.LA(1)) {
			case 30:
				enterOuterAlt(_localctx, 1);
				{
				setState(406); idThis();
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(407); idIDENT();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdThisContext extends ParserRuleContext {
		public IdThisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idThis; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterIdThis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitIdThis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitIdThis(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdThisContext idThis() throws RecognitionException {
		IdThisContext _localctx = new IdThisContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_idThis);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410); match(30);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdIDENTContext extends ParserRuleContext {
		public Token name;
		public TerminalNode IDENT() { return getToken(MiniJavaParser.IDENT, 0); }
		public IdIDENTContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idIDENT; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).enterIdIDENT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaListener ) ((MiniJavaListener)listener).exitIdIDENT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniJavaVisitor ) return ((MiniJavaVisitor<? extends T>)visitor).visitIdIDENT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdIDENTContext idIDENT() throws RecognitionException {
		IdIDENTContext _localctx = new IdIDENTContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_idIDENT);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412); ((IdIDENTContext)_localctx).name = match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3)\u01a1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\7\2e\n\2\f\2\16\2h"+
		"\13\2\3\3\3\3\3\3\3\3\5\3n\n\3\3\3\3\3\7\3r\n\3\f\3\16\3u\13\3\3\3\7\3"+
		"x\n\3\f\3\16\3{\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\7\5\u0087"+
		"\n\5\f\5\16\5\u008a\13\5\3\5\7\5\u008d\n\5\f\5\16\5\u0090\13\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\5\b\u009c\n\b\3\t\3\t\3\t\5\t\u00a1\n"+
		"\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\5\16\u00ae\n\16\3\16"+
		"\5\16\u00b1\n\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00b9\n\16\f\16\16"+
		"\16\u00bc\13\16\5\16\u00be\n\16\3\16\3\16\3\16\7\16\u00c3\n\16\f\16\16"+
		"\16\u00c6\13\16\3\16\7\16\u00c9\n\16\f\16\16\16\u00cc\13\16\3\16\3\16"+
		"\3\16\3\17\3\17\5\17\u00d3\n\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u00df\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00e8"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\5\30\u010c\n\30\3\30\3\30\3\30\3\30"+
		"\3\30\5\30\u0113\n\30\5\30\u0115\n\30\3\30\3\30\3\30\3\31\3\31\3\31\3"+
		"\31\3\31\5\31\u011f\n\31\3\31\3\31\3\32\3\32\3\32\7\32\u0126\n\32\f\32"+
		"\16\32\u0129\13\32\3\33\3\33\3\33\7\33\u012e\n\33\f\33\16\33\u0131\13"+
		"\33\3\34\3\34\3\34\3\34\5\34\u0137\n\34\5\34\u0139\n\34\3\35\3\35\3\35"+
		"\7\35\u013e\n\35\f\35\16\35\u0141\13\35\3\36\3\36\3\36\3\36\5\36\u0147"+
		"\n\36\5\36\u0149\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\5\37\u0157\n\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3"+
		"$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3*"+
		"\3*\3+\3+\3,\3,\3-\3-\3-\5-\u0184\n-\3-\3-\3-\3-\3-\5-\u018b\n-\5-\u018d"+
		"\n-\3-\3-\3.\3.\3.\7.\u0194\n.\f.\16.\u0197\13.\3/\3/\5/\u019b\n/\3\60"+
		"\3\60\3\61\3\61\3\61\2\62\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&"+
		"(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`\2\3\4\2\t\t\33\33\u01a3\2b\3\2"+
		"\2\2\4i\3\2\2\2\6~\3\2\2\2\b\u0084\3\2\2\2\n\u0093\3\2\2\2\f\u0096\3\2"+
		"\2\2\16\u009b\3\2\2\2\20\u00a0\3\2\2\2\22\u00a2\3\2\2\2\24\u00a6\3\2\2"+
		"\2\26\u00a8\3\2\2\2\30\u00aa\3\2\2\2\32\u00ad\3\2\2\2\34\u00d2\3\2\2\2"+
		"\36\u00d4\3\2\2\2 \u00de\3\2\2\2\"\u00e0\3\2\2\2$\u00e9\3\2\2\2&\u00ef"+
		"\3\2\2\2(\u00f4\3\2\2\2*\u00fc\3\2\2\2,\u0102\3\2\2\2.\u010b\3\2\2\2\60"+
		"\u0119\3\2\2\2\62\u0122\3\2\2\2\64\u012a\3\2\2\2\66\u0138\3\2\2\28\u013a"+
		"\3\2\2\2:\u0148\3\2\2\2<\u0156\3\2\2\2>\u0158\3\2\2\2@\u015b\3\2\2\2B"+
		"\u015e\3\2\2\2D\u0161\3\2\2\2F\u0164\3\2\2\2H\u0169\3\2\2\2J\u016d\3\2"+
		"\2\2L\u016f\3\2\2\2N\u0174\3\2\2\2P\u0178\3\2\2\2R\u017a\3\2\2\2T\u017c"+
		"\3\2\2\2V\u017e\3\2\2\2X\u0183\3\2\2\2Z\u0190\3\2\2\2\\\u019a\3\2\2\2"+
		"^\u019c\3\2\2\2`\u019e\3\2\2\2bf\5\6\4\2ce\5\4\3\2dc\3\2\2\2eh\3\2\2\2"+
		"fd\3\2\2\2fg\3\2\2\2g\3\3\2\2\2hf\3\2\2\2ij\7#\2\2jm\7%\2\2kl\7\22\2\2"+
		"ln\7%\2\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2os\7\21\2\2pr\5\n\6\2qp\3\2\2\2"+
		"ru\3\2\2\2sq\3\2\2\2st\3\2\2\2ty\3\2\2\2us\3\2\2\2vx\5\32\16\2wv\3\2\2"+
		"\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|}\7\24\2\2}\5\3\2"+
		"\2\2~\177\7#\2\2\177\u0080\7%\2\2\u0080\u0081\7\21\2\2\u0081\u0082\5\32"+
		"\16\2\u0082\u0083\7\24\2\2\u0083\7\3\2\2\2\u0084\u0088\7\21\2\2\u0085"+
		"\u0087\5\n\6\2\u0086\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089\u008e\3\2\2\2\u008a\u0088\3\2\2\2\u008b"+
		"\u008d\5 \21\2\u008c\u008b\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2"+
		"\2\2\u008e\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091"+
		"\u0092\7\24\2\2\u0092\t\3\2\2\2\u0093\u0094\5\f\7\2\u0094\u0095\7\36\2"+
		"\2\u0095\13\3\2\2\2\u0096\u0097\5\16\b\2\u0097\u0098\7%\2\2\u0098\r\3"+
		"\2\2\2\u0099\u009c\5\20\t\2\u009a\u009c\5\22\n\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009a\3\2\2\2\u009c\17\3\2\2\2\u009d\u00a1\5\24\13\2\u009e\u00a1\5\26"+
		"\f\2\u009f\u00a1\5\30\r\2\u00a0\u009d\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0"+
		"\u009f\3\2\2\2\u00a1\21\3\2\2\2\u00a2\u00a3\5\20\t\2\u00a3\u00a4\7\b\2"+
		"\2\u00a4\u00a5\7\3\2\2\u00a5\23\3\2\2\2\u00a6\u00a7\7\25\2\2\u00a7\25"+
		"\3\2\2\2\u00a8\u00a9\7\r\2\2\u00a9\27\3\2\2\2\u00aa\u00ab\7%\2\2\u00ab"+
		"\31\3\2\2\2\u00ac\u00ae\7\5\2\2\u00ad\u00ac\3\2\2\2\u00ad\u00ae\3\2\2"+
		"\2\u00ae\u00b0\3\2\2\2\u00af\u00b1\7\27\2\2\u00b0\u00af\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\5\34\17\2\u00b3\u00b4\7"+
		"%\2\2\u00b4\u00bd\7\13\2\2\u00b5\u00ba\5\f\7\2\u00b6\u00b7\7\6\2\2\u00b7"+
		"\u00b9\5\f\7\2\u00b8\u00b6\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2"+
		"\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd"+
		"\u00b5\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\7\32"+
		"\2\2\u00c0\u00c4\7\21\2\2\u00c1\u00c3\5\n\6\2\u00c2\u00c1\3\2\2\2\u00c3"+
		"\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00ca\3\2"+
		"\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c9\5 \21\2\u00c8\u00c7\3\2\2\2\u00c9"+
		"\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cd\3\2"+
		"\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00ce\5\60\31\2\u00ce\u00cf\7\24\2\2\u00cf"+
		"\33\3\2\2\2\u00d0\u00d3\5\36\20\2\u00d1\u00d3\5\16\b\2\u00d2\u00d0\3\2"+
		"\2\2\u00d2\u00d1\3\2\2\2\u00d3\35\3\2\2\2\u00d4\u00d5\7\20\2\2\u00d5\37"+
		"\3\2\2\2\u00d6\u00df\5\b\5\2\u00d7\u00df\5\"\22\2\u00d8\u00df\5$\23\2"+
		"\u00d9\u00df\5&\24\2\u00da\u00df\5(\25\2\u00db\u00df\5,\27\2\u00dc\u00df"+
		"\5*\26\2\u00dd\u00df\5.\30\2\u00de\u00d6\3\2\2\2\u00de\u00d7\3\2\2\2\u00de"+
		"\u00d8\3\2\2\2\u00de\u00d9\3\2\2\2\u00de\u00da\3\2\2\2\u00de\u00db\3\2"+
		"\2\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df!\3\2\2\2\u00e0\u00e1"+
		"\7\f\2\2\u00e1\u00e2\7\13\2\2\u00e2\u00e3\5\62\32\2\u00e3\u00e4\7\32\2"+
		"\2\u00e4\u00e7\5\b\5\2\u00e5\u00e6\7\23\2\2\u00e6\u00e8\5\b\5\2\u00e7"+
		"\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8#\3\2\2\2\u00e9\u00ea\7\7\2\2"+
		"\u00ea\u00eb\7\13\2\2\u00eb\u00ec\5\62\32\2\u00ec\u00ed\7\32\2\2\u00ed"+
		"\u00ee\5 \21\2\u00ee%\3\2\2\2\u00ef\u00f0\5\\/\2\u00f0\u00f1\7\35\2\2"+
		"\u00f1\u00f2\5\62\32\2\u00f2\u00f3\7\36\2\2\u00f3\'\3\2\2\2\u00f4\u00f5"+
		"\5Z.\2\u00f5\u00f6\7\b\2\2\u00f6\u00f7\5\62\32\2\u00f7\u00f8\7\3\2\2\u00f8"+
		"\u00f9\7\35\2\2\u00f9\u00fa\5\62\32\2\u00fa\u00fb\7\36\2\2\u00fb)\3\2"+
		"\2\2\u00fc\u00fd\7\4\2\2\u00fd\u00fe\7\13\2\2\u00fe\u00ff\5\62\32\2\u00ff"+
		"\u0100\7\32\2\2\u0100\u0101\7\36\2\2\u0101+\3\2\2\2\u0102\u0103\7\30\2"+
		"\2\u0103\u0104\7\13\2\2\u0104\u0105\5\62\32\2\u0105\u0106\7\32\2\2\u0106"+
		"\u0107\7\36\2\2\u0107-\3\2\2\2\u0108\u0109\5Z.\2\u0109\u010a\7\31\2\2"+
		"\u010a\u010c\3\2\2\2\u010b\u0108\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d"+
		"\3\2\2\2\u010d\u010e\7%\2\2\u010e\u0114\7\13\2\2\u010f\u0112\5\62\32\2"+
		"\u0110\u0111\7\6\2\2\u0111\u0113\5\62\32\2\u0112\u0110\3\2\2\2\u0112\u0113"+
		"\3\2\2\2\u0113\u0115\3\2\2\2\u0114\u010f\3\2\2\2\u0114\u0115\3\2\2\2\u0115"+
		"\u0116\3\2\2\2\u0116\u0117\7\32\2\2\u0117\u0118\7\36\2\2\u0118/\3\2\2"+
		"\2\u0119\u011e\7\34\2\2\u011a\u011b\7\13\2\2\u011b\u011c\5\62\32\2\u011c"+
		"\u011d\7\32\2\2\u011d\u011f\3\2\2\2\u011e\u011a\3\2\2\2\u011e\u011f\3"+
		"\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\7\36\2\2\u0121\61\3\2\2\2\u0122"+
		"\u0127\5\64\33\2\u0123\u0124\7\37\2\2\u0124\u0126\5\64\33\2\u0125\u0123"+
		"\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128"+
		"\63\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u012f\5\66\34\2\u012b\u012c\7\""+
		"\2\2\u012c\u012e\5\66\34\2\u012d\u012b\3\2\2\2\u012e\u0131\3\2\2\2\u012f"+
		"\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\65\3\2\2\2\u0131\u012f\3\2\2"+
		"\2\u0132\u0139\58\35\2\u0133\u0136\58\35\2\u0134\u0135\7\16\2\2\u0135"+
		"\u0137\58\35\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0139\3\2"+
		"\2\2\u0138\u0132\3\2\2\2\u0138\u0133\3\2\2\2\u0139\67\3\2\2\2\u013a\u013f"+
		"\5:\36\2\u013b\u013c\t\2\2\2\u013c\u013e\5:\36\2\u013d\u013b\3\2\2\2\u013e"+
		"\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u01409\3\2\2\2"+
		"\u0141\u013f\3\2\2\2\u0142\u0149\5<\37\2\u0143\u0146\5<\37\2\u0144\u0145"+
		"\7\n\2\2\u0145\u0147\5<\37\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147"+
		"\u0149\3\2\2\2\u0148\u0142\3\2\2\2\u0148\u0143\3\2\2\2\u0149;\3\2\2\2"+
		"\u014a\u0157\5B\"\2\u014b\u0157\5D#\2\u014c\u0157\5> \2\u014d\u0157\5"+
		"@!\2\u014e\u0157\5J&\2\u014f\u0157\5L\'\2\u0150\u0157\5X-\2\u0151\u0157"+
		"\5N(\2\u0152\u0157\5P)\2\u0153\u0157\5R*\2\u0154\u0157\5T+\2\u0155\u0157"+
		"\5V,\2\u0156\u014a\3\2\2\2\u0156\u014b\3\2\2\2\u0156\u014c\3\2\2\2\u0156"+
		"\u014d\3\2\2\2\u0156\u014e\3\2\2\2\u0156\u014f\3\2\2\2\u0156\u0150\3\2"+
		"\2\2\u0156\u0151\3\2\2\2\u0156\u0152\3\2\2\2\u0156\u0153\3\2\2\2\u0156"+
		"\u0154\3\2\2\2\u0156\u0155\3\2\2\2\u0157=\3\2\2\2\u0158\u0159\7!\2\2\u0159"+
		"\u015a\5H%\2\u015a?\3\2\2\2\u015b\u015c\7!\2\2\u015c\u015d\5F$\2\u015d"+
		"A\3\2\2\2\u015e\u015f\7\t\2\2\u015f\u0160\5<\37\2\u0160C\3\2\2\2\u0161"+
		"\u0162\7$\2\2\u0162\u0163\5<\37\2\u0163E\3\2\2\2\u0164\u0165\7\r\2\2\u0165"+
		"\u0166\7\b\2\2\u0166\u0167\5\62\32\2\u0167\u0168\7\3\2\2\u0168G\3\2\2"+
		"\2\u0169\u016a\7%\2\2\u016a\u016b\7\13\2\2\u016b\u016c\7\32\2\2\u016c"+
		"I\3\2\2\2\u016d\u016e\5\\/\2\u016eK\3\2\2\2\u016f\u0170\5Z.\2\u0170\u0171"+
		"\7\b\2\2\u0171\u0172\5\62\32\2\u0172\u0173\7\3\2\2\u0173M\3\2\2\2\u0174"+
		"\u0175\7\13\2\2\u0175\u0176\5\62\32\2\u0176\u0177\7\32\2\2\u0177O\3\2"+
		"\2\2\u0178\u0179\7\26\2\2\u0179Q\3\2\2\2\u017a\u017b\7\17\2\2\u017bS\3"+
		"\2\2\2\u017c\u017d\7&\2\2\u017dU\3\2\2\2\u017e\u017f\7\'\2\2\u017fW\3"+
		"\2\2\2\u0180\u0181\5Z.\2\u0181\u0182\7\31\2\2\u0182\u0184\3\2\2\2\u0183"+
		"\u0180\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0186\7%"+
		"\2\2\u0186\u018c\7\13\2\2\u0187\u018a\5\62\32\2\u0188\u0189\7\6\2\2\u0189"+
		"\u018b\5\62\32\2\u018a\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018d\3"+
		"\2\2\2\u018c\u0187\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018e\3\2\2\2\u018e"+
		"\u018f\7\32\2\2\u018fY\3\2\2\2\u0190\u0195\5\\/\2\u0191\u0192\7\31\2\2"+
		"\u0192\u0194\7%\2\2\u0193\u0191\3\2\2\2\u0194\u0197\3\2\2\2\u0195\u0193"+
		"\3\2\2\2\u0195\u0196\3\2\2\2\u0196[\3\2\2\2\u0197\u0195\3\2\2\2\u0198"+
		"\u019b\5^\60\2\u0199\u019b\5`\61\2\u019a\u0198\3\2\2\2\u019a\u0199\3\2"+
		"\2\2\u019b]\3\2\2\2\u019c\u019d\7 \2\2\u019d_\3\2\2\2\u019e\u019f\7%\2"+
		"\2\u019fa\3\2\2\2$fmsy\u0088\u008e\u009b\u00a0\u00ad\u00b0\u00ba\u00bd"+
		"\u00c4\u00ca\u00d2\u00de\u00e7\u010b\u0112\u0114\u011e\u0127\u012f\u0136"+
		"\u0138\u013f\u0146\u0148\u0156\u0183\u018a\u018c\u0195\u019a";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}