//HERE ARE SOME BASIC TEST ALGOLRITHMS IN SWIFT

1. SUM OF FIRST N NUMBERS
var N = 10
var sum = 0
while N > 0 {
	sum = sum + N
	N = N - 1
}
print(sum) // output 55

2. FACTORIAL OF N

var N = 5
var mul = 1
while N > 0 {
	mul = mul * N
	N = N - 1
}
print(mul) // 120

3. GCD OF TWO NUMBERS
var a = 48
var b = 18
while b != 0 {
    var temp = b
    b = a % b
    a = temp
}
print(a) // Output: 6

4. Reverse a Number
var number = 1234
var reversed = 0
var temp = 0
while number != 0 {
	reversed = reversed * 10
	temp = number % 10
	reversed = reversed + temp
	number = number / 10
}
print(reversed) // output 4321


5. Check if a Number is Prime
var N = 30
var isPrime = 0
var counter = 2
while counter < N {
	if( N % counter == 0 ) {
		isPrime = isPrime + 1
	}
	counter = counter + 1
}

if( isPrime > 0 ) {
	print("This number is not prime")
}

if( isPrime = 0 ) {
	print("This number is prime")
}

6.  Check if a Number is Palindrome
var a = 1221
var c = a
var b = 0
var temp = 0

while a != 0 {
	b = b * 10
	temp = a % 10
	b = b + temp
	a = a / 10
}

if( c == b ) {
	print("it is a palindrome")
}

if( c != b ) {
	print("this is not a palindrome")
}

7. Find the Largest Digit in a Number
var number = 3947

var maxDigit = 0

var digit = 0


while number != 0 {
	digit = number % 10
	if( digit > maxDigit ) {
		maxDigit = digit
	}
	number = number / 10
}

print(maxDigit) // Output: 9

8. Sum of Digits
var number = 1234
var sum = 0
var temp = 0
while number != 0 {
	temp = number % 10
	sum = sum + temp
	number = number / 10
}
print(sum) // Output: 10

9. Multiplication Table
var N = 5
var k = 1
var result = 1

while k < 11 {
	result = k * N
	print(result)
	k = k + 1
}
// Output:
// 5
// 10
// 15
// 20
// 25
// 30
// 35
// 40
// 45
// 50

10. Nth Fibonacci Number

var first = 0
var second = 1
var count = 1

var N = 10

var next = 0

while count < N {
 	next = first + second
 	first = second
 	second = next
 	count = count + 1
}

print(second)    // 1 1 2 3 5 8 13 21 34


/* on output we need 
 1. Sum of First N Numbers
int N = 10;
int sum = 0;
while (N > 0) {
    sum = sum + N;
    N = N - 1;
}
System.out.println(sum); // Output: 55

// 2. Factorial of N
int N = 5;
int mul = 1;
while (N > 0) {
    mul = mul * N;
    N = N - 1;
}
System.out.println(mul); // Output: 120

// 3. GCD of Two Numbers
int a = 48, b = 18;
while (b != 0) {
    int temp = b;
    b = a % b;
    a = temp;
}
System.out.println(a); // Output: 6

// 4. Reverse a Number
int number = 1234;
int reversed = 0;
while (number != 0) {
    int temp = number % 10;
    reversed = reversed * 10 + temp;
    number = number / 10;
}
System.out.println(reversed); // Output: 4321

// 5. Check if a Number is Prime
int N = 13;
boolean isPrime = true;
if (N < 2) {
    isPrime = false;
} else {
    for (int i = 2; i < N; i++) {
        if (N % i == 0) {
            isPrime = false;
            break;
        }
    }
}
System.out.println(isPrime); // Output: true

// 6. Check if a Number is Palindrome
int a = 1221, c = a, b = 0;
while (a != 0) {
    int temp = a % 10;
    b = b * 10 + temp;
    a = a / 10;
}
if (c == b) {
    System.out.println("It is a palindrome");
} else {
    System.out.println("This is not a palindrome");
}

// 7. Find the Largest Digit in a Number
int number = 3947;
int maxDigit = 0;
while (number != 0) {
    int digit = number % 10;
    if (digit > maxDigit) {
        maxDigit = digit;
    }
    number = number / 10;
}
System.out.println(maxDigit); // Output: 9

// 8. Sum of Digits
int number = 1234;
int sum = 0;
while (number != 0) {
    int temp = number % 10;
    sum = sum + temp;
    number = number / 10;
}
System.out.println(sum); // Output: 10

// 9. Multiplication Table
int N = 5, k = 1;
while (k <= 10) {
    int result = k * N;
    System.out.println(result);
    k = k + 1;
}
// Output:
// 5
// 10
// 15
// 20
// 25
// 30
// 35
// 40
// 45
// 50

// 10. Nth Fibonacci Number
int first = 0, second = 1, count = 1;
int N = 10, next = 0;
while (count < N) {
    next = first + second;
    first = second;
    second = next;
    count = count + 1;
}
System.out.println(second); // Output: 34 (10th Fibonacci number)
*/
