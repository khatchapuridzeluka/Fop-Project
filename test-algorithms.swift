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
var N = 13
var isPrime = true
if N < 2 {
    isPrime = false
} else {
    for i in 2..<N {
        if N % i == 0 {
            isPrime = false
            break
        }
    }
}
print(isPrime) // Output: true

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
while number != 0 {
    let digit = number % 10
    if digit > maxDigit {
        maxDigit = digit
    }
    number /= 10
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
