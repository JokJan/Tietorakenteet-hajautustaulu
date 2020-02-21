# Tietorakenteet-hajautustaulu
My practical work for my course on data structures.

The task was to make a program that implements set operation OR, AND, XOR. These were meant to be implemented using a selfmade hashtable. 
The Integers used in the operations are in files SetA.txt and SetB.txt, and the results are in files AND.txt, OR.txt and XOR.txt
The programs allows for the removal of numbers from the result sets before they are written.

My implementation of hashtable is an array that has a linked list in each index. 
Each key k is added to the index of its value, so 5 is added to index 5
The hashtables size is 10000, and the maximum value is 9999.
The values are stored into a class meant to save key-value pairs

The code and comments are in finnish.

To do:
Ask the user for the size of the hashtable and the names of the input files
Headers for the output files
Make the hashtable change its size based on how full it is
