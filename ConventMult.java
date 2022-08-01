/*
Kristen Niekamp
Lab 1 - Matrix multiplication

This class implements a conventional method of multiplying matrices resulting
in a runtime of O(n^3), which is slower than the Strassen Algorithm for
matrix multiplication.
*/

import java.util.*;
import java.io.*;

public class ConventMult {

  public int[][] multMatrices (int[][] A, int[][] B){
    int n = A.length;
    int[][] C = new int[n][n+1];
    int count = 0;

    for(int i = 0; i < n; i++){// for each row i in matrix C
      for(int j = 0; j < n; j++){ //for each column j in row i of matrix C
        C[i][j] = 0; //set current position in C matrix to 0
        /* k ensures that the corresponding row and column values of A and B that
        should contribute to the final value of the position in C are included in the sum */
        for(int k = 0; k < n; k++){
          C[i][j] = C[i][j] + (A[i][k] * B[k][j]);
          count++;
        }
      }
    }
    C[n-1][n] = count;
    return C;
  }//end multMatrices



}//end class
