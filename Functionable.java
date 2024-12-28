package interpreter;

import java.util.Set;

public interface Functionable {
	
	void setValue(String variable, int value);
	
	void print(String name, boolean isVar);
	
	int handlePrint(String[] code, int i);
	
	int calculate(char operand, String s, int sum);
	
	void variableAssignment(String[] code, int i, boolean isVarDeclaration, Set<Character> operations);
	
	boolean isTrue(int sum, int otherSum, char statement);
	
	int handleIf(String[] code, int i, Set<Character> statements);
	
	int handleWhile(String[] code, int i);
	
	int handleIsWhile(String[] code, int i);
}
