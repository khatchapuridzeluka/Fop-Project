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
    private Set<Character> statements = Set.of('>','<', '&','|','=');

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
            
            
            // HANDLE IF STATEMENT
            if(code[i].startsWith(Keys.IF.name().toLowerCase())) {

            	int sum = Character.isDigit(code[++i].charAt(0)) 
            			? Integer.parseInt(code[i]) 
            			: map.get(code[i]);
            	
            	while(!statements.contains(code[i+1].charAt(0))) {
            		sum=functions.calculate(code[i+1].charAt(0), code[i+2], sum, map);
            		i+=2;
            	}
            	i++;
            	
            	char state = code[i].charAt(0);
            	
            	int otherSum = Character.isDigit(code[++i].charAt(0)) 
            			? Integer.parseInt(code[i]) 
            			: map.get(code[i]);
            	
            	while(code[i+1].charAt(0) != ')') {
            		otherSum=functions.calculate(code[i+1].charAt(0), code[i+2], otherSum, map);
            		i+=2;
            	}
            	
            	
            	if(functions.isTrue(sum, otherSum, state)) {
            		i=i+2;
            	}else {
            		while(code[i].charAt(0) !='}') {
            			i++;
            		}
            	}
            	
            }
            
            // Printing variables
            if (code[i].startsWith(Keys.print.name())) {
            	if(code[i].charAt(6) !='"') {
            		functions.print(code[i].substring(6,code[i].length()-1), map, true);
            	}else {
            		String string = code[i].substring(7,code[i].length());
            		string+= " ";
            		i++;
            		while(code[i].charAt(code[i].length()-1) != ')') {
            			string+=code[i];
            			string+=" ";
            			i++;
            		}
            		string+=code[i].substring(0,code[i].length()-2);
            		functions.print(string, map, false);
            	}
            }
        }
    }
}
