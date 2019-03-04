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

import java.util.HashMap;

import org.bepl.types.*;

public class BEPLCustomListener extends BEPLBaseListener {
    public static boolean debug = false;
    HashMap<String, BEPLType<?>> variables;

    /**
     * This method gets the result of a sequence of operations.
     * 
     * @param ctx
     *            the current context.
     * @return the result of the operator sequence.
     */
    private int getOperationResult(BEPLParser.OperationContext ctx) {
        BEPLParser.OperationContext currentOperationCtx = ctx; // Beginning context.
        BEPLParser.OperationContext previousOperationCtx = ctx; // Previous context. Used to keep track of the operator.

        int total = 0;

        while (currentOperationCtx != null) {
            int nextNum = Integer.parseInt(currentOperationCtx.INT(0).getText());
            String op = previousOperationCtx.OPERATOR().getText();

            if (currentOperationCtx == ctx) {
                total = Integer.parseInt(currentOperationCtx.INT(0).getText());
            } else {
                if (op.equals("+"))
                    total += nextNum;
                else if (op.equals("-"))
                    total -= nextNum;
                else if (op.equals("*"))
                    total *= nextNum;
                else if (op.equals("/"))
                    total /= nextNum;
            }

            if (currentOperationCtx.operation() == null) {
                nextNum = Integer.parseInt(currentOperationCtx.INT(1).getText());
                op = currentOperationCtx.OPERATOR().getText();
                if (op.equals("+"))
                    total += nextNum;
                else if (op.equals("-"))
                    total -= nextNum;
                else if (op.equals("*"))
                    total *= nextNum;
                else if (op.equals("/"))
                    total /= nextNum;
            }

            previousOperationCtx = currentOperationCtx;
            currentOperationCtx = currentOperationCtx.operation();
        }

        return total;
    }
    
    private BEPLType<?> cloneVariableAssign(BEPLParser.AssignContext ctx) {
        String name = ctx.VAR(1).getText();
        BEPLType<?> toBeCloned = variables.get(name);
        
        if (toBeCloned == null) {
            String var0 = ctx.VAR(0).getText();
            String var1 = ctx.VAR(1).getText();
            throw new BEPLInterpreterException("`" + var0 + "`" + " cannot be assigned to " + 
                                               "`" + var1 + "`" + " because " + 
                                               "`" + var1 + "`" + " does not exist!");
        }
            
        
        return toBeCloned.clone();
    }
    
    public BEPLCustomListener() {
        variables = new HashMap<>();
    }

    @Override
    public void enterProgram(BEPLParser.ProgramContext ctx) {
        // DEBUG
        if (debug) {
            System.out.println("BEPL Version 0.1");
            System.out.println("- Listener: BEPLCustomListener");
            System.out.println("- Status: Starting.");
            System.out.println();
        }
    }

    @Override
    public void exitProgram(BEPLParser.ProgramContext ctx) {
        // DEBUG
        if (debug) {
            System.out.println("BEPL Version 0.1");
            System.out.println("- Listener: BEPLCustomListener");
            System.out.println("- Status: Finished.");
            System.out.println();
        }
    }

    @Override
    public void exitAssign(BEPLParser.AssignContext ctx) {
        String name = ctx.VAR(0).getText();
        BEPLType<?> value;

        if (ctx.operation() != null) {
            value = new BEPLInteger(getOperationResult(ctx.operation()));
        } else if (ctx.STRING() != null) {
            value = new BEPLString(ctx.STRING().getText());
        } else if (ctx.INT() != null) {
            value = new BEPLInteger(Integer.parseInt(ctx.INT().getText()));
        } else if (ctx.VAR(1) != null) {
            value = cloneVariableAssign(ctx);
        } else {
            value = null;
        }

        variables.put(name, value);

        // DEBUG
        if (debug) {
            System.out.println("variables updated. " + variables);
            System.out.println();
        }
    }

    @Override
    public void enterPrint(BEPLParser.PrintContext ctx) {
        // DEBUG
        if (debug) {
            System.out.print("BEPL PRINT: ");
        }
    }

    @Override
    public void exitPrint(BEPLParser.PrintContext ctx) {
        if (ctx.operation() != null) {
            System.out.println(ctx.operation());
        } else if (ctx.STRING() != null) {
            System.out.println(new BEPLString(ctx.STRING().getText()));
        } else if (ctx.VAR() != null) {
            System.out.println(variables.get(ctx.VAR().getText()));
        } else {
            System.out.println(ctx.INT().getText());
        }

        // DEBUG
        if (debug) {
            System.out.println();
        }
    }
}
