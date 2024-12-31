package interpreter;

// Exception for using undeclared variables
public class VariableNotDeclaredException extends Exception{
	private final String variable;
	
	public VariableNotDeclaredException(String variable) {
		super("VariableNotDeclaredException: variable: " + "'" + variable  + "'"+ " is not declared!");
		this.variable = variable;
	}
	
	@Override
	public String toString() {
		return "VariableNotDeclaredException: variable: " + "'" + variable  + "'"+ " is not declared!";
	}
}
