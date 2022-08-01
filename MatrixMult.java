/*
Kristen Niekamp
Lab 1 - Matrix Multiplication

This is the driver class that handles the input file and creates the matrices
A and B that will be multiplied using Strassen's Algorithm and a conventional
matrix multiplication algorithm.
*/

import java.util.*;
import java.io.*;


class MatrixMult {
  public static void main(String[] args) throws IOException {
    MatrixMult mm = new MatrixMult();
    StrassenAlg sa = new StrassenAlg();
    ConventMult cm = new ConventMult();
    RecAlg ra = new RecAlg();

    int n;
    File inputFile = null;
    Scanner scan = null;
    FileWriter outputStream = new FileWriter(args[1],true); //setup to write output file
    BufferedWriter output = new BufferedWriter(outputStream);

    if(args.length != 2){
      System.out.println("Usage: java MatrixMult [inputFile.txt] [outputFile.txt]");
      scan.close();
      System.exit(0);
    }

    try{
      inputFile = new File(args[0]);//set up the file to be read by the Scanner
      scan = new Scanner(inputFile); //scanner to read input file
    } catch (IOException ioe) {
      System.out.println("There was an error regarding the input file: " + ioe.getMessage());
    }

    if (inputFile.length() == 0) { //check that the input file is not empty
      System.out.println("The input file is empty. Please provide matrices for multiplication.");
      output.write("The input file is empty. Please provide matrices for multiplication.");
      output.close();
      scan.close();
      System.exit(0);
    }
    else if (!scan.hasNextInt()) { //check that the input file is not empty
      System.out.println("The input file must contain matrices of integers. Please provide matrices for multiplication.");
      output.write("The input file must contain matrices of integers. Please provide matrices for multiplication.");
      output.close();
      scan.close();
      System.exit(0);
    }

    while(scan.hasNextInt()){
      n = scan.nextInt();

      // matrices A and B are the input matrices; matrix C is the output after multiplication
      int[][] A = new int[n][n];
      int[][] B = new int[n][n];

      A = mm.makeMatrix(A, scan, n);
      B = mm.makeMatrix(B, scan, n);

      //multiply using strassen's algorithm
      long strassenStart = System.nanoTime();
      int[][] strassenC = sa.multMats(A,B);
      long strassenElapsed = System.nanoTime() - strassenStart;

      //multiply using the conventional iterative algorithm
      long conventStart = System.nanoTime();
      int[][] conventC = cm.multMatrices(A,B);
      long conventElapsed=  System.nanoTime() - conventStart;

      //multiply using the conventional recursive algorithm
      long recStart = System.nanoTime();
      int[][] recC = ra.multMats(A,B);
      long recElapsed = System.nanoTime() - recStart;

      int strassenCount;
      int conventCount;
      int recCount;
      if (n == 1) { //if array is size 1x1, then only 1 multiplication would have taken place
        strassenCount = 1;
        conventCount = 1;
        recCount = 1;
      }
      else{
        strassenCount = strassenC[n-1][n];
        conventCount = conventC[n-1][n];
        recCount = recC[n-1][n];
      }

      output.write("\nInput matrix size: " + n);
      output.write("\n\nInput matrix A:");
      mm.printMat(A,output);
      output.write("\n\nInput matrix B:");
      mm.printMat(B,output);

      //print each output matrix to the output file
      output.write("\n\nStrassen's Algorithm:");
      mm.printMatAndStats(strassenC,strassenCount,strassenElapsed,output);
      output.write("\nConventional matrix multiplication:");
      mm.printMatAndStats(conventC,conventCount,conventElapsed,output);
      output.write("\nRecursion conventional algorithm:");
      mm.printMatAndStats(recC,recCount,recElapsed,output);

    }//end while

    scan.close();
    output.close();
  }//end main

  /* make matrices A and B of size n using the input file and scanner */
  public int[][] makeMatrix(int[][] matrix, Scanner scan, int n){
    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        matrix[i][j] = (int)scan.nextInt();
      }
    }
    return matrix;
  }//end makeMatrix

  /*
  This method prints the input matrix, the number of multiplications associated,
  the amount of time taken, and outputs it into the output .txt file
  */
  public void printMatAndStats(int[][] mat, int count, long time, BufferedWriter output) throws IOException {
    int n = mat.length;
    for(int i = 0; i < n; i++){
      output.write("\n");
      for(int j = 0; j < n; j++){
        output.write(mat[i][j] + " ");
      }
    }
    output.write("\nNumber of multiplications: " + count + "\n");
    output.write("Time elapsed: " + time + "\n");
  }

  /*
  This method prints the input matrices A and B without the extra values
  needed for printMatAndStats
  */
  public void printMat(int[][] mat, BufferedWriter output) throws IOException {
    int n = mat.length;
    for(int i = 0; i < n; i++){
      output.write("\n");
      for(int j = 0; j < n; j++){
        output.write(mat[i][j] + " ");
      }
    }
  }

}//end class
