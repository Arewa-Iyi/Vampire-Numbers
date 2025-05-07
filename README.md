# Vampire-Numbers
Finding Vampire Numbers in the interval 100000 to 999999 with multi-threading

### Summary
The goal of this program is to find all the Vampire numbers in the interval [100000, 999999]. To achieve this 
goal we will scan all the integer numbers from 100000 to 999999 performing a test to verify if that number
is a Vampire number or not. The work is divided between the two worker threads: 
        one worker will scan and verify all the even numbers and the other worker will scan and verify all the 
        odd numbers in the interval. 

I devised a method called Limit With Sums to further hasten the discovery of Vampire Numbers.
This method increases detectiton time by roughly 78%


### Works Cited
Module 3. Threads and Concurrency 3.1 Threads vs. Processes. (n.d.). Retrieved May 31, 2024, from https://leocontent.umgc.edu/content/dam/course-content/tus/cmsc/cmsc- 
    412/document/Module%203%20Commentary.pdf?ou=1032351

Silberschatz, A., Peter Baer Galvin, & Gagne, G. (2018). Operating system concepts (10th ed.). Wiley, Cop.
    The Online Encyclopedia of Integer Sequences. (2024, May 31). A020342 - OEIS. Oeis.org. https://oeis.org/A020342
Vampire Number. (2017, October 12). GeeksforGeeks. https://www.geeksforgeeks.org/vampire-number/
Vampire number. (2023, November 2). Wikipedia. https://en.wikipedia.org/wiki/Vampire_number
Vampire number. (2024, February 16). Rosetta Code. https://rosettacode.org/wiki/Vampire_number
Y Daniel Liang. (2020). Introduction To Java Programming And Data Structures Revel Access Card. Prentice Hall.
