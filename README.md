# Objectives

The objectives of this lab are:

* Implement a given simple interface according to specification.

* Write JUnit tests to verify that implementation matches the specification.

* Submit written code to the server and experience the testing feedback.

# 2 The `Money` interface

In this lab, you have to implement a representation for US money and implement some functionality for it.

An amount of money is represented by the `Money` interface. This interface should contain the following methods:

* A method to add two money amounts: `Money add(Money other)`.

* A method to add a money amount with another given as a separate dollar and cent value: `Money add(int dollars,int cents)`. This method should throw an `IllegalArgumentException` exception if the money provided to it as arguments is invalid.

* A method that returns the decimal value of the money in the format "xx.yy": `double getDecimalValue()`. The cent value should be padded with leading zeroes if necessary (i.e. 8 dollars and 2 cents should be 8.02).

# 3 What to do

* Create the above interface, and document its specifications as detailed above.

* Design JUnit tests that verify these specifications for an implementation called `SimpleMoney`. All tests should be in `test/java/SimpleMoneyTest.java`

* Implement the `Money` interface in a `SimpleMoney` class. Leave all the methods blank for now, but document them properly. The specifications for this implementation (beyond what the interface specifies) are:

    * This class can only represent valid money (i.e. money that can be dispensed).

    * This class should have a single public constructor that takes the dollar and cent amount of the money as integers as its only arguments. Any attempt to create a negative amount of money, or using a negative amount of dollars or cents should throw an `IllegalArgumentException`.

    * This class should also override the `toString` method, which returns a string of the form "\$xx.yy". There should be exactly two digits after the decimal point, padded with leading zeroes if necessary.

    * Note that wherever applicable, you may not assume that this is the only implementation of the `Money` interface.

* For each method to be implemented in the `SimpleMoney` class: design and write all JUnit tests to verify its specification, then complete the implementation and run the tests. Proceed in this "write tests -> implement method -> run tests" to complete the class.

Note: How will you test the constructor? The purpose of the constructor is to create the object as specified, or die trying (i.e. throw an exception). The latter can be easily tested, but how to test the former? Since we cannot (should not) directly access fields from the test, one has to look to other (simple) methods to test this. But how do we know those methods are themselves correct? If these methods are short and simple (e.g. they do not compute anything, but rather directly report something about the object) then it is improbable that they work incorrectly. It is a "leap of faith" in some ways, but it fulfills our objectives of testing everything we implement, while also not resorting to make fields accessible or write methods just to be able to test.

* Fix the style, and submit by pushing code to the repo. Remember to have the src and test files as mentioned in this document. If you fail to do this your submission will not be considered by the server.

* Look at and fix any style and test errors.

# 4 Questions to discuss and ponder

When you are done with the above, here are some additional tasks that you should do. You are encouraged to discuss them with the person next to you, and talk to the course staff as needed.

## 4.1 Improving the design

The second `add` method does not really belong in the interface, although hopefully you found it useful. A consequence of putting it in the interface is that it is a `public` method in all its implementations.

Can you redesign your classes and interfaces such that this method is no longer public, but other methods still work as described? Whatever design you come up with, what are its limitations?

Talk to the course staff in the room and bounce off ideas.

## 4.2 Better testing

How did you test whether your `add` method works correctly? Did you have a few sample inputs and expected outputs? How many samples did you try? Are they enough?

An alternative way is fuzzy testing, or random-sample testing. This type of testing is useful when a method can have a large number (seemingly infinite) of inputs that produce expected outputs. It works as follows:

1. Determine if it is possible to categorize the possible inputs (e.g. all positive numbers, all negative numbers, etc.). This will depend on the specific problem at hand.

2. For each category, generate a large number of random inputs using a random number generator. Be sure to give a constant seed to the generator, so that one can run the test repeatedly and be assured of the same set of random inputs.

3. Write a test that repeatedly (e.g. using loops) uses random input(s) and verifies expected output(s).

How large should the sample size be (i.e. how many sets of random inputs should we try)? Note that this testing probabilistic in nature: we are hoping that we test enough inputs so that the probability of *any* input producing the correct expected output is "high enough". A few thousand samples are usually a good default size to start with.

Test your `add` method using fuzzy testing. Now test the other methods similarly. Show your work to the course staff.