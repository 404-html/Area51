.ORIG x3000
  ;  initialize CONST0 and CONST1
  AND R2, R2, 0
  ADD R3, R2, 1
  ;  set SFP and HP 
  LD R6, LAB_15
  LD R4, LAB_16
  BR LAB_14
  ;  data for SFP and HP
LAB_15
.FILL 32768
LAB_16
.FILL 65023
  ;  arguments to main
  ;  string contents
LAB_17
.STRINGZ ""
  ;  strings
LAB_18
.FILL LAB_17
.FILL 1
  ;  args array
LAB_9
.FILL 1
.FILL LAB_18
  ; 
  ; Virtual Method Tables
  ; 
  ; 
  ; BEGIN A virtual method table
  ; 
LAB_10
.FILL LAB_19
LAB_19
  ADD R0, R0, 0
  ; 
  ; END A virtual method table
  ; 
  ; 
  ; BEGIN Object virtual method table
  ; 
LAB_11
.FILL LAB_20
LAB_20
  ADD R0, R0, 0
  ; 
  ; END Object virtual method table
  ; 
  ; 
  ; BEGIN String virtual method table
  ; 
LAB_12
.FILL LAB_21
LAB_21
  ADD R0, R0, 0
  ; 
  ; END String virtual method table
  ; 
LAB_14
  ADD R0, R0, 0
LAB_13
  ADD R0, R0, 0
  ; 
  ;  METHOD main
  ; 
  ;  save SFP R7 and initialize SP R5
  STR R7, R6, 2
  ADD R5, R6, 7
  ;  body 
  ; 
  ;  ASSIGN 
  ; 
  ;  rhs 
  ; 
  ;  BOOLEAN CONST false
  ; 
  ADD R5, R5, 1
  STR R2, R5, -1
  ;  lhs 
  ; 
  ;  IDENTIFIER t lhs 
  ; 
  ;  push address 
  ADD R0, R6, 6
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  ;  store 
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  STR R0, R1, 0
  ; 
  ;  ASSIGN END
  ; 
  ; 
  ;  ASSIGN 
  ; 
  ;  rhs 
  ; 
  ;  INT CONST 1
  ; 
  ADD R5, R5, 1
  STR R3, R5, -1
  ;  lhs 
  ; 
  ;  IDENTIFIER j lhs 
  ; 
  ;  push address 
  ADD R0, R6, 5
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  ;  store 
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  STR R0, R1, 0
  ; 
  ;  ASSIGN END
  ; 
  ; 
  ;  ASSIGN 
  ; 
  ;  rhs 
  ; 
  ;  INT CONST 0
  ; 
  ADD R5, R5, 1
  STR R2, R5, -1
  ;  lhs 
  ; 
  ;  IDENTIFIER i lhs 
  ; 
  ;  push address 
  ADD R0, R6, 4
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  ;  store 
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  STR R0, R1, 0
  ; 
  ;  ASSIGN END
  ; 
  ; 
  ;  ASSIGN 
  ; 
  ;  rhs 
  ; 
  ;  MINUS BEGIN 
  ; 
  ; 
  ;  INT CONST 1
  ; 
  ADD R5, R5, 1
  STR R3, R5, -1
  ADD R5, R5, -1
  LDR R0, R5, 0
  ; 
  ;  INT CONST 1
  ; 
  ADD R5, R5, 1
  STR R3, R5, -1
  ADD R5, R5, -1
  LDR R1, R5, 0
  NOT R1, R1
  ADD R1, R1, 1
  ADD R0, R0, R1
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  MINUS END 
  ; 
  ;  lhs 
  ; 
  ;  IDENTIFIER j lhs 
  ; 
  ;  push address 
  ADD R0, R6, 5
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  ;  store 
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  STR R0, R1, 0
  ; 
  ;  ASSIGN END
  ; 
  ; 
  ;  ASSIGN 
  ; 
  ;  rhs 
  ; 
  ;  NEGATE BEGIN 
  ; 
  ; 
  ;  IDENTIFIER t rhs 
  ; 
  ;  push value 
  LDR R0, R6, 6
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  NOT R0, R0
  AND R0, R0, 1
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  NEGATE END 
  ; 
  ;  lhs 
  ; 
  ;  IDENTIFIER t lhs 
  ; 
  ;  push address 
  ADD R0, R6, 6
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  ;  store 
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  STR R0, R1, 0
  ; 
  ;  ASSIGN END
  ; 
  ; 
  ;  IF/ELSE 
  ; 
  ; 
  ;  NEGATE BEGIN 
  ; 
  ; 
  ;  IDENTIFIER t rhs 
  ; 
  ;  push value 
  LDR R0, R6, 6
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  NOT R0, R0
  AND R0, R0, 1
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  NEGATE END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  BRz LAB_22
  ; 
  ;  STRING CONST BEGIN 
  ; 
  BR LAB_28
LAB_27
.STRINGZ "Hello World!"
LAB_25
.FILL LAB_27
.FILL 14
LAB_26
.FILL LAB_25
LAB_28
  LD R0, LAB_26
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  STRING CONST END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  BRz LAB_30
  LDR R0, R0, 0
  BR LAB_23
LAB_30
  LEA R0, LAB_29
  BR LAB_23
LAB_29
.STRINGZ "null"
  BR LAB_23
LAB_24
.FILL LAB_1
LAB_23
  TRAP x22
  LD R1, LAB_24
  JSRR R1
  ; 
  ;  End of if block
  ; 
  BR LAB_31
  ; 
  ; Start of elseblock
  ; 
LAB_22
  ; 
  ;  STRING CONST BEGIN 
  ; 
  BR LAB_37
LAB_36
.STRINGZ "false hahahahaa"
LAB_34
.FILL LAB_36
.FILL 17
LAB_35
.FILL LAB_34
LAB_37
  LD R0, LAB_35
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  STRING CONST END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  BRz LAB_39
  LDR R0, R0, 0
  BR LAB_32
LAB_39
  LEA R0, LAB_38
  BR LAB_32
LAB_38
.STRINGZ "null"
  BR LAB_32
LAB_33
.FILL LAB_1
LAB_32
  TRAP x22
  LD R1, LAB_33
  JSRR R1
LAB_31
  ADD R0, R0, 0
  ; 
  ;  IF/ELSE END 
  ; 
  ; 
  ;  WHILE BEGIN 
  ; 
LAB_40
  ; 
  ;  LESS 
  ; 
  ;  lhs 
  ; 
  ;  IDENTIFIER i rhs 
  ; 
  ;  push value 
  LDR R0, R6, 4
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  ;  rhs 
  ; 
  ;  INT CONST 3
  ; 
  LD R0, 3
  ADD R5, R5, 1
  STR R0, R5, -1
  BR LAB_42
  ;  value 
.FILL 3
LAB_42
  ; 
  ;  INT CONST END
  ; 
  ;   compute lhs - rhs  
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  NOT R1, R1
  ADD R1, R1, 1
  ADD R0, R0, R1
  ;  if lhs < rhs, N flag is set
  BRn LAB_43
  ;  if not negative, push 0 
  ADD R5, R5, 1
  STR R2, R5, -1
  BR LAB_44
LAB_43
  ;  if negative, push 1 
  ADD R5, R5, 1
  STR R3, R5, -1
LAB_44
  ; 
  ;  LESS END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  BRz LAB_41
  ; 
  ;  STRING CONST BEGIN 
  ; 
  BR LAB_50
LAB_49
.STRINGZ "GoodBeirut World"
LAB_47
.FILL LAB_49
.FILL 18
LAB_48
.FILL LAB_47
LAB_50
  LD R0, LAB_48
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  STRING CONST END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  BRz LAB_52
  LDR R0, R0, 0
  BR LAB_45
LAB_52
  LEA R0, LAB_51
  BR LAB_45
LAB_51
.STRINGZ "null"
  BR LAB_45
LAB_46
.FILL LAB_1
LAB_45
  TRAP x22
  LD R1, LAB_46
  JSRR R1
  ; 
  ;  ASSIGN 
  ; 
  ;  rhs 
  ; 
  ;  PLUS BEGIN
  ; 
  ;  lhs 
  ; 
  ;  IDENTIFIER i rhs 
  ; 
  ;  push value 
  LDR R0, R6, 4
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  ;  rhs 
  ; 
  ;  INT CONST 1
  ; 
  ADD R5, R5, 1
  STR R3, R5, -1
  ;  add integers 
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  ADD R0, R0, R1
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  PLUS END
  ; 
  ;  lhs 
  ; 
  ;  IDENTIFIER i lhs 
  ; 
  ;  push address 
  ADD R0, R6, 4
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  ;  store 
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  STR R0, R1, 0
  ; 
  ;  ASSIGN END
  ; 
  BR LAB_40
LAB_41
  ADD R0, R0, 0
  ; 
  ;  WHILE END
  ; 
  ; 
  ;  STRING CONST BEGIN 
  ; 
  BR LAB_58
LAB_57
.STRINGZ "Nu Christian glad:"
LAB_55
.FILL LAB_57
.FILL 20
LAB_56
.FILL LAB_55
LAB_58
  LD R0, LAB_56
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  STRING CONST END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  BRz LAB_60
  LDR R0, R0, 0
  BR LAB_53
LAB_60
  LEA R0, LAB_59
  BR LAB_53
LAB_59
.STRINGZ "null"
  BR LAB_53
LAB_54
.FILL LAB_1
LAB_53
  TRAP x22
  LD R1, LAB_54
  JSRR R1
  ; 
  ;  STRING CONST BEGIN 
  ; 
  BR LAB_65
LAB_64
.STRINGZ "i: "
LAB_62
.FILL LAB_64
.FILL 5
LAB_63
.FILL LAB_62
LAB_65
  LD R0, LAB_63
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  STRING CONST END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  BRz LAB_67
  LDR R0, R0, 0
  BR LAB_61
LAB_67
  LEA R0, LAB_66
  BR LAB_61
LAB_66
.STRINGZ "null"
LAB_61
  TRAP x22
  ; 
  ;  IDENTIFIER i rhs 
  ; 
  ;  push value 
  LDR R0, R6, 4
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  BR LAB_70
LAB_71
.FILL LAB_3
LAB_70
  LD R1, LAB_71
  JSRR R1
  ADD R5, R5, -1
  LDR R0, R5, 0
  BR LAB_68
LAB_69
.FILL LAB_1
LAB_68
  TRAP x22
  LD R1, LAB_69
  JSRR R1
  ; 
  ;  STRING CONST BEGIN 
  ; 
  BR LAB_76
LAB_75
.STRINGZ "j: "
LAB_73
.FILL LAB_75
.FILL 5
LAB_74
.FILL LAB_73
LAB_76
  LD R0, LAB_74
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  STRING CONST END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  BRz LAB_78
  LDR R0, R0, 0
  BR LAB_72
LAB_78
  LEA R0, LAB_77
  BR LAB_72
LAB_77
.STRINGZ "null"
LAB_72
  TRAP x22
  ; 
  ;  IDENTIFIER j rhs 
  ; 
  ;  push value 
  LDR R0, R6, 5
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  IDENTIFIER END 
  ; 
  BR LAB_81
LAB_82
.FILL LAB_3
LAB_81
  LD R1, LAB_82
  JSRR R1
  ADD R5, R5, -1
  LDR R0, R5, 0
  BR LAB_79
LAB_80
.FILL LAB_1
LAB_79
  TRAP x22
  LD R1, LAB_80
  JSRR R1
  ; 
  ;  RETURN BEGIN
  ; 
  ;  get return PC from stack frame
  LDR R7, R6, 2
  RET
  ; 
  ;  RETURN END
  ; 
  ; 
  ;  METHOD END main
  ; 
  ; 
  ;  helper functions 
  ; 
  ; 
  ; 
  ; 
  ; 
  ;  translate top of stack to string, pushes result
  ; 
LAB_3
  ; 
  ; This algorithm takes the 2's complement representation of a signed
  ; integer, within the range -999 to +999, and converts it into an ASCII
  ; string consisting of a sign digit, followed by three decimal digits.
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  ADD R5, R5, 1
  STR R7, R5, -1
  LEA R1, LAB_83
  ADD R0, R0, 0
  BRzp LAB_86
  LD R7, LAB_84
  STR R7, R1, 0
  ADD R1, R1, 1
  NOT R0, R0
  ADD R0, R0, 1
LAB_86
  AND R7, R7, 0
  LD R2, LAB_87
LAB_88
  ADD R0, R0, R2
  BRn LAB_89
  ADD R7, R7, 1
  BR LAB_88
  ; 
LAB_89
  ADD R7, R7, 0
  BRz LAB_112
  LD R2, LAB_85
  ADD R7, R7, R2
  STR R7, R1, 0
  ADD R1, R1, 1
LAB_112
  LD R2, LAB_90
  ADD R0, R0, R2
  ; 
  AND R7, R7, 0
LAB_91
  LD R2, LAB_92
LAB_93
  ADD R0, R0, R2
  BRn LAB_94
  ADD R7, R7, 1
  BR LAB_93
  ; 
LAB_94
  ADD R7, R7, 0
  BRz LAB_111
  LD R2, LAB_85
  ADD R7, R7, R2
  STR R7, R1, 0
  ADD R1, R1, 1
LAB_111
  LD R2, LAB_95
  ADD R0, R0, R2
  ; 
  AND R7, R7, 0
LAB_96
  LD R2, LAB_97
LAB_98
  ADD R0, R0, R2
  BRn LAB_99
  ADD R7, R7, 1
  BR LAB_98
  ; 
LAB_99
  ADD R7, R7, 0
  BRz LAB_110
  LD R2, LAB_85
  ADD R7, R7, R2
  STR R7, R1, 0
  ADD R1, R1, 1
LAB_110
  LD R2, LAB_100
  ADD R0, R0, R2
  ; 
  AND R7, R7, 0
LAB_101
  LD R2, LAB_102
LAB_103
  ADD R0, R0, R2
  BRn LAB_104
  ADD R7, R7, 1
  BR LAB_103
  ; 
LAB_104
  ADD R7, R7, 0
  BRz LAB_109
  LD R2, LAB_85
  ADD R7, R7, R2
  STR R7, R1, 0
  ADD R1, R1, 1
LAB_109
  ADD R0, R0, 10
  ; 
  LD R7, LAB_85
LAB_106
  ADD R7, R7, R0
  STR R7, R1, 0
  ADD R1, R1, 1
  AND R2, R2, 0
  STR R2, R1, 0
  ADD R5, R5, -1
  LDR R7, R5, 0
  LEA R0, LAB_83
  ADD R5, R5, 1
  STR R0, R5, -1
  RET
  ; 
  ; data
  ; 
LAB_83
  .BLKW 7
LAB_84
.FILL 45
LAB_85
.FILL 48
LAB_87
.FILL -10000
LAB_92
.FILL -1000
LAB_97
.FILL -100
LAB_102
.FILL -10
LAB_107
.FILL -1
LAB_90
.FILL 10000
LAB_95
.FILL 1000
LAB_100
.FILL 100
LAB_105
.FILL 10
LAB_108
.FILL 1
  ; 
  ;  print newline 
  ; 
LAB_1
  ADD R5, R5, 1
  STR R7, R5, -1
  LD R0, 1
  BR LAB_113
.FILL LAB_114
LAB_114
.STRINGZ "\n"
LAB_113
  TRAP x22
  ADD R5, R5, -1
  LDR R7, R5, 0
  RET
LAB_2
  ; 
  ;  create an object with size top of stack, result in HP
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  ; allocate object
  NOT R0, R0
  ADD R0, R0, 1
  ADD R4, R4, R0
  ADD R5, R5, 0
  BRp LAB_116
  ADD R4, R4, 0
  BRp LAB_117
LAB_118
  ADD R1, R5, 0
  NOT R1, R1
  ADD R1, R1, 1
  ADD R1, R4, R1
  BRp LAB_115
  BR LAB_117
LAB_116
  ADD R4, R4, 0
  BRn LAB_115
  BR LAB_118
LAB_117
  TRAP x25
LAB_115
  ADD R5, R5, 1
  STR R4, R5, -1
  RET
  ; 
  ;  nullify 
  ; 
  ;  overwrites memory area a to b with 0s 
  ;  expects operands in top of stack 
  ;  assumes a<b!!! 
LAB_5
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  ADD R5, R5, 1
  STR R0, R5, -1
  NOT R0, R0
  ADD R0, R0, 1
  ADD R1, R1, R0
  ADD R5, R5, -1
  LDR R0, R5, 0
LAB_119
  STR R2, R0, 0
  ADD R0, R0, 1
  ADD R1, R1, -1
  BRp LAB_119
  RET
  ; 
  ;  multiplication routine 
  ; 
  ;  expects operands on top of stack 
LAB_4
  ;  while loop that multiplies a and b, R7 is sum 
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  ;  get a and b
  ;  check signs 
  ;  CONST0 is used to store the flag of the result
  ;  0 means positive (default), 1 negative
  ADD R0, R0, 0
  ;  if one is zero we're done
  BRz LAB_121
  ;  if a is positive, jump
  BRp LAB_122
  ;  negate a 
  NOT R0, R0
  ADD R0, R0, 1
  ADD R1, R1, 0
  ;  if one is zero we're done
  BRz LAB_121
  ;  if b is positive, jump
  BRp LAB_123
  ;  a is neg, b is too
  ;  negate b
LAB_124
  NOT R1, R1
  ADD R1, R1, 1
  ;  go, multiply!
  BR LAB_120
LAB_123
  ;  a is negative, b is positive
  ;  set flag to 1 for negative result
  ADD R2, R2, 1
  ;  go, multiply!
  BR LAB_120
LAB_122
  ;  a is positive 
  ADD R1, R1, 0
  ;  if one is zero we're done
  BRz LAB_121
  ;  if b is positive, go multiply!
  BRp LAB_120
  ;  a is pos, b is neg
  ;  set flag to 1 for negative result
  ADD R2, R2, 1
  BR LAB_124
  ; 
  ;  multiply 
  ; 
LAB_120
  ADD R5, R5, 1
  STR R7, R5, -1
  ;  reset sum
  AND R7, R7, 0
LAB_125
  ADD R7, R7, R0
  ADD R1, R1, -1
  BRp LAB_125
  ;  adjust sign 
  ADD R2, R2, 0
  BRz LAB_126
  NOT R7, R7
  ADD R7, R7, 1
LAB_126
  ;  reset CONST0 
  AND R2, R2, 0
  ;  transfer sum to TMP0, get RET from stack
  ADD R0, R7, 0
  ADD R5, R5, -1
  LDR R7, R5, 0
  ;  result in R0 
  ADD R5, R5, 1
  STR R0, R5, -1
  RET
  ;  one factor was 0
LAB_121
  ADD R5, R5, 1
  STR R2, R5, -1
  RET
  ; 
  ;  null pointer exception 
  ; 
  ;  prints error message and exits
LAB_7
  LEA R0, LAB_127
  TRAP x22
  TRAP x25
LAB_127
.STRINGZ "null_pointer_exception\n"
  ; 
  ;  index out of bounds 
  ; 
  ;  prints error message and exits
LAB_8
  LEA R0, LAB_128
  TRAP x22
  TRAP x25
LAB_128
.STRINGZ "index_out_of_bounds\n"
  ; 
  ;  add two strings 
  ; 
  ;  expects args on top of stack, puts result on stack
LAB_6
  LDR R0, R5, -2
  BRnp LAB_132
  LEA R0, LAB_134
  STR R0, R5, -2
  BR LAB_133
LAB_132
  LDR R0, R5, -1
  BRnp LAB_133
  LEA R0, LAB_134
  STR R0, R5, -1
  BR LAB_133
LAB_134
.FILL LAB_135
.FILL 5
LAB_135
.STRINGZ "null"
LAB_133
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  ADD R5, R5, 1
  STR R7, R5, -1
  ;  compute combined length 
  LDR R7, R0, 1
  ADD R5, R5, 1
  STR R0, R5, -1
  ADD R0, R7, 0
  LDR R7, R1, 1
  ADD R5, R5, 1
  STR R1, R5, -1
  ADD R0, R0, R7
  BR LAB_130
LAB_131
.FILL LAB_2
LAB_129
.FILL 3
  ;  copy string 
LAB_137
.FILL LAB_136
LAB_136
  ADD R5, R5, 1
  STR R7, R5, -1
  LDR R0, R0, 0
LAB_139
  LDR R7, R0, 0
  BRz LAB_138
  STR R7, R1, 0
  ADD R0, R0, 1
  ADD R1, R1, 1
  BR LAB_139
LAB_138
  ADD R5, R5, -1
  LDR R7, R5, 0
  RET
LAB_130
  ;  invoke new method 
  LD R1, LAB_129
  ADD R0, R1, R0
  ADD R5, R5, 1
  STR R0, R5, -1
  LD R1, LAB_131
  JSRR R1
  ;  initialize object pointed to by HP 
  ;  initialize string 
  ADD R0, R4, 2
  STR R0, R4, 0
  ;  initialize length 
  ADD R5, R5, -1
  LDR R0, R5, 0
  STR R0, R4, 1
  ;  get two strings 
  ADD R5, R5, -2
  LDR R0, R5, 0
  LDR R1, R5, 1
  ADD R5, R5, 1
  STR R1, R5, -1
  ;  copy first string from TMP0->0 to HP->0 
  LDR R1, R4, 0
  LD R7, LAB_137
  JSRR R7
  ;  copy second string from TMP0->0 to HP->0 
  ADD R5, R5, -1
  LDR R0, R5, 0
  LD R7, LAB_137
  JSRR R7
  ADD R5, R5, -1
  LDR R7, R5, 0
  ADD R5, R5, 1
  STR R4, R5, -1
  RET
.END
