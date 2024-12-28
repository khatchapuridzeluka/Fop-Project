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
    private Set<Character> statements = Set.of('>','<', '&','|','=','!');
    private int counter = 0;
    private boolean isWhile = false;
    private boolean isIf = false;


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
                functions.variableAssignment(code, i, true, operations, map);
            }

            // Handle variable reassignment (= symbol)
            if (code[i].charAt(0) == '=') {
               functions.variableAssignment(code, i, false,operations,map);
            }
            
            
            // HANDLE IF STATEMENT
            if(code[i].startsWith(Keys.IF.toString())) {

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
            //print("Hello World")
            //print("hello")
            // Printing variables
            if (code[i].startsWith(Keys.PRINT.toString())) {
            	if(code[i].charAt(6) !='"') {
            		functions.print(code[i].substring(6,code[i].length()-1), map, true);
            	}else {
            		String text = "";
            		if(code[i].charAt(code[i].length()-1) == ')') {
            			text+=code[i].substring(7,code[i].length()-2);
            		}else {
            			while(true) {
            				text+=code[i];
            				text+=" ";
            				if(code[i].charAt(code[i].length()-1) == ')') {
            					break;
            				}
            				i++;
            			}
            			text = text.substring(7,text.length()-3);
            		}
            		System.out.println(text);
            	}
            }
            
            // WHILE
            if(code[i].equals(Keys.WHILE.toString())) {
            	counter = 0;
            	isIf = false;
            	isWhile = false;
            	int sum = map.get(code[i+1]);
            	char state = code[i+2].charAt(0);
            	int otherSum = Character.isDigit(code[i+3].charAt(0))
            			? Integer.parseInt(code[i+3]) 
            			: map.get(code[i+3]);
            	if(!functions.isTrue(sum, otherSum, state)) {
            		while(code[i].charAt(0) !='}' ) {
            			i++;
            		}
            	}else {
            		isWhile = true;
            		i = i+5;
               		int j = i;
            		while(code[j].charAt(0) != '}') {
            			if(code[j].startsWith("if")) {
            				isIf = true;
            			}
            			j++;
            		}
            		i--;
            	}
            }
            
            // if we are in while loop, we have to jump back!
            if(isWhile) {
            	//System.out.println("here");
            	if(code[i].charAt(0) == '}' && !isIf) {
            		while(!code[i].equals("while")) {
            			i--;
            		}
            		i--;
            	}
            	
            	if(code[i].charAt(0) == '}' && isIf) {
            		counter++;
            	}
            	
            	if(counter == 2) {
            		while(!code[i].equals("while")) {
            			i--;
            			//System.out.println(code[i]);
            		}
            		i--;
            	}
            	
            }
        }
    }
}
