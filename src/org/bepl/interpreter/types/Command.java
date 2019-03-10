package org.bepl.interpreter.types;

public class Command {
    private final String name;
    
    /**
     * Constructs a new command.
     * @param name the name of the command.
     */
    public Command(String name) {
        if (name == "" || name == null)
            throw new RuntimeException("Command name can not be empty or null!");
        this.name = name;
    }
    
    public final String getName() {
        return name;
    }
    
    /**
     * Executes the given command.
     * An implementation of this
     * should be defined when registering
     * a custom command.
     * @param args the arguments of the command.
     * The first argument should always be 
     * the command itself.
     */
    public void execute(String[] args) {
        throw new RuntimeException("You forgot to implement execute() for " + name + ".");
    }
    
    /**
     * Used to provide a description for a 
     * command. An implementation of this
     * should be defined when registering
     * a custom command.
     * @return a string giving a description of the command.
     */
    public String about() {
        throw new RuntimeException("You forgot to implement about() for " + name + ".");
    }
}

