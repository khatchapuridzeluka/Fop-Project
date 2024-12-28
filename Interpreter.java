package interpreter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;
import java.io.IOException;

public class Interpreter {
    private String url;
    private HashMap<String, Integer> map = new HashMap<>();
    private Functions functions = new Functions(map);
    private Set<Character> operations = Set.of('+', '-', '%', '/', '*');
    private Set<Character> statements = Set.of('>','<', '&','|','=','!');

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
        	
            if (code[i].equals(Keys.VAR.toString())) {
                functions.variableAssignment(code, i, true, operations);
            }

            // Handle variable reassignment (= symbol)
            
            if (code[i].charAt(0) == '=') {
               functions.variableAssignment(code, i, false,operations);
            }
            
            
            // HANDLE IF STATEMENT
            
            if(code[i].startsWith(Keys.IF.toString())) {
            	i = functions.handleIf(code, i, statements);
            }


            // Printing variables
            
            if (code[i].startsWith(Keys.PRINT.toString())) {
            	i = functions.handlePrint(code, i);
            }
            
            // WHILE
            if(code[i].equals(Keys.WHILE.toString())) {
            	i = functions.handleWhile(code, i);
            }

            // if we are in while loop, we have to jump back!
            boolean isWhile = functions.getIsWhile();
            if(isWhile) {
            	i = functions.handleIsWhile(code, i);
            }
            
            // HANDLING ++ AND -- ( without space: x++)
            if(code[i].length() > 2) {
            	functions.handleIncDec(code[i]);
            }
            

            

        }
    }
}
