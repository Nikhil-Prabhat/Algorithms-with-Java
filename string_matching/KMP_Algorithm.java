package string_matching;

import java.util.Arrays;

/*
* Time Complexity : O(m+n)
* References :
* https://www.youtube.com/watch?v=lhhqbGH7Pao
* https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
* Abdul Bari KMP Algo explanation
* */

public class KMP_Algorithm {

    public static void main(String[] args) {

        String pattern = "abcabcd";
        String text = "abcabcabcabcd";
        int[] piOfPattern = KMP_Algorithm.calculatePi(pattern);
        System.out.println("LPS of the pattern : "+ Arrays.toString(piOfPattern));

        int i = 0, j = 0, pos = -1;

        while (i < text.length()) {
            if (Character.compare(text.charAt(i), pattern.charAt(j)) == 0) {
                i++;
                j++;
            }
            else {
                // Iterate the lps array and compare if the index is not the starting index
                // If it is not matching, then, increase the index to look for the expected string
                if(j != 0)
                    j = piOfPattern[j-1];
                else
                    i++;
            }

            if(j == pattern.length()) {
                pos = i - pattern.length();
                break;
            }
        }

        System.out.println("The string is found at the index : "+ pos);


    }

    /*
     * Calculate pi of the pattern
     * abcabcd = [0,0,0,1,2,3,0]
     */
    private static int[] calculatePi(String pattern) {
        int lengthOfPattern = pattern.length();

        // By default, fill the pi array with 0
        int[] piOfPattern = new int[lengthOfPattern];
        Arrays.fill(piOfPattern, 0);

        for (int i = 1; i < lengthOfPattern; i++) {
            int j = piOfPattern[i - 1];

            while (j > 0 && Character.compare(pattern.charAt(i), pattern.charAt(j)) != 0)
                j = piOfPattern[j - 1];

            if (Character.compare(pattern.charAt(i), pattern.charAt(j)) == 0)
                j++;

            piOfPattern[i] = j;
        }

        return piOfPattern;
    }

}
