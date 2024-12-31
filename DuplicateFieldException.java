package interpreter;

//Exception for already existed variable declaration
public class DuplicateFieldException extends Exception{
	private final String variable;
	
	public DuplicateFieldException(String variable) {
		super("DuplicateFieldException: variable: " + variable + "already exists!");
		this.variable = variable;
	}
	
	@Override
	public String toString() {
		return "DuplicateFieldException: variable: " + "'" + variable  + "'"+ " already exists!";
	}
}
