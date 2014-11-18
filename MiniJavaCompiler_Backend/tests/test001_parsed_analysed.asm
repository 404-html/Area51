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
LAB_19 .fill 0
  ; 
  ; END A virtual method table
  ; 
  ; 
  ; BEGIN Object virtual method table
  ; 
LAB_11
.FILL LAB_20
LAB_20
  ; 
  ; END Object virtual method table
  ; 
  ; 
  ; BEGIN String virtual method table
  ; 
LAB_12
.FILL LAB_21
LAB_21
  ; 
  ; END String virtual method table
  ; 
LAB_14
LAB_13
  ; 
  ;  METHOD main
  ; 
  ;  save SFP R7 and initialize SP R5
  STR R7, R6, 2
  ADD R5, R6, 4
  ;  body 
  ; 
  ;  IF 
  ; 
  ; 
  ;  BOOLEAN CONST true
  ; 
  ADD R5, R5, 1
  STR R3, R5, -1
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
LAB_22
  ; 
  ;  IF END 
  ; 
  ; 
  ;  STRING CONST BEGIN 
  ; 
  BR LAB_36
LAB_35
.STRINGZ "GoodBeirut World"
LAB_33
.FILL LAB_35
.FILL 18
LAB_34
.FILL LAB_33
LAB_36
  LD R0, LAB_34
  ADD R5, R5, 1
  STR R0, R5, -1
  ; 
  ;  STRING CONST END 
  ; 
  ADD R5, R5, -1
  LDR R0, R5, 0
  BRz LAB_38
  LDR R0, R0, 0
  BR LAB_31
LAB_38
  LEA R0, LAB_37
  BR LAB_31
LAB_37
.STRINGZ "null"
  BR LAB_31
LAB_32
.FILL LAB_1
LAB_31
  TRAP x22
  LD R1, LAB_32
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
  LEA R1, LAB_39
  ADD R0, R0, 0
  BRzp LAB_42
  LD R7, LAB_40
  STR R7, R1, 0
  ADD R1, R1, 1
  NOT R0, R0
  ADD R0, R0, 1
LAB_42
  AND R7, R7, 0
  LD R2, LAB_43
LAB_44
  ADD R0, R0, R2
  BRn LAB_45
  ADD R7, R7, 1
  BR LAB_44
  ; 
LAB_45
  ADD R7, R7, 0
  BRz LAB_68
  LD R2, LAB_41
  ADD R7, R7, R2
  STR R7, R1, 0
  ADD R1, R1, 1
LAB_68
  LD R2, LAB_46
  ADD R0, R0, R2
  ; 
  AND R7, R7, 0
LAB_47
  LD R2, LAB_48
LAB_49
  ADD R0, R0, R2
  BRn LAB_50
  ADD R7, R7, 1
  BR LAB_49
  ; 
LAB_50
  ADD R7, R7, 0
  BRz LAB_67
  LD R2, LAB_41
  ADD R7, R7, R2
  STR R7, R1, 0
  ADD R1, R1, 1
LAB_67
  LD R2, LAB_51
  ADD R0, R0, R2
  ; 
  AND R7, R7, 0
LAB_52
  LD R2, LAB_53
LAB_54
  ADD R0, R0, R2
  BRn LAB_55
  ADD R7, R7, 1
  BR LAB_54
  ; 
LAB_55
  ADD R7, R7, 0
  BRz LAB_66
  LD R2, LAB_41
  ADD R7, R7, R2
  STR R7, R1, 0
  ADD R1, R1, 1
LAB_66
  LD R2, LAB_56
  ADD R0, R0, R2
  ; 
  AND R7, R7, 0
LAB_57
  LD R2, LAB_58
LAB_59
  ADD R0, R0, R2
  BRn LAB_60
  ADD R7, R7, 1
  BR LAB_59
  ; 
LAB_60
  ADD R7, R7, 0
  BRz LAB_65
  LD R2, LAB_41
  ADD R7, R7, R2
  STR R7, R1, 0
  ADD R1, R1, 1
LAB_65
  ADD R0, R0, 10
  ; 
  LD R7, LAB_41
LAB_62
  ADD R7, R7, R0
  STR R7, R1, 0
  ADD R1, R1, 1
  AND R2, R2, 0
  STR R2, R1, 0
  ADD R5, R5, -1
  LDR R7, R5, 0
  LEA R0, LAB_39
  ADD R5, R5, 1
  STR R0, R5, -1
  RET
  ; 
  ; data
  ; 
LAB_39
  .BLKW 7
LAB_40
.FILL 45
LAB_41
.FILL 48
LAB_43
.FILL -10000
LAB_48
.FILL -1000
LAB_53
.FILL -100
LAB_58
.FILL -10
LAB_63
.FILL -1
LAB_46
.FILL 10000
LAB_51
.FILL 1000
LAB_56
.FILL 100
LAB_61
.FILL 10
LAB_64
.FILL 1
  ; 
  ;  print newline 
  ; 
LAB_1
  ADD R5, R5, 1
  STR R7, R5, -1
  LD R0, 1
  BR LAB_69
.FILL LAB_70
LAB_70
.STRINGZ "\n"
LAB_69
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
  BRp LAB_72
  ADD R4, R4, 0
  BRp LAB_73
LAB_74
  ADD R1, R5, 0
  NOT R1, R1
  ADD R1, R1, 1
  ADD R1, R4, R1
  BRp LAB_71
  BR LAB_73
LAB_72
  ADD R4, R4, 0
  BRn LAB_71
  BR LAB_74
LAB_73
  TRAP x25
LAB_71
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
LAB_75
  STR R2, R0, 0
  ADD R0, R0, 1
  ADD R1, R1, -1
  BRp LAB_75
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
  BRz LAB_77
  ;  if a is positive, jump
  BRp LAB_78
  ;  negate a 
  NOT R0, R0
  ADD R0, R0, 1
  ADD R1, R1, 0
  ;  if one is zero we're done
  BRz LAB_77
  ;  if b is positive, jump
  BRp LAB_79
  ;  a is neg, b is too
  ;  negate b
LAB_80
  NOT R1, R1
  ADD R1, R1, 1
  ;  go, multiply!
  BR LAB_76
LAB_79
  ;  a is negative, b is positive
  ;  set flag to 1 for negative result
  ADD R2, R2, 1
  ;  go, multiply!
  BR LAB_76
LAB_78
  ;  a is positive 
  ADD R1, R1, 0
  ;  if one is zero we're done
  BRz LAB_77
  ;  if b is positive, go multiply!
  BRp LAB_76
  ;  a is pos, b is neg
  ;  set flag to 1 for negative result
  ADD R2, R2, 1
  BR LAB_80
  ; 
  ;  multiply 
  ; 
LAB_76
  ADD R5, R5, 1
  STR R7, R5, -1
  ;  reset sum
  AND R7, R7, 0
LAB_81
  ADD R7, R7, R0
  ADD R1, R1, -1
  BRp LAB_81
  ;  adjust sign 
  ADD R2, R2, 0
  BRz LAB_82
  NOT R7, R7
  ADD R7, R7, 1
LAB_82
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
LAB_77
  ADD R5, R5, 1
  STR R2, R5, -1
  RET
  ; 
  ;  null pointer exception 
  ; 
  ;  prints error message and exits
LAB_7
  LEA R0, LAB_83
  TRAP x22
  TRAP x25
LAB_83
.STRINGZ "Null pointer exception
"
  ; 
  ;  index out of bounds exception 
  ; 
  ;  prints error message and exits
LAB_8
  LEA R0, LAB_84
  TRAP x22
  TRAP x25
LAB_84
.STRINGZ "Index out of bounds exception
"
  ; 
  ;  add two strings 
  ; 
  ;  expects args on top of stack, puts result on stack
LAB_6
  LDR R0, R5, -2
  BRnp LAB_88
  LEA R0, LAB_90
  STR R0, R5, -2
  BR LAB_89
LAB_88
  LDR R0, R5, -1
  BRnp LAB_89
  LEA R0, LAB_90
  STR R0, R5, -1
  BR LAB_89
LAB_90
.FILL LAB_91
.FILL 5
LAB_91
.STRINGZ "null"
LAB_89
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
  BR LAB_86
LAB_87
.FILL LAB_2
LAB_85
.FILL 3
  ;  copy string 
LAB_93
.FILL LAB_92
LAB_92
  ADD R5, R5, 1
  STR R7, R5, -1
  LDR R0, R0, 0
LAB_95
  LDR R7, R0, 0
  BRz LAB_94
  STR R7, R1, 0
  ADD R0, R0, 1
  ADD R1, R1, 1
  BR LAB_95
LAB_94
  ADD R5, R5, -1
  LDR R7, R5, 0
  RET
LAB_86
  ;  invoke new method 
  LD R1, LAB_85
  ADD R0, R1, R0
  ADD R5, R5, 1
  STR R0, R5, -1
  LD R1, LAB_87
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
  LD R7, LAB_93
  JSRR R7
  ;  copy second string from TMP0->0 to HP->0 
  ADD R5, R5, -1
  LDR R0, R5, 0
  LD R7, LAB_93
  JSRR R7
  ADD R5, R5, -1
  LDR R7, R5, 0
  ADD R5, R5, 1
  STR R4, R5, -1
  RET
.END
