// Java program to print largest contiguous array sum
import java.io.*;
import java.util.*;

/*
* The idea of Kadane’s algorithm is to maintain a variable max_ending_here that stores the maximum sum contiguous subarray ending at current index and a 
* variable max_so_far stores the maximum sum of contiguous subarray found so far, Everytime there is a positive-sum value in max_ending_here compare it with 
* max_so_far and update max_so_far if it is greater than max_so_far.
* For i=0,  a[0] =  -2
*
* max_ending_here = max_ending_here + (-2)
* Set max_ending_here = 0 because max_ending_here < 0
* and set max_so_far = -2
* For i=1,  a[1] =  -3
*
* max_ending_here = max_ending_here + (-3)
* Since max_ending_here = -3 and max_so_far = -2, max_so_far will remain -2
* Set max_ending_here = 0 because max_ending_here < 0
* For i=2,  a[2] =  4
*
* max_ending_here = max_ending_here + (4)
* max_ending_here = 4
* max_so_far is updated to 4 because max_ending_here greater than max_so_far which was -2 till now
* For i=3,  a[3] =  -1
*
* max_ending_here = max_ending_here + (-1)
* max_ending_here = 3
* For i=4,  a[4] =  -2
*
* max_ending_here = max_ending_here + (-2)
* max_ending_here = 1
* For i=5,  a[5] =  1
*
* max_ending_here = max_ending_here + (1)
* max_ending_here = 2
* For i=6,  a[6] =  5
*
* max_ending_here = max_ending_here + (5)
* max_ending_here =
* max_so_far is updated to 7 because max_ending_here is greater than max_so_far
* For i=7,  a[7] =  -3
*
* max_ending_here = max_ending_here + (-3)
* max_ending_here = 4
*/

class Kadane {
    // Driver Code
    public static void main(String[] args)
    {
        int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
        System.out.println("Maximum contiguous sum is "
                           + maxSubArraySum(a));
    }

    // Function Call
    static int maxSubArraySum(int a[])
    {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here
                                            = 0;

        for (int i = 0; i < size; i++) {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
}
