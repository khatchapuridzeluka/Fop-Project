package interpreter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.io.IOException;

public class Interpreter {
    private String url;
    private Functions functions = new Functions();
    private Set<Character> operations = Set.of('+', '-', '%', '/', '*');
    private Set<Character> statements = Set.of('>','<', '&','|','=','!');

    public Interpreter(String url) {
        this.url = url;
    }
    
    // Reading the .txt file and compiling
    public void readSwift() throws DuplicateFieldException, VariableNotDeclaredException, InvalidVariableNameException{
        try {
            String content = Files.readString(Paths.get(url));
            interpret(content.split("\\s+"));
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    
    // Compiling and executing the code
    private void interpret(String[] code) throws DuplicateFieldException, VariableNotDeclaredException, InvalidVariableNameException{
        for (int i = 0; i < code.length; i++) {
        	// If the code is nonsense
            // Handle variables declaration (var keyword)	
            if (code[i].equals(Keys.VAR.toString())) {
                functions.variableAssignment(code, i, true, operations);
            }

            // Handle variable reassignment (= symbol)
            if (code[i].charAt(0) == '=') {
               functions.variableAssignment(code, i, false,operations);
            }
            
            // Printing variables
            if (code[i].startsWith(Keys.PRINT.toString())) {
            	i = functions.handlePrint(code, i);
            }
            
            
            // HANDLE IF STATEMENT
            if(code[i].startsWith(Keys.IF.toString())) {
            	i = functions.handleIf(code, i, statements);
            }

            // Handling else statement
            if(code[i].toString().startsWith("else") || code[i].startsWith("}else")) {
            	i = functions.handleELSE(code, i);
            }

            // WHILE
            if(code[i].equals(Keys.WHILE.toString())) {
            	i = functions.handleWhile(code, i);
            }
            
            
            // HANDLING ++ AND -- ( without space: x++)
            if(code[i].length() > 2) {
            	functions.handleIncDec(code[i]);
            }
            

            // If we are in while loop, we have to jump back!
            boolean isWhile = functions.getIsWhile();
            if(isWhile) {
            	i = functions.handleIsWhile(code, i);
            }
            
        }
    }
}
