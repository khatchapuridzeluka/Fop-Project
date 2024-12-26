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
            // Initializing variables
            if (code[i].equals(Keys.var.name())) {
                String variable = code[++i];
                int sum = Character.isDigit(code[i + 2].charAt(0))
                        ? Integer.parseInt(code[i + 2])
                        : map.get(code[i + 2]);
                i += 3;

                // Process operations
                while (operations.contains(code[i].charAt(0))) {
                    sum = functions.calculate(code[i].charAt(0), code[++i], sum, map);
                    i++;
                }
                functions.setValue(variable, sum, map);
                i--;
            }

            // Printing variables
            if (code[i].startsWith(Keys.print.name())) {
                String varName = code[i].substring(6, code[i].length() - 1); // Extract variable name
                functions.print(varName, map);
            }
        }
    }
}
