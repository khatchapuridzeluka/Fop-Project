//HERE ARE SOME BASIC TEST ALGOLRITHMS IN SWIFT

1. SUM OF FIRST N NUMBERS
var N = 10
var sum = 0
for i in 1...N {
    sum += i
}
print(sum) // Output: 55

2. FACTORIAL OF N

va N = 5
var result = 1
for i in 1...N {
    result *= i
}
print(result) // Output: 120

3. GCD OF TWO NUMBERS
var a = 48
var b = 18
while b != 0 {
    let temp = b
    b = a % b
    a = temp
}
print(a) // Output: 6

4. Reverse a Number
var number = 1234
var reversed = 0
while number != 0 {
    reversed = reversed * 10 + number % 10
    number /= 10
}
print(reversed) // Output: 4321

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
var number = 121
var original = number
var reversed = 0
while original != 0 {
    reversed = reversed * 10 + original % 10
    original /= 10
}
print(number == reversed) // Output: true

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
while number != 0 {
    sum += number % 10
    number /= 10
}
print(sum) // Output: 10

9. Multiplication Table
var N = 5
for i in 1...10 {
    print(N * i)
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

var N = 10
var a = 0
var b = 1
var fib = 0
if N == 0 {
    fib = 0
} else if N == 1 {
    fib = 1
} else {
    for _ in 2...N {
        fib = a + b
        a = b
        b = fib
    }
}
print(fib) // Output: 34
