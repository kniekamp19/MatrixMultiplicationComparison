/*
Kristen Niekamp
Lab 1 - Matrix Multiplication

This class contains a recursive version of conventional matrix multiplication
as described in section 4.2 of CLRS under "A simple divide-and-conquer
algorithm". This algorithm takes O(n^3), which is the same as the iterative
version in the file "ConventMult.java".
*/

public class RecAlg {
  public int count; //counter to count the number of multiplication calculations

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

      //create the four n/2 pieces of the final matrix
      int[][] C11 = add(multMatsRec(A11,B11),multMatsRec(A12,B21));
      int[][] C12 = add(multMatsRec(A11,B12),multMatsRec(A12,B22));
      int[][] C21 = add(multMatsRec(A21,B11),multMatsRec(A22,B21));
      int[][] C22 = add(multMatsRec(A21,B12),multMatsRec(A22,B22));

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

}//end RecAlg
