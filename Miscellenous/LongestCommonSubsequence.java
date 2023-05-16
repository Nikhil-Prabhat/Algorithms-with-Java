package Miscellenous;

import java.util.Arrays;

/*
 *  String s1 : abdace
 *  String s2 : babce
 *  Result    : bace / abce
 *  Recusion without memoization : O(2^n)
 *  Recursion with memoization   : O(m*n) where m and n are the lengths of the strings respectively
 *  Dynamic Programming          : O(m*n) where m and n are the lengths of the strings respectively
 * */
public class LongestCommonSubsequence {

    public static int[][] memoizationArray = new int[6][5];

    public static void main(String[] args) {
        String s1 = "abdace";
        String s2 = "babce";
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();

        for (int[] row : memoizationArray) {
            Arrays.fill(row, -1);
        }

        // LCS Without Memoization
        int lcsWithRecursion = LongestCommonSubsequence.findLCSWithRecursion(s1Array, s2Array, 0, 0);
        System.out.println(lcsWithRecursion);

        // LCS With Memoization
        int lcsWithRecursionWithMemoization = LongestCommonSubsequence.findLCSWithRecursionWithMemoization(s1Array, s2Array, 0, 0, memoizationArray);
        System.out.println(lcsWithRecursionWithMemoization);

    }

    /* Find LCS With Recursion Without Memoization */
    public static int findLCSWithRecursion(char[] X, char[] Y, int i, int j) {
        if (i == X.length || j == Y.length)
            return 0;
        else if (X[i] == Y[j])
            return 1 + findLCSWithRecursion(X, Y, i + 1, j + 1);
        else
            return Math.max(findLCSWithRecursion(X, Y, i + 1, j), findLCSWithRecursion(X, Y, i, j + 1));
    }

    /* Find LCS With Recursion With Memoization */
    public static int findLCSWithRecursionWithMemoization(char[] X, char[] Y, int i, int j, int[][] memoizationArray) {
        if (i == X.length || j == Y.length)
            return 0;

        // If the state is already computed
        if (memoizationArray[i][j] != -1)
            return memoizationArray[i][j];

        if (X[i] == Y[j]) {
            // Store it in the array to avoid furthur repetitive work

            memoizationArray[i][j] = 1 + findLCSWithRecursionWithMemoization(X, Y, i + 1, j + 1, memoizationArray);
            return memoizationArray[i][j];
        } else {
            memoizationArray[i][j] = Math.max(findLCSWithRecursionWithMemoization(X, Y, i + 1, j, memoizationArray), findLCSWithRecursionWithMemoization(X, Y, i, j + 1, memoizationArray));
            return memoizationArray[i][j];
        }
    }

    /* Find LCS With DP */
    int lcs(char[] X, char[] Y, int m, int n) {
        int L[][] = new int[m + 1][n + 1];

        /* Following steps build L[m+1][n+1] in bottom up fashion. Note
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X[i - 1] == Y[j - 1])
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }
        return L[m][n];
    }
}
