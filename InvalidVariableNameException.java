package interpreter;

//Exception for invalid variable name ( var 15 = 30 )
public class InvalidVariableNameException extends Exception{
	private final String variable;
	
	public InvalidVariableNameException(String variable) {
		super("InvalidVariableNameException: Variable name can't be: " + variable);
		this.variable = variable;
	}
	
	@Override
	public String toString() {
		return "InvalidVariableNameException: Variable name can't be: " + variable;

	}
}
