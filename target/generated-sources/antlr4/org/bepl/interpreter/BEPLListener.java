// Generated from BEPL.g4 by ANTLR 4.7.2

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

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BEPLParser}.
 */
public interface BEPLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BEPLParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(BEPLParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link BEPLParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(BEPLParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link BEPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(BEPLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BEPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(BEPLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BEPLParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(BEPLParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link BEPLParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(BEPLParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link BEPLParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(BEPLParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link BEPLParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(BEPLParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link BEPLParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(BEPLParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BEPLParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(BEPLParser.OperationContext ctx);
}