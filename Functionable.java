package interpreter;

import java.util.HashMap;

public interface Functionable {
	void setValue(String variable, int value, HashMap<String,Integer> map);
	void print(String name, HashMap<String,Integer> map);
	int calculate(char operand, String s, int sum, HashMap<String,Integer> map);
}
