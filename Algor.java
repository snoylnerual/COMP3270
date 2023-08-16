//import java.util.Random;
import java.math.*;
import java.util.*;
import java.io.File;

/**
Maximum Sum Contiguous Subvector (MSCS) Problem: Compute the sum of the subsequence of numbers (a 
subsequence is a consecutive set of one or more numbers) in an array of numbers (for this HW, we will use integers) 
that sum to the largest value possible; but if this value is negative, MSCS is defined to be zero. E.g., in an array of 
all positive (or all negative) numbers, the Maximum Contiguous Subvector consists of all numbers in the array (or 
zero). There may be multiple Maximum Subsequences in an array, all of which sum to the same largest sum. E.g., in 
an array of all zeroes, MSCS =0 and any 0 in the array is a Maximum Subsequence. Remember that the input array 
may contain zeroes, negative and/or positive integers. For example, if the input is [1,2,-4,3,-5,2,0] then MSCS =3 
and there are two Maximum Subsequences that sum to the same MSCS: A[1]+A[2]=1+2=3 or A[4]=3.

 */


 /*

 First, the main program should read from a file named “phw_input.txt” containing 10 comma-delimited 
integers in the first line, create an array containing these 10 integers, and run each of the algorithms on that 
input array, and print out the answer produced by each on the console as follows: "algorithm-1: <answer>; 
algorithm-2:<answer>; algorithm-3:<answer>; algorithm-4:<answer>  where <answer> is the MSCS as 
determined by each of the algorithms. 
Next, create 19 integer sequences of length 10,15,20,25,......90,95,100, containing randomly generated 
negative, zero and positive integers, and store these in 19 arrays of size 10,15,...,95,100: A1-A19. 
7. Then use the system clock to measure time t1, run one of the four algorithms on array Ai (starting with i=1) 
N times (where N is at least 100, but if your system clock does not have a good resolution you may need N 
to be larger, like 500 or 1000 in order to get meaningful running times), then measure time t2, and compute 
average time needed by that algorithm to solve the problem with input size = size of Ai. Do this for each of 
the algorithms executing on each of the 19 input arrays to fill the first four columns of a 19X8 matrix of 
integers with average execution times.  Each row of this matrix corresponds to one input size, from 10-100.  
8. Fill the last four columns of this matrix with values ceiling(T1(n)), ceiling(T2(n)), ceiling(T3(n)), and 
ceiling(T4(n)) where n = each input size and T(n) are the polynomials representing the theoretically 
calculated complexity of the three algorithms that you determined in step 2 part (b). So, column 1 will have 
measured running times of your first algorithm and column 5 will have the calculated complexity for the 
same algorithm; similarly for columns 2 & 6, 3 & 7, and 4 & 8. You may need to scale the complexity 
values (or use an appropriate time unit such as nano/micro/milli seconds for the measured running times) in 
order to bring all data into similar ranges. 
9. Your main program should write one text line of algorithm and complexity order titles separated by 
commas (e.g., "algorithm-1,algorithm-2,algorithm-3,algorithm-4,T1(n),T2(n),T3(n), T4(n)"), followed by the 
above matrix also in comma-delimited format (19 lines of 8 integers separated by commas) to a file called 
"yourname_phw_output.txt". 
10. Open yourname_phw_output.txt with a spreadsheet and produce a labeled graph with 10-100 on the x-
axis and 8 curves showing the actual time taken and predicted time (the complexity order) for each 
algorithm. Label the curves appropriately. 
*/

public class MSCS
{
    public static void main(String[] args)
    {
        //read from file named "phw_input.txt"
        //10 comma delimited ints in the first line
        //create an array for these 10 ints

        File myFile = new File("phw_input.txt");
        Scanner FScan = new Scanner(myFile);
        FScan.useDelimiter(",");
        int[] array = myFile.nextLine();
        
        int opp = alg1(array);

        system.out.print("algorithm-1: " + alg1(array));
        system.out.print();
        

        //print the rest

        int[] randArr = {a[10],b[15],c[20],d[25],e[30],f[35],g[40],h[45],i[50],j[55],l[60],m[65],
                n[70],o[75],p[80],q[85],r[90],s[95],t[100]};
        
        Random rC = new Random();

        for (int[] ar : randArr) 
        {
            for (int i=0; i<ar.length; i++)
            {
                ar[i] = rC.nextInt() * (random .nextBoolean() ? -1 : 1);
                //https://stackoverflow.com/questions/27976857/how-do-i-get-a-random-number-with-a-negative-number-in-range#:~:text=To%20get%20a%20random%20number,also%20works%20with%20negative%20numbers.
            }
        }
        

    }

    public void printArr(int[] arr)
    {
        for(int i=0; i<arr.length-1; i++)
        {
            system.out.print(arr[i] + ", ");
        }
        system.out.print(arr[arr.length-1]);
        system.out.print(";\n");
    }

    public int alg1(int[] X) //X: array[P..Q] of integer
    {
	    int maxSoFar = 0, sum = 0;
        for (int L = 0; L < X.length; L++)  //for L = P to Q  
        {
            for (int U = L; U < X.length; U++) //for U = L to Q
            {
                sum = 0; 
                for (int I = L; I <= U; I++) //for I = L to U
                {
                    sum = sum + X[I]; 
                    /* sum now contains the sum of X[L..U]  */ 
                    maxSoFar = Math.max(maxSoFar, sum);
                } 
            }
        }            
        return maxSoFar;
	}

    public int alg2(int[] X) //X: array[P..Q] of integer
    {
        int maxSoFar = 0, sum = 0;
        for (int L = 0; L < X.length; L++) //L = P to Q 
        {
            sum = 0; 
            for (int U = L; U < X.length; U++) //U = L to Q 
            {
                sum = sum + X[U]; 
                /* sum now contains the sum of X[L..U] */ 
                maxSoFar = Math.max(maxSoFar,sum);
            }
        }
        return maxSoFar;
    }

    public int alg3(int[] X, int L, int U) //recursive function MaxSum(X[L..U]: array of integer, L, U: integer)
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
        for (int I = M; I >= L ; I--) //I = M downto L
        {
            sum = sum + X[I]; 
            maxToLeft = Math.max(maxToLeft, sum);
        }
        /* Find max crossing to right */ 	 
        sum = 0; 
        int maxToRight = 0; 
        for (int I = M + 1; I <= U; I++) //I = M+1 to U 
        sum = sum + X[I]; 
        maxToRight = Math.max(maxToRight, sum); 
        int maxCrossing = maxToLeft + maxToRight; 
        
        int maxInA = maxSum(X, L, M);
        int maxInB = maxSum(X, M+1, U); 
        return max(maxCrossing, maxInA, maxInB); 
    }

    public int alg4(int[] X)  
    {
        int maxSoFar = 0, maxEndingHere = 0;
        for (int I = 0; I < X.length; I++)  {
            maxEndingHere = Math.max(0, maxEndingHere + X[I]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

}

