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
import java.math.BigDecimal;

import org.bepl.interpreter.types.BEPLInterpreterException;
import org.bepl.types.*;

public class BEPLCustomListener extends BEPLBaseListener {
    public static boolean DEBUG = false;
    HashMap<String, BEPLType<?>> variables;

    /**
     * This method gets the result of a sequence of operations.
     * 
     * @param ctx
     *            the current context.
     * @return the result of the operator sequence.
     */
    private BigDecimal getOperationResult(BEPLParser.OperationContext ctx) {
        BEPLParser.OperationContext currentOperationCtx = ctx; // Beginning context. Keeps track of VAR(0)
        BEPLParser.OperationContext previousOperationCtx = ctx; // Previous context. Used to keep track of the operator.

        BigDecimal total = new BigDecimal("0");

        while (currentOperationCtx != null) {
            BigDecimal nextNum = new BigDecimal(currentOperationCtx.NUMBER(0).getText());
            String op = previousOperationCtx.OPERATOR().getText();

            if (currentOperationCtx == ctx) {
                total = new BigDecimal(currentOperationCtx.NUMBER(0).getText());
            } else {
                if (op.equals("+"))
                    total = total.add(nextNum);
                else if (op.equals("-"))
                    total = total.subtract(nextNum);
                else if (op.equals("*"))
                    total = total.multiply(nextNum);
                else if (op.equals("/"))
                    total = total.divide(nextNum);
            }

            if (currentOperationCtx.operation() == null) {
                nextNum = new BigDecimal(currentOperationCtx.NUMBER(1).getText());
                op = currentOperationCtx.OPERATOR().getText();
                if (op.equals("+"))
                    total = total.add(nextNum);
                else if (op.equals("-"))
                    total = total.subtract(nextNum);
                else if (op.equals("*"))
                    total = total.multiply(nextNum);
                else if (op.equals("/"))
                    total = total.divide(nextNum);
            }

            previousOperationCtx = currentOperationCtx;
            currentOperationCtx = currentOperationCtx.operation();
        }

        return total;
    }
    
    /**
     * Clones a variable to be assigned.
     * @param ctx the current context.
     * @return the cloned variable.
     */
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
    
    /**
     * Clones a variable to be printed.
     * @param ctx the current context.
     * @return the cloned variable.
     */
    private BEPLType<?> cloneVariablePrint(BEPLParser.PrintContext ctx) {
    	String name = ctx.VAR().getText();
    	BEPLType<?> toBeCloned = variables.get(name);
    	
    	if (toBeCloned == null) {
    		String var0 = name;
    		throw new BEPLInterpreterException("`" + var0 + "`" +
    										   " could not be printed because it does not exist!");
    	}
    	
    	return toBeCloned.clone();
    }
    
    public BEPLCustomListener() {
        variables = new HashMap<>();
    }

    @Override
    public void exitAssign(BEPLParser.AssignContext ctx) {
        String name = ctx.VAR(0).getText();
        BEPLType<?> value;

        if (ctx.operation() != null) {
            value = new BEPLNumber(getOperationResult(ctx.operation()));
        } else if (ctx.STRING() != null) {
            value = new BEPLString(ctx.STRING().getText());
        } else if (ctx.NUMBER() != null) {
            value = new BEPLNumber(new BigDecimal(ctx.NUMBER().getText()));
        } else if (ctx.VAR(1) != null) {
            value = cloneVariableAssign(ctx);
        } else {
            value = null;
        }

        variables.put(name, value);

        // DEBUG
        if (DEBUG) {
            System.out.println("variables updated. " + variables);
            System.out.println();
        }
    }

    @Override
    public void enterPrint(BEPLParser.PrintContext ctx) {
        // DEBUG
        if (DEBUG) {
            System.out.print("BEPL PRINT: ");
        }
    }

    @Override
    public void exitPrint(BEPLParser.PrintContext ctx) {
        if (ctx.operation() != null) {
            System.out.println(getOperationResult(ctx.operation()));
        } else if (ctx.STRING() != null) {
            System.out.println(new BEPLString(ctx.STRING().getText()));
        } else if (ctx.VAR() != null) {
            System.out.println(cloneVariablePrint(ctx));
        } else if (ctx.NUMBER() != null) {
            System.out.println(ctx.NUMBER().getText());
        } else {
        	throw new BEPLInterpreterException("Print statement is empty!");
        }

        // DEBUG
        if (DEBUG) {
            System.out.println();
        }
    }
}
