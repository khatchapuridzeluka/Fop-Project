package interpreter;

import java.util.HashMap;
import java.util.Set;

public class Functions implements Functionable{

	@Override
	// Initializing variables 
	public void setValue(String variable, int value, HashMap<String, Integer> map) {
		map.put(variable, value);
	}
	
	@Override
	// Printing variables
	public void print(String name, HashMap<String,Integer> map, boolean isVar) {
		if(isVar) {
			if(!map.containsKey(name)) {
				// Exceptions will be added 
				System.err.print("Variable not found exception");
				//return;
			}else {
				System.out.println(map.get(name));
			}
		}else {
			System.out.println(name);
		}
	}

	@Override
	public int calculate(char operand, String s, int sum, HashMap<String, Integer> map) {
	    int value = Character.isDigit(s.charAt(0)) ? Integer.parseInt(s) : map.get(s);

	    switch (operand) {
	        case '+' -> sum += value;
	        case '-' -> sum -= value;
	        case '/' -> sum /= value;
	        case '*' -> sum *= value;
	        case '%' -> sum %= value;
	    }
	    return sum;
	}

	@Override
	public void variableAssignment(String[] code, int i, boolean isVarDeclaration,Set<Character> operations, HashMap<String,Integer> map) {
	    // Determine the variable name based on the context
	    String variable = isVarDeclaration ? code[++i] : code[--i];

	    // Initialize the sum
	    int sum = Character.isDigit(code[i + 2].charAt(0))
	            ? Integer.parseInt(code[i + 2])
	            : map.get(code[i + 2]);
	    i += 3;

	    // Process operations
	    while (operations.contains(code[i].charAt(0))) {
	        sum = this.calculate(code[i].charAt(0), code[++i], sum, map);
	        i++;
	    }

	    // Set the variable value in the map
	   this.setValue(variable, sum, map);
	    i--;
	}

	@Override
	public boolean isTrue(int sum, int otherSum, char statement) {
	    return switch (statement) {
        case '>' -> sum > otherSum;
        case '<' -> sum < otherSum;
        case '=' -> sum == otherSum;
        case '!' -> sum != otherSum;
        default -> throw new IllegalArgumentException("Invalid statement: " + statement);
	    };
	}


}
