package Miscellenous;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
* Time Complexity : O(nlogn)
* 
* 2   3     4
* \  /    /
*   5   /
*   \  /
*    9
* */

public class OptimalMergePattern {

    public static void main(String[] args) {
        int files[] = new int[] { 2, 3, 4, 5, 6, 7 };
        System.out.println(OptimalMergePattern.minComputation(files));
    }

    private static int minComputation(int[] files) {

        // Create a min-heap and add sizes to the priority queue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Arrays.stream(files).forEach(
                a -> minHeap.add(a)
        );

        int totalCount = 0;

        // Pop minimum two elements and add the cost obtained to the heap again
        while (minHeap.size() > 1) {
            int tempCost = minHeap.poll() + minHeap.poll();
            totalCount += tempCost;
            minHeap.add(tempCost);
        }

        return totalCount;
    }
}
