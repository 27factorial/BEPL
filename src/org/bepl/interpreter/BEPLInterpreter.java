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

            BEPLCustomListener.debug = false;

            parser.program();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
