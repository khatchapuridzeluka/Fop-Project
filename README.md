SWIFT-TO-JAVA Interpreter
This project interprets Swift code written in a swift.txt file and executes it as Java code. The interpreter handles various aspects of Swift syntax and provides feedback on errors or unsupported operations.
a
Team Members
Luka Khatchapuridze - Handles while loops and exceptions.
Andria Tsilosani - Handles if/else statements.
Zurab Antadze - Handles variable initialization.
Nikoloz Sikhashvili - Handles printing and calculations with variables.
Features
Supports basic Swift syntax:

Variable initialization and manipulation: var a = 30 / a = a + 30.
Printing: print(a) (variable) or print("Hello World").
Conditional statements: if, else.
Loops: while loops.
Error Handling:

InvalidVariableNameException: Triggered if the variable name does not start with an alphabet.
VariableNotDeclaredException: Triggered if attempting to use an undeclared variable.
DuplicateFieldException: Triggered if creating a variable that already exists.
Includes 10 pre-written testing algorithms in swift.txt for demonstration purposes.

Setup and Usage
Download the Project: Clone or download the repository.

bash
Copy code
git clone https://github.com/khatchapuridzeluka/Fop-Project
cd SWIFT-TO-JAVA-Interpreter
Write Your Algorithm: Write or edit your Swift code in the swift.txt file.

Variable Initialization: Include spaces, e.g., var a = 30 or a = a + 30.

Printing: No extra spaces required, e.g., print(a) or print("Hello World").

While Loop: Example:
while a < b {
  // Your logic here
}
Run the Interpreter: Execute the interpreter to see the results:

swift
example code:
var a = 10
var b = 20
while a < b {
    print(a)
    a++
}
Expected Output:

plaintext
Copy code
10
11
12
...
19
Contributing
Feel free to contribute to this project by creating a fork and submitting a pull request. Ensure your contributions follow the existing code structure and style guidelines.



