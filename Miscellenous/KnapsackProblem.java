package Miscellenous;

/*

Knapsack Problem is of two types :
1. 0/1 Knapsack Problem         : Splitting of items is not allowed
2. Fractional Knapsack Problem  : Splitting of items is allowed

 */


public class KnapsackProblem {

    public static void main(String[] args) {

        int[] profits = new int[]{10, 5, 15, 7, 6, 18, 3};
        int[] weights = new int[]{2, 3, 5, 7, 1, 4, 1};
        KnapsackProblem.findKnapsack(profits,weights,15);

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
}
