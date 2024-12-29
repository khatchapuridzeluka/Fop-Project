package interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Functions implements Functionable{
	private boolean isWhile = false;
	private boolean isIf = false;
	private int counter = 0;
	private HashMap<String, Integer> map;
	private boolean ELSE = false;
	private ArrayList<String> tempVariables = new ArrayList();
	
	public boolean getELSE() {
		return this.ELSE;
	}
	
	public Functions(HashMap<String, Integer> map) {
		this.map = map;
	}
	
	public boolean getIsWhile() {
		return isWhile;
	}
	

	@Override
	// initializing variables
	public void setValue(String variable, int value) {
		map.put(variable, value);
	}
	
	@Override
	public void variableAssignment(String[] code, int i, boolean isVarDeclaration,Set<Character> operations) throws DuplicateFieldException, VariableNotDeclaredException {
	    // Determine the variable name based on the context
	    String variable = isVarDeclaration ? code[++i] : code[--i];
	    
	    if(isVarDeclaration && isWhile) {
	    	this.tempVariables.add(variable);
	    }
	    
	    if(isVarDeclaration && map.containsKey(code[i])) {
	    	throw new DuplicateFieldException(code[i]);
	    }
	    
	    if(!isVarDeclaration && !map.containsKey(code[i])) {
	    	throw new VariableNotDeclaredException(code[i]);
	    }

	    // Initialize the sum
	    if(Character.isAlphabetic(code[i+2].charAt(0)) && !map.containsKey(code[i+2]))
	    	throw new VariableNotDeclaredException(code[i+2]);
	    int sum = this.getSumValue(code[i+2], map);
	    
	    if(i < code.length -4) {
	    	i += 3;
	    }
	    
	    // Process operations
	    while (operations.contains(code[i].charAt(0)) && i <= code.length-3) { 
	    	if(Character.isAlphabetic(code[i+1].charAt(0)) && !map.containsKey(code[i+1]))
	    		throw new VariableNotDeclaredException(code[i+1]);
	        sum = this.calculate(code[i].charAt(0), code[i+1], sum);
	        i+=2;
	    }

	    // Set the variable value in the map
	   this.setValue(variable, sum);
	    i--;
	}

	
	@Override
	// printing variables
	public void print(String name, boolean isVar) {
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
	
	// handlePrint is the one calling print method. it is choosing between variable and text
	@Override
	public int handlePrint(String[] code, int i) throws VariableNotDeclaredException {
		if(code[i].charAt(6) !='"') {
			String temp = code[i].substring(6,code[i].length()-1);
			if(map.containsKey(temp))
				this.print(temp, true);
			else throw new VariableNotDeclaredException(temp);
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
		return i;
	}

	
	@Override
	public int calculate(char operand, String s, int sum) {
	    int value = this.getSumValue(s, map);

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
	public boolean isTrue(int sum, int otherSum, char statement) {
	    return switch (statement) {
        case '>' -> sum > otherSum;
        case '<' -> sum < otherSum;
        case '=' -> sum == otherSum;
        case '!' -> sum != otherSum;
        default -> throw new IllegalArgumentException("Invalid statement: " + statement);
	    };
	}
	
	public int getSumValue(String num, HashMap<String,Integer> map) {
		return Character.isDigit(num.charAt(0))
				? Integer.parseInt(num) 
						: map.get(num);
		
	}

	@Override
	//if(a>b) 
	public int handleIf(String[] code, int i, Set<Character> statements) {
		ELSE = false;
		ArrayList<String> list = new ArrayList<>();
		StringBuilder string = new StringBuilder();

		// Process the input into tokens
		while (code[i].charAt(code[i].length() - 1) != ')' && code[i].charAt(code[i].length() - 1) != '{') {
		    if (!code[i].trim().isEmpty()) {
		        list.add(code[i]);
		        list.add(" ");
		    }
		    i++;
		}

		// Handle the last token
		if (code[i].charAt(0) != ')') {
		    if (code[i].charAt(code[i].length() - 1) == '{') {
		        list.add(code[i].substring(0, code[i].length() - 2));
		    } else {
		        list.add(code[i].substring(0, code[i].length() - 1));
		    }
		}

		// Adjust the first token
		if (list.get(0).length() != 3) {
		    list.set(0, list.get(0).substring(3));
		} else {
		    list.remove(0);
		}

		// Build the string and split into array
		for (String st : list) {
		    string.append(st);
		}
		String[] arr = string.toString().split(" ");

		// Initialize counters and sums
		int cnt = arr[0].isEmpty() ? 1 : 0;
		int sum = this.getSumValue(arr[cnt], map);

		// Calculate the first sum
		while (!statements.contains(arr[cnt + 1].charAt(0))) {
		    sum = this.calculate(arr[cnt + 1].charAt(0), arr[cnt + 2], sum);
		    cnt += 2;
		}

		// Process the operator and the second sum
		char op = arr[cnt + 1].charAt(0);
		cnt += 2;
		int otherSum = this.getSumValue(arr[cnt], map);

		while (cnt < arr.length - 1) {
		    otherSum = this.calculate(arr[cnt + 1].charAt(0), arr[cnt + 2], otherSum);
		    cnt += 2;
		}

		// Evaluate the condition
		if (this.isTrue(sum, otherSum, op)) {
		    return i;
		} else {
		    while (code[i].charAt(0) != '}') {
		        i++;
		        ELSE = true;
		    }
		    return i;
		}

	}

	@Override
	public int handleELSE(String[] code, int i) {
		if(!this.getELSE()) {
			while(code[i].charAt(code[i].length()-1) != '}') {
				i++;
			}
		}
		return i;
	}
	
	@Override
	public int handleWhile(String[] code, int i) {
		for(String variable : this.tempVariables) {
			map.remove(variable);
		}
		this.tempVariables.clear();

    	counter = 0;
    	isIf = false;
    	isWhile = false;
    	int sum = map.get(code[i+1]);
    	
    	char state = code[i+2].charAt(0);
    	
    	int otherSum = this.getSumValue(code[i+3], map);
    	
    	if(!this.isTrue(sum, otherSum, state)) {
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
    	return i;
	}

	@Override
	public int handleIsWhile(String[] code, int i) {
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
		//System.out.println(this.tempVariables);

    	if(counter == 2) {
    		while(!code[i].equals("while")) {
    			i--;
    			//System.out.println(code[i]);
    		}

    		i--;
    	}
		return i;
	}

	@Override
	public void handleIncDec(String string) {
        String varName = string.substring(0, string.length() - 2);
		int length = string.length();
		if(string.charAt(length-1) == Keys.PLUSPLUS.toString().charAt(0) 
				&& string.charAt(length-2) == Keys.PLUSPLUS.toString().charAt(0)) 
            map.put(varName, map.getOrDefault(varName, 0) + 1);

		if(string.charAt(length-1) == Keys.MINUSMINUS.toString().charAt(0)
				&& string.charAt(length-2) == Keys.MINUSMINUS.toString().charAt(0)) 
            map.put(varName, map.getOrDefault(varName, 0)  -1);
	}
	
}






