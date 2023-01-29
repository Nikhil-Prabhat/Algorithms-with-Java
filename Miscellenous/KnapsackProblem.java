package Miscellenous;

/*

Knapsack Problem is of two types :
1. 0/1 Knapsack Problem         : Splitting of items is not allowed
2. Fractional Knapsack Problem  : Splitting of items is allowed

Time Complexity of Greddy Approach : O(n)
Time Complexity of DP Approach     : O(n^2)

## Table Creation Example

int W[] = new int[]{12, 2, 1, 1, 4};
int V[] = new int[]{4, 2, 1, 2, 10};

int M = 15;
int n = V.length;

0 0 0 3 3 3 3 3 3 3 3 3
0 0 0 3 4 4 4 7 7 7 7 7
0 0 0 3 4 4 4 7 7 8 8 8
0 0 0 3 4 4 4 7 7 10 10 10
0 0 0 3 4 4 4 7 8 10 10 11
Max Value:	11
Selected Packs:
	Package 5 with W = 4 and Value = 4
	Package 2 with W = 4 and Value = 4
	Package 1 with W = 3 and Value = 3

 */


public class KnapsackProblem {

    public static void main(String[] args) {

        int[] profits = new int[]{10, 5, 15, 7, 6, 18, 3};
        int[] weights = new int[]{2, 3, 5, 7, 1, 4, 1};
        KnapsackProblem.findKnapsack(profits,weights,15);
        
        findKnapsackWithDP();

    }

    // This is a greddy appraoch
    private static void findKnapsack(int[] profits, int[] weights, int bagCapacity) {
        float[] included = new float[profits.length];
        float[] profitsByWeights = new float[profits.length];

        // Finding ProfitsByWeights for each object
        for (int i = 0; i < profits.length; i++) {
            profitsByWeights[i] = (float) profits[i] / weights[i];
        }

        //Adding element in the bag
        for (int i = 0; i < included.length; i++) {
            int maxCurrentIndex = findIndexOfMaxElement(profitsByWeights);
            profitsByWeights[maxCurrentIndex] = Float.MIN_VALUE;

            if (bagCapacity >= weights[maxCurrentIndex]) {
                included[maxCurrentIndex] = 1;
                bagCapacity -= weights[maxCurrentIndex];
            } else if (bagCapacity < weights[maxCurrentIndex]) {
                included[maxCurrentIndex] = (float) bagCapacity / weights[maxCurrentIndex];
                bagCapacity = 0;
            }
        }

        float maxProfit = 0;
        for (int i = 0; i < included.length; i++) {
            maxProfit += (included[i] * profits[i]);
        }

        System.out.println("Maximum Profits : " + maxProfit);
    }

    private static int findIndexOfMaxElement(float[] profitsByWeight) {
        float maxVal = Float.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < profitsByWeight.length; i++) {
            if (profitsByWeight[i] > maxVal) {
                maxVal = profitsByWeight[i];
                index = i;
            }
        }
        return index;
    }
    
    // This is a Dynamic Programming Approach
    private static void findKnapsackWithDP() {
        // Always include 0 as the first row and first column
        int[] profits = new int[]{0, 1, 2, 5, 6};
        int[] weights = new int[]{0, 2, 3, 4, 5};
        int bagCapacity = 8;
        int objCount = 4;
        int maximumWeight = 0;

        // The row should be 1 more than the objCount and the column should be 1 more than the bagCapacity
        int[][] computeMatrix = new int[5][9];

        for (int i = 0; i <= objCount; i++) {
            for (int w = 0; w <= bagCapacity; w++) {
                // For first row and first column
                if (i == 0 || w == 0) {
                    computeMatrix[i][w] = 0;
                } else if (weights[i] <= w) {
                    computeMatrix[i][w] = Math.max(profits[i] + computeMatrix[i - 1][w - weights[i]], computeMatrix[i - 1][w]);
                } else {
                    // Copy the previous column value when the column weight is not greater than the actual weight
                    computeMatrix[i][w] = computeMatrix[i - 1][w];
                }
            }
        }

        // Maximum weight to be included
        maximumWeight = computeMatrix[objCount][bagCapacity];
        System.out.println("Maximum weight to be included in the bag : " + maximumWeight);

        // Find the objects to be included
        int i = objCount;
        int j = bagCapacity;

        while (i > 0 && j > 0) {
            if (computeMatrix[i][j] == computeMatrix[i - 1][j]) {
                System.out.println(i + " 0");
                i--;
            } else {
                System.out.println(i + " 1");
                j = j - weights[i];
                i--;
            }
        }
    }
}
