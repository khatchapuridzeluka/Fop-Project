package interpreter;

import java.util.HashMap;
import java.util.Set;

public interface Functionable {
	// setting the value to the variable ( final result)
	void setValue(String variable, int value, HashMap<String,Integer> map);
	// printing the variable
	void print(String name, HashMap<String,Integer> map);
	// calculating each operand
	int calculate(char operand, String s, int sum, HashMap<String,Integer> map);
	// getting the final result ( should be changed, calculate and variableAssignment should be used together in order ro avoid bugs)
	void variableAssignment(String[] code, int i, boolean isVarDeclaration, Set<Character> operations, HashMap<String,Integer> map);
}
