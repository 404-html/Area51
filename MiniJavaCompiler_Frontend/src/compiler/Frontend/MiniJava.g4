grammar MiniJava;

program
  : mainClass ( classDeclaration )*
  ;

classDeclaration
  : 'class' className=IDENT ( 'extends' superClassName=IDENT )? 
    '{' ( varDeclaration 
        )* 
        ( methodDeclaration 
        )*
    '}'
  ;

mainClass
  : 'class' className=IDENT 
    '{' 
      'public' 'static' 'void' 'main' '(' 'String' '[' ']' parameterName=IDENT ')' 
      block
    '}'
  ;
  
block
  : '{' ( varDeclaration )* 
        ( statement )*
    '}'
  ;
    
varDeclaration
  : variable  ';'
  ;

variable : type variableName=IDENT
  ;

type
  : typeBoolean
  | typeInt
  | typeClass
  | arraytype
  ;

arraytype   : arrayType = simpleType'[' ']';
typeBoolean : 'boolean' ;
typeInt     : 'int' ;
typeClass   : className=IDENT;

simpleType
: 'boolean'
| 'int'
| className=IDENT
;
methodDeclaration
  : ( isPublic='public'  )?
    ( isStatic='static'  )? 
    procType methodName=IDENT 
    '(' 
      ( variable ( ',' variable )* )? 
    ')' 
    '{' ( varDeclaration )* 
        ( statement )*
        statementReturn
    '}'
  ;
  
procType
  : typeVoid  
  | type 
  ;

typeVoid: 'void' ;
  
statement
  : block
  | statementIf
  | statementWhile
  | statementAssign
  | statementArrayAssign
  | statementPrintln
  | statementPrint
  | statementMethodCall
  ;


statementIf      : 'if' '('expr=expression ')' ifblock=block ('else' elseblock=block)?;
statementWhile   : 'while' '(' expr=expression ')' stat=statement;
statementAssign  : lhs=id '=' rhs=expression ';' ;
statementArrayAssign : lhs=id '[' size=expression ']' '=' rhs=expression ';'; 
statementPrintln : 'System.out.println' '(' argument=expression ')' ';' ;
statementPrint   : 'System.out.print' '(' argument=expression ')' ';' ;
statementMethodCall : (identifier '.')? methodId=IDENT '(' (head=expression ( ',' tail+=expression)*)? ')' ';';
statementReturn  : 'return' ('(' argument=expression ')' )? ';' ;

expression
  : head=level1 ( '&&' tail+=level1 )*
  ;

level1
  : head=level2 ( '==' tail+=level2 )*
  ;
  
level2
  : head=level3 ('<' tail+=level3)*
  ;
    
level3
  : head=level4 (binOp+=('-'|'+') tail+=level4)*
  ;

level4
  : head=level5 ( '*' tail+=level5)*
  ;
  
level5
  : expressionUnaryMinus
  | expressionIdentifier
  | expressionParentheses
  | expressionConstantTrue
  | expressionConstantFalse
  | expressionConstantInteger
  | expressionConstantString
  ;
  
expressionUnaryMinus      : '-' argument=level5 ;
expressionNegation        : '!' argument=level5;
expressionNewArray        : 'new' 'int' '[' argument=expression ']' ;
expressionNewObject       : 'new' id '(' ')';
expressionIdentifier      : id ;
expressionArrayAccess     : id '[' argument=expression ']';
expressionMethodCall      : identifier '(' (argument=expression (',' tail+=expression)* )? ')';
expressionParentheses     : '(' argument=expression ')' ;
expressionConstantTrue    : 'true' ;
expressionConstantFalse   : 'false' ;
expressionConstantInteger : value=INT ;
expressionConstantString  : value=STRING ;

identifier
  : id ( '.' selectors+=IDENT )*
  ;

id
  : idThis
  | idIDENT
  ;

idThis  : 'this' ;
idIDENT : name=IDENT ;

fragment LOWER : ('a'..'z');
fragment UPPER : ('A'..'Z');
fragment NONNULL : ('1'..'9');
fragment NUMBER : ('0' | NONNULL);
IDENT : ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )*;
fragment NEWLINE:'\r'? '\n';
INT : '0' | ( NONNULL NUMBER* );
fragment CHAR : ' ' | '!' | ('\u0023'..'\u005B') | ('\u005D'..'\u007E') | '\\"' | '\\\\' | '\\t' | '\\n';
STRING : '"' CHAR* '"' ;
COMMENT : ( '//' .* NEWLINE | '/*' .* '*/' ) -> skip;
WHITESPACE  :   ( ' ' | '\t' | NEWLINE )+  -> skip;