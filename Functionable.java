package interpreter;

import java.util.HashMap;
import java.util.Set;

public interface Functionable {
	void setValue(String variable, int value, HashMap<String,Integer> map);
	void print(String name, HashMap<String,Integer> map);
	void variableAssignment(String[] code, int i, boolean isVarDeclaration, Set<Character> operations, HashMap<String,Integer> map);
	int calculate(char operand, String s, int sum, HashMap<String,Integer> map);
}
