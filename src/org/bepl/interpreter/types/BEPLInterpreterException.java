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

package org.bepl.interpreter.types;

public class BEPLInterpreterException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public BEPLInterpreterException() {
        super("\n    BEPL Interpreter Exception!\n"
              + "    Cause: Unknown.", null, true, false);
    }
    
    public BEPLInterpreterException(String message) {
        super("\n    BEPL Interpreter Exception!\n"
              + "    Cause: " + message, null, true, false);
    }
}
