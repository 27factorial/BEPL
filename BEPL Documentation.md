# Basic English-like Programming Language #

## ***NOTE: This documentation is nowhere near complete, and neither is the language. use both at your own risk.*** ##

This is a document detailing the usage and syntax of the Basic English-like
Programming Language, or BEPL for short. The language is implemented in Java,
and is meant to be easy to read and write by humans, while also providing many
of the same features other languages offer, and at a minimal cost to performance.

The language's compiler is referred to as BC, which is short for BEPL Compiler.
I know, it's a revolutionary naming scheme. The compiler works by translating
the entire BEPL source file into a Java source file from top to bottom, which
can then be turned into a Java class file by `javac`.

***<u>TODO: Possible name change? BEPL sounds a bit ridiculous.</u>***

# 1.0 Syntax #

## 1.1 Arithmetic ##

Basic arithmetic can be done with the operators that you've learned in your math
courses, such as:

- `+`, the addition and concatenation operator.
- `-`, the subtraction operator (also can be used for reverse concatenation).
       ***<u>TODO: Decide whether to keep reverse concatenation.</u>***
- `*`, the multiplication operator.
- `/`, the division operator. This rounds down when both operands are 
       `Integer`s.

There are also more operators that might not have an obvious meaning at first
glance, and they are listed below:

- `|`, the bitwise/logical OR operator.  (can also use `or`).
- `&`, the bitwise/logical AND operator. (can also use `and`).
- `!`, the bitwise/logical NOT operator. (can also use `not`).
- `^`, the bitwise/logical XOR operator. This is ***not*** used for 
       exponentiation.
- `%`, the modulo operator. Used to get the remainder of dividing two
       `Integer`s.
- `as`, the casting operator. Used to convert a value from one type to another.
- `**`, the exponentiation operator. This returns an `Integer` when both 
        operands are `Integer`s.
- `//`, the division operator. Used for division, but implicitly converts the
        result to a `Decimal` value, so it does not round down when both
        operands are `Integer`s.

There are more operators to be talked about, especially when it comes to
defining custom types, but those will be talked about in later sections of this
documentation.

Parentheses can also be used to disambiguate certain expressions, such as
dividing and then casting to another type, as in one of the examples below.


#### Examples: ####

```
=> 5 + 5
~> 10

=> 12 - 5
~> 7

=> 2048 * 2
~> 4096

=> 3 / 2
~> 1

=> 3 / 2.0
~> 1.5

=> 3 // 2
~> 1.5

=> 3 / 2 as Decimal # 3 / 2 as Decimal -> 1 as Decimal -> 1.0
~> 1.0

=> 3 / (2 as Decimal) # Using parentheses to diambiguate the expression.
~> 1.5

=> 480327 % 42
~> 15

=> 2 ** 10
~> 1024

=> "Hello, " + "World!"
~> "Hello, World!"

=> "Hello, World!" - "World!" # Not recommended to use.
~> "Hello, "

```


## 1.2 Comments ##

Comments are parts of the source code that do not get converted into Java source
code, and can therefore be used to document your BEPL source code.  

Line comments are denoted with `#`, which means that anything after the `#`  on
the same line will be interpreted as a comment.

Block comments are denoted with a starting `###`, as well as an ending one.
Anything between this set of hash signs will be interpreted as a comment, so
they can be used to create comments that are longer than one line.


#### Examples: ####

```
# I'm a line comment! Everything past the # will not be read by the compiler.

x is an Integer # I can put this at the end of the line too.
x is 5

###
I'm a block comment, that means
I can be
multiline.
You should use these to document functions and methods.
###

```



## 1.3 Variables ##

A variable should be declared first by specifying the type with the `is a` or
`is an` phrase, and then by writing the variable's value using the `is` keyword,
whether it be a literal or some expression (a function call, if statement, or
pattern matching). Variables can also be assigned to a value when their type is
declared by using the `with value` phrase.  

Variables can also be changed, or mutated, by using the `is now` phrase. This is
used to set a new value without giving it a new name or new type.

Variables can also be declared as `constant`, which means that they can only
be assigned once (when they are instantiated), and can not be changed for the
duration of the program. Constants should be named using SCREAMING_SNAKE_CASE to
distinguish them from normal, mutable variables.


#### Notes: ####

1. Normal variables should always start with a lowercase letter, and can 
   ***not*** contain any spaces, and should use camelCase. This naming scheme 
   is similar to what is used for types, which will be covered in the 
   ***Custom Types*** section.
2. If you're using the `with value` phrase to assigning variables, the
   `with value <expr>` section should be indented 2 spaces on the next line.


#### Layout: ####

```
<variable name> is  <type>
<variable name> is <expression>

<variable name> is (a/an) <type>
    with value <expression>
```


#### Examples: ####

```
=> x is an Integer
=> x is 5
~> x = 5

=> b is a Character
=> b is 'b'
~> b = 'b'

=> b is now 'c'
~> b = 'c'


=> s is a String
=> s is "Hello, World!"
~> s = "Hello, World!"

=> pi is a Decimal
=>  with value 3.1415926535
~> pi = 3.1415926535

=> MAX_SCORE is a constant Integer
=> MAX_SCORE is 100
~> MAX_SCORE := 100 # The := here denotes assigning a value to a constant.
```



## 1.4 Built-in Types ##

Variables can be a variety of different types, some of which are built into the
language itself. These are called "built-in" types, and are the basis for which
other types are created.
The built-in types are listed below:

- `Integer` - A 32-bit signed integer. (int in Java)
- `BigInteger` - A 64-bit signed integer. (long in Java)
- `Boolean` - A boolean value. True or False. (boolean in Java)
- `Character` - An unsigned 8-bit value. (char in Java)
- `Decimal` - A signed 64-bit decimal value. (double in Java)
- `String` [^1] - A sequence of characters. (String in Java)
- `Array` [^2] - A collection of values that are all grouped together into one
                 variable. (Similar to T[] in Java, where T is some Java 
                 primitive or object.)
- `Nothing` [^3] - A type that represents, well, nothing. This can only be used
                   in method declarations as a return type or as a way to assign
                   a variable to `null` in the compiled code.

Types should generally be declared on the line before the variable is
instantiated, but built-in types can be inferred by the compiler. Custom types
**must** be declared on the line before. Custom types will be talked about more
in the ***Custom Types*** subsection of this documentation.

#### Notes: ####

1. String is not technically a primitive type in Java, as it lives in the
   `java.lang` class. For BEPL, it may be treated as a primitive type.
2. The same applies for arrays, although arrays can hold non-primitive types
   within them.
3. `Nothing` is also analogous to `null` when used as anything other than
   a return type. So writing `s is Nothing` is perfectly valid, as long as the
   type is not converted to a Java primitive type, like `Integer` or `Decimal`
   are, when compiled. The type must be annotated when using `Nothing`,
   otherwise the compiler will not know what type the variable is, causing an
   error.


### Inferred Types ###

Certain types, namely the ones that are built into BEPL, can be inferred from
the context in which they are used. 

```
=> x is 5
~> x = 5

=> :type x # This can be used to retrieve information about types in the REPL.
~> x is of type Integer.
```

While built-in types are able to be inferred, user-defined types must be 
annotated with the `is a` or `is an` phrase. Even though some types *can* be
inferred, it is generally better to annotate the types anyways. Function return
types must also be annotated, and can not be inferred. Functions will be talked
about more in the ***Functions*** section of this documentation.


### Arrays ###

Arrays have a special syntax that is associated with them. Arrays are only able
to hold values of one type, so the type that the array holds must be declared
along with the array. To do this, the syntax is:

```
<variable> is an Array of <type>
```

Arrays can be instantiated directly, or have items added to it at a later time.
To instantiate an array directly, you must declare the items that are going to
be in the array when the variable is created. This is done by declaring the
items inside of brackets seperated by spaces. The type of an array must be
specified explicitly, unless it is being instantiated directly, in which case,
the first item's type will be the type of the array.

Remember, arrays can only hold values of one type. If a value of one type is
invalid, an error will be thrown. As stated previously, the array's type is
determined either by a type annotation, or by the first element in the array
(if instantiating directly). Arrays that hold custom types ***must*** be
annotated.

You can get an element of an array by using the `at` keyword with an index. This
is another piece of syntax that is specific to arrays. The first index is `0`,
and it is easiest to think of indices as an offset from the first element. The
array has a size, given by the array's `length` property, and the indices of an
array range from `0` to `length - 1`.

#### Notes: ####

1. Arrays are able to hold other arrays as elements, and can grow in size 
   at runtime.
2. The length and indices of an array are always `Integer`s. This means that an
   array can have a maximum size of (2^31)-1, or 2,147,483,647 elements.
3. Attempting to access an element at an index less than `0` will result in
   a `NegativeIndexException` at runtime, while an index greater than
   `length - 1` will result in an `MaximumIndexException` at runtime.

#### Examples: ####

```
# Instantiating array directly

=> x is [1 2 3 4 5 6]
~> x = [1 2 3 4 5 6]

=> b is ['a' 'b' 'c']
~> b = ['a' 'b' 'c']

=> :type x
~> x is of type Array[Integer]

# Adding elements to array at a later time

=> c is an Array of Decimal
~> c -> type = Array[Decimal]

=> c -> call append where 
             value is 3.1415 # This syntax will be talked about later.
~> True

=> c -> call append where
             value is 2.7182
~> True

=> c -> length
~> 2

# Error examples

=> d is [1 2 3 'a']
~> ERROR: TypeMismatchException
 |    message: d is of type Array[Integer], but a
 |             value of type Character was found
 |             in the array. Aborting.
 |    location: repl, 
 |              line 15,
 |              col 1

=> c at 2
~> ERROR: MaximumIndexException
 |    message: c has a maximum index of 1, but
 |             an access of index 2 was
 |             attempted. Aborting.
 |    location: repl,
 |              line 16,
 |              col 6

=> c at -1
~> ERROR: NegativeIndexException
 |    message: Negative index detected. The
 |             minimum index of c is 0, but
 |             an access of index -1 was
 |             attempted. Aborting.
 |    location: repl,
 |              line 17,
 |              col 6
```



## 1.5 Loops ##

Loops are an almost ubiquitous contruct in programming languages. In BEPL, they
are used for repeated actions, and there are three different types of loops for
different actions. Repeat loops, For loops, and While loops. These loops are
much simpler than their Java counterparts.


### `repeat` Loops ###

`repeat` loops do exactly what their name suggests; they repeat a sequence of 
actions. They work by using the keyword `repeat`, along with an `Integer` and 
the keyword `times`. To signify the end of a loop, the `end` keyword is used, 
along with the loop's name.

#### Layout ####

```
repeat <number> times
    <statements>
    ...
    ...
end repeat
```

Notice that the loop's body is indented. This is required by BEPL, as it tells 
the compiler which loop the body is a part of. It also makes the body clearer to
see from the rest of the loop. Failure to indent this code will result in the 
code not compiling.

#### Examples ####

```
repeat 5 times
    call printLine where
         value is "Hello, World!"
end repeat

Output:

Hello, World!
Hello, World!
Hello, World!
Hello, World!
Hello, World!
```


### `for` Loops ###
`for` loops are used for iterating over Arrays. They are used in the following
fashion:

```
for <variable> in <array variable>
    <statements>
    ...
    ...
end for
```

`for` loops use the `in` keyword to seperate the variable from the array. You 
can think of this as saying "for each variable in this array". On each 
iteration, the variable changes to become the next element in the array. Note 
that this does not tell you the index of the element; that must be stored in a 
seperate variable that is incremented on each iteration of the loop. The 
variable automatically gets assigned the type that the array holds.

#### Examples ####

```
arr is an Array of String
arr = ["Hello" "," "World" "!"]

for x in arr
    call printLine where
         value is x
end for

Output:

Hello
,
World
!
```


### `while` Loops ###

`while` loops are used when you don't know exactly when the loop will be 
exiting. Because of this, they take a boolean value, that when false, the loop
exits. The loop will continue to run through its statements *until* the
condition is false. This means that you must make sure that your condition is 
being updated, otherwise an infinite loop will be created.

#### Layout ####

```
while <condition>
    <statements>
    ...
    ...
end while
```

Note that since the `while` loop takes a boolean as a condition, both `True` and 
`False` can be entered as the condition. `while True` would make a loop run 
forever, and `while False` would make the loop never run (Though I have no idea
why you would want to do this.)

#### Examples ####

```
x is an Integer
x is 123456789

total is an Integer
total is 0

while x > 0
    if x % 2 == 0
        total is now total + 1
    x is now x / 10
end while

call print where
     value is total

Output:

4
```



## 1.6 Functions ##

Functions (aka. methods) are the building blocks for almost every non-trivial
program. They allow you to group certain functionality together to make programs
shorter. Functions in BEPL are created with the `function` keyword, and can take
arguments of all different types and values. Using a function is called
"calling" the function, and uses the `call` keyword.

Functions work by grouping specific statements together, and returning a value
at the end of the function.


### Function Signatures ###

The signature of a function depends on what the function itself does. A
signature includes four things:
- The `function` keyword
- The name of the function
- The function's parameter(s)
- The function's return type.
