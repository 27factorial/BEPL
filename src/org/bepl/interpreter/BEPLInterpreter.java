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

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class BEPLInterpreter {

    public static void main(String[] args) {
        try {
            CharStream input = CharStreams.fromFileName("test.bepl");
            BEPLLexer lexer = new BEPLLexer(input);
            TokenStream tokens = new CommonTokenStream(lexer);
            BEPLParser parser = new BEPLParser(tokens);
            parser.addParseListener(new BEPLCustomListener());

            BEPLCustomListener.debug = true;

            parser.program();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
