import java.math.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 Authors: Lauren Lyons, Catherine Bickerton

 MSCS class file will provide the driver with methods to use

 Problem to Solve:

 Maximum Sum Contiguous Subvector (MSCS) Problem: Compute the sum of the subsequence of numbers (a 
 subsequence is a consecutive set of one or more numbers) in an array of numbers (for this HW, we will use integers) 
 that sum to the largest value possible; but if this value is negative, MSCS is defined to be zero. E.g., in an array of 
 all positive (or all negative) numbers, the Maximum Contiguous Subvector consists of all numbers in the array (or 
 zero). There may be multiple Maximum Subsequences in an array, all of which sum to the same largest sum. E.g., in 
 an array of all zeroes, MSCS=0 and any 0 in the array is a Maximum Subsequence. Remember that the input array 
 may contain zeroes, negative and/or positive integers. For example, if the input is [1,2,-4,3,-5,2,0] then MSCS =3 
 and there are two Maximum Subsequences that sum to the same MSCS: A[1]+A[2]=1+2=3 or A[4]=3.
*/

public class MSCS
{    
    // Algorithm-1 from the HW Instructions
    // mostly just added parentheses for syntax and
    // formatted for loops
    // P = 0 & Q = X.length 
    public int alg1(int[] X) // X: array[P..Q] of integer
    {
	    int maxSoFar = 0, sum = 0;
        for (int L = 0; L < X.length; L++)  // for L = P to Q  
        {
            for (int U = L; U < X.length; U++) // for U = L to Q
            {
                sum = 0; 
                for (int I = L; I <= U; I++) // for I = L to U
                {
                    sum = sum + X[I]; 
                    /* sum now contains the sum of X[L..U]  */ 
                    maxSoFar = Math.max(maxSoFar, sum);
                } 
            }
        }            
        return maxSoFar;
	 }

    // Algorithm-2 from the HW Instructions
    // mostly just added parentheses for syntax and
    // formatted for loops
    // P = 0 & Q = X.length 
    public int alg2(int[] X) // X: array[P..Q] of integer
    {
        int maxSoFar = 0, sum = 0;
        for (int L = 0; L < X.length; L++) // L = P to Q 
        {
            sum = 0; 
            for (int U = L; U < X.length; U++) // U = L to Q 
            {
                sum = sum + X[U]; 
                /* sum now contains the sum of X[L..U] */ 
                maxSoFar = Math.max(maxSoFar,sum);
            }
        }
        return maxSoFar;
    }

    // Algorithm-3 : MaxSum from the HW Instructions
    // Added parentheses for syntax, formatted for loops, and
    // formatted Max Functions
    public int MaxSum(int[] X, int L, int U) // recursive function MaxSum(X[L..U]: array of integer, L, U: integer)
    {
        if (L > U)
        { 
            return 0;   /* zero- element vector */ 
        }
        if (L == U)
        {
            return Math.max(0 , X[L]);  /* one-element vector */ 
        }
        int M = (L+U)/2;  /* A is X[L..M], B is X[M+1..U] */ 
        /* Find max crossing to left */ 
        int sum = 0, maxToLeft = 0;
        for (int I = M; I >= L ; I--) // I = M downto L
        {
            sum = sum + X[I]; 
            maxToLeft = Math.max(maxToLeft, sum);
        }
        /* Find max crossing to right */ 	 
        sum = 0; 
        int maxToRight = 0; 
        for (int I = M + 1; I < U; I++) // I = M+1 to U //I <= U gives index 10 out of bounds error
        {
           sum = sum + X[I]; 
           maxToRight = Math.max(maxToRight, sum); 
        }
        int maxCrossing = maxToLeft + maxToRight; 
        
        int maxInA = MaxSum(X, L, M);
        int maxInB = MaxSum(X, M+1, U); 
        return Math.max(maxCrossing, Math.max(maxInA, maxInB)); 
    }

    // Algorithm-4 from the HW Instructions
    // mostly just added parentheses for syntax and
    // formatted for loops
    // P = 0 & Q = X.length 
    public int alg4(int[] X)  
    {
        int maxSoFar = 0, maxEndingHere = 0;
        for (int I = 0; I < X.length; I++)  // I = P to Q
        {
            maxEndingHere = Math.max(0, maxEndingHere + X[I]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
    
    // This method preps the initial array given by the file and returns it 
    public int[] prepArr()
    {
        try 
        {
           // read from a file named “phw_input.txt” containing 10 comma-delimited 
           // integers in the first line and create an array containing these 10 integers
           File myFile = new File("phw_input.txt");
           Scanner FScan = new Scanner(myFile);
           String input = FScan.nextLine();         //grabs the single line of info
           
           String[] sArr = input.split(",");        //comma delimiter
           int[] array = new int[sArr.length];      //makes room for the case that there are more than 10 integers
           for (int i = 0; i < array.length; i++)
           {
               array[i] = Integer.parseInt(sArr[i]);
           }
           
           return array;
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("The error " + e + " occurred.");        
        }
        return null;
    }
    
    // This method creates 19 integer sequences of length 10,15,20,25,......90,95,100, containing randomly generated 
    // negative, zero and positive integers, and store these in 19 arrays of size 10,15,...,95,100: A1-A19.
    // Returns a jagged array
    public int[][] prepRandArr()
    {
         int[][] randArr = {new int[10],new int[15],new int[20],new int[25],new int[30],new int[35],new int[40],
                new int[45],new int[50],new int[55],new int[60],new int[65],new int[70],
                new int[75],new int[80],new int[85],new int[90],new int[95],new int[100]};
        
        Random rC = new Random();
        for (int[] ar : randArr) 
        {
            rC = new Random();
            for (int i=0; i<ar.length; i++)
            {
                ar[i] = rC.nextInt() * (rC.nextBoolean() ? -1 : 1);  // This multiplication term will allow for randomly negative numbers
                // https://stackoverflow.com/questions/27976857/how-do-i-get-a-random-number-with-a-negative-number-in-range#:~:text=To%20get%20a%20random%20number,also%20works%20with%20negative%20numbers.
            }
        }
        
        return randArr;
    }
    
    // Write one text line of algorithm and complexity order titles separated by 
    // commas (e.g., "algorithm-1,algorithm-2,algorithm-3,algorithm-4,T1(n),T2(n),T3(n), T4(n)"), followed by the 
    // complexity matrix also in comma-delimited format (19 lines of 8 integers separated by commas) to a file called 
    // "yourname_phw_output.txt" -> "LaurenLyons_phw_output.txt"
    public void fileWrite(float[][] ar)
    {
        try  
        {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter("LaurenLyons_phw_output.txt"));
            
            String str = "algorithm-1,algorithm-2,algorithm-3,algorithm-4,T1(n),T2(n),T3(n),T4(n)\n";
            bWriter.write(str);
            
            for (int i=0; i<19; i++)
            {
                for (int j=0; j<7; j++)
                {
                    bWriter.write(Float.toString(ar[i][j])); // writes 7 terms separated by commas
                    bWriter.write(",");
                }
                bWriter.write(Float.toString(ar[i][7])); // last term does not need a comma after so that is why its separate
                bWriter.write("\n"); 
            }
            bWriter.close();
        }
        catch (IOException e) 
        {
            System.out.println("The error " + e + " occurred.");        
        }
    }
}

