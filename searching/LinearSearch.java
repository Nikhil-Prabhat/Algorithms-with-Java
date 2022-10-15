package searching;

import java.util.stream.IntStream;

/*
* Time Complexity - O(n)
* */

public class LinearSearch {

    public static void main(String[] args) {

        int[] array = new int[]{3,6,4,1,2,8};
        int indexByLinearSearch = LinearSearch.linearSearch(array, 1);
        System.out.println(indexByLinearSearch);
    }

    // Linear Search
    private static int linearSearch(int[] array, int toSearch) {

        // Using For Loop
        /*
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == toSearch) {
                index = i;
                return index;
            }
        }
        return index;
        */

        // Using Stream API
        int indexByLinearSearch = IntStream.range(0, array.length)
                .filter(index -> array[index] == toSearch)
                .findFirst()
                .orElse(-1);

        return indexByLinearSearch;
    }

}
