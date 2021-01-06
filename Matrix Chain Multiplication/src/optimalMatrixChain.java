import java.util.Scanner;
 
public class optimalMatrixChain {
	
	// Create two matrices to store the data, and declare the length variable for finding chain length
    private int[][] A;
    private int[][] B;
    private int length;
 
    public static void main(String[] args) {
    	
        Scanner input = new Scanner(System.in);
        
        // Have the user enter the chain length
        System.out.println("Enter the matrix chain length: ");
        int length = input.nextInt();
        
        // Create an array to store the dimensions found by entering chain length
        int matDimensions[] = new int[length];
        
        // Have the user enter the matrix dimensions
        System.out.println("Enter the matrix dimensions: ");
        for (int i = 0; i < length; i++) {
            matDimensions[i] = input.nextInt();
        }
        
        // Create an object for printing the output
        optimalMatrixChain object = new optimalMatrixChain(matDimensions);
        
        System.out.println();
        System.out.println("Optimal Matrix Chain Order: ");
        System.out.println(object.toString());
        
        // Close the scanner
        input.close();
    }
    
    // Define the matrices declared in main by increasing length of 1
    public optimalMatrixChain(int[] C) {
    	length = C.length - 1;
        A = new int[length + 1][length + 1];
        B = new int[length + 1][length + 1];
        
        // Run the method for solving the matrix chain order
        matrixChainOrder(C);
    }
    
    // Method for finding the matrix chain order for A and B
    private void matrixChainOrder(int[] C) {
    	
    	// Initialize the cost
        for (int i = 1; i <= length; i++) {
            A[i][i] = 0;
        }
        // Solve for chains of increasing length of 1
        for (int l = 2; l <= length; l++) {
            for (int i = 1; i <= length - l + 1; i++) {
            	
                int j = i + l - 1;
                A[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int q = A[i][k] + A[k + 1][j] + C[i - 1] * C[k] * C[j];
                    if (q < A[i][j]) {
                        A[i][j] = q;
                        B[i][j] = k;
                    }
                }
            }
        }
    }
 
    // Print the output inside parenthesis
    private String printOutput(int i, int j) {
        if (i == j) {
            return "A" + i;
        }
        else {
            return "(" + printOutput(i, B[i][j]) + printOutput(B[i][j] + 1, j) + ")";
        }
    }
    
    // Convert the output to string
    public String toString() {
        return printOutput(1, length);
    }
}