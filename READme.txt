The source code in this assignment can be compiled with Java SE 8 from the command line.
It was constructed using Atom v1.51.0.

To run the program, enter: java MatrixMult [inputFile].txt [outputFile.txt]

Note: If output file already exists, new output will be appended to existing file.


Input should be a square matrix nxn where n is a power of 2. Input format should contain
a line with the number of rows/columns (n) followed by the two matrices to be multiplied,
without any blank lines. An input file may contain more than one set of matrices to
multiply with a blank line between separate sets of matrices.

Input format example:

2 //number of rows/columns
1 2 
3 4 //end matrix 1
5 6
7 8 //end matrix 2

4
0 -1 -3 -4 
1 5 6 -4 
7 0 8 4 
5 3 4 -2 //end matrix 1
6 5 2 3 
4 6 3 1 
-1 7 9 4 
7 -2 0 0 //end matrix 2