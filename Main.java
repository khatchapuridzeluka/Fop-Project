package interpreter;

public class Main {
    public static void main(String[] args) throws DuplicateFieldException, VariableNotDeclaredException{
    	//Reading the swift code from the specific .txt file
    	new Interpreter("src/interpreter/swift.txt").readSwift();
    }
}
