# **SWIFT-TO-JAVA Interpreter**

This project interprets Swift code written in a `swift.txt` file and executes it as Java code.  
The interpreter handles various aspects of Swift syntax and provides feedback on errors or unsupported operations.

## **Team Members**
- **Luka Khatchapuridze** - Handles `while` loops and exceptions.
- **Andria Tsilosani** - Handles `if/else` statements.
- **Zurab Antadze** - Handles variable initialization.
- **Nikoloz Sikhashvili** - Handles printing and calculations with variables.

### **Features**
Supports basic Swift syntax:
- **Variable Initialization and Manipulation**: `var a = 30` / `a = a + 30` > Needs spaces.
- **Printing**: `print(a)` (variable) or `print("Hello World")`. > Does not need additional spaces inside
- **Conditional Statements**: Supports `if`, `else`.
- **Loops**: Supports `while` loops.   > while a < b { ... } -> Needs spaces

### **Error Handling**
- `InvalidVariableNameException`: Triggered if the variable name does not start with an alphabet.
- `VariableNotDeclaredException`: Triggered if attempting to use an undeclared variable.
- `DuplicateFieldException`: Triggered if creating a variable that already exists.

> Includes **10 pre-written testing algorithms** in `swift.txt` for demonstration purposes.

#### **Setup and Usage**
1. **Download the Project**:  
   Clone or download the repository.
   ```bash
   git clone https://github.com/khatchapuridzeluka/Fop-Project
   cd SWIFT-TO-JAVA-Interpreter
2. **Write Swift Algorithm in swift.txt file**
   ```bash
   print("5. Check If a Number is Prime: ")
   var N2 = 13
   var isPrime = 0
   var counter = 2
   while counter < N2 {
     if( N2 % counter == 0 ) {
       isPrime++
     }
     counter++
   }
   
   if(isPrime > 0) {
     print("this number is not prime")
   }else{
     print("this number is prime")
   }

3. **Run the code**
