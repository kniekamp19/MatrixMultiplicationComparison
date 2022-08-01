/*
Kristen Niekamp
Lab 1 - Matrix Multiplication

This class was made to create matrices of size 2^n x 2^n to use for test case
purposes. This class is not part of project requirements but may be used if desired.
It will output the new matrix to a .txt file with the output name provided
as an argument.
*/

import java.util.*;
import java.io.*;

class MatrixGenerator {
  public static void main(String[] args) throws IOException {
    FileWriter fw = new FileWriter(args[0]);
    BufferedWriter output = new BufferedWriter(fw);
    Scanner scan = new Scanner(System.in);
    String input;

    if (args.length != 1) {
      System.out.println("Usage: java matrixGenerator [outputFile].txt");
      System.exit(0);
    }

    //input how many sets of 2 matrices to make; each set of 2 will be multiplied together
    System.out.println("How many separate multiplication problems would you like in the output file?");
    while(!scan.hasNextInt()) {
      input = scan.nextLine();
      System.out.println("Input must be an integer.");
    }

    int x = scan.nextInt();//set x as the desired number of sets of matrices to be multiplied

    int[][] A;//each of the two matrices to be created
    int[][] B;
    int i = 0;
    int n;
    int min = -5;//the minimum and maximum values to have in the array
    int max = 9;
    int range = max - min + 1;
    double val;

    //this will loop for the number of times that the user input as their desired number
    //of matrix multiplications
    while (i < x){
      //input the desired size of the array for each problem
      //user will be asked this for each of x sets of arrays
      System.out.println("Matrices must be a size that is a power of 2. Enter a value n for matrix #" + (i+1) + " such that the size will be 2^n.");
      while(!scan.hasNextInt()) {
        input = scan.nextLine();
        System.out.println("Input must be an integer.");
      }
      n = (int)Math.pow(2,scan.nextInt());//ensures that size is a power of 2
      A = new int[n][n];
      B = new int[n][n];

      //create array A and output to file
      output.write(n + "");
      for(int j = 0; j < n; j++){
        output.newLine();
        for (int k = 0; k < n; k++) {
          val = Math.random() * range + min;
          A[j][k] = (int)(Math.random() * range + min);
          output.write(A[j][k] + " ");
        }
      }
      //create array B and output to file
      for(int p = 0; p < n; p++){
        output.newLine();
        for (int q = 0; q < n; q++) {
          val = Math.random() * range + min;
          B[p][q] = (int)(Math.random() * range + min);
          output.write(B[p][q] + " ");
        }
      }
      output.write("\n\n");
      i++;

    }//end outer while

    scan.close();
    output.close();

  }
}
