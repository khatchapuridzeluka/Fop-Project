package interpreter;

import java.util.HashMap;
import java.util.Set;

public class Functions implements Functionable{
	private boolean isWhile = false;
	private boolean isIf = false;
	private int counter = 0;
	private HashMap<String, Integer> map;
	
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
	
	@Override
	public int handlePrint(String[] code, int i) {
		if(code[i].charAt(6) !='"') {
    		this.print(code[i].substring(6,code[i].length()-1), true);
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
	public void variableAssignment(String[] code, int i, boolean isVarDeclaration,Set<Character> operations) {
	    // Determine the variable name based on the context
	    String variable = isVarDeclaration ? code[++i] : code[--i];

	    // Initialize the sum
	    int sum = this.getSumValue(code[i+2], map);
	    i += 3;

	    // Process operations
	    while (operations.contains(code[i].charAt(0))) {
	        sum = this.calculate(code[i].charAt(0), code[++i], sum);
	        i++;
	    }

	    // Set the variable value in the map
	   this.setValue(variable, sum);
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
	
	public int getSumValue(String num, HashMap<String,Integer> map) {
		return Character.isDigit(num.charAt(0))
				? Integer.parseInt(num) 
						: map.get(num);
		
	}

	@Override
	public int handleIf(String[] code, int i, Set<Character> statements) {
		int sum = this.getSumValue(code[++i], map);
    	
    	while(!statements.contains(code[i+1].charAt(0))) {
    		sum=this.calculate(code[i+1].charAt(0), code[i+2], sum);
    		i+=2;
    	}
    	i++;
    	
    	char state = code[i].charAt(0);
    	
    	int otherSum = this.getSumValue(code[++i], map);
    	while(code[i+1].charAt(0) != ')') {
    		otherSum=this.calculate(code[i+1].charAt(0), code[i+2], otherSum);
    		i+=2;
    	}
    	
    	
    	if(this.isTrue(sum, otherSum, state)) {
    		i=i+2;
    	}else {
    		while(code[i].charAt(0) !='}') {
    			i++;
    		}
    	}
		return i;
	}

	@Override
	public int handleWhile(String[] code, int i) {
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






