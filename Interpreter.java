package interpreter;

import java.nio.file.Files;
import java.nio.file.Paths; 
import java.util.HashMap;
import java.util.Set;
import java.io.IOException;

public class Interpreter {
	private String url;
	private HashMap<String, Integer> map = new HashMap<>();
	Functions functions = new Functions();
	private Set<Character> operations = Set.of('+', '-', '%','/', '*');
	public Interpreter(String url) {
		this.url = url;
	}
	
	// reading the .txt file
	public void readSwift() {
		String content = "";
	    try {
	    	content = Files.readString(Paths.get(url));
	    } catch (IOException e) {
	        System.out.println("An error occurred: " + e.getMessage());
	    }
	    String[] code = content.split("\\s+");
	    
	    compile(code);
	   
	}
	
	// executring the code 
	private void compile(String[] code) {		
		for(int i =0; i < code.length; i ++) {
			// inisializing the variable
			if(code[i].equals(Keys.var.name())) {
				int index = i+1;
				int sum = 0;
				if(Character.isDigit(code[i+3].charAt(0))) {
					sum = Integer.valueOf(code[i+3]);
				}else {
					sum+=map.get(code[i+3]);
				}
				i = i+4;
				while(operations.contains(code[i].charAt(0))) {
					sum = functions.calculate(code[i].charAt(0), code[i+1], sum, map);
					i=i+2;
				}
				functions.setValue(code[index],sum, map);
				i = i-1;
			}
			
			/*Character.isDigit(code[i+3].charAt(0)) 
					&& !operations.contains(code[i+4].charAt(0))*/
			// needs to be repaired ( ugly code ) 
			// printing the variable
			if(code[i].startsWith(Keys.print.name())) {
				String varName = "";
				int j = 6;
				while(code[i].charAt(j) !=')') {
					varName+=code[i].charAt(j);
					j++;
				}
				functions.print(varName, map);
			}
	}
	}
}
