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
    public static final String VERSION = "0.1";

    /**
     * This function runs the specified file in the interpreter.
     * @param path The path of the file.
     * @param debugEnabled Whether or not debug output should be printed.
     */
    public static void runFile(String path, boolean debugEnabled) {
        try {
            CharStream input = CharStreams.fromFileName(path);
            BEPLLexer lexer = new BEPLLexer(input);
            TokenStream tokens = new CommonTokenStream(lexer);
            BEPLParser parser = new BEPLParser(tokens);
            parser.addParseListener(new BEPLCustomListener());

            BEPLCustomListener.DEBUG = debugEnabled;

            parser.program();
        } catch (IOException e) {
            System.err.println("The specified file could not be accessed. Stacktrace:");
            e.printStackTrace();
        }
    }

    public static void printHelp() {
        System.out.println("BEPL v" + VERSION + " Interpreter Help:");
        System.out.println("    -h | --help\t\t\tPrint this message.");
        System.out.println("    -f | --file [filePath]\tRun the BEPL interpreter with a specified file.");
        System.out.println("    -i | --interactive\t\tRun the BEPL interpreter in interactive mode.");
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("This program requires one parameter.");
            System.err.println("Run this program with -h to see available commands.");
            System.exit(1);
        } else if (args[0].equals("-h") || args[0].equals("--help")) {
            printHelp();
        } else if (args[0].equals("-f") || args[0].equals("--file")) {
            if (args.length < 2) {
                System.err.println("Please specify a file or path.");
            } else {
                String filePath = args[1];
                runFile(filePath, false);
            }
        } else if (args[0].equals("-i") || args[0].equals("--interactive")) {
            System.out.println("Interactive mode not yet implemented.");
            System.out.println("If you're seeing this, forgot to implement the REPL.");
        } else {
            System.err.println("Unknown parameter.");
            System.err.println("Run this program with -h to see available commands.");
        }
    }
}
