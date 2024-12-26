package interpreter;

import java.nio.file.Files;
import java.nio.file.Paths; 
import java.util.HashMap;
import java.util.Set;
import java.io.IOException;

public class Interpreter {
    private String url;
    private HashMap<String, Integer> map = new HashMap<>();
    private Functions functions = new Functions();
    private Set<Character> operations = Set.of('+', '-', '%', '/', '*');

    public Interpreter(String url) {
        this.url = url;
    }

    // Reading the .txt file and compiling
    public void readSwift() {
        try {
            String content = Files.readString(Paths.get(url));
            compile(content.split("\\s+"));
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Compiling and executing the code
    private void compile(String[] code) {
        for (int i = 0; i < code.length; i++) {
            // Handle variable declaration (var keyword)
            if (code[i].equals(Keys.var.name())) {
                functions.variableAssignment(code, i, true, operations, map);
            }

            // Handle variable reassignment (= symbol)
            if (code[i].charAt(0) == '=') {
               functions.variableAssignment(code, i, false,operations,map);
            }

            // Printing variables
            if (code[i].startsWith(Keys.print.name())) {
                String varName = code[i].substring(6, code[i].length() - 1); // Extract variable name
                functions.print(varName, map);
            }
        }
    }
    


}
