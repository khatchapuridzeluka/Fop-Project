package interpreter;

import java.util.Set;

public interface Functionable {
	
	// setting the value to the variable 
	void setValue(String variable, int value);
	
	// assigning the variable
	void variableAssignment(String[] code, int i, boolean isVarDeclaration, Set<Character> operations);

	// handling ++ and --
	void handleIncDec(String string);
	
	// printing the variable or just text
	void print(String name, boolean isVar);
	
	// handling print function
	int handlePrint(String[] code, int i);
	
	// calculating arithmetic operations
	int calculate(char operand, String s, int sum);
	
	// checks if the condition is true
	boolean isTrue(int sum, int otherSum, char statement);
	
	// handling if statement
	int handleIf(String[] code, int i, Set<Character> statements);

	// handling else statment
	int handleELSE(String[] code, int i);
	
	// handling while loop
	int handleWhile(String[] code, int i);
	
	// checks if there is while going on
	int handleIsWhile(String[] code, int i);
	
}
