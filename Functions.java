package interpreter;

import java.util.HashMap;

public class Functions implements Functionable{

	@Override
	// initializing variables
	public void setValue(String variable, int value, HashMap<String, Integer> map) {
		if(!map.containsKey(variable)) {
			map.put(variable, value);
		}
		else {
			System.err.println("The variable already exists");
			return;
		}
	}
	
	@Override
	// printing variables
	public void print(String name, HashMap<String,Integer> map) {
		if(!map.containsKey(name)) {
			// Exceptions will be added 
			System.err.print("Variable not found exception");
			//return;
		}else {
			System.out.println(map.get(name));
		}
	}

	@Override
	public int calculate(char operand, String s, int sum, HashMap<String, Integer> map) {
		if(operand == '+') {
			if(Character.isDigit(s.charAt(0))) {
				sum+=Integer.parseInt(s);
			}else {
				sum+=map.get(s);
			}
		}
		if(operand == '-') 
			if(Character.isDigit(s.charAt(0))) {
				sum-=Integer.parseInt(s);
			}else {
				sum-=map.get(s);
			}
		
		if(operand =='/')
			if(Character.isDigit(s.charAt(0))) {
				sum/=Integer.parseInt(s);
			}else {
				sum/=map.get(s);
			}
		
		if(operand =='*')
			if(Character.isDigit(s.charAt(0))) {
				sum*=Integer.parseInt(s);
			}else {
				sum*=map.get(s);
			}
		if(operand =='%')
			if(Character.isDigit(s.charAt(0))) {
				sum%=Integer.parseInt(s);
			}else {
				sum%=map.get(s);
			}
		return sum;
	}
}
