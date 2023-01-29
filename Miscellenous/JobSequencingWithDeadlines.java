package Miscellenous;

import java.util.Arrays;
/*
* This is a greedy approach
* */

public class JobSequencingWithDeadlines {

    public static void main(String[] args) {
        // Jobs are already sorted in decreasing order as per profits
        int[] profits = new int[]{35, 30, 25, 20, 15, 12, 5};
        int[] deadlines = new int[]{3, 4, 4, 2, 3, 1, 2};

        // Maximum deadlines to perform tasks
        int maxDeadline = Arrays.stream(deadlines).max().getAsInt();
        int[] jobExecution = new int[maxDeadline];

        // Initialise jobExecution with -1
        Arrays.fill(jobExecution, -1);


        // Performing operations
        for (int i = 0; i < profits.length; i++) {
            int jobDeadline = deadlines[i] - 1;
            if (jobExecution[jobDeadline] == -1)
                jobExecution[jobDeadline] = profits[i];
            else {
                int k = jobDeadline;
                while ( k >= 0 && jobExecution[k] != -1)
                    k--;

                if (k != -1)
                    jobExecution[k] = profits[i];
            }
        }

        // Maximum profit to be made
        int totalProfit = Arrays.stream(jobExecution).sum();
        System.out.println("Total Profit : "+totalProfit);


    }
}
