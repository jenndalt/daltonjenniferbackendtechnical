Provide implementation for the following features (see examples below):

show
Output the current number of each denomination in the register in format $<total> <# of 20’s> <# of 10’s> <# of 5’s> <# of 2’s> <# of 1’s>

put
Adds some number of each denomination from the register, then print the current state. Same output format as show command.

take
Removes some number of each denomination from the register, then print the current state. Same output format as show command.

change
Returns change for some amount of money. Output should be
denominations of change for the value asked in format <# of 20’s> <# of
10’s> <# of 5’s> <# of 2’s> <# of 1’s>, e.g. 0 0 4 0 0.
This should also deduct the resulting denominations from the register.

quit
Exit the program

● Be written in either Java, Kotlin, Groovy or Scala.
● Be readable and logically organized.
● Not dependent on third party libraries, though you can use libraries for testing like junit, spock or mockito.
● Be runnable from the command line.
● Include tests that exercise functionality.
One of the test cases we ask people to look for in this code is how to make $8 in change if you have $13 in the cash register.
● Run in jdk 8 or above.
