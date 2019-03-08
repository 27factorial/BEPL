/**
 * The Basic English-like Programming Language.
 * This file is a description of the language,
 * these rules are implemented in 
 * org.bepl.interpreter.BEPLCustomListener
 */
grammar BEPL;

@header {
/*
 * The Basic English-like Programming Language.
 * Created by Team Rocket
 * CS 143, Section 1415 @ TCC.
 * 
 * Credit to Shalitha Suranga for
 * the usage of Simplerlang in
 * early versions of BEPL.
 * Simplerlang is licensed under the MIT License.
 * https://github.com/shalithasuranga/simpler/blob/master/LICENSE
 */
package org.bepl.interpreter;
}

/**
 * Parser Rules
 */
program : statement* ;                                              // A full program consists of zero or more statements.

statement : assign                                                  // Statement: Can either assign a variable,
          | print                                                   // print something,
          | operation                                               // or do an operation.
          ;
assign    : VAR 'is' (operation | STRING | INT | VAR) ;             // Implemented as a HashMap<String, BEPLType<?>>
print     : 'print' (operation | STRING | INT | VAR) ;              // Analogous to System.out.println();
operation : INT OPERATOR (operation | INT)                          // Arithmetic implementation.
          ;

/**
 * Lexer Rules
 */
STRING   : '"' .*? '"' ;                                            // Zero or more characters, up to the quotation mark.
VAR      : [a-zA-Z_]+ ;                                             // Lowercase letters, Uppercase letters, and an underscore.
INT      : DIGIT+ ;                                                 // Any combination of the numbers 0-9.
OPERATOR : '+'                                                      // Basic math operators.
         | '-'
         | '*'
         | '/'
         ;

/**
 * Fragments
 */
fragment DIGIT : [0-9] ;

/**
 * Skip these rules.
 */
SINGLELINE_COMMENT : '#' .*? [\r\n] -> skip ;                         // Ignore everything past a # character on the same line.
MULTILINE_COMMENT  : '###' .*? '###' -> skip ;                        // Ignore multi line comments.
WS                 : [ \r\n\t]+ -> skip ;                             // Ignore whitespace.
