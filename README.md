This project is an exercise set by my Director of Studies, the following is his outline:  

You are going to write a lexer and a parser for a new calculator application! For the purposes of this exercise, you may not use lex, yacc, or other compiler compilers! You may use ML, Java or C#. The calculator is to read in ASCII strings containing arithmetic expressions (e.g. “2.3+4”) and you should emit a parse tree for the calculation. Accepted input numbers are to be signed floating point numbers:  
* An optional + or - character followed by…  
* A non-empty sequence of 0,1..9 characters, optionally interspersed with (exactly) one decimal point  
* Optionally followed by an exponent suffix, which consists of a single letter ‘e’, a single optional + or - character, and a decimal integer.  
  
The operators, in order of increasing precedence are:  
Level 1: "+" and "-", left associative diadic infix operators.  
Level 2: "^" a right associative diadic infix operator.  
Level 3: "cos", a monadic prefix operator.  
Level 4: "!", a monadic postfix operator.  
