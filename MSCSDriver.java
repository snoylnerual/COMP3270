/**
Authors: Lauren Lyons, Catherine Bickerton

MSCSDriver class file uses and depends on the MSCS file for methods

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

public class MSCSDriver
{
    public static void main(String[] args)
    {
        // create an instance of our MSCS class to call methods from
        MSCS drvr = new MSCS();
        // prepArr will return our inital array from “phw_input.txt”
        int[] array = drvr.prepArr();
        // and run each of the algorithms on that 
        // input array, and print out the answer produced by each on the console as follows: "algorithm-1: <answer>; 
        // algorithm-2:<answer>; algorithm-3:<answer>; algorithm-4:<answer>" where <answer> is the MSCS as 
        // determined by each of the algorithms.
        System.out.print("algorithm-1: " + drvr.alg1(array) + "; ");
        System.out.print("algorithm-2: " + drvr.alg2(array) + "; ");
        System.out.print("algorithm-3: " + drvr.MaxSum(array, 0, 9) + "; ");
        System.out.print("algorithm-4: " + drvr.alg4(array));
        
        // prepRandArr will return a jagged array of the randomly filled int arrays
        // that vary in length by 5 ranging from 10-100
        int[][] randArray = drvr.prepRandArr();
        
        // timeArray will hold the times from running the algorithms and the
        // calculated time complexities
        float[][] timeArray = new float[19][8];

        /**
         Use the system clock to measure time t1, run one of the four algorithms on array Ai (starting with i=1) 
         n times (n = 1000), then measure time t2, and compute average time needed by that algorithm to solve the 
         problem with input size = size of Ai. Do this for each of the algorithms executing on each of the 19 input 
         arrays to fill the first four columns of a 19X8 matrix of integers with average execution times.  
         Each row of this matrix corresponds to one input size, from 10-100. 

         Fill the last four columns of this matrix with values ceiling(T1(n)), ceiling(T2(n)), ceiling(T3(n)), and 
         ceiling(T4(n)) where n = each input size and T(n) are the polynomials representing the theoretically 
         calculated complexity of the three algorithms that you determined in step 2 part (b). So, column 1 will have 
         measured running times of your first algorithm and column 5 will have the calculated complexity for the 
         same algorithm; similarly for columns 2 & 6, 3 & 7, and 4 & 8. You may need to scale the complexity 
         values (or use an appropriate time unit such as nano/micro/milli seconds for the measured running times) in 
         order to bring all data into similar ranges. 

         For scaling, we only divided by each avgTime by 100 instead of 1000 so scaled each by 10 and left the
         calculated complexities alone
        */
        
        long start, stop;
        int n = 1000, in = 0;
        for (int j = 0; j < 19; j++)
        {  
            in = randArray[j].length; // each array is a different length so we must recompute every time
            
            // Algorithm 1
            int avgTime = 0;
            for (int i = 0; i < n; i++)
            {
                start = System.nanoTime();
                drvr.alg1(randArray[j]);
                stop = System.nanoTime();
                avgTime += stop-start;
            }
            timeArray[j][0] = avgTime/100; 
            timeArray[j][4] = ((7*((in*in*in)/2))+ (7*((in*in)/2))+(17*in)+4); // 7((n^3)/2) + 7((n^2)/2) + 17n + 4
            
            // Algorithm 2
            avgTime = 0;
            for (int i = 0; i < n; i++)
            {
                start = System.nanoTime();
                drvr.alg2(randArray[j]);
                stop = System.nanoTime();
                avgTime += stop-start;
            }
            timeArray[j][1] = avgTime/100;
            timeArray[j][5] = (((6 * in * in))+((9 * in))+4); // 6(n^2) + 9n + 4
            
            // Algorithm 3
            avgTime = 0;
            for (int i = 0; i < n; i++)
            {
                start = System.nanoTime();
                drvr.MaxSum(randArray[j], 0, randArray[j].length-1);
                stop = System.nanoTime();
                avgTime += stop-start;
            }
            timeArray[j][2] = avgTime/100;
            timeArray[j][6] = ((11*in)*((float)(Math.log(in)/Math.log(2))))+(11*in); // (11n)(log(base2)n) + 11n
            
            // Algorithm 4
            avgTime = 0;
            for (int i = 0; i < n; i++)
            {
                start = System.nanoTime();
                drvr.alg4(randArray[j]);
                stop = System.nanoTime();
                avgTime += stop-start;
            }
            timeArray[j][3] = avgTime/100;
            timeArray[j][7] = (16 * in)+5; // 16n + 5
        }
        
        // fileWrite will output the timed algorithms and the calculated complexities in
        // the correct format to "LaurenLyons_phw_output.txt"
        drvr.fileWrite(timeArray);
    }   
}