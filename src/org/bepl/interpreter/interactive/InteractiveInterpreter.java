package org.bepl.interpreter.interactive;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.bepl.interpreter.types.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.TokenStream;
import org.bepl.interpreter.BEPLCustomListener;
import org.bepl.interpreter.BEPLLexer;
import org.bepl.interpreter.BEPLListener;
import org.bepl.interpreter.BEPLMain;
import org.bepl.interpreter.BEPLParser;

public final class InteractiveInterpreter {
    private BEPLFile currentFile;
    private HashMap<String, Command> commands;
    private boolean isInitialized = false;
    private boolean debugEnabled = false;
    private final String prefix;
    private final BEPLListener listener;
    private final Command[] defaultCommands = {
        new Command("help") {
            @Override
            public void execute(String[] args) {
                for (String name : commands.keySet()) {
                    Command cmd = commands.get(name);
                    System.out.println("    " + name + " -> " + cmd.about());
                }
            }
            
            @Override
            public String about() {
                return "Prints this message.";
            }
        },
        new Command("debug") {
          @Override
          public void execute(String[] args) {
              debugEnabled = !debugEnabled;
              System.out.println("Debug output: " + debugEnabled);
          }
          
          @Override
          public String about() {
              return "Enable / Disable debug output.";
          }
        },
        new Command("sys") {
            @Override
            public void execute(String[] args) {
                if (args.length < 2) {
                    System.out.println("Please provide a system command to run.");
                } else {
                    try {
                        new ProcessBuilder("cmd", "/c", args[1]).inheritIO().start().waitFor();
                    } catch (Exception e) {
                        System.out.println("Could not run system command.");
                    }   
                }
            }
            
            @Override
            public String about() {
                return "Runs a system command.";
            }
        },
        new Command("exit") {
            @Override
            public void execute(String[] args) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            
            @Override
            public String about() {
                return "Exits BEPLi.";
            }
        }
    };
    
    /**
     * Creates an interactive interpreter with 
     * the default prefix and an empty BEPLFile.
     * Prefix: ":"
     */
    public InteractiveInterpreter() {
        prefix = ":";
        currentFile = new BEPLFile();
        listener = new BEPLCustomListener();
        commands = new HashMap<String, Command>();
    }
    
    /**
     * Creates an interactive interpreter with
     * the default command prefix and the BEPLFile 
     * at the specified path.</br>
     * Prefix: ":"
     * @param path the path to the file.
     * @throws FileNotFoundException if file is inaccessible or the path does not exist.
     */
    public InteractiveInterpreter(String path) throws FileNotFoundException {
        this.prefix = ":";
        currentFile = new BEPLFile(path);
        listener = new BEPLCustomListener();
        commands = new HashMap<String, Command>();
    }
    
    /**
     * Creates an interactive interpreter with
     * a custom command prefix and the BEPLFile 
     * at the specified path.
     * @param prefix the custom command prefix.
     * @param path the path to the file.
     * @throws FileNotFoundException if file is inaccessible or the path does not exist.
     */
    public InteractiveInterpreter(String prefix, String path) throws FileNotFoundException {
        this.prefix = prefix;
        currentFile = new BEPLFile(path);
        listener = new BEPLCustomListener();
        commands = new HashMap<String, Command>();
    }
    
    private void runCommand(String input) {
        String[] inputArray = input.split(" ");
        String command = inputArray[0].substring(prefix.length(), inputArray[0].length());
        
        if (!commands.containsKey(command)) {
            System.out.println("Invalid command. Type " + prefix + "help to list all available commands.");
        } else {
            commands.get(command).execute(inputArray);
        }
    }
    
    /**
     * The main REPL method. The REPL will
     * interpret inputs as either code or commands
     * depending on whether the string starts with
     * the prefix or not.
     */
    private void repl() {
        System.out.println("Welcome to BEPLi!");
        
        Scanner s = new Scanner(System.in);
        
        System.out.print(currentFile.NAME + "=> ");
        while (s.hasNext()) {
            String input = s.nextLine();
            
            if (input.startsWith(prefix)) { // We're looking at a command.
                runCommand(input);
            } else {
                currentFile.addLine(input);
                
                CharStream file = CharStreams.fromString(currentFile.toString());
                BEPLLexer lexer = new BEPLLexer(file);
                TokenStream tokens = new CommonTokenStream(lexer);
                BEPLParser parser = new BEPLParser(tokens);
                parser.addParseListener(listener);
                
                BEPLCustomListener.debug = debugEnabled;
                
                try {
                    parser.program();
                } catch (RecognitionException re) {
                    currentFile.removeLine();
                }
            }
            System.out.print(currentFile.NAME + "=> ");
        }
        
        s.close();
    }
    
    /**
     * Initializes the interpreter.
     * This method should only be called
     * one time, and has no effect when
     * called multiple times.
     * 
     * The initialize() method registers
     * all default commands. If a command
     * of the same name is already registered,
     * the default command behavior will overwrite
     * the registered command's behavior.
     * 
     * BEPLi is definitely not a pun on GHCi...
     */
    public void initialize() {
        if (isInitialized) {
            System.err.println("BEPLi has already been initialized.");
        } else {
            System.out.println("Initializing BEPLi");
            System.out.println("Registering default commands...");
            registerCommand(defaultCommands);
            System.out.println("BEPLi has been successfully initialized.");
            isInitialized = true;
        }
    }
    
    /**
     * Registers a new command to BEPLi.
     * This method should be called after
     * calling initialize().
     * @param cmd the command to be registered.
     */
    public void registerCommand(Command cmd) {
        commands.put(cmd.getName(), cmd);
    }
    
    /**
     * Registers multiple new commands
     * to BEPLi. This method should
     * be called after calling
     * initialize().
     * @param cmds the commands to be added.
     */
    public void registerCommand(Command[] cmds) {
        for (Command cmd : cmds) {
            commands.put(cmd.getName(), cmd);
        }
    }
    
    /**
     * Starts BEPLi.
     */
    public void start() {
        System.out.println("Starting BEPLi v" + BEPLMain.VERSION + "...");
        System.out.println("PLEASE NOTE: This is an alpha version of BEPL. Use it with caution.");
        repl();
    }
}
