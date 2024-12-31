# **SWIFT-TO-JAVA Interpreter**

This project interprets Swift code written in a `swift.txt` file and executes it as Java code.  
The interpreter handles various aspects of Swift syntax and provides feedback on errors or unsupported operations.

## **Team Members**
- **Luka Khatchapuridze** (khatchapuridzeluka) - Handles `while` loops and exceptions.
- **Andria Tsilosani** (andriatsilosani1)- Handles `if/else` statements.
- **Zurab Antadze** (johntheripper10) - Handles variable initialization.
- **Nikoloz Sikhashvili** (N0B0DYYYYY) - Handles printing and calculations with variables.

### **Features**
Supports basic Swift syntax:
- **Variable Initialization and Manipulation**: `var a = 30` / `a = a + 30` > Needs spaces.
 ```bash
  var a = 30
  var b = a + 30
```
- **Printing**: `print(a)` (variable) or `print("Hello World")`. > Does not need additional spaces inside
```bash
  print("Hello World!")
```
- **Conditional Statements**: Supports `if`, `else`.
```bash
 if(a % 2 ==0) {
   print("The number is even")
 }else{
   print("The number is odd")
  }
```
- **Loops**: Supports `while` loops. -> Needs spaces
```bash
   while a < b {
     print(a)
     a++
   }  
  ```
### **Error Handling**
- `InvalidVariableNameException`:
```bash
   var 15 = 30
   // Triggered if the variable's name does not start with a letter
```
  

- `VariableNotDeclaredException`: 
```bash
   var a = 30
   print(b)
  // Triggered if attempting to use an undeclared variable.
```
- `DuplicateFieldException`:
```bash
   var a = 30
   var a = 50
  // Triggered if creating a variable that already exists.
```

#### **Setup and Usage**
> Includes **10 pre-written testing algorithms** in `swift.txt` for demonstration purposes.
1. **Download the Project**:  
   Clone or download the repository.
   ```bash
   git clone https://github.com/khatchapuridzeluka/Fop-Project
   ```
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
   ```
3. **Run the code**
