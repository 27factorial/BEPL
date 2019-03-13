package org.bepl.interpreter.types;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BEPLFile {
    private ArrayList<String> lines;
    private int length;
    public final String NAME;
    
    public BEPLFile() {
        lines = new ArrayList<String>();
        length = 0;
        NAME = "";
    }
    
    public BEPLFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner s = new Scanner(file);
        lines = new ArrayList<String>();
        length = 0;
        NAME = file.getName();
        
        while (s.hasNextLine()) {
            lines.add(s.nextLine());
        }
        
        s.close();
    }
    
    /**
     * Adds a line of BEPL code to the internal "file".
     * @param line the line to add.
     */
    public void addLine(String line) {
        lines.add(line);
        length++;
    }
    
    /**
     * Removes the last line in the "file".
     * Useful for cleaning up syntax errors.
     */
    public void removeLine() {
        if (lines.size() >= 1) {
            lines.remove(lines.size() - 1);
            length--;
        }
    }
    
    public int size() {
        return length;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line + "\n");
        }
        
        // Delegate to StringBuilder
        return sb.toString();
    }
}
