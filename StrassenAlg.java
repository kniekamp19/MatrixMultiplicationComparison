/*
Kristen Niekamp
Lab 1 - Matrix Multiplication


This file contains the code for the Strassen Algorithm described in
section 4.2 of the textbook. This algorithm is able to multiply matrices with
an asysmptotic upper bound of O(n^lg7), which is faster than a typical
matrix multiplication algorithm of O(n^3).
*/

import java.util.*;
import java.io.*;


public class StrassenAlg {
  private static int count = -1; //counter to count the number of multiplication calculations

  /* This method serves to reset the counter for each subsequent matrix
  multiplication after the first.
  The counter starts at -1 to account for the method call that will not result
  in a multiplication calculation but will join the four parts of the final
  matrix together. */
  public int[][] multMats(int[][] A, int[][] B) {
    count = -1;
    int[][] C = multMatsRec(A,B);
    return C;
  }
  /* method to recursively multiply matrices */
  public int[][] multMatsRec (int[][] A, int[][] B){
    int n = A.length;
    int[][] finalMat = new int[n][n+1];
    count++;

    if(n == 1){
      finalMat[0][0] = A[0][0] * B[0][0];
    }
    else {
      //new matrices of size n/2
      int[][] A11 = new int[n/2][n/2];
      int[][] A12 = new int[n/2][n/2];
      int[][] A21 = new int[n/2][n/2];
      int[][] A22 = new int[n/2][n/2];
      int[][] B11 = new int[n/2][n/2];
      int[][] B12 = new int[n/2][n/2];
      int[][] B21 = new int[n/2][n/2];
      int[][] B22 = new int[n/2][n/2];

      //split matrix A into four n/2 matrices
      A11 = split(A, A11, 0, 0);
      A12 = split(A, A12, 0, n/2);
      A21 = split(A, A21, n/2, 0);
      A22 = split(A, A22, n/2, n/2);
      //split matrix B into four n/2 matrices
      B11 = split(B, B11, 0, 0);
      B12 = split(B, B12, 0, n/2);
      B21 = split(B, B21, n/2, 0);
      B22 = split(B, B22, n/2, n/2);

      //create the 10 sum or difference matrices
      int[][] S1 = subtract(B12,B22);
      int[][] S2 = add(A11,A12);
      int[][] S3 = add(A21,A22);
      int[][] S4 = subtract(B21,B11);
      int[][] S5 = add(A11,A22);
      int[][] S6 = add(B11,B22);
      int[][] S7 = subtract(A12,A22);
      int[][] S8 = add(B21,B22);
      int[][] S9 = subtract(A11,A21);
      int[][] S10 = add(B11,B12);

      /* recursively multiply matrices to get sum/difference of products of A
      and B submatrices */
      int[][] P1 = multMatsRec(A11,S1);
      int[][] P2 = multMatsRec(S2,B22);
      int[][] P3 = multMatsRec(S3,B11);
      int[][] P4 = multMatsRec(A22,S4);
      int[][] P5 = multMatsRec(S5,S6);
      int[][] P6 = multMatsRec(S7,S8);
      int[][] P7 = multMatsRec(S9,S10);

      //create the four n/2 pieces of the final matrix
      int[][] C11 = add(subtract(add(P5,P4),P2),P6);
      int[][] C12 = add(P1,P2);
      int[][] C21 = add(P3,P4);
      int[][] C22 = subtract(subtract(add(P5,P1),P3),P7);

      //join the four n/2 matrices into one matrix of size n
      finalMat = combine(finalMat,C11,0,0);
      finalMat = combine(finalMat,C12,0,n/2);
      finalMat = combine(finalMat,C21,n/2,0);
      finalMat = combine(finalMat,C22,n/2,n/2);
    }

    finalMat[n-1][n] = count;
    return finalMat;

  } //end multMatsRec

  /* method to add two matrices together to form resulting matrix */
  public int[][] add(int[][] mat1,int[][] mat2){
    int n = mat1.length;
    int[][] addMat = new int[n][n];
    for(int i = 0; i < addMat.length; i++){
      for(int j = 0; j < addMat.length; j++){
        addMat[i][j] = mat1[i][j] + mat2[i][j];
      }
    }
    return addMat;
  }

  /* method to subtract two matrices to form resulting matrix */
  public int[][] subtract(int[][] mat1, int[][] mat2){
    int n = mat1.length;
    int[][] subMat = new int[n][n];
    for(int i = 0; i < subMat.length; i++){
      for(int j = 0; j < subMat.length; j++){
        subMat[i][j] = mat1[i][j] - mat2[i][j];
      }
    }
    return subMat;

  }

  /* split a parent matrix into a child matrix of size n/2 */
  public int[][] split(int[][] parent, int[][] child, int iA, int jA){
    for(int i = 0, i2 = iA; i < child.length; i++, i2++){
      for(int j = 0, j2 = jA; j < child.length; j++, j2++){
        child[i][j] = parent[i2][j2];
      }
    }
    return child;
  }

  /* combine child matrices of size n/2 into a parent matrix */
  public int[][] combine(int[][] parent, int[][]child, int iA, int jA){
    for(int i = 0, i2 = iA; i < child.length; i++, i2++){
      for(int j = 0, j2 = jA; j < child.length; j++, j2++){
        parent[i2][j2] = child[i][j];
      }
    }
    return parent;
  }
}
