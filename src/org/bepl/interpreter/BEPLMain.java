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

import java.io.FileNotFoundException;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.bepl.interpreter.interactive.InteractiveInterpreter;

public class BEPLMain {
    public static final String VERSION = "0.2";

    /**
     * This function runs the specified file in the interpreter.
     * @param path The path of the file.
     * @param debugEnabled Whether or not debug output should be printed.
     */
    private static void runFile(String path, boolean debugEnabled) {
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

    private static void printHelp() {
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
            InteractiveInterpreter interactive = null;
            
            if (args.length > 1) {
                try {
                    interactive = new InteractiveInterpreter(args[1]);
                } catch (FileNotFoundException e) {
                    System.err.println("The specified file could not be found. Opening with an empty file instead.");
                    interactive = new InteractiveInterpreter();
                }
            } else {
                interactive = new InteractiveInterpreter();
            }
            
            interactive.initialize();
            interactive.start();
        } else {
            System.err.println("Unknown parameter.");
            System.err.println("Run this program with -h to see available commands.");
            System.exit(1);
        }
    }
}
